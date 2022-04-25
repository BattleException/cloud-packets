package com.battlelabs.cloudpackets.tests;

import com.battlelabs.cloudpackets.Packet;
import com.battlelabs.cloudpackets.server.NettyServer;
import io.netty.channel.ChannelHandlerContext;

public class TestNettyServer extends NettyServer {
  public TestNettyServer() {
    super("127.0.0.1", 1364);
  }

  @Override
  public void readPacket(ChannelHandlerContext context, Packet packet) {
    System.out.println("Reading packet...");
  }
}
