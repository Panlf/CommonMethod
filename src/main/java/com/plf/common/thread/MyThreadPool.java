package com.plf.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPool {

	public static void main(String[] args) {
		//固定的线程池  --- 3个线程的线程池
		//ExecutorService threadPool = Executors.newFixedThreadPool(3);
		
		//有多少任务创建多少线程
		//ExecutorService threadPool = Executors.newCachedThreadPool();
		
		//单个线程  (一个线程死掉之后会有备用线程)
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		for(int i=1;i<=10;i++){
			final int task = i;
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					for(int j=0;j<10;j++){
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()+" is looping of "+j+" for task of "+task);
					}
				}
			});
		}
		
		System.out.println("all of 10 tasks has committed");
		//threadPool.shutdown();
	}

}
