package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;
import sk.stuba.fei.uim.oop.cards.blue.Dynamite;
import sk.stuba.fei.uim.oop.cards.blue.Prison;
import sk.stuba.fei.uim.oop.cards.brown.*;

import java.util.*;

public class Game {
    List<Player> players;
    List<Card> cardsInStack;
    List<Card> cardsInGame;
    Random rand;
    boolean isWin;
    Game(int numberOfPlayers) {
        this.isWin = false;
        this.players = new ArrayList<Player>();
        for(int a = 0; a < numberOfPlayers; a++) {
            this.players.add(new Player("Player " + a ));
        }
        this.cardsInStack = new ArrayList<Card>();
        this.rand = new Random();
    }
    public List<Player> getPlayers() {
        return this.players;
    }
    public List<Card> getCardsInStack() {
        return this.cardsInStack;
    }
    public List<Card> getCardsInGame() {
        return this.cardsInGame;
    }

    public void initCards(){
        for(int a = 0; a < 2; a++){
            this.cardsInStack.add(new Barrel("Barrel","blue"));
        }
        this.cardsInStack.add(new Dynamite("Dynamite","blue"));
        for(int a = 0; a < 3; a++){
            this.cardsInStack.add(new Prison("Prison","blue"));
        }
        for(int a = 0; a < 30; a++){
            this.cardsInStack.add(new Bang("Bang","brown"));
        }
        for(int a = 0; a < 15; a++){
            this.cardsInStack.add(new Missed("Missed","brown"));
        }
        for(int a = 0; a < 8; a++){
            this.cardsInStack.add(new Beer("Beer","brown"));
        }
        for(int a = 0; a < 6; a++){
            this.cardsInStack.add(new CatBalou("CatBalou","brown"));
        }
        for(int a = 0; a < 2; a++){
            this.cardsInStack.add(new Indians("Indians","brown"));
        }
        for(int a = 0; a < 4; a++){
            this.cardsInStack.add(new Stagecoach("Stagecoach","brown"));
        }
        Collections.shuffle(this.cardsInStack);
    }
    public void initPlayerEnemies() {
        for(int a = 0; a < this.getPlayers().size(); a++) {
            getPlayers().get(a).addEnemies(this.getPlayers());
            System.out.println(Arrays.toString(getPlayers().get(a).enemyPlayers.toArray()));
        }
    }
    public void drawCards(){
        int numberOfPlayers = this.players.size();
        for(int a = 0; a < numberOfPlayers; a++) {
            this.players.get(a).drawCard(4,this.cardsInStack);
        }
    }
    public void printCards() { //rovnake aj u player
        for(int a = 0; a < this.cardsInStack.size(); a++) {
            System.out.println(this.cardsInStack.get(a).getName());
        }
    }
    public void printPlayers() {
        System.out.println(Arrays.toString(this.players.toArray()));
    }

    public void isWin() {
        if (this.players.size() == 1) {
            this.isWin = true;
        }
    }


}
