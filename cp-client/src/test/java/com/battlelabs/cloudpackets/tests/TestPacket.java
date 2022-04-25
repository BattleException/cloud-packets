package com.battlelabs.cloudpackets.tests;

import com.battlelabs.cloudpackets.Packet;
import com.battlelabs.cloudpackets.data.PacketBuffer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class TestPacket extends Packet {

  private String playerName;

  public TestPacket(String playerName) {
    this.playerName = playerName;
  }

  public TestPacket() {
  }

  @Override
  public void readPacketData(@NotNull PacketBuffer buffer) throws IOException {
    this.playerName = buffer.readStringFromBuffer(16);
  }

  @Override
  public void writePacketData(@NotNull PacketBuffer buffer) throws IOException {
    buffer.writeString(this.playerName);
  }

  public String playerName() {
    return playerName;
  }

  public TestPacket setPlayerName(String playerName) {
    this.playerName = playerName;
    return this;
  }
}
