package proxy;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class ClientConnectionInitializer extends ChannelInitializer<SocketChannel> {

    private final EventLoopGroup mainGroup;

    public ClientConnectionInitializer(EventLoopGroup mainGroup){
        this.mainGroup = mainGroup;
    }


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {


        /*

        Bootstrap bootstrap = new Bootstrap();
        Channel channel = bootstrap.group(mainGroup)
                .channel(NioSocketChannel.class)
                .handler(new RealServersInitializer(socketChannel))
                .connect("localhost", 8080).sync().channel();



        socketChannel.pipeline().addFirst(new ClientConnectionHandler(channel));



         */

        ChannelPipeline pipeline = socketChannel.pipeline();


        //pipeline.addLast(new HttpResponseEncoder());
        pipeline.addLast(new HttpRequestDecoder());

        pipeline.addLast(new ClientConnectionHandler());


    }
}
