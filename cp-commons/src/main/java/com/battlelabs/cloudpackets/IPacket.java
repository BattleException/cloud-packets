package com.battlelabs.cloudpackets;

import com.battlelabs.cloudpackets.data.PacketBuffer;

import java.io.IOException;

public interface IPacket {

  /**
   * Reads the raw packet data from the data stream.
   */
  void readPacketData(PacketBuffer buffer) throws IOException;

  /**
   * Writes the raw packet data to the data stream.
   */
  void writePacketData(PacketBuffer buffer) throws IOException;


}
