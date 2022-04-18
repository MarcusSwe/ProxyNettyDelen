package proxy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.ArrayList;
import java.util.List;

public class Proxy {

    static EventLoopGroup mainGroup = new NioEventLoopGroup();
    static int proxyNumber =0;
    static List<Server> serversX = new ArrayList<>();

    public void start(){

        serversX.add(new Server("localhost", 8080));
        serversX.add(new Server("localhost", 8080));
        serversX.add(new Server("localhost", 8080));
        serversX.add(new Server("localhost", 8080));
        serversX.add(new Server("localhost", 8080));


        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(mainGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ClientConnectionInitializer(mainGroup));

            bootstrap.bind(7000).sync().channel().closeFuture().sync();

        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            mainGroup.shutdownGracefully();
        }

    }

    public static void rotateServers(){
        if(proxyNumber==4){
            proxyNumber=0;
        } else proxyNumber++;
    }

}
