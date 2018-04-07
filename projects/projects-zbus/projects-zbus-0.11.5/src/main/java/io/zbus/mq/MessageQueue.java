
package io.zbus.mq;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import io.zbus.kit.logging.Logger;
import io.zbus.kit.logging.LoggerFactory;
import io.zbus.mq.Protocol.ConsumeGroupInfo;
import io.zbus.mq.Protocol.TopicInfo;
import io.zbus.mq.server.MessageLogger;
import io.zbus.mq.server.ReplyKit;
import io.zbus.transport.Session;

public interface MessageQueue { 

	void produce(Message message) throws IOException;  
	Message consume(String consumeGroup) throws IOException;  
	void ack(Message message, Session session) throws IOException;
	
	void consume(Message message, Session session) throws IOException;   
	void unconsume(Message message, Session session) throws IOException;   
	void cleanSession(Session sess);  
	int sessionCount(String consumeGroup);  
	
	ConsumeGroupInfo declareGroup(ConsumeGroup consumeGroup) throws Exception;  
	void removeGroup(String groupName) throws IOException;   
	void destroy() throws IOException;  
	
	String topic(); 
	TopicInfo topicInfo(); 
	ConsumeGroupInfo groupInfo(String groupName);  
	
	long createdTime();
	long updatedTime();
	long messageDepth();
	
	String getCreator(); 
	void setCreator(String value); 
	
	int getMask(); 
	void setMask(int value);  
	
	void setMessageLogger(MessageLogger messageLogger);
}


class PullSession { 
	Session session;
    Message pullMessage;  
   
    final ReentrantLock lock = new ReentrantLock(); 
	final BlockingQueue<Message> msgQ = new LinkedBlockingQueue<Message>(); 
	
	public PullSession(Session sess, Message pullMessage) { 
		this.session = sess;
		this.setPullMessage(pullMessage);
	}  
	public Session getSession() {
		return session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	public Message getPullMessage() {
		return this.pullMessage;
	}
	
	public void setPullMessage(Message msg) { 
		this.lock.lock();
		this.pullMessage = msg;
		if(msg == null){
			this.lock.unlock();
			return; 
		} 
		this.lock.unlock();
	}  

	public BlockingQueue<Message> getMsgQ() {
		return msgQ;
	}
	
	public String getConsumerAddress(){
		return session.remoteAddress();   
	}
}



abstract class AbstractQueue implements MessageQueue{
	private static final Logger log = LoggerFactory.getLogger(AbstractQueue.class);   
	protected Map<String, AbstractConsumeGroup> consumeGroups = new ConcurrentSkipListMap<String, AbstractConsumeGroup>(String.CASE_INSENSITIVE_ORDER); 
	protected long lastUpdatedTime = System.currentTimeMillis();  
	protected String topic;   
	protected MessageLogger messageLogger;
	protected long groupNumber = consumeGroups.size();
	  
	public AbstractQueue(){
		
	}
	public AbstractQueue(String topic){
		this.topic = topic;
	}
	
	protected void loadConsumeGroups() throws IOException{ }
	
	protected String nextGroupName(){
		if(!consumeGroups.containsKey(topic)) return topic;
		
		while(true){
			String name = topic + groupNumber;
			if(consumeGroups.containsKey(name)){
				groupNumber++;
				continue;
			}
			return name;
		}
	}
	
	@Override
	public void setMessageLogger(MessageLogger messageLogger) {
		this.messageLogger = messageLogger;
	}
	
	@Override
	public void destroy() throws IOException { 
		
	}
	
	@Override
	public String topic() { 
		return this.topic;
	} 
	
	@Override
	public long updatedTime() {
		return lastUpdatedTime;
	}
	
	@Override
	public ConsumeGroupInfo groupInfo(String groupName) {
		AbstractConsumeGroup group = consumeGroups.get(groupName); 
		if(group == null){
			return null;
		}
		return group.getConsumeGroupInfo();
	}
	
	@Override
	public void removeGroup(String groupName) throws IOException {
		AbstractConsumeGroup group = consumeGroups.remove(groupName); 
		if(group == null){
			throw new MqException("ConsumeGroup("+groupName+") Not Found"); 
		}
		group.delete();
	}
	  
	 
	@Override
	public void unconsume(Message message, Session session) throws IOException {
		String consumeGroup = message.getConsumeGroup();
		if(consumeGroup == null){
			consumeGroup = this.topic;
		}  
		
		AbstractConsumeGroup group = consumeGroups.get(consumeGroup);
		if(group == null){
			message.setBody(consumeGroup + " not found");
			ReplyKit.reply404(message, session, "ConsumeGroup(" + consumeGroup + ") Not Found");
			return;
		}   
		group.removeSession(session); 
	}
	
