package com.battlelabs.cloudpackets.codec;

import com.battlelabs.cloudpackets.Packet;
import com.battlelabs.cloudpackets.data.PacketBuffer;
import com.battlelabs.cloudpackets.data.PacketInitializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {
  @Override
  protected void decode(
      ChannelHandlerContext ctx, @NotNull ByteBuf byteBuf, @NotNull List<Object> out)
      throws Exception {
    Packet packet =
        PacketInitializer.singleton()
            .packetClasses()
            .get(byteBuf.readInt())
            .newInstance();
    packet.readPacketData(new PacketBuffer(byteBuf));
    out.add(packet);
  }
}
