package Entities;

public class Player {
    private String name = "";

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public NetworkTransferable<Player> networkTransferable(){
        return new NetworkTransferable<>() {
            @Override
            public String toTransferString(Player value) {
                return value.getName();
            }

            @Override
            public Player fromTransferableString(String transferableString) {
                return new Player(transferableString);
            }
        };
    }

}
