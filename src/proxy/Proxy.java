package proxy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Proxy {

    static EventLoopGroup mainGroup = new NioEventLoopGroup();

    public void start(){

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(mainGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ClientConnectionInitializer(mainGroup));

            bootstrap.bind(7000).sync().channel().closeFuture().sync();


        } catch (Exception e){
            e.printStackTrace();
        }



    }


}
