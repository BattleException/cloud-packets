package com.battlelabs.cloudpackets.tests;

import com.battlelabs.cloudpackets.data.PacketInitializer;

public class Launcher {

  public static void main(String[] args) {
    // Registering packets, so we can handle them while sending / receiving them
    // Needs to be the same order on client and server
    PacketInitializer.singleton().registerPackets(TestPacket.class);

    final TestNettyClient client = new TestNettyClient();
    try {
      client.connect();
      System.out.println("Successfully connected.");
    } catch (InterruptedException e) {
      System.out.printf("Failed to connect. (%s)%n", e);
    }

    Runtime.getRuntime().addShutdownHook(new Thread(client::disconnect));
  }

}
