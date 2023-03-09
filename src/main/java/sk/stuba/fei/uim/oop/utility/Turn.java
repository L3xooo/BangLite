package sk.stuba.fei.uim.oop.utility;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Turn {
    public void playerTurn(Player playerOnTurn, List<Card> cardDeck){
        playerOnTurn.printPlayerStats();
        playerOnTurn.drawCard(2,cardDeck);
        playerOnTurn.cardChoose(cardDeck);







        playerOnTurn.checkCardCount(cardDeck);
    }
}
