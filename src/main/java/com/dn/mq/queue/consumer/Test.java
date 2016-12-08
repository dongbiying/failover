package com.dn.mq.queue.consumer;

public class Test {
	public static void main(String[] args) {
		DNConsumer consumer = new DNConsumerImpl();
		consumer.init();
		consumer.getMessage("DN-kl-queue");
		//master
	}
}
