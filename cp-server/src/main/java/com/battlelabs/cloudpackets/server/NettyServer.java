package com.battlelabs.cloudpackets.server;

import com.battlelabs.cloudpackets.Packet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.HashMap;
import java.util.Map;

public abstract class NettyServer implements INettyServer {

  private final String host;
  private final int port;

  private final NioEventLoopGroup bossNioEventLoopGroup = new NioEventLoopGroup(1), workerNioEventLoopGroup = new NioEventLoopGroup(0);
  private final Map<ChannelHandlerContext, ConnectedClient> connectedClients = new HashMap<>();
  private ChannelFuture channelFuture;

  public NettyServer(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public void start() throws InterruptedException {
    this.channelFuture =
            new ServerBootstrap()
                    .channel(NioServerSocketChannel.class)
                    .group(this.bossNioEventLoopGroup, this.workerNioEventLoopGroup)
                    .childHandler(new NettyServerInitializer(this))
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.AUTO_READ, true)
                    .bind(this.host, this.port)
                    .addListener(ChannelFutureListener.CLOSE_ON_FAILURE)
                    .addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE)
                    .sync()
                    .channel()
                    .closeFuture();
  }

  @Override
  public void stop() {
    this.channelFuture.channel().close();
  }

  @Override
  public void onConnect(ChannelHandlerContext context) {
    this.connectedClients.put(context, new ConnectedClient(context.channel()));
  }

  @Override
  public void onDisconnect(ChannelHandlerContext context) {
    this.connectedClients.remove(context);
  }

  @Override
  public void onTimeOut(ChannelHandlerContext context) {
    this.onDisconnect(context);
  }

  public String host() {
    return host;
  }

  public int port() {
    return port;
  }

  public Map<ChannelHandlerContext, ConnectedClient> connectedClients() {
    return connectedClients;
  }

  public ChannelFuture channelFuture() {
    return channelFuture;
  }

  public record ConnectedClient(Channel channel) {
    public void sendPacket(Packet packet) {
      this.channel.writeAndFlush(packet);
    }
  }
}
