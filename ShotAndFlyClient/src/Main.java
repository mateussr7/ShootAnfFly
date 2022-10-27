import Client.ShotAndFlyClient;

public class Main {
    public static void main(String[] args) {
        ShotAndFlyClient client = new ShotAndFlyClient("localhost", 4000);
        Thread thread = new Thread(client);
        thread.start();
    }
}