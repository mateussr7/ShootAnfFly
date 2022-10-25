package Communication;

import Client.ConnectionHandler;
import Entities.NetworkTransferable;
import Enum.MessageType;

import java.io.IOException;
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
        StringBuilder builder = new StringBuilder();
        builder.append(messageType.toString() + "&" + message);
        connectionHandler.getDataOutputStream().writeUTF(builder.toString());
    }

    public void sendMessage(String message, MessageType messageType) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append(messageType.toString() + "&" + message);
        connectionHandler.getDataOutputStream().writeUTF(builder.toString());
    }

    public ServerAnswer getMessage(NetworkTransferable<?> networkTransferable) throws IOException {
        String message = connectionHandler.getDataInputStream().readUTF();
        return (ServerAnswer) networkTransferable.fromTransferString(message);
    }

    public ServerAnswer getMessage() throws IOException {
        String message = connectionHandler.getDataInputStream().readUTF();
        String[] splitedMessage = message.split("&");

        return new ServerAnswer(splitedMessage[1], MessageType.valueOf(splitedMessage[0]));
    }
}
