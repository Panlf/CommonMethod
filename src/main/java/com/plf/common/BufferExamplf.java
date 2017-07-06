package com.plf.common;

import org.junit.Test;

public class BufferExamplf {
	@Test
	public void TestStringBuffer(){
		/**
		 * �ַ�������
		 * �ص�
		 * 1�����Զ��ַ������ݽ����޸�
		 * 2����һ������
		 * 3���ǿɱ䳤��
		 * 4���������п��Դ洢�������͵�����
		 * 5��������Ҫ����ַ���
		 */
		String str="plf";
		StringBuffer sb=new StringBuffer(str);
		
		//���
		sb.append("pcq").append(1314);//�ں�������
		System.out.println(sb.getClass().getTypeName()+":"+sb);
		System.out.println(sb.insert(1,"an"));//ָ��λ�����
		
		//ɾ��
		sb.delete(1, 2);//������ߣ��������ұ�
		System.out.println(sb);//ɾ��ָ��λ��
		System.out.println(sb.deleteCharAt(1));
	
		//�޸�
		sb.replace(3, 4, "pan");
		System.out.println(sb);
		sb.setCharAt(0, 'P');
		System.out.println(sb);
		//sb.setLength(3);//��ȡ
		System.out.println(sb);
		
		//����
		System.out.println(sb.indexOf("pan"));
		System.out.println(sb.indexOf("pan",2));
		System.out.println(sb.lastIndexOf("pan"));
		System.out.println(sb.lastIndexOf("pan",2));
		
		//��ȡ�Ӵ�
		System.out.println(sb.substring(4));
		System.out.println(sb.substring(1, 4));
		
		//��ת
		System.out.println(sb.reverse());
	}
}
