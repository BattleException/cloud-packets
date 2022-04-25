package com.battlelabs.cloudpackets.server;

import com.battlelabs.cloudpackets.Packet;
import io.netty.channel.ChannelHandlerContext;

public interface INettyServer {

  void start() throws InterruptedException;

  void stop();

  void readPacket(ChannelHandlerContext context, Packet packet);

  void onConnect(ChannelHandlerContext context);

  void onDisconnect(ChannelHandlerContext context);

  void onTimeOut(ChannelHandlerContext context);

}
