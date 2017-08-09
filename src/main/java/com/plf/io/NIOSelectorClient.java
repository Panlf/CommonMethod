package com.plf.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOSelectorClient {

	private Selector selector;
	private ByteBuffer outBuff=ByteBuffer.allocate(1024);
	private ByteBuffer inBuff=ByteBuffer.allocate(1024);
	private int keys=0;
	private SocketChannel channel=null;
	
	public void initClient() throws IOException{
		//���һ��socketͨ������û�н�������
		channel = SocketChannel.open();
		//���һ��ͨ��������
		selector = Selector.open();
		//����Ϊ������
		channel.configureBlocking(false);
		
		//���ӷ�����
		channel.connect(new InetSocketAddress("127.0.0.1",8888));
		//ע��ͻ������ӷ������¼�
		channel.register(this.selector, SelectionKey.OP_CONNECT);
	}
	//������ͨ���������ע����¼�
	@SuppressWarnings("static-access")
	public void listen() throws IOException{
		//������ѯ
		while(true){
			keys=this.selector.select();
			if(keys>0){
				Iterator<SelectionKey> it=this.selector.selectedKeys().iterator(); 
				while(it.hasNext()){
					SelectionKey key=it.next();
					//���Դ�ͨ���Ƿ�����׽��ֵ�����
					if(key.isConnectable()){
						//����������������ͨ��
						SocketChannel channel=(SocketChannel) key.channel();
						if(channel.isConnectionPending()){
							channel.finishConnect();
							System.out.println("�������");
						}
						channel.register(this.selector, SelectionKey.OP_WRITE);
					}
					//��ͨ���������д����
					else if(key.isWritable()){
						SocketChannel channel=(SocketChannel) key.channel();
						outBuff.clear();
						System.out.println("�ͻ�������д����...");
						channel.write(outBuff.wrap("����ClientA".getBytes()));
						channel.register(this.selector, SelectionKey.OP_READ);
						System.out.println("�ͻ���д�������");
					}
				//��ͨ���Ͻ��ж�ȡ
				else if(key.isReadable()){
					SocketChannel channel=(SocketChannel) key.channel();
					inBuff.clear();
					System.out.println("client start read data");
					channel.read(inBuff);
					System.out.println("==>"+new String(inBuff.array()));
					System.out.println("client finish read data");
					}
				}
			}
			else{
				System.out.println("û���ҵ�����Ȥ���¼�");
			}
		}
	}	
	public void start(){
		try{
			initClient();
			listen();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NIOSelectorClient().start();
	}

}
