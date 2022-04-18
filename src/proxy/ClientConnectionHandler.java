package proxy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

public class ClientConnectionHandler extends SimpleChannelInboundHandler<Object> {

    private HttpRequest request;
    private final Channel channel;
    public ClientConnectionHandler(Channel channel) {
        this.channel = channel;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object yeah) throws Exception {

    if (yeah instanceof HttpRequest) {
        HttpRequest request = this.request = (HttpRequest) yeah;
        HttpHeaders headers = request.headers();
        headers.add("password", "ad1Xs4kC6jfh7Ds8a8dDjk!fh");

        channel.writeAndFlush(request);
         }

    }


}
