package Game;

import Communication.CommunicationHandler;
import Entities.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Enum.*;

public class Lobby {

    private List<Player> players = new ArrayList<>();
    private static Lobby instance = null;

    private Lobby() {
    }

    public static Lobby getInstance() {
        if (instance == null) {
            instance = new Lobby();
        }

        return instance;
    }

    public void addPlayer(Player player) throws IOException {
        players.add(player);
        if (players.size() == 2) {
            List<Player> playersToGame = new ArrayList<>(players);

            for(Player p : playersToGame){
                CommunicationHandler.getCommunication(p.getConnectionManager()).sendMessage(
                        "Jogo encontrado",
                        MessageType.MATCH_FOUND
                );
            }

            GameInstance gameInstance = new GameInstance(playersToGame, GameMode.MULTIPLAYER);
            Thread thread = new Thread(gameInstance);
            thread.start();

            players = new ArrayList<>();
        }
    }
}