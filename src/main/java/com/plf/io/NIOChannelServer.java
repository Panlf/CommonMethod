package com.plf.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOChannelServer {
	
	private ByteBuffer  buff=ByteBuffer.allocate(1024);
	//����һ��int����������ͼ���˻��������ݵĸ������»��������ǿɼ��ģ���֮��Ȼ
	private IntBuffer intBuff=buff.asIntBuffer();
	private SocketChannel clientChannel=null;
	private ServerSocketChannel serverChannel=null;
	
	public void openChannel() throws IOException{
		//����һ���µ�����ͨ��
		serverChannel=ServerSocketChannel.open();
		//Ϊ�µ�ͨ�����÷��ʵĽӿ�
		serverChannel.socket().bind(new InetSocketAddress(8888));
		System.out.println("����ͨ���Ѿ���...");
	}
	
	//�ȴ��ͻ��˵�����
	public void waitReqConn() throws IOException {
		while(true){
			clientChannel=serverChannel.accept();
			if(null != clientChannel){
				System.out.println("�µ����Ӽ��룡");
			}
			
			processReq();//��������
			clientChannel.close();
		}
		
	}
	
	//�����������������
	public void processReq() throws IOException {
		System.out.println("��ʼ��ȡ�ʹ���ͻ��˵�����");
		buff.clear();//�ѵ�ǰλ������Ϊ0������ֵ�޸�Ϊ������ֵ
		clientChannel.read(buff);
		int result=intBuff.get(0)+intBuff.get(1);
		buff.flip();
		buff.clear();
		//�޸���ͼ��ԭ���Ļ�����Ҳ��仯
		intBuff.put(0,result);
		clientChannel.write(buff);
		System.out.println("��ȡ�ʹ���ͻ����������");
		
	}

	public void start(){
		try{
			//�򿪷���ͨ��
			openChannel();
			
			//�����ȴ��ͻ�������
			waitReqConn();
			
			clientChannel.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		new NIOChannelServer().start();
	}
}
