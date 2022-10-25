package Entities;

import Enum.GameMode;

import java.util.ArrayList;
import java.util.List;

public class Lobby {

    private GameMode gameMode;
    private List<Player> players;

    public Lobby(GameMode gameMode){
        this.gameMode = gameMode;
        this.players = new ArrayList<>();
    }

    public void insertPlayer(Player player) throws Exception{
        if(gameMode.equals(GameMode.SINGLE_PLAYER) && players.size() == 0)
            players.add(player);
        else if(gameMode.equals(GameMode.MULTIPLAYER) && players.size() < 2)
            players.add(player);

        throw new Exception("Total de players atingido");
    }
}
