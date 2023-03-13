package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;
import sk.stuba.fei.uim.oop.cards.blue.Dynamite;
import sk.stuba.fei.uim.oop.cards.blue.Prison;
import sk.stuba.fei.uim.oop.cards.brown.*;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import sk.stuba.fei.uim.oop.utility.Turn;

import java.util.*;

public class Game {
    private List<Player> players;
    private List<Card> cardDeck;
    private boolean isWin;
    private Player winner;

    //Constructors Start
    Game() {
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
        for(int a = 0; a < numberOfPlayers; a++) {
            String playerName = KeyboardInput.readString("Enter name for Player " + (a+1));
            this.players.add(new Player(playerName));
        }
        this.cardDeck = new ArrayList<>();
        this.winner = null;

        this.gameInitialization();

        this.startGame();
    }
    //Constructors End

    //Getters Start
    public List<Player> getPlayers() {
        return this.players;
    }
    public List<Card> getCardDeck() {
        return this.cardDeck;
    }
    public boolean getIsWin() { return this.isWin; }
    public Player getWinner() { return this.winner; }
    //Getter End

    //Methods Start
        //GameInitialization Start
    public void gameInitialization () {
        this.initPlayerEnemies();
        this.initCards();
        this.initDrawCards();
    }
    public void initCards(){
        for(int a = 0; a < 2; a++){
            this.cardDeck.add(new Barrel());
        }
        this.cardDeck.add(new Dynamite());
        for(int a = 0; a < 3; a++){
            this.cardDeck.add(new Prison());
        }
        for(int a = 0; a < 30; a++){
            this.cardDeck.add(new Bang());
        }
        for(int a = 0; a < 15; a++){
            this.cardDeck.add(new Missed());
        }
        for(int a = 0; a < 8; a++){
            this.cardDeck.add(new Beer());
        }
        for(int a = 0; a < 6; a++){
            this.cardDeck.add(new CatBalou());
        }
        for(int a = 0; a < 2; a++){
            this.cardDeck.add(new Indians());
        }
        for(int a = 0; a < 4; a++){
            this.cardDeck.add(new Stagecoach());
        }
        Collections.shuffle(this.cardDeck);
    }
    public void initPlayerEnemies() {
        for(int a = 0; a < this.getPlayers().size(); a++) {
            getPlayers().get(a).addEnemies(this.getPlayers());
        }
    }
        //GameInitialization End

    public void startGame(){
        Turn turn = new Turn(this.getPlayers().get(0));
        while(true) {
            this.isWin();
            if (this.getIsWin()) {
                System.out.println("Winner is " + this.getWinner());
                break;
            }
            turn.playerTurn(this.getCardDeck(),this.getPlayers());
        }
    }

    public void initDrawCards(){
        for (Player player : this.players) {
            player.drawCard(4, this.cardDeck);
        }
    }
    public void isWin() {
        if (this.getPlayers().size() == 1) {
            this.isWin = true;
            this.winner = this.players.get(0);
        }
    }
    //Methods End
}
