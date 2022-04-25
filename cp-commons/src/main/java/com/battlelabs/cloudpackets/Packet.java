package com.battlelabs.cloudpackets;

import com.battlelabs.cloudpackets.data.PacketInitializer;

public abstract class Packet implements IPacket {

  private final int id;

  public Packet() {
    this.id = PacketInitializer.singleton().packetIDMap().get(getClass());
  }

  public int id() {
    return id;
  }
}
