package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.List;

public class CatBalou extends Card {

    public CatBalou(String name, String type) {
        super(name,type);
    }

    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardsInStack) {
        System.out.println("CatBalou!");
        playerOnTurn.printEnemyPlayers();
        while (true) {
            try{
                int index = KeyboardInput.readInt("Enter player index");
                if (index < 0 || index >= playerOnTurn.getEnemyPlayers().size()) {
                    throw new IndexOutOfBoundsException("Invalid index.");
                }
                Player targetPlayer = playerOnTurn.getEnemyPlayers().get(index);
                if (targetPlayer.getPlayerCards().size() == 0 || targetPlayer.getBlueCards().size() == 0) {
                    throw new IndexOutOfBoundsException("This player doesnt have any cards.");
                }
                while (true) {
                    try{
                        int brownOrBlue = KeyboardInput.readInt("Enter 0 - cardsInHand, 1 - cardsInFront");
                        if (brownOrBlue !=0 && brownOrBlue !=1) {
                            throw new IllegalArgumentException("Input must be 0 or 1");
                        }
                        List<Card> playerCards;
                        if (brownOrBlue == 0) {
                            playerCards = targetPlayer.getPlayerCards();
                            if (playerCards.size() == 0) {
                                throw new IndexOutOfBoundsException("This player doesnt have cards in hand");
                            }
                        } else {
                            playerCards = targetPlayer.getBlueCards();
                            if (playerCards.size() == 0) {
                                throw new IndexOutOfBoundsException("This player doesnt have any blue cards on table");
                            }
                        }
                        int maxIndex = playerCards.size();
                        int cardIndex = this.getRand().nextInt(maxIndex);
                        Card card = playerCards.get(cardIndex);
                        cardsInStack.add(card);
                        playerCards.remove(card);
                        break;
                    }catch (IllegalArgumentException e) {
                        System.out.println("You entered wrong argument. Please try again!");
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage() + " Please try again!");
                    }
                }
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage() + "Please enter correct index!");
            }
        }


        int brownOrBlue = KeyboardInput.readInt("Enter 0 - cardsInHand, 1 - cardsInFront");
        int cardIndex = KeyboardInput.readInt("Enter card index");
        Card targetCard;
        }
}
