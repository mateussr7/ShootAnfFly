package Enum;

import Entities.NetworkTransferable;

public enum GameMode {
    SINGLE_PLAYER, MULTIPLAYER, AI;


    public static NetworkTransferable<GameMode> networkTransferable(){
        return new NetworkTransferable<>() {
            @Override
            public String toTransferString(GameMode value) {
                return value.toString();
            }

            @Override
            public GameMode fromTransferableString(String transferString) {
                return GameMode.valueOf(transferString);
            }
        };
    }
}
