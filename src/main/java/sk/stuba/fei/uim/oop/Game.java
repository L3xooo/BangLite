package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;
import sk.stuba.fei.uim.oop.cards.blue.Dynamite;
import sk.stuba.fei.uim.oop.cards.blue.Prison;
import sk.stuba.fei.uim.oop.cards.brown.*;

import java.util.*;

public class Game {
    List<Player> players;
    List<Card> cardDeck;
    boolean isWin;
    Player winner;

    //Constructors Start
    Game(int numberOfPlayers) {
        this.isWin = false;
        this.players = new ArrayList<>();
        for(int a = 0; a < numberOfPlayers; a++) {
            this.players.add(new Player("Player " + a));
        }
        this.cardDeck = new ArrayList<>();
        this.winner = null;
    }
    //Constructors End

    //Getters Start
    public List<Player> getPlayers() {
        return this.players;
    }
    public List<Card> getCardsInStack() {
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
        this.printPlayers();
        this.initDrawCards();
    }
    public void initCards(){
        for(int a = 0; a < 2; a++){
            this.cardDeck.add(new Barrel("Barrel","blue"));
        }
        this.cardDeck.add(new Dynamite("Dynamite","blue"));
        for(int a = 0; a < 3; a++){
            this.cardDeck.add(new Prison("Prison","blue"));
        }
        for(int a = 0; a < 30; a++){
            this.cardDeck.add(new Bang("Bang","brown"));
        }
        for(int a = 0; a < 15; a++){
            this.cardDeck.add(new Missed("Missed","brown"));
        }
        for(int a = 0; a < 8; a++){
            this.cardDeck.add(new Beer("Beer","brown"));
        }
        for(int a = 0; a < 6; a++){
            this.cardDeck.add(new CatBalou("CatBalou","brown"));
        }
        for(int a = 0; a < 2; a++){
            this.cardDeck.add(new Indians("Indians","brown"));
        }
        for(int a = 0; a < 4; a++){
            this.cardDeck.add(new Stagecoach("Stagecoach","brown"));
        }
        Collections.shuffle(this.cardDeck);
    }
    public void initPlayerEnemies() {
        for(int a = 0; a < this.getPlayers().size(); a++) {
            getPlayers().get(a).addEnemies(this.getPlayers());
            System.out.println(Arrays.toString(getPlayers().get(a).enemyPlayers.toArray()));
        }
    }
        //GameInitialization End
    public void initDrawCards(){
        for (Player player : this.players) {
            player.drawCard(4, this.cardDeck);
        }
    }
    public void printPlayers() {
        System.out.println(Arrays.toString(this.players.toArray()));
    }
    public void isWin() {
        if (this.getPlayers().size() == 1) {
            this.isWin = true;
            this.winner = this.players.get(0);
        }
    }
    //Methods End
}
