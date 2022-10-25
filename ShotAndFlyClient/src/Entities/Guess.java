package Entities;

public class Guess {
    private Integer value;

    public Guess(Integer value){
        this.value = value;
    }

    public static NetworkTransferable<Guess> networkTransferable() {
        return new NetworkTransferable<>() {
            @Override
            public String toTransferString(Guess value) {
                return value.toString();
            }

            @Override
            public Guess fromTransferString(String transferString) {
                try{
                    return new Guess(Integer.parseInt(transferString));
                }catch (Exception ex){
                    throw new IllegalArgumentException("Forneça valores numéricos");
                }
            }
        };
    }
}
