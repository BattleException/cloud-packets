package com.battlelabs.cloudpackets.tests;

import com.battlelabs.cloudpackets.Packet;
import com.battlelabs.cloudpackets.client.NettyClient;
import org.jetbrains.annotations.NotNull;

public class TestNettyClient extends NettyClient {
  public TestNettyClient() {
    super("127.0.0.1", 1364);
  }

  @Override
  public void connect() throws InterruptedException {
    super.connect();
    this.channel().writeAndFlush(new TestPacket("BattleException"));
  }

  @Override
  public void readPacket(@NotNull Packet packet) {
    System.out.println(packet.getClass());
    if (packet instanceof TestPacket testPacket) {
      System.out.println(testPacket.playerName());
    }
  }
}
