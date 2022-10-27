import Server.ShotAndFlyTCPServer;
import Util.NumberUtil;

public class Main {
    public static void main(String[] args) {
        ShotAndFlyTCPServer server = new ShotAndFlyTCPServer();
        Thread thread = new Thread(server);
        thread.start();
    }
}