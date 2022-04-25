package com.battlelabs.cloudpackets.client;

import com.battlelabs.cloudpackets.Packet;

public interface INettyClient {

  void connect() throws InterruptedException;

  void disconnect();

  void readPacket(Packet packet);

}
