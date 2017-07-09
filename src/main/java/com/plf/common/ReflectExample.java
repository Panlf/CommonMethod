package com.plf.common;

import java.lang.reflect.Constructor;
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
		String className="com.plf.common.Person";
		try {
			//��ȡClass����
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
			
			//��ȡ���е����з���
			Method[] methods=clazz.getMethods();
			methods=clazz.getDeclaredMethods();//��ȡ���еķ�������˽�з���
			for (Method method : methods) {
				System.out.println(method);
			}
			System.out.println("--------------------------");
			
			//�ô������Ĺ��캯���½�����
			Constructor<?> constructor=clazz.getConstructor(String.class,int.class);
			Object obj1=constructor.newInstance("pcq",23);
			//Method m=clazz.getMethod("Say",String.class,int.class);
			//m.invoke(obj1,"",0);
			System.out.println(obj1);
			
			//��ȡ���캯��
			Constructor<?>[] constructors=clazz.getConstructors();
			constructors=clazz.getDeclaredConstructors();
			for (Constructor<?> constructor2 : constructors) {
				System.out.println(constructor2);
			}
			//���÷���
			Method method=clazz.getMethod("Say",String.class,int.class);
			//��ʵ��������ķ������þ���ָ�����еĿղ������캯����������������г�ʼ����
			Object person=clazz.newInstance();
			method.invoke(person,"plf",19);
			
			//����˽�з��������ǲ�����ʹ�ã���Ϊ˽�з����������ǲ�ϣ���ⲿʹ��
//			Method method1=clazz.getDeclaredMethod("Eat",null);
//			method1.setAccessible(true);
//			System.out.println(method1.invoke(person, null));
			
			//��̬����
			//Method method2=clazz.getMethod("Run", null);
			//method2.invoke(null, null);
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
	
	public void Say(String name,int age){
		System.out.println("I am "+age+".My name is "+name+"!"+Eat());
	}
	
	private String Eat(){
		return "I eat rice!";
	}
	public static void Run(){
		System.out.println("I can run");
	}
}