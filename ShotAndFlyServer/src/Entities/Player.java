package Entities;

import Server.ConnectionManager;

import java.io.IOException;

public class Player {
    private String name = "";
    private ConnectionManager connectionManager;

    public Player(String name, ConnectionManager connectionManager){
        this.name = name;
        this.connectionManager = connectionManager;
    }

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Player){
            Player player = (Player) obj;
            return player.getName().equals(this.name);
        }
        return false;
    }

    public ConnectionManager getConnectionManager(){
        return this.connectionManager;
    }

    public static NetworkTransferable<Player> networkTransferable(){
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
