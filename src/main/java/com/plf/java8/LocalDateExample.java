package com.plf.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

/**
 * LocalDate()
 * @author plf 2017��6��15������3:17:01
 *
 */
public class LocalDateExample {
	/**
	 * LocalDate.now()��ȡϵͳ��ǰ����
	 * LocalDate.of(int year,int month,int dayOfMonth)
	 * ��ָ�����ڴ���LocalDate����
	 * getYear() ���������е����
	 * getMonthValue() ���������е��·�
	 * getDayOfMonth() ���������е���
	 */
	
	/**
	 * LocalTime --ʱ����
	 * 
	 * LocalDateTime --������ʱ����
	 */
	
	/**
	 * DateTimeFormatter 
	 * static ofPattern(String pattern)
	 * 
	 * LocalDateTime.parse(strDate,formatter)
	 */
	
	/**
	 * ZonedDateTime �������ں�ʱ������Ӧ��ʱ��
	 * ZonedDateTime.now()
	 * 
	 * String format(DateTimeFormatter formatter)
	 */
	@Test
	public void TestLocalDate(){
		LocalDate localDate=LocalDate.now();
		System.out.println(localDate.getYear()+"��"+localDate.getMonthValue()+"��"+localDate.getDayOfMonth()+"��");
		System.out.println(localDate.toString());
	}
	
	@Test
	public void TestLocalTime(){
		LocalTime time=LocalTime.now();
		System.out.println(time.getHour()+"ʱ"+time.getMinute()+"��"+time.getSecond()+"��");
		System.out.println(time.toString());
	}
	
	@Test
	public void TestLocalDateTime(){
		LocalDateTime time=LocalDateTime.now();
		System.out.println(time.getYear()+"��"+time.getMonthValue()+"��"+time.getDayOfMonth()+"��"+time.getHour()+"ʱ"+time.getMinute()+"��"+time.getSecond()+"��");
		System.out.println(time.toString());
	}
	
	@Test
	public void TestPattern(){
		DateTimeFormatter formater=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime time=LocalDateTime.now();
		System.out.println(time.format(formater));
	}

	@Test
	public void TestZonedDateTime(){
		ZonedDateTime time=ZonedDateTime.now();
		DateTimeFormatter formater=DateTimeFormatter.ofPattern("yyyy/MM/dd HH/mm/ss");
		System.out.println(time.format(formater));
	}
	
	@Test
	//����ʱ��
	public void addTime(){
		LocalDateTime time=LocalDateTime.now();
		System.out.println(time.plusHours(-3));
	
		DateTimeFormatter formater=DateTimeFormatter.ofPattern("yyyy/MM/dd HH/mm/ss");
		System.out.println(formater.format(time));
		//����3s
		System.out.println(formater.format(time.plusSeconds(3)));
	}
}
