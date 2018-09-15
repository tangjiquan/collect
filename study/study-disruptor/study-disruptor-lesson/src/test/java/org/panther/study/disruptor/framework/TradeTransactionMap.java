package com.sinosoft.common.utils.makePoster;

import com.lmax.disruptor.EventHandler;
import com.sinosoft.common.utils.ImageUtil5;
import com.sinosoft.common.utils.ImageUtil6;
import com.sinosoft.common.utils.mytest.ApplicationContextHelper;
import com.sinosoft.modules.weixin.entity.*;
import com.sinosoft.modules.weixin.service.*;
import com.sinosoft.modules.weixin.utils.HttpClientUtil;
import com.sinosoft.modules.weixin.utils.PropertiesLoader;
import com.sinosoft.modules.weixin.utils.WeixinUtil;
import com.sinosoft.modules.weixin.utils.WxSysConst;
import net.sf.json.JSONObject;
import sun.java2d.pipe.AAShapePipe;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Kevin
 * @date: created in 下午1:30 2018-07-27
 * @version: V1.0
 */
public class TradeTransactionMap implements EventHandler<TradeTransaction> {
	private Integer postion;
	static PropertiesLoader weixinLoader = new PropertiesLoader("weixin.properties");


	public void setPostion(Integer postion){
		this.postion = postion;
	}
	@Override
	public void onEvent(TradeTransaction event, long sequence,
                        boolean endOfBatch) throws Exception {

		//获取对应postion的代理人信息，然后合成海报
		ArrayList list  = event.getInputValues();
		ConcurrentHashMap map= (ConcurrentHashMap) list.get(0);
		String uploadPath = "/app/wx/upload";
		WxRegisterInfo register= (WxRegisterInfo) map.get("wxRegisterInfo");
		String activityName=(String) map.get("activityName");
		ArrayList postList= (ArrayList) map.get("postList");
		String imgurl="";
		ApplicationContextHelper context=new ApplicationContextHelper();
		WxAccesstokenJsTokenService wxAccesstokenJsTokenService=context.getBean(WxAccesstokenJsTokenService.class);
		WeixinAccount account = new WeixinAccount();
		account.setAccountappid(weixinLoader.getProperty("appId"));
		WeixinAccountService weixinAccountService=context.getBean(WeixinAccountService.class);
		WeixinAccount accountResult = weixinAccountService.getAccountByAppid(account);
		String accessToken = "";
		accessToken = wxAccesstokenJsTokenService.getToken(accountResult.getAccountappid(), accountResult.getAccountappsecret(), WxSysConst.ACCESS_TOKEN).getTokenValue();
		//合成当前代理人所有海报，拼接url
		for(int i=0;i<postList.size();i++){
			Map postMap= (Map) postList.get(i);
			ConcurrentHashMap imgParam = new ConcurrentHashMap();
			imgParam= (ConcurrentHashMap) postMap.get("imgParam");
			JSONObject post= (JSONObject) postMap.get("post");
			ConcurrentHashMap<String, String> qr_param= (ConcurrentHashMap<String, String>) imgParam.get("qr_param");
			ConcurrentHashMap<String, String> name_param= (ConcurrentHashMap<String, String>) imgParam.get("name_param");
			WxRegisterInfo wxRegisterInfo = register;
			//sceneid放置的是代理人编码
			WxQrCode wxQrCode = new WxQrCode();
			//二维码有效期不填、默认七天
			wxQrCode.setExpireseconds(2592000L);
			wxQrCode.setCodeflag(1);
			wxQrCode.setSceneid(wxRegisterInfo.getOpenid());
			wxQrCode.setSceneDescrible(activityName);
			wxQrCode.setAccountId(account.getAccountnumber());
			//获取二维码Ticket
			WeixinQrCodeService weixinQrCodeService=context.getBean(WeixinQrCodeService.class);
			JSONObject jsonResult = weixinQrCodeService.createQrCodeTicket(accessToken, wxQrCode.getCodeflag(), wxQrCode.getSceneid(), wxQrCode.getExpireseconds(), uploadPath);
			String ticket = (String) jsonResult.get("ticket");
			//通过Ticket获取二维码并写入服务器
			String localpath = weixinQrCodeService.getQrCodePhotoWithOpenId(ticket, post.getJSONObject("qr").get("path").toString(), accessToken,wxRegisterInfo.getOpenid());
			//二维码相关信息存入数据库
			wxQrCode.setTicket(ticket);
			wxQrCode.setWxpath((String) jsonResult.get("url"));
			wxQrCode.setTotal((long) 0);
			wxQrCode.setRemarks("2");
			if (wxQrCode.getCodeflag() == 0) {
				wxQrCode.setExpireseconds(Long.parseLong("0"));
			} else {
				wxQrCode.setExpireseconds(Long.valueOf((Integer) jsonResult.get("expire_seconds")));
			}
			//生成完整的分享信息（包含二维码）
           /* String picsPaths = PropertiesLoader.getPropertiesWxByKey("imagePath");
            String topPicFile = "/app/wx/upload/20180101/20180101.jpg";//从两个图片中随机获取一个图片作为顶部图片
            String srcQrFile = uploadPath + File.separator + "20180101"+ File.separator + "20180101qr" + File.separator + localpath;
            String posterFile = uploadPath + File.separator + "20180101"+ File.separator + "20180101poster" + File.separator + "newyearday"+localpath;*/
			//获取用户的头像, 获取微信用户的头像
			String infoUrl = WeixinUtil.WX_INFO_URL + "&access_token=" + accessToken + "&openid=" + wxRegisterInfo.getOpenid();
			String result = HttpClientUtil.postRequest(infoUrl, "");
			JSONObject jsons = JSONObject.fromObject(result);
			// String headimgurl = (String) jsons.get("headimgurl");
			qr_param.put("qr_img", post.getJSONObject("qr").get("path").toString() + File.separator + localpath);
			imgParam.put("poster", post.get("poster") + localpath);
			name_param.put("content", wxRegisterInfo.getCoreAgentName());
			if (activityName.equals("07")) {
				String agentName = name_param.get("content");
				int num = agentName.length();
				int n_x = 210 - 50 * num;
				name_param.put("name_x", String.valueOf(n_x));
			} else if (activityName.equals("call")) {
				String agentName = name_param.get("content");
				int num = agentName.length();
				int n_x = (750 - 70 * num) / 2;
				name_param.put("name_x", String.valueOf(n_x));
			}
			imgParam.put("name_param", name_param);
			try {
				//	System.out.println("before ： "+imgParam);
					//System.identityHashCode(imgParam);
					ImageUtil6 util6=new ImageUtil6();
					util6.exec(imgParam);
				//People p=new People();
				//p.get(imgParam);
				//	System.out.println("end:"+imgParam);


			} catch (Exception e) {
				e.printStackTrace();
			}
			wxQrCode.setLocalpath(post.get("poster") + localpath);//包含/app/wx/upload的路径
			WxQrCodeService wxQrCodeService=context.getBean(WxQrCodeService.class);
			wxQrCodeService.save(wxQrCode);
			imgurl+=  post.get("poster") + localpath;
		}
		// 获取模板消息的话术
		JSONObject msg = (JSONObject) map.get("msg");
		ArrayList<String> paramsList = new ArrayList<String>();
		HashMap<String, String> msg_param = new HashMap<String, String>();
		int keyNum=0;
		Iterator<String> sIterator = msg.keys();
		while(sIterator.hasNext()) {
			// 获得key数量+1
			String key= sIterator.next();
			keyNum++;
		}
		for(int i=1;i<=keyNum;i++){
			msg_param.put("key"+i,(String) msg.get("key"+i));
		}
		//获取消息模板
		WxSendMessageTemple wxSendMessageTemple = new WxSendMessageTemple();
		wxSendMessageTemple.setMessageCode("Template_MSG07");//微信预约承保模板消息
		WxSendMessageTempleService wxSendMessageTempleService=context.getBean(WxSendMessageTempleService.class);
		wxSendMessageTemple = wxSendMessageTempleService.getByMessageCode(wxSendMessageTemple);
		String tUrl = wxSendMessageTemple.getMessageUrl()+imgurl;
		tUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ accountResult.getAccountappid() +"&redirect_uri=" + tUrl + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		WeixinUtil wxu = new WeixinUtil();
		List<String> params = new ArrayList<String>();
		String openId=register.getOpenid();
		/****************** 从对象中获取模板消息发送数据 ***************/
		//params.add(wxSendMessageTemple.getMessageFirst() + "\\n");//param1
		for(int i=1;i<=keyNum;i++){
			if(i==1){
				//第一行
				params.add(msg_param.get("key"+i) +  "\\n");
			}else if(i==keyNum){
				//最后一行
				params.add("\\n" + msg_param.get("key"+i)+"\\n"+"↓↓↓");
			}else{
				//中间部分
				params.add(msg_param.get("key"+i));
			}
		}
		String messageResult = wxu.sendTemplateMsg(accessToken, openId, tUrl, wxSendMessageTemple, params, openId);//发送模板消息
		JSONObject jsonObject=JSONObject.fromObject(messageResult);
		String errcode=jsonObject.getString("errcode");
		if("0".equals(errcode)){
			ArrayList list2=null;
			Field f=event.getClass().getDeclaredField("outputValues");
			f.setAccessible(true);
			if(f.get(event) == null || f.get(event).equals("")){
				list2=new ArrayList();
				int num=1;
				list2.add(num);
			}else{
				list2=event.getOutputValues();
				int num=1;
				list2.add(num);
			}
			event.setOutputValues(list2);
		}

	}

}
