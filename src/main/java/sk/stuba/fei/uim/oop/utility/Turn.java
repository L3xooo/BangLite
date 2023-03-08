package sk.stuba.fei.uim.oop.utility;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Turn {
    public void playerTurn(Player playerOnTurn, List<Card> cardDeck){
        System.out.printf("%s is on turn.",playerOnTurn.getName() );
        playerOnTurn.drawCard(2,cardDeck);
        playerOnTurn.cardChoose(cardDeck);


    }
}
