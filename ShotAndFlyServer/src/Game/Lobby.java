package Game;

import Entities.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lobby {

    private List<Player> lobby = new ArrayList<>();

    public int getPlayersAmount(){
        return lobby.size();
    }

    public Map<String, Player> getDuoToPlay(){
        Map<String, Player> duo = new HashMap<String, Player>();
        duo.put("PLAYER_ONE", lobby.remove(0));
        duo.put("PLAYER_TWO", lobby.remove(0));
        return duo;
    }

    public void insertPlayerInLobby(Player player){
        lobby.add(player);
    }
}