	public void ack(Message message, Session session) throws IOException {
		String consumeGroup = message.getConsumeGroup();
		if(consumeGroup == null){
			consumeGroup = this.topic;
		}  
		AbstractConsumeGroup group = consumeGroups.get(consumeGroup);
		if(group == null){ 
			return;
		}     
		Long offset = message.getOffset();
		group.ack(offset);
		
	}

	@Override
	public void consume(Message message, Session session) throws IOException {
		String consumeGroup = message.getConsumeGroup();
		if(consumeGroup == null){
			consumeGroup = this.topic;
		}  
		
		AbstractConsumeGroup group = consumeGroups.get(consumeGroup);
		if(group == null){
			message.setBody(consumeGroup + " not found");
			ReplyKit.reply404(message, session, "ConsumeGroup(" + consumeGroup + ") Not Found");
			return;
		}   
		 
		if(!group.pullSessions.containsKey(session.id())){ 
			if (group.pullSessions.size() > 0) { //thread safty TODO
				if((group.getMask() & Protocol.MASK_EXCLUSIVE) != 0){ 
					ReplyKit.reply401(message, session, String.format("ConsumeGroup(%s) exclusive, forbbiden", 
							consumeGroup));
					return;
				}
			}
			group.pullSessions.put(session.id(), session);
		}   
		 
		
		Long offset = message.getOffset(); 
		if(offset != null) { //handle case of pulling message with offset
			Message res = null;
			try{
				res = group.read(offset);
			} catch (IllegalStateException e) {
				log.warn(e.getMessage());;
			}
			if(res == null) {
				ReplyKit.reply404(message, session, "Message Not Found: offset=" + offset);
				return;
			}
			String pullMsgId = message.getId();
			sendOutMessage(group.groupName, session, res, pullMsgId);
			return;
		}
		
		for(PullSession pull : group.pullQ){
			if(pull.getSession() == session){
				pull.setPullMessage(message);  
				dispatch(group);
				return; 
			}
		}  
		PullSession pull = new PullSession(session, message);
		group.pullQ.offer(pull);  
		dispatch(group);
	}    
	 
	
	protected void dispatch() throws IOException{  
		Iterator<Entry<String, AbstractConsumeGroup>> iter = consumeGroups.entrySet().iterator();
		while(iter.hasNext()){
			AbstractConsumeGroup group = iter.next().getValue();
			dispatch(group);
		} 
	}
	
	protected void dispatch(AbstractConsumeGroup group) throws IOException {   
		while(group.pullQ.peek() != null){
			Message msg = group.readTimeoutMessage();
			if(msg == null && group.isEnd()) break;
			
			if(group.isNakFull()) break; //NAk message full
			
			PullSession pull = group.pullQ.poll(); 
			if(pull == null) break; 
			if( !pull.getSession().active() ){  
				continue;
			}  
			if(msg == null) {
				msg = group.read();
			}
			
			if(msg == null){
				group.pullQ.offer(pull);
				break; 
			} 
			
			this.lastUpdatedTime = System.currentTimeMillis(); 
			try {  
				
				if(group.isAckEnabled()) {
					try {
						group.recordNak(msg.getOffset(),msg.getRetry());
					} catch (Exception e) {
						log.error(e.getMessage(), e);  
					}
				} 
				
				Message pullMsg = pull.getPullMessage(); 
				sendOutMessage(group.groupName, pull.getSession(), msg, pullMsg.getId()); 
			} catch (Exception ex) {   
				log.error(ex.getMessage(), ex);  
			} 
		} 
		
	} 
	
	private void sendOutMessage(String groupName, Session session, Message msg, String pullMsgId) {
		Message writeMsg = Message.copyWithoutBody(msg); 
		
		writeMsg.removeHeader(Protocol.TOKEN); //Remove sensitive Token info
		writeMsg.setOriginId(msg.getId());  
		writeMsg.setId(pullMsgId);
		writeMsg.setConsumeGroup(groupName);
		
		Integer status = writeMsg.getStatus();
		if(status == null){
			writeMsg.setOriginMethod(writeMsg.getMethod());
			if(!"/".equals(writeMsg.getUrl())){
				writeMsg.setOriginUrl(writeMsg.getUrl()); 
			}  
		} else {
			writeMsg.setOriginStatus(status);
		} 
		writeMsg.setStatus(200); //status meaning changed to 'consume-status'
		if(messageLogger != null) {
			messageLogger.log(writeMsg, session);
		}
		session.write(writeMsg);  
	}
	
