package com.dn.mq.queue.producter;

public interface DNProducter 	{
	
	 public void init();
	 public void sendMessage(String queueName);
}
