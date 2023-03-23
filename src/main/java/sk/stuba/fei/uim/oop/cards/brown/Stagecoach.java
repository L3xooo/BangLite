package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.Card;
import java.util.List;

public class Stagecoach extends Card {
    private static final String CARD_NAME = "Stagecoach";
    private static final String CARD_TYPE = "Brown";
    private static final int CARD_COUNT = 2;
    private List<Card> cardDeck;
    public Stagecoach(List<Card> cardDeck){
        super(CARD_NAME,CARD_TYPE);
        this.cardDeck = cardDeck;
    }
    public int getCardCount() { return CARD_COUNT; }
    public List<Card> getCardDeck() { return this.cardDeck; }
    @Override
    public boolean canPlay(Player playerOnTurn) {
        return true;
    }
    @Override
    public void playCard (Player playerOnTurn) {
        super.playCard(playerOnTurn);
        this.cardAbility(playerOnTurn);
    }
    @Override
    public void cardAbility(Player playerOnTurn){
        playerOnTurn.drawCard(this.getCardCount(),this.getCardDeck());
    }

}
