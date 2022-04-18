package proxy;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class ClientConnectionInitializer extends ChannelInitializer<SocketChannel> {

    private final EventLoopGroup mainGroup;

    public ClientConnectionInitializer(EventLoopGroup mainGroup){
        this.mainGroup = mainGroup;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline = socketChannel.pipeline();

        Bootstrap bootstrap = new Bootstrap();
        Channel channel = bootstrap.group(mainGroup)
                .channel(NioSocketChannel.class)
                .handler(new RealServersInitializer(socketChannel))
                .connect(Proxy.serversX.get(Proxy.proxyNumber).getHost(),
                        Proxy.serversX.get(Proxy.proxyNumber).getPort()).sync().channel();

       pipeline.addLast(new HttpRequestDecoder());
       pipeline.addLast(new ClientConnectionHandler(channel));

        Proxy.rotateServers();
    }
}
