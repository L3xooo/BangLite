package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.List;

public class CatBalou extends Card {

    //Constructors Start
    public CatBalou(String name, String type) {
        super(name,type);
    }
    //Constructors End

    //Methods Start
    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardDeck,List<Player> pLayers) {
        playerOnTurn.printEnemyPlayers();
        while (true) {
            int index = KeyboardInput.readInt("Enter player index");
            if (index < 0 || index >= playerOnTurn.getEnemyPlayers().size()) {
                System.out.println("Index is out of bounds. Please enter correct index!");
                continue;
            }
            Player targetPlayer = playerOnTurn.getEnemyPlayers().get(index);
            if (targetPlayer.getPlayerCards().size() == 0 && targetPlayer.getBlueCards().size() == 0) {
                System.out.println("This player doesnt have any cards.");
                continue;
            }
            while (true) {
                int brownOrBlue = KeyboardInput.readInt("Enter 0 - cardsInHand, 1 - cardsInFront");
                if (brownOrBlue !=0 && brownOrBlue !=1) {
                    System.out.println("Input must be 0 or 1");
                    continue;
                }
                List<Card> playerCards;
                if (brownOrBlue == 0) {
                    playerCards = targetPlayer.getPlayerCards();
                    if (playerCards.size() == 0) {
                        System.out.println("This player doesnt have cards in hand");
                        continue;
                    }
                } else {
                    playerCards = targetPlayer.getBlueCards();
                    if (playerCards.size() == 0) {
                        System.out.println("This player doesnt have any blue cards on table");
                        continue;
                    }
                }
                playerOnTurn.getPlayerCards().remove(this);
                cardDeck.add(this);
                int cardIndex = this.getRand().nextInt(playerCards.size());
                Card card = playerCards.get(cardIndex);
                System.out.println("Removing " + card.getName() + " from " + targetPlayer.getName());
                cardDeck.add(card);
                playerCards.remove(card);
                break;
            }
            break;
        }
    }
    @Override
    public void blueCardAbility(Player playerOnTurn, List<Card> cardDeck, List<Player> players) {

    }
    //Methods End
}
