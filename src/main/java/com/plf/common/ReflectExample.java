package com.plf.common;

import java.lang.reflect.Method;

import org.junit.Test;

/**
 * ����Ļ�������
 * @author plf 2017��7��8������10:18:59
 *
 */
public class ReflectExample {

	//���似������ʵ���Ƕ�̬����һ��ָ�����࣬����ȡ�����е���������
	//��˵�����似�����Զ�һ������н���
	/*
	 * ����Ļ������裺
	 * 1�����Class���󣬾��ǻ�ȡ��ָ�������Ƶ��ֽ����ļ�����
	 * 2��ʵ�������󣬻��������ԡ��������캯��
	 * 3���������ԡ����÷��������ù��캯����������
	 */
	@Test
	public void TestReflect(){
		//��ȡClass����
		String className="com.plf.common.Person";
		try {
			//1�����ݸ������������   ���������
			Class<?> clazz=Class.forName(className);
			System.out.println(clazz);//�˶������Person.class
			
			//2������õ��˶��󣬲�֪����ʲô����  ���ڻ�ö��������
			Object obj=new Person();
			Class<?> clazz1=obj.getClass();//��ö�����������
			System.out.println(clazz1);
			
			//3���������ȷ�ػ��ĳ�����Class����   ��Ҫ���ڴ���
			Class<?> clazz2 = Person.class;
			System.out.println(clazz2);
			
			//���÷���
			Method method=clazz.getMethod("Say",int.class,String.class);
			Object person=clazz.newInstance();
			method.invoke(person,19, "plf");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class Person{
	private String name;
	private int age;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person(){}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public void Say(int age,String name){
		System.out.println("I am "+age+".My name is "+name);
	}
}