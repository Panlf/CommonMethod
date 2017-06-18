package com.plf.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * Lambda��ʵ��
 * @author plf 2017��6��15������11:17:57
 *
 */
public class LambdaExample {
/**
 * lambda���﷨
 * 1�������б�
 * 2����ͷ����"->"
 * 3�������
 */
	
	//��lambda��Runnable�ӿڵ�ʵ��
	@Test
	public void SimplifyRunnable(){
		new Runnable() {
			public void run() {
				System.out.println("�����ڲ���ʵ��Runnable�ӿ�");
			}
		}.run();
		int i=1;
		Runnable r=()->{
			System.out.println("��lambdaʵ��Runnable�ӿ�");
			//i++;���Ǵ���ģ��������޸��ⲿ������ֵ
			System.out.println("i="+i);
		};
		r.run();
	}
	
	
	//lambdaʵ���Զ���ӿڣ�ģ���½����
	@Test
	public void interfacelambda(){
		new Action(){
			public void execute(String content){
				System.out.println(content);
			}
		}.execute("jdk1.8֮ǰ�������ڲ���ʵ�ַ�ʽ��ִ�е�½����");
		
		Action login=(String content)->{
			System.out.println(content);
		};
		login.execute("jdk1.8��lambda�﷨ʵ�ֵ�½");
	}
	static interface Action{
		void execute(String content);
	}
	
	@Test
	public void NewLambda(){
		List<String> idList=new ArrayList<String>();
		idList.add("1");
		idList.add("4");
		idList.add("2");
		idList.add("3");
		idList.forEach(x->System.out.println(x));
		
		Mysort(idList);
		idList.forEach(System.out::println);
		
		String ids=String.join(",", idList);
		System.out.println(ids);
	}
	
	
	public static void Mysort(List<String> id){
		Collections.sort(id,(o1,o2)->o1.compareTo(o2));
	}
}
