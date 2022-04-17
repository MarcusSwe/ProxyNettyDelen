package proxy;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class RealServersInitializer extends ChannelInitializer<SocketChannel> {

    private final Channel channel;

    public RealServersInitializer(Channel channel) {
        this.channel = channel;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addFirst(new RealServersHandler(channel));
    }


}
