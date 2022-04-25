package com.battlelabs.cloudpackets.server;

import com.battlelabs.cloudpackets.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<Packet> {

  private final NettyServer nettyServer;

  public NettyServerHandler(NettyServer nettyServer) {
    this.nettyServer = nettyServer;
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Packet packet) {
    this.nettyServer.readPacket(ctx, packet);
  }

  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    this.nettyServer.onConnect(ctx);
    super.handlerAdded(ctx);
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    this.nettyServer.onDisconnect(ctx);
    super.handlerRemoved(ctx);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) {
    this.nettyServer.onTimeOut(ctx);
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) {
    this.nettyServer.onDisconnect(ctx);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    //super.exceptionCaught(ctx, cause);
  }
}
