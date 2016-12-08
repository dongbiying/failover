package com.dn.mq.queue.producter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class DNProducterImpl implements DNProducter{
	
 
	public static String USENAME = 	ActiveMQConnection.DEFAULT_USER;
	public static String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	public static String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	ConnectionFactory factory;
	
	Connection connection ;
	
	Session session;
	
	public void init() {
		try {
			
			factory = new ActiveMQConnectionFactory(USENAME,PASSWORD,URL);
			
			connection = factory.createConnection();
			
			connection.start();
			
			session = connection.createSession(true, Session.SESSION_TRANSACTED);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String queueName) {
		try {
			Queue queue = session.createQueue(queueName);
			
			MessageProducer producer =  session.createProducer(queue);
			
			for (int i = 0; i < 100; i++) {
				TextMessage tm = session.createTextMessage("product : content "+i);
				producer.send(tm);
			}
			session.commit();
			
		} catch (JMSException e) {
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
