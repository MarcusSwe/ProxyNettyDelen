package proxy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

public class RealServersHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private final Channel channel;

    public RealServersHandler(Channel channel) {
        this.channel = channel;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        ByteBuf clone = Unpooled.copiedBuffer(byteBuf);
        channel.writeAndFlush(clone);
    }

}
