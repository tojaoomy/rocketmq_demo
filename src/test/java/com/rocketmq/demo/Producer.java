/*
 * Copyright (C), 2013-2014, 上海汽车集团股份有限公司
 */
package com.rocketmq.demo;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

/**
 * @author hejian
 *
 */
public class Producer {
	
	private static DefaultMQProducer producer = new DefaultMQProducer("Producer");
	private static transient boolean isStartUp = false;
	
	public static void init(){
		producer.setNamesrvAddr("localhost:9876");
			try {
				if(!isStartUp){
					producer.start();
					isStartUp = true;
				}
				Message msg = new Message("PushTopic", "push","1","Just for test".getBytes());
				long start = System.currentTimeMillis();
				SendResult result = producer.send(msg);
				System.out.println("id : " + result.getMsgId() + " result : " + result.getSendStatus() + " cost time : " + (System.currentTimeMillis() - start) + " ms");
				
				msg = new Message("PushTopic", "push","2","Just for test".getBytes());
				start = System.currentTimeMillis();
				result = producer.send(msg);
				System.out.println("id : " + result.getMsgId() + " result : " + result.getSendStatus() + " cost time : " + (System.currentTimeMillis() - start) + " ms");
				
				msg = new Message("PushTopic", "pull","1","Just for test".getBytes());
				start = System.currentTimeMillis();
				result = producer.send(msg);
				System.out.println("id : " + result.getMsgId() + " result : " + result.getSendStatus() + " cost time : " + (System.currentTimeMillis() - start) + " ms");
			} catch (MQClientException e) {
				e.printStackTrace();
			} catch (RemotingException e) {
				e.printStackTrace();
			} catch (MQBrokerException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		
	}
	
	public static void destory(){
		if(producer != null){
			producer.shutdown();
		}
	}

	public static void main(String[] args) {
		init();
		destory();
	}
	
}
