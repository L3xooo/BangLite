package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import sk.stuba.fei.uim.oop.utility.Turn;


public class Assignment1 {
    public static void main(String[] args) {
        int numberOfPlayers;
        do{
            numberOfPlayers = KeyboardInput.readInt("Enter number of players (2-4)");
            if (numberOfPlayers < 2 || numberOfPlayers > 4) {
                System.out.println("You entered wrong number of players! Please try again!");
            }
        } while (numberOfPlayers < 2 || numberOfPlayers > 4);


        Game game = new Game(numberOfPlayers);
        game.gameInitialization();
        for (int a = 0; a < game.getPlayers().size(); a++) {
            game.getPlayers().get(a).printPlayerStats();
            game.getPlayers().get(a).printCards();
        }

        Turn turn = new Turn(game.getPlayers().get(0));
        //End of game initialization

        //Start of one Turn and game
        while(true) {
            game.isWin();
            if (game.getIsWin()) {
                System.out.println("Winner is " + game.getWinner());
                break;
            }
            turn.playerTurn(game.getCardsInStack(),game.getPlayers());
        }
    }
}
