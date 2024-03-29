package com.jdyh.chatroom.common.base;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Netty服务端基础类
 * 
 * @author walkman
 *
 */
public abstract class BaseNettyServer {
	
	private Logger logger = LoggerFactory.getLogger(BaseNettyServer.class);
	/**
	 * 	处理器集合
	 * 
	 * @return
	 */
	public abstract List<ChannelHandler> getHandlerList();

	/**
	 * 	启动服务端
	 * 
	 * @param port
	 * @throws Exception
	 */
	public void start(int port) throws Exception {
		logger.info("服务启动端口：" + port);
		// 事件循环组
		EventLoopGroup parentLoopGroup = new NioEventLoopGroup();
		EventLoopGroup childLoopGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(parentLoopGroup, childLoopGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<Channel>() {
						protected void initChannel(Channel ch)
								throws Exception {
							for (ChannelHandler factory : getHandlerList()) {
								ch.pipeline().addLast(factory);
							}
						}
					});

			ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			parentLoopGroup.shutdownGracefully();
			childLoopGroup.shutdownGracefully();
		}
	}

}
