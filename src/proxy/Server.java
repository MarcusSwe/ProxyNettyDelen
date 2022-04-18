package proxy;

public class Server {

    private String host;
    private int port;

    public Server(String host, int port){
        this.host = host;
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public String getHost(){
        return host;
    }
}
