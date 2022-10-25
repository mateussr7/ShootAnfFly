package Client;

import Game.GameManager;

import java.io.IOException;
import java.net.Socket;

public class ShotAndFlyClient implements Runnable{

    private final String host;
    private final int port;

    public ShotAndFlyClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try{
            Socket socket = new Socket(host, port);
            ConnectionHandler handler = new ConnectionHandler(socket);
            GameManager manager = new GameManager(handler);
            manager.loginPLayer();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
