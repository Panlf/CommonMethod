package com.plf.io;

import java.nio.IntBuffer;

import org.junit.Test;

/*
 * NIO����
 * Ϊ���е�ԭʼ�����ṩ(Buffer)����֧��
 * �ַ����������������
 * Channel:һ���µ�ԭʼI/O����
 * ֧�������ڴ�ӳ���ļ����ļ����ʽӿ�
 * �ṩ��·(non-bloking)������ʽ�ĸ�����������I/O
 */
public class NIOExample {
	//���л������������ĸ��������ṩ������������������Ԫ�ص���Ϣ
	@SuppressWarnings("static-access")
	/*1������  �������ܹ����ɵ�����Ԫ�ص��������
	 *2���Ͻ� �������ĵ�һ�����ܱ�����д��Ԫ��
	 *3��λ�� ��һ��Ҫ������д��Ԫ�ص�����
	 *4����� һ������λ��
	 **/
	//����������:ByteBuffer��CharBuffer��DoubleBuffer��FloatBuffer��IntBuffer��LongBuffer��ShortBuffer
	@Test
	public void TestBuffer(){
		//����ָ�����ȵĻ�����
		IntBuffer buff=IntBuffer.allocate(10);
		int[] array=new int[]{1,3,5};
		
		//ʹ������������һ����������ͼ
		buff = buff.wrap(array);
		
		//���������ĳһ��������������ͼ
		//buff = buff.wrap(array,0,2);
		
		//�Ի�����ĳ��λ�������Ԫ���޸�
		buff.put(0,8);
		
		//����������������
		for(int i=0;i<buff.limit();i++){
			System.out.print(buff.get()+"\t");
		}
		System.out.println("\nBuffer����Ϣ��"+buff);
		buff.flip();//�Ի��������з�ת��(limit=pos;pos=0)
		System.out.println("\nBuffer����Ϣ��"+buff);
		//buff.clear();
		
		//��ֵһ���µĻ�����
		IntBuffer newBuff=buff.duplicate();
		System.out.println(newBuff);
	}
	
}
