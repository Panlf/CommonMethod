package com.plf.netty.helloworld;

//import java.util.concurrent.Callable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.channel.ChannelHandler.Sharable;

/**
 * 
 * @Sharable 代表当亲Handler是一个可以分享的处理器。也就意味着，服务器注册此Handler后，可以分享多个客户端使用
 * 	如果不使用注解描述类型，则客户端请求时，必须为客户端重新创建一个新Handler对象
 * 
 * 如果handler是Sharable的，一定要避免定义可写的实例变量
 * */
@Sharable
public class ServerHandler extends ChannelHandlerAdapter {
	
	//group 就是充当业务线程池，可以将任务提交到该线程池
	static final EventExecutorGroup group = new DefaultEventExecutorGroup(16);
	
	/**
	 * 业务处理逻辑
	 * 	用于处理读取数据的请求逻辑
	 * @param ctx  上下文对象。其中包含于客户端建立连接的所有资源。如：对应的Channel
	 * @param msg  读取到的数据，默认的类型是ByteBuf，是netty自定义的，是对ByteBuffer的封装，不需要考虑复位问题
	 * @throws Exception
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("ServerHandler的线程是="+Thread.currentThread().getName());
		
		//加入异步线程池第一种方式
		/**
		 * 在handler中添加异步，更加的自由。想用异步就用异步。
		 */
		/*
		group.submit(new Callable<Object>() {

			@Override
			public Object call() throws Exception {
				//获取读取的数据，是一个缓冲
				ByteBuf readBuffer = (ByteBuf) msg;
				//创建一个字节数组，用于保存缓存中的数据
				byte[] tempDatas = new byte[readBuffer.readableBytes()];
				//将缓存中的数据读取到字节数组中
				readBuffer.readBytes(tempDatas);
				String message = new String(tempDatas,"UTF-8");
				System.out.println("from client : "+ message);
				if("exit".equals(message)){
					ctx.close();
				}
				
				System.out.println("group submit 的call线程是="+Thread.currentThread().getName());
				
				String line = "server message to client!";
				//写操作自动释放缓存，避免内存溢出问题
				ctx.writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")));
				//注意，如果调用的是write方法，不会刷新缓存，缓存中的数据不会发送到客户端，必须再次调用flush才行。
				return null;
			}
			
		});*/
		
		
		//获取读取的数据，是一个缓冲
		ByteBuf readBuffer = (ByteBuf) msg;
		//创建一个字节数组，用于保存缓存中的数据
		byte[] tempDatas = new byte[readBuffer.readableBytes()];
		//将缓存中的数据读取到字节数组中
		readBuffer.readBytes(tempDatas);
		String message = new String(tempDatas,"UTF-8");
		System.out.println("from client : "+ message);
		if("exit".equals(message)){
			ctx.close();
		}
		
		System.out.println("普通调用方式   线程是="+Thread.currentThread().getName());
		
		String line = "server message to client!";
		//写操作自动释放缓存，避免内存溢出问题
		ctx.writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")));
		//注意，如果调用的是write方法，不会刷新缓存，缓存中的数据不会发送到客户端，必须再次调用flush才行。
		
		System.out.println("Go On");
		
	}
	
	/**
	 * 异常处理逻辑，当客户端异常退出时，也会运行
	 * 	ChannelHandlerContext关闭，也代表当前客户端连接的资源关闭
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("server exceptionCaught method run...");
		ctx.close();
	}

}
