package Communication;

import Client.ConnectionHandler;
import Entities.NetworkTransferable;
import Enum.MessageType;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CommunicationHandler {
    private final ConnectionHandler connectionHandler;

    public CommunicationHandler(ConnectionHandler connectionHandler){
        this.connectionHandler = connectionHandler;
    }


    public static CommunicationHandler getCommunication(ConnectionHandler handler) {
        return new CommunicationHandler(handler);
    }

    public <T> void sendMessage(NetworkTransferable<T> handler, T value, MessageType messageType) throws IOException {
        String message = handler.toTransferString(value);
        String builder = messageType.toString() + "&" + message;
        connectionHandler.getDataOutputStream().writeUTF(builder);
    }

    public void sendMessage(String message, MessageType messageType) throws IOException {
        String formatted = String.format("%s&%s", messageType.toString(), message);
        connectionHandler.getDataOutputStream().writeUTF(formatted);
    }

    public ServerAnswer getMessage() throws IOException {
        String message = connectionHandler.getDataInputStream().readUTF();
        String[] splitMessage = message.split("&");

        return new ServerAnswer(splitMessage[1], MessageType.valueOf(splitMessage[0]));
    }
}
