package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;
import java.util.List;

public class Stagecoach extends Card {
    private static final String CARD_NAME = "Stagecoach";

    //Constructors Start
    public Stagecoach(){
        super(CARD_NAME);
    }
    //Constructors End

    //Methods Start
    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardDeck,List<Player> players){
        System.out.println(playerOnTurn.getName() + " used Stagecoach and draw 2 cards!");
        if (cardDeck.size() < 2) {
            System.out.println("There are no cards left in deck.");
        } else {
            playerOnTurn.drawCard(2,cardDeck);
            playerOnTurn.getPlayerCards().remove(this);
            cardDeck.add(this);
        }
    }
    @Override
    public void blueCardAbility(Player playerOnTurn, List<Card> cardDeck, List<Player> players) {

    }
    //Methods End
}
