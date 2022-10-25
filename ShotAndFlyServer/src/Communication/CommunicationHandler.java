package Communication;

import Server.ConnectionManager;

public class CommunicationHandler {

    private ConnectionManager connectionManager;

    public CommunicationHandler(ConnectionManager manager){
        this.connectionManager = manager;
    }

    public static CommunicationHandler getCommunication(ConnectionManager manager){
        return new CommunicationHandler(manager);
    }
}
