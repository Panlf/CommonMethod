package com.plf.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class fileChannel {
	public static void main(String[] args){
		fileChanneldemo();
	}
	
	@SuppressWarnings("resource")
	public static void fileChanneldemo(){
		try{
			//���建��������
			ByteBuffer buff=ByteBuffer.allocate(1024);
			//ͨ���ļ�����������ļ�ͨ�����󣨶�ȡ������
			FileChannel infc=new FileInputStream("E://a.txt").getChannel();
			//׷��д���ļ�
			FileChannel outfc=new FileOutputStream("E://a.txt",true).getChannel();
			//��ȡ����
			buff.clear();
			int len=infc.read(buff);
			System.out.println(new String(buff.array(),0,len));
			
			
			//д����
			ByteBuffer buff2=ByteBuffer.wrap("jack".getBytes());
			outfc.write(buff2);
			
			//�ر���Դ
			outfc.close();
			infc.close();
					
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
