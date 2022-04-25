package com.battlelabs.cloudpackets.client;

import com.battlelabs.cloudpackets.codec.PacketDecoder;
import com.battlelabs.cloudpackets.codec.PacketEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.jetbrains.annotations.NotNull;

public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {

  private final NettyClient nettyClient;

  public NettyClientInitializer(NettyClient nettyClient) {
    this.nettyClient = nettyClient;
  }

  @Override
  protected void initChannel(@NotNull SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();
    pipeline.addLast("packet-decoder", new PacketDecoder());
    pipeline.addLast("packet-encoder", new PacketEncoder());
    pipeline.addLast("handler", new NettyClientHandler(this.nettyClient));
  }
}
