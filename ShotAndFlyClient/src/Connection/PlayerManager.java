package Connection;

import Entities.Player;

import java.util.Scanner;

public class PlayerManager {

    public static Player getPlayer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Este é o jogo Tiro e Mosca, quem é o desafiante?");
        String userName = scanner.nextLine();
        System.out.println("Desejamos boa sote, " + userName + ". Vamos nessa!");
        return new Player(userName);
    }
}
