package Game;

import Server.ConnectionManager;
import Util.NumberUtil;

import java.io.IOException;

public class SinglePlayerGameMode implements Runnable{
    private Integer targetNumber = 0;
    private ConnectionManager connectionManager;

    public SinglePlayerGameMode(ConnectionManager connectionManager){
        this.connectionManager = connectionManager;
    }

    @Override
    public void run() {
        runSinglePlayerMode();
    }

    public Integer getTargetNumber(){
        return targetNumber;
    }

    public void runSinglePlayerMode() throws IOException{
        while(true) {

        }
    }


}
