package com.battlelabs.cloudpackets.tests;

import com.battlelabs.cloudpackets.Packet;
import com.battlelabs.cloudpackets.server.NettyServer;
import io.netty.channel.ChannelHandlerContext;
import org.jetbrains.annotations.NotNull;

public class TestNettyServer extends NettyServer {
  public TestNettyServer() {
    super("127.0.0.1", 1364);
  }

  @Override
  public void readPacket(ChannelHandlerContext context, @NotNull Packet packet) {
    System.out.println(packet.getClass());
    if (packet instanceof TestPacket testPacket) {
      System.out.println(testPacket.playerName());
      testPacket.setPlayerName("ExceptionBattle");
      context.channel().writeAndFlush(testPacket);
    }
  }
}
