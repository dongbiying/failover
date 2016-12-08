package com.dn.mq.queue.producter;

public class Test {
	public static void main(String[] args) {
			DNProducter product = new DNProducterImpl();
			product.init();
			product.sendMessage("DN-kl-queue");
	}
}
