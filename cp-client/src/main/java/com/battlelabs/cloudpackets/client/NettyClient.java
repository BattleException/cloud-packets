package com.battlelabs.cloudpackets.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public abstract class NettyClient implements INettyClient {

  private final String host;
  private final int port;

  private final NioEventLoopGroup group = new NioEventLoopGroup();
  private Channel channel;

  public NettyClient(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public void connect() throws InterruptedException {
    this.channel =
        new Bootstrap()
            .group(this.group)
            .channel(NioSocketChannel.class)
            .handler(new NettyClientInitializer(this))
            .connect(this.host, this.port)
            .sync()
            .channel();
    this.channel.closeFuture();
  }

  @Override
  public void disconnect() {
    this.group.shutdownGracefully();
  }

  public String host() {
    return host;
  }

  public int port() {
    return port;
  }

  public Channel channel() {
    return channel;
  }
}
