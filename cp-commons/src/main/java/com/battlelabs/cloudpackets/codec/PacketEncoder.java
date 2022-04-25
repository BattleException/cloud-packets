package com.battlelabs.cloudpackets.codec;

import com.battlelabs.cloudpackets.Packet;
import com.battlelabs.cloudpackets.data.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.jetbrains.annotations.NotNull;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
  @Override
  protected void encode(ChannelHandlerContext ctx, @NotNull Packet packet, @NotNull ByteBuf byteBuf) throws Exception {
    byteBuf.writeInt(packet.id());
    packet.writePacketData(new PacketBuffer(byteBuf));
  }
}
