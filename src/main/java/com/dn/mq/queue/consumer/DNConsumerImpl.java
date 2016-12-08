package com.dn.mq.queue.consumer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class DNConsumerImpl implements DNConsumer{
	
	String USERNAME = ActiveMQConnection.DEFAULT_USER;
	String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	ConnectionFactory  factory;

	Connection connection;

	Session session;
	
	public void init() {
		factory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,URL);
		
		try {
			connection = factory.createConnection();
			
			connection.start();
			
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

	public void getMessage(String queueName) {
			try {
				Queue queue = session.createQueue(queueName);
				
				MessageConsumer consumer =  session.createConsumer(queue);
				
				while(true){
					TextMessage tmsg =  (TextMessage) consumer.receive();
					if(tmsg!=null){
						System.out.println("consumer :"+tmsg.getText());
					}else{
						System.out.println("break end");
						break;
					}
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(connection!=null){
					try {
						connection.close();
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			}
	}

}
