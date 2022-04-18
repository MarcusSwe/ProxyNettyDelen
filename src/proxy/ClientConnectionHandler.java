package proxy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

public class ClientConnectionHandler extends SimpleChannelInboundHandler<Object> {

  /*  private final Channel channel;

    public ClientConnectionHandler(Channel channel) {
        this.channel = channel;
    }*/


    private HttpRequest request;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object yeah) throws Exception {


    if (yeah instanceof HttpRequest) {
        HttpRequest request = this.request = (HttpRequest) yeah;
        HttpHeaders headers = request.headers();
        headers.add("passwordx", "testlösen");
        System.out.println(headers.get("searchItem"));
        System.out.println(headers.get("passwordx"));

    }


        /*
    HttpRequest request = (HttpRequest) yeah;
    HttpHeaders headers = request.headers();
*/


    System.out.println("hej");



    }


}
