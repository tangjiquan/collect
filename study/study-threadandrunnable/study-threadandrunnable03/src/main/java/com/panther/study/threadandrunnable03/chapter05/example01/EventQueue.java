package com.panther.study.threadandrunnable03.chapter05.example01;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @author: Kevin
 * @date: created in 下午10:43 2018-10-12
 * @version: V1.0
 */
public class EventQueue {

	private final int max;
	static class Event{}

	private final LinkedList<Event> eventQueue = new LinkedList<>();
	private final static int DEFAULT_MAX_EVENT = 10;
	public EventQueue(){
		this(DEFAULT_MAX_EVENT);
	}

	public EventQueue(int max){
		this.max = max;
	}

	public void offer(Event event){
		synchronized (eventQueue){
			if(eventQueue.size()>=max){
				try {
					System.out.println(" the queue is full");
					eventQueue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("the new event is submitted");
			eventQueue.addLast(event);
			eventQueue.notify();
		}
	}

	public Event take(){
		synchronized (eventQueue){
			if(eventQueue.isEmpty()){
				try {
					eventQueue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Event event = eventQueue.removeFirst();
			this.eventQueue.notify();
			System.out.println("the event" + event + "is handled");
			return event;
		}
	}


	public static void main(String[] args){
		final EventQueue eventQueue = new EventQueue();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					eventQueue.offer(new EventQueue.Event());
				}
			}
		}, "producter").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					eventQueue.take();
					try {
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "consumer").start();
	}
}
