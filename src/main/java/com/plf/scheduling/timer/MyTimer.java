package com.plf.scheduling.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

public class MyTimer {
	
	//�����������ȱ��
	//Timer����ֻ��һ���߳�ȥִ�ж�ʱ����������ڶ������������ʱ��������ᵼ��ִ��Ч����Ԥ�ڲ���
	//���TimerTask�׳�RuntimeException��Timer��ֹͣ�������������
	//��ʱЧ��Ҫ��ϸߵĶ����񲢷���ҵ
	//�Ը��ӵ��������
	
	/*1���״μƻ�ִ��ʱ�����ڵ�ǰʱ��
	schedule �� �����һ��ʱ�䱻delay�ˣ�����ִ��ʱ�䰴��
				��һ��ʵ��ִ����ɵ�ʱ�����м���
	scheduleAtFixedRate�� �����һ��ʱ�䱻delay�ˣ�����ִ��ʱ�䰴��
				��һ�ο�ʼ��ʱ�����м��㣬����Ϊ�˸��Ͻ��Ȼ���ִ������
				���TimerTask�е�ִ������Ҫ����ͬ��
				
	2������ִ��ʱ�䳬��ִ�����ڼ��
	schedule ����һ��ִ��ʱ���������һ��ʵ��ִ����ɵ�ʱ��㣬���ִ��ʱ��᲻���Ӻ�
	scheduleAtFixedRate����һ��ִ��ʱ���������һ�ο�ʼ��ʱ��㣬���ִ��ʱ��һ�㲻���Ӻ�
			��˴��ڲ�����
	
	*/
	public static void main(String[] args){
		test();
		//TestScheduling();
		//testScheduleAtFixedRate();
	}
	public static void test() {
		//1���½�һ��timerʵ��
		Timer timer=new Timer();
		//2������һ��MyTimerTaskʵ��
		MyTimerTask myTimerTask = new MyTimerTask("plf");
		//3������timer��ʱ��Ƶ�ʵ���myTimerTask��ҵ���߼�
		timer.schedule(myTimerTask, 2000L,1000L);//�ڳ���������2s����ã�֮��ÿ��1s����
	
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ȡ��myTimerTask������
		myTimerTask.cancel();
		//����ȡ���������������Ӷ������Ƴ�
		System.out.println(timer.purge());
		
		//ȡ����������
		//timer.cancel();
	}
	
	//schedule(task,time)
	public static void TestScheduling(){
		//1���½�һ��timerʵ��
		Timer timer=new Timer();
		//2������һ��MyTimerTaskʵ��
		MyTimerTask myTimerTask = new MyTimerTask();
				
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now=formatter.format(calendar.getTime());
		System.out.println("��ǰʱ�䣺"+now);
		calendar.add(Calendar.SECOND, 3);
		myTimerTask.setInputname("TestScheduling");
		//��ʱ����ڻ��߳���time��ʱ��ִ����ִֻ��һ��task
		timer.schedule(myTimerTask,calendar.getTime());
		
		//���ش��������ʵ��ִ�е��Ѱ���ִ�е�ʱ��
		System.out.println(formatter.format(myTimerTask.scheduledExecutionTime()));
		//schedule(task,time,period)
		//��ʱ����ڻ��߳���time��ʱ��ִ����ִֻ��һ��task ֮��ûperiodִ��һ������
		
		//schedule(task,delay)
		//�ڵȴ�delay����֮��ִ����ִֻ��һ��
		
		//schedule(task,delay,period)
		//�ӳ�delay����֮��ִ�У�֮��ÿperiodִ��һ��
	}
	
	public static void testScheduleAtFixedRate(){
		//1���½�һ��timerʵ��
		Timer timer=new Timer();
		//2������һ��MyTimerTaskʵ��
		MyTimerTask myTimerTask = new MyTimerTask("testScheduleAtFixedRate");
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now=formatter.format(calendar.getTime());
		System.out.println("��ǰʱ�䣺"+now);
		calendar.add(Calendar.SECOND, 3);
		
		//scheduleAtFixedRate(task,firsttime,period)
		timer.scheduleAtFixedRate(myTimerTask, calendar.getTime(), 2000L);
			
		//scheduleAtFixedRate(task,delay,period)
	}
}
