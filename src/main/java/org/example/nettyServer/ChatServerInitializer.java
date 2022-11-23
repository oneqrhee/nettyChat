package org.example.nettyServer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;

public class ChatServerInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslContext;

    public ChatServerInitializer(SslContext sslContext){
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(SocketChannel arg0) throws Exception{
        ChannelPipeline channelPipeline = arg0.pipeline();

        channelPipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        channelPipeline.addLast(new StringDecoder());
        channelPipeline.addLast(new StringEncoder());

        channelPipeline.addLast(new ChatServerHandler());
    }
}
