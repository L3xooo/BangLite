package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Dynamite extends Card {
    private static final String CARD_NAME = "Dynamite";
    private static final String CARD_TYPE = "Blue";
    private static final int DAMAGE = 3;
    private List<Card> cardDeck;
    private List<Player> players;
    //Constructor Start
    public Dynamite(List<Player> players,List<Card> cardDeck) {
        super(CARD_NAME,CARD_TYPE);
        this.players = players;
        this.cardDeck = cardDeck;
    }
    //Constructor End

    //Getters Start
    public int getDamage() { return DAMAGE; }
    public List<Card> getCardDeck() {
        return this.cardDeck;
    }
    public List<Player> getPlayers() {
        return this.players;
    }
    //Getters End

    //Methods Start
    @Override
    public boolean canPlay(Player playerOnTurn) {
        return playerOnTurn.checkCard(playerOnTurn.getBlueCards(), Dynamite.class) == -1;
    }
    @Override
    public void playCard(Player playerOnTurn, List<Card> cardDeck) {
        super.playCard(playerOnTurn,cardDeck);
        playerOnTurn.getBlueCards().add(this);
    }
    @Override
    public boolean blueCardAbility(Player playerOnTurn) {
        if (this.getRand().nextInt(8) == 0) {
            System.out.println("Dynamite didn't exploded!");
            int activeIndex = this.getPlayers().indexOf(playerOnTurn);
            int newIndex = activeIndex-1;
            if(newIndex < 0){
                newIndex = this.getPlayers().size()-1;
            }
            System.out.println("Dynamite was added to " + this.getPlayers().get(newIndex).getName() + " blue cards!");
            playerOnTurn.getBlueCards().remove(this);
            this.getPlayers().get(newIndex).getBlueCards().add(this);
            return true;
        } else {
            playerOnTurn.decreaseHealth(this.getDamage());
            System.out.println("Dynamite exploded!");
            System.out.println("Player: " + playerOnTurn.getName() + " received damage from Dynamite! " +
                    "Player health dropped to " + playerOnTurn.getHealth());
            this.getCardDeck().add(this);
            playerOnTurn.getBlueCards().remove(this);
            playerOnTurn.checkDeath();
            return !playerOnTurn.getDeath();
        }
    }
    //Methods End
}
