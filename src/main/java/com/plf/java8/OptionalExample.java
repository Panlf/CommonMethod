package com.plf.java8;

import java.util.Optional;

import org.junit.Test;

public class OptionalExample {
	@Test
	public void TestOptional(){
		//ofΪ��null��ֵ����һ��Optional��
		//Ϊָ����ֵ����һ��Optional�����ָ����ֵΪnull���򷵻�һ���յ�Optional��
		
		//ofNullable��of�������ƣ�Ψһ�������ǿ��Խ��ܲ���Ϊnull�����
		Optional<String> name=Optional.of("plf");
		Optional<String> empty=Optional.ofNullable(null);
		System.out.println(name+""+empty);
		
		//isPresent ���ֵ���ڷ���true�����򷵻�false
		//get ���Optional��ֵ���䷵�أ������׳�NoSuchElementException��
		if(name.isPresent()){
			System.out.println(name.get());
		}
		
		//���Optionalʵ����ֵ��Ϊ�����consumer������������
		empty.ifPresent((value)->{
			System.out.println(value.length());
		});
		
		//orElse�����ֵ���䷵�أ����򷵻�ָ��������ֵ��
		//orElseGet    orElseGet��orElse�������ƣ��������ڵõ���Ĭ��ֵ��
		//orElse������������ַ�����ΪĬ��ֵ��orElseGet�������Խ���Supplier�ӿڵ�ʵ����������Ĭ��ֵ��
		System.out.println(empty.orElse("NO Value"));
		System.out.println(empty.orElseGet(()->"No Value"));
		
		//orElseThrow �����ֵ���䷵�أ������׳�supplier�ӿڴ������쳣��
		/*try {
			empty.orElseThrow(Exception::new);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		//map �� flatMap ��filter
		//�����ֵ��Ϊ��ִ��mapping��������Optional���ͷ���ֵ�����򷵻ؿ�Optional��flatMap��map��Funtion���������ƣ�
		//��������flatMap�е�mapper����ֵ������Optional�����ý���ʱ��flatMap����Խ����Optional��װ��
		System.out.println(name.map(x->x.toUpperCase()));
		System.out.println(name.flatMap(x->Optional.of(x.toUpperCase())));
		System.out.println(name.filter(x->!x.equals("plf")));
		
		
		Person person=new Person();
		System.out.println("PersonName==��"+getNewName(person));
	
		person.setName("yoooo");
		System.out.println("PersonName==��"+getNewName(person));
	}
	
	
	public static String getNewName(Person p) {
	    return Optional.ofNullable(p)
	                    .map(x->p.getName())
	                    .orElse("Unknown");
	}
}
