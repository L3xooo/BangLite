package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import sk.stuba.fei.uim.oop.utility.Turn;

public class Assignment1 {
    public static void main(String[] args) {
        int numberOfPlayers;
        do{
            numberOfPlayers = KeyboardInput.readInt("Enter number of players");
        } while (numberOfPlayers < 2 || numberOfPlayers > 4);

        Turn turn = new Turn();
        Game game = new Game(numberOfPlayers);
        game.initPlayerEnemies();
        game.initCards();
        game.printPlayers();
        game.drawCards();
        //End of game initialization
        int playerIndex = 0;
        //Start of one Turn and game
        while(true) {
            if (playerIndex >= numberOfPlayers) {
                playerIndex = 0;
            }
            Player playerOnTurn = game.getPlayers().get(playerIndex);
            turn.playerTurn(playerOnTurn,game.getCardsInStack());



            playerIndex++;
        }
    }
}
