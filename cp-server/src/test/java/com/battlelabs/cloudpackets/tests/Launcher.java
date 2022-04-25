package com.battlelabs.cloudpackets.tests;

import com.battlelabs.cloudpackets.data.PacketInitializer;

public class Launcher {

  public static void main(String[] args) {
    // Registering packets, so we can handle them while sending / receiving them
    // Needs to be the same order on client and server
    PacketInitializer.singleton().registerPackets(TestPacket.class);

    final TestNettyServer server = new TestNettyServer();
    try {
      server.start();
      System.out.println("Successfully started server.");
    } catch (InterruptedException e) {
      System.out.printf("Failed to start server. (%s)%n", e);
    }

    Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
  }

}