	@Override
	public int sessionCount(String consumeGroup) {
		if(consumeGroup == null){
			consumeGroup = this.topic;
		}
		
		AbstractConsumeGroup group = consumeGroups.get(consumeGroup);
		if(group == null){
			return 0;
		}   
		return group.pullQ.size();
	}
	
	public void cleanSession(Session sess) {
		if(sess == null){
			cleanInactiveSessions();
			return;
		}
		
		Iterator<Entry<String, AbstractConsumeGroup>> iter = consumeGroups.entrySet().iterator();
		while(iter.hasNext()){
			AbstractConsumeGroup group = iter.next().getValue();
			cleanSession(group, sess);
		} 
	}
	
	private void cleanSession(AbstractConsumeGroup group, Session sess){
		group.pullSessions.remove(sess.id());
		
		Iterator<PullSession> iter = group.pullQ.iterator();
		while(iter.hasNext()){
			PullSession pull = iter.next();
			if(sess == pull.session){
				iter.remove();
				break;
			}
		}
		//remove group if masked as delete_on_exit
		if((group.getMask() & Protocol.MASK_DELETE_ON_EXIT) != 0){
			if(group.pullSessions.size() == 0){ 
				try {
					group.delete();
				} catch (IOException e) {
					log.warn(e.getMessage());
				}
			}
		}
	} 
	 
	private void cleanInactiveSessions() { 
		Iterator<Entry<String, AbstractConsumeGroup>> iter = consumeGroups.entrySet().iterator();
		while(iter.hasNext()){
			AbstractConsumeGroup group = iter.next().getValue(); 
			Iterator<PullSession> iterSess = group.pullQ.iterator();
			while(iterSess.hasNext()){
				PullSession pull = iterSess.next();
				if(!pull.session.active()){
					group.pullSessions.remove(pull.session.id());
					iterSess.remove(); 
				}
			}
			
			//remove group if masked as delete_on_exit
			if((group.getMask() & Protocol.MASK_DELETE_ON_EXIT) != 0){
				if(group.pullSessions.size() == 0){
					iter.remove();
					try {
						group.delete();
					} catch (IOException e) {
						log.warn(e.getMessage());
					}
				}
			}
		}  
	} 

	@Override
	public TopicInfo topicInfo() {
		TopicInfo info = new TopicInfo(); 
		info.topicName = topic;
		info.createdTime = createdTime();
		info.lastUpdatedTime = updatedTime(); 
		info.mask = getMask();
		info.messageDepth = messageDepth();
		info.consumerCount = 0; //TODO
		info.consumeGroupList = new ArrayList<ConsumeGroupInfo>();
		for(AbstractConsumeGroup group : consumeGroups.values()){
			ConsumeGroupInfo groupInfo = group.getConsumeGroupInfo();
			info.consumerCount += groupInfo.consumerCount;
			info.consumeGroupList.add(groupInfo);
		}  
		return info;
	} 
	
	@Override
	public String toString() { 
		return this.getClass().getSimpleName() + "[" + topic + "]";
	}
	
	static abstract class AbstractConsumeGroup implements Closeable{  
		public final String groupName;
		public final BlockingQueue<PullSession> pullQ = new LinkedBlockingQueue<PullSession>();  
		public final Map<String, Session> pullSessions = new ConcurrentHashMap<String, Session>(); 
		
		public AbstractConsumeGroup(String groupName) throws IOException { 
			this.groupName = groupName; 
		} 
		
		public void removeSession(Session session){
			pullSessions.remove(session.id());
			Iterator<PullSession> iter = pullQ.iterator();
			while(iter.hasNext()){
				if(iter.next().session == session){
					iter.remove();
					break;
				}
			}
		}
		
		public Message readTimeoutMessage() throws IOException{
			return null;
		}
		
		public void ack(long offset) throws IOException{
			
		}
		
		public abstract Message read() throws IOException;
		
		public abstract Message read(long offset) throws IOException;

		public abstract boolean isEnd();
		
		public Integer getMask(){
			return 0;
		}
		
		public boolean isAckEnabled() {
			Integer mask = getMask();
			if(mask == null) return false;
			return (mask & Protocol.MASK_ACK_REQUIRED) != 0;
		}
		
		public boolean isNakFull(){
			return false;
		}
		
		public void recordNak(Long offset, Integer retryCount) {
			
		}
		
		@Override
		public void close() throws IOException { } 
		
		public void delete() throws IOException{  }
		
		public abstract ConsumeGroupInfo getConsumeGroupInfo();
		
		@Override
		public String toString() { 
			return this.getClass().getSimpleName() + "[" + groupName + "]";
		}
	} 
}