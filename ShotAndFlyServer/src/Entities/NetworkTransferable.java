package Entities;

public interface NetworkTransferable<T> {

    String toTransferString(T value);
    T fromTransferableString(String transferableString);
}
