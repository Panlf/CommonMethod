package com.plf.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Stream
 * @author plf 2017��6��15������12:42:51
 *
 */
public class StreamExample {
	/**
	 * һ��
	 * 1��Stream ��Java8�б�����Ϊ���ͽӿ�
	 * 2��Stream�ӿڴ���������
	 * 3��Stream�������ݽṹ����ֱ�Ӵ洢����
	 * 4��Streamͨ���ܵ���������
	 * 5������Stream�ӿ�ʵ�������
	 * 		stream():����һ��Stream�ӿ�ʵ����Ķ���
	 * ����
	 * �ܵ�:����һ����������
	 * �ܵ�����һ�����
	 * 	1�����ݼ�:�����Ǽ��ϡ������
	 * 	2����������м�ҵ���������
	 * 	3��һ���ն˲�������forEach
	 * 
	 * ����
	 * ������:�ø�����������Դ���ݻ����Ϲ��˳��µ����ݣ�������һ��Stream����
	 * 2������������ƥ���ν��
	 * 		���磺�ж�p�����Ƿ�Ϊ���Ե�lambda���ʽ
	 * Stream<Person> stream=people.stream();
	 * stream=stream.filter(p->p.getGender()=='��');
	 * 
	 * �ġ�
	 * DoubleStream �ӿڱ�ʾԪ��������double������Դ
	 * DoubleStream �ӿڵĳ��÷���
	 * max().getAsDouble() ��ȡ�������ݼ������ֵ
	 * stream().min().getAsDouble ��ȡ�������ݼ�����Сֵ
	 * stream.average() ��ȡ�������ݼ���ƽ��ֵ 
	 */
	
	//����һ��Ԫ��ΪPerson��ļ��ϣ�peopleʹ��stream��forEach��ʾ�ü�������Ԫ��
	@Test
	public void CollectionStream(){
		List<Person> people=createPeople();
		Stream<Person> stream=people.stream();
		
		//stream.forEach(p->System.out.println(p.toString()));
		
		//������--����FEMALE
		//stream.filter(p->p.getGender()==Person.Sex.MALE).
		//forEach(p->System.out.println(p.toString()));
		
		double a=stream.filter(p->p.getName().indexOf("��")>=0)
		.mapToDouble(p->p.getHeight())
		.average()
		.getAsDouble();
		System.out.println(a);
	}
	static List<Person> createPeople(){
		List<Person> people=new ArrayList<Person>();
		Person person=new Person("����",Person.Sex.MALE,30,2.0);
		people.add(person);
		person=new Person("����",Person.Sex.MALE,31,1.8);
		people.add(person);
		person=new Person("����",Person.Sex.FEMALE,32,1.6);
		people.add(person);
		return people;
	}
	
	@Test
	public void NewStream(){
		Stream<String> fruit=Stream.of("apple","orange","banner","pear");
		fruit.sorted().map(String::toUpperCase).forEach(System.out::println);
	}
}
