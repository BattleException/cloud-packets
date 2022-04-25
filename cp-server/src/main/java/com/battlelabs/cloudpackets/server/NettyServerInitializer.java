package com.battlelabs.cloudpackets.server;

import com.battlelabs.cloudpackets.codec.PacketDecoder;
import com.battlelabs.cloudpackets.codec.PacketEncoder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.jetbrains.annotations.NotNull;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

  private final NettyServer nettyServer;

  public NettyServerInitializer(NettyServer nettyServer) {
    this.nettyServer = nettyServer;
  }

  @Override
  protected void initChannel(@NotNull SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();
    pipeline.addLast("packet-decoder", new PacketDecoder());
    pipeline.addLast("packet-encoder", new PacketEncoder());
    pipeline.addLast("handler", new NettyServerHandler(this.nettyServer));
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, @NotNull Throwable cause) {
    cause.printStackTrace();
  }
}
