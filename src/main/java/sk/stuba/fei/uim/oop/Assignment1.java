package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import sk.stuba.fei.uim.oop.utility.Turn;

public class Assignment1 {
    public static void main(String[] args) {
        int numberOfPlayers;
        do{
            numberOfPlayers = KeyboardInput.readInt("Enter number of players");
        } while (numberOfPlayers < 2 || numberOfPlayers > 4);


        Game game = new Game(numberOfPlayers);
        game.initPlayerEnemies();
        game.initCards();
        game.printPlayers(); //potom prec
        game.drawCards();
        Turn turn = new Turn(game.getPlayers().get(0));
        //End of game initialization

        //Start of one Turn and game
        Player playerOnTurn = game.getPlayers().get(0);
        while(true) {
            if (game.getIsWin()) {
                System.out.println("Winner is " + game.getWinner());
                break;
            }
            turn.playerTurn(game.getCardsInStack(),game.getPlayers());



        }
    }
}
