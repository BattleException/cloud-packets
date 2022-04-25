package com.battlelabs.cloudpackets.data;

import com.battlelabs.cloudpackets.Packet;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public final class PacketInitializer {

  private static final PacketInitializer SINGLETON = new PacketInitializer();

  private final HashMap<Integer, Class<? extends Packet>> packetClasses = new HashMap<>();
  private final HashMap<Class<? extends Packet>, Integer> packetIDMap = new HashMap<>();

  public static PacketInitializer singleton() {
    return SINGLETON;
  }

  @SafeVarargs
  public final void registerPackets(Class<? extends Packet> @NotNull ... classes) {
    for (int id = 0; id < classes.length; id++) {
      Class<? extends Packet> packetClass = classes[id];
      this.packetClasses.put(id, packetClass);
      this.packetIDMap.put(packetClass, id);
    }
  }

  public HashMap<Integer, Class<? extends Packet>> packetClasses() {
    return packetClasses;
  }

  public HashMap<Class<? extends Packet>, Integer> packetIDMap() {
    return packetIDMap;
  }
}
