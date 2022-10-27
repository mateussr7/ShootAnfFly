package Entities;

import Enum.MessageType;

public class ClientAnswer {
    private Object value;
    private MessageType messageType;

    public ClientAnswer(Object value, MessageType messageType){
        this.value = value;
        this.messageType = messageType;
    }

    public Object getValue(){
        return this.value;
    }

    public MessageType getMessageType(){
        return this.messageType;
    }

    public static NetworkTransferable<ClientAnswer> networkTransferable(){
        return new NetworkTransferable<>() {
            @Override
            public String toTransferString(ClientAnswer value) {
                return value.getMessageType().toString() + "&" + value.getValue();
            }

            @Override
            public ClientAnswer fromTransferableString(String transferableString) {
                System.out.println("mensagem: " + transferableString);
                String[] parts = transferableString.split("&");
                return new ClientAnswer(parts[1], MessageType.valueOf(parts[0]));
            }
        };
    }
}
