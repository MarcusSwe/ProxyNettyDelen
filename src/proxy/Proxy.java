package proxy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Proxy {

    public void start(){
        EventLoopGroup clientConnections = new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(clientConnections)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ClientConnectionInitializer());

            bootstrap.bind(7000).sync().channel().closeFuture().sync();


        } catch (Exception e){
            e.printStackTrace();
        }



    }


}
