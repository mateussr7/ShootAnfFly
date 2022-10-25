package Communication;

import Entities.NetworkTransferable;
import Enum.MessageType;

public class ServerAnswer {
    Object value;
    MessageType messageType;

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
                StringBuilder builder = new StringBuilder();
                builder.append(value.messageType + "&" + value.value);
                return builder.toString();
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
