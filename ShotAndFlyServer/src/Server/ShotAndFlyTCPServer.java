package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ShotAndFlyTCPServer implements Runnable{

    private static final int SERVER_PORT = 4000;

    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(SERVER_PORT)){
            while(true){
                Socket socket = serverSocket.accept();
                ConnectionManager manager = new ConnectionManager(socket);

            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
