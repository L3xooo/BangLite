package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.turn.Turn;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.*;

public class Game {
    private final List<Player> players;
    private List<Card> cardDeck;
    private List<Card> discardCardDeck;
    private boolean isWin;
    private Player winner;

    public Game() {
        System.out.println("--- Welcome to game BANG ---");
        int numberOfPlayers;
        do{
            numberOfPlayers = KeyboardInput.readInt("Enter number of players (2-4)");
            if (numberOfPlayers < 2 || numberOfPlayers > 4) {
                System.out.println("You entered wrong number of players! Please try again!");
            }
        } while (numberOfPlayers < 2 || numberOfPlayers > 4);

        this.isWin = false;
        this.players = new ArrayList<>();
        this.cardDeck = new ArrayList<>();
        this.discardCardDeck = new ArrayList<>();
        this.winner = null;

        playerInitialization(numberOfPlayers);

        System.out.println();

        this.gameInitialization();

        this.startGame();
    }

    private List<Card> getDiscardCardDeck() { return this.discardCardDeck; }
    private List<Player> getPlayers() {
        return this.players;
    }
    private List<Card> getCardDeck() {
        return this.cardDeck;
    }
    private boolean getIsWin() { return this.isWin; }
    private Player getWinner() { return this.winner; }

    private void gameInitialization () {
        this.initPlayerEnemies();
        this.initCards();
        this.initDrawCards();
    }

    private void playerInitialization(int numberOfPlayers) {
        int a = 0;
        do {
            String playerName = KeyboardInput.readString("Enter name for Player " + (a+1));
            if (playerName.isBlank()) {
                System.out.println("You cannot leave name empty, choose another name!");
                continue;
            }
            boolean contain = false;
            for (Player player : this.getPlayers()) {
                if (player.getName().equals(playerName)) {
                    contain = true;
                    System.out.println("Player name is already taken, choose another name!");
                    break;
                }
            }
            if (!contain) {
                this.getPlayers().add(new Player(playerName,this.getDiscardCardDeck()));
                a++;
            }
        } while (a < numberOfPlayers);
    }
    private void initCards(){
        for(int a = 0; a < 2; a++){
            this.getCardDeck().add(new Barrel());
        }
        this.getCardDeck().add(new Dynamite(this.getPlayers()));
        for(int a = 0; a < 3; a++){
            this.getCardDeck().add(new Prison());
        }
        for(int a = 0; a < 30; a++){ //30
            this.getCardDeck().add(new Bang());
        }
        for(int a = 0; a < 15; a++){ //15
            this.getCardDeck().add(new Missed());
        }
        for(int a = 0; a < 8; a++){
            this.getCardDeck().add(new Beer());
        }
        for(int a = 0; a < 6; a++){
            this.getCardDeck().add(new CatBalou());
        }
        for(int a = 0; a < 2; a++){
            this.getCardDeck().add(new Indians());
        }
        for(int a = 0; a < 4; a++){
            this.getCardDeck().add(new Stagecoach(this.getCardDeck()));
        }
        Collections.shuffle(this.getCardDeck());
    }
    private void initPlayerEnemies() {
        for(int a = 0; a < this.getPlayers().size(); a++) {
            this.getPlayers().get(a).addEnemies(this.getPlayers());
        }
    }

    private void startGame(){
        System.out.println("--- GAME START ---");
        Turn turn = new Turn();
        Iterator<Player> it = this.getPlayers().iterator();
        Player next;
        while(true) {
            if (!it.hasNext()) {
                it = this.getPlayers().iterator();
            }
            next = it.next();
            if (next.getDeath()) {
                continue;
            }
            turn.setPlayerOnTurn(next);


            this.isWin(turn);
            if (this.getIsWin()) {
                System.out.println("\n\n\n\n\n--- WINNER IS PLAYER: " + this.getWinner() + " ---\n\n\n\n\n");
                break;
            }

            turn.playerTurn(this.getCardDeck());

        }
    }

    private void initDrawCards(){
        for (Player player : this.getPlayers()) {
            player.drawCard(4, this.getCardDeck());
        }
    }
    public void isWin(Turn turn) {
        int count = 0;
        for (Player player : this.getPlayers()) {
            if (!player.getDeath()) {
                 count++;
            }
        }
        if (count == 1) {
            this.isWin = true;
            this.winner = turn.getPlayerOnTurn();
        }
    }
}
