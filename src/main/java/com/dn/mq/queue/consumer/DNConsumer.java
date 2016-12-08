package com.dn.mq.queue.consumer;

public interface DNConsumer {
	public void init();
	public void getMessage(String queueName);
}
