/*
 * Copyright (C), 2013-2014, 上海汽车集团股份有限公司
 */
package com.rocketmq.demo;

import java.util.Date;
import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * @author hejian
 *
 */
public class Consumer {
	
	private static DefaultMQPushConsumer consumer = null;
	
	public static void consumer(String topic, String tags){
		consumer = new DefaultMQPushConsumer(topic + "_" + tags);
		consumer.setNamesrvAddr("localhost:9876");
		try {
			//订阅PushTopic下Tag为push的消息
			consumer.subscribe(topic, tags);
			//从队头到队尾读取数据
			consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
			consumer.registerMessageListener(new MessageListenerConcurrently() {
				
				public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
						ConsumeConcurrentlyContext context) {
					System.out.println("消息队列大小 : " + msgs.size());
					Message msg = msgs.get(0);
					System.out.println(new Date() + " Topic : " + msg.getTopic() + "Tags : " + msg.getTags() );
					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
				}
			});
			consumer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void consumer2(String topic, String tags){
		consumer = new DefaultMQPushConsumer("PushConsumer pull");
		consumer.setNamesrvAddr("localhost:9876");
		try {
			//订阅PushTopic下Tag为push的消息
			consumer.subscribe(topic, tags);
			//从队头到队尾读取数据
			consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
			consumer.registerMessageListener(new MessageListenerConcurrently() {
				
				public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
						ConsumeConcurrentlyContext context) {
					System.out.println("消息队列大小 : " + msgs.size());
					Message msg = msgs.get(0);
					System.out.println(new Date() + " Topic : " + msg.getTopic() + "Tags : " + msg.getTags() );
					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
				}
			});
			consumer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
		}
	}
	
	public static void shutdown(){
		consumer.shutdown();
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		consumer("PushTopic", "push");
		consumer("PushTopic", "pull");
	}

}
