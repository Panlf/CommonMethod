package com.plf.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOSelectorServer {

	private Selector selector;
	private int keys=0;
	private ServerSocketChannel serverChannel=null;
	
	//��ʼ�����������ͨ���͹��������Ѿ�ע���¼�
	public void initServer() throws IOException{
		this.selector = Selector.open();
		serverChannel = ServerSocketChannel.open();
		serverChannel.socket().bind(new InetSocketAddress("127.0.0.1",8888));
		serverChannel.configureBlocking(false);
		
		//��serverChannel���ͨ��ע�ᵽͨ������������acceptSelectorȥ�����пͻ�������ʱ����
		serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);
	}
	//�Կͻ��˵�����ͨ���������Ȥ���¼������м���
	public void listen() throws IOException{
		System.out.println("�������Ѿ�������");
		while(true){
			//��ͨ������������ѡ��һ��ͨ��
			keys=this.selector.select();
			Iterator<SelectionKey> it=this.selector.selectedKeys().iterator();
			if(keys>0){
				//������ѯ
				while(it.hasNext()){
					SelectionKey key=it.next();
					it.remove();
					//�ͻ��������¼�
					if(key.isAcceptable()){
						serverChannel=(ServerSocketChannel) key.channel();
						//��úͿͻ������ӵ�ͨ��
						SocketChannel channel=serverChannel.accept();
						channel.configureBlocking(false);//���÷�������ʽ
						
						//���ͻ��˷���Ϣ
						channel.write(ByteBuffer.wrap(new String("hello").getBytes()));
						//����Ҫ��ȡ�ͻ��˹��������ݣ�����ע��һ��ȥ�����ݵ��¼�
						channel.register(this.selector, SelectionKey.OP_READ);
					}else if(key.isReadable()){
						read(key);
					}
				}
			}
			else{
				System.out.println("Select finished without any keys");
			}
		}
		
	}
	
	//����SelectionKey��������ȡ�ͻ��˷��͵�ͨ���������
	public void read(SelectionKey key) throws IOException {
		SocketChannel channel=(SocketChannel) key.channel();
		//������ 
		ByteBuffer buff=ByteBuffer.allocate(1024);
		int len=channel.read(buff);
		String msg="������յ�����Ϣ�ǣ�"+new String(buff.array(),0,len);
		System.out.println(msg);
	}
	
	public void start(){
		try{
			NIOSelectorServer ns=new NIOSelectorServer();
			ns.initServer();
			ns.listen();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NIOSelectorServer().start();
	}

}
