package Entities;

public class Player {

    private String userName;

    public Player(String userName){
        this.userName = userName;
    }

    public static NetworkTransferable<Player> networkTransferable() {
        return new NetworkTransferable<>() {
            @Override
            public String toTransferString(Player player) {
                return player.userName;
            }

            @Override
            public Player fromTransferString(String transferString) {
                return new Player(transferString);
            }
        };
    }
}
