package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.Card;
import java.util.List;

public class Stagecoach extends Card {
    private static final String CARD_NAME = "Stagecoach";
    private static final String CARD_TYPE = "Brown";
    private static final int CARD_COUNT = 2;
    private List<Card> cardDeck;
    //Constructors Start
    public Stagecoach(List<Card> cardDeck){
        super(CARD_NAME,CARD_TYPE);
        this.cardDeck = cardDeck;
    }
    //Constructors End

    //Getters Start
    public int getCardCount() { return CARD_COUNT; }
    public List<Card> getCardDeck() { return this.cardDeck; }
    //Getters End

    //Methods Start
    @Override
    public boolean canPlay(Player playerOnTurn) {
        if (this.getCardDeck().size() < this.getCardCount()) {
            //System.out.println("You cannot play Stagecoach card! Choose another card!");
            return false;
        } else {
            return true;
        }
    }

    public void playCard (Player playerOnTurn, List<Card> cardDeck) {
        super.playCard(playerOnTurn,cardDeck);
        this.cardAbility(playerOnTurn);
    }
    @Override
    public void cardAbility(Player playerOnTurn){
        playerOnTurn.drawCard(this.getCardCount(),this.getCardDeck());
    }

    //Methods End
}
