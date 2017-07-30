package com.plf.scheduling.timer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

/**
 * Timer ��ʱ�������
 * @author plf 2017��7��30������2:59:40
 *
 */
public class MyTimerTask extends TimerTask{

	private String inputname;
	private Integer count=0;
	public MyTimerTask(String inputname) {
		super();
		this.inputname = inputname;
	}
	
	public MyTimerTask() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getInputname() {
		return inputname;
	}

	public void setInputname(String inputname) {
		this.inputname = inputname;
	}

	@Override
	public void run() {
		if(count>3){
			cancel();
			System.out.println("�����Ѿ�ȡ��");
		}
		LocalDateTime local=LocalDateTime.now();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String now=formatter.format(local);
		System.out.println("������ǣ�"+inputname+"---  ִ������ʱ��"+now);
		count++;
	}

}
