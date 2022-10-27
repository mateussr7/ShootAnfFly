package Communication;

import Entities.ClientAnswer;
import Entities.NetworkTransferable;
import Server.ConnectionManager;

import Enum.*;

import java.io.DataInputStream;
import java.io.IOException;

public class CommunicationHandler {

    private ConnectionManager connectionManager;

    public CommunicationHandler(ConnectionManager manager){
        this.connectionManager = manager;
    }

    public static CommunicationHandler getCommunication(ConnectionManager manager){
        return new CommunicationHandler(manager);
    }

    public <T> void sendMessage(NetworkTransferable<T> handler, T value, MessageType messageType) throws IOException {
        String message = handler.toTransferString(value);
        String concatenatedString = messageType.toString() + "&" + message;
        connectionManager.getDataOutputStream().writeUTF(concatenatedString);
    }

    public void sendMessage(String message, MessageType messageType) throws IOException {
        String concatenatedString = messageType.toString() + "&" + message;
        connectionManager.getDataOutputStream().writeUTF(concatenatedString);
    }

    public ClientAnswer getMessage() throws IOException {
        String message = connectionManager.getDataInputStream().readUTF();
        String[] parts = message.split("&");
        return new ClientAnswer(parts[1], MessageType.valueOf(parts[0]));
    }

}
