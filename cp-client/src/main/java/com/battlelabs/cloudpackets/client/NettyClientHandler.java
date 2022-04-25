package com.battlelabs.cloudpackets.client;

import com.battlelabs.cloudpackets.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyClientHandler extends SimpleChannelInboundHandler<Packet> {

  private final NettyClient nettyClient;

  public NettyClientHandler(NettyClient nettyClient) {
    this.nettyClient = nettyClient;
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
    this.nettyClient.readPacket(packet);
  }
}
