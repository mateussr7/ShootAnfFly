package Communication;

import Entities.NetworkTransferable;
import Enum.MessageType;

public class ServerAnswer {
    private Object value;
    private MessageType messageType;

    public ServerAnswer(Object value, MessageType messageType){
        this.value = value;
        this.messageType = messageType;
    }

    public Object getValue(){
        return this.value;
    }

    public MessageType getMessageType(){
        return this.messageType;
    }

    public static NetworkTransferable<ServerAnswer> networkTransferable() {
        return new NetworkTransferable<>() {
            @Override
            public String toTransferString(ServerAnswer value) {
                return value.getMessageType() + "&" + value.getValue().toString();
            }

            @Override
            public ServerAnswer fromTransferString(String transferString) {
                String[] parts = transferString.split("&");
                MessageType type = MessageType.valueOf(parts[0]);
                return new ServerAnswer(parts[1], type);
            }
        };
    }
}
