package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.List;
import java.util.Random;

public class CatBalou extends Card {
    private static final String CARD_NAME = "CatBalou";
    private static final String CARD_TYPE = "Brown";
    private final Random rand;

    //Constructors Start
    public CatBalou() {
        super(CARD_NAME,CARD_TYPE);
        this.rand = new Random();
    }
    //Constructors End

    //Getters Start
    public Random getRand() { return this.rand; }
    //Getters End

    //Methods Start
    @Override
    public boolean canPlay(Player playerOnTurn) {
        for (int a = 0; a < playerOnTurn.getEnemyPlayers().size(); a++) {
            Player player = playerOnTurn.getEnemyPlayers().get(a);
            if (player.getPlayerCards().size() != 0 || player.getBlueCards().size() !=0) {
                return true;
            }
        }
        //System.out.println("You cannot play CatBalou card! Enemy players don't have any cards! Choose another card!" );
        return false;
    }
    public int chooseCard (Player player) {
        int cardIndex;
        while(true) {
            cardIndex = KeyboardInput.readInt("Enter card index");
            if (cardIndex < 0 || cardIndex >= player.getBlueCards().size()) {
                System.out.println("Index is out of range. Please enter correct index!");
            } else {
                break;
            }
        }
        return cardIndex;
    }
    @Override
    public int choosingPlayer(Player player){
        int playerIndex;
        while(true) {
            playerIndex = super.choosingPlayer(player);
            Player targetPlayer = player.getEnemyPlayers().get(playerIndex);
            if (targetPlayer.getPlayerCards().size() == 0 && targetPlayer.getBlueCards().size() == 0) {
                System.out.println("This player doesnt have any cards. Choose another player!");
            } else {
                break;
            }
        }
        return playerIndex;
    }
    @Override
    public void playCard(Player playerOnTurn) {
        playerOnTurn.printEnemyPlayers();
        int playerIndex = choosingPlayer(playerOnTurn);
        super.playCard(playerOnTurn);
        Player targetPlayer = playerOnTurn.getEnemyPlayers().get(playerIndex);
        cardAbility(targetPlayer);
    }

    @Override
    public void cardAbility(Player targetPlayer) {
        while (true) {
            int brownOrBlue = KeyboardInput.readInt("Enter 0 - playerCardsInHand, 1 - playerBlueCardsOnTable");
            if (brownOrBlue !=0 && brownOrBlue !=1) {
                System.out.println("Input must be 0 or 1");
                continue;
            }
            List<Card> playerCards;
            int cardIndex;
            Card card;
            if (brownOrBlue == 0) {
                playerCards = targetPlayer.getPlayerCards();
                if (playerCards.size() == 0) {
                    System.out.println("This player doesnt have cards in hand.");
                    continue;
                } else {
                    cardIndex = this.getRand().nextInt(playerCards.size());
                    card = playerCards.get(cardIndex);
                    targetPlayer.getPlayerCards().remove(cardIndex);
                    targetPlayer.getDiscardCardDeck().add(card);
                }
            } else {
                playerCards = targetPlayer.getBlueCards();
                if (playerCards.size() == 0) {
                    System.out.println("This player doesnt have any blue cards on table.");
                    continue;
                } else {
                    targetPlayer.printBlueCards();
                    cardIndex = chooseCard(targetPlayer);
                    card = playerCards.get(cardIndex);
                    targetPlayer.getBlueCards().remove(cardIndex);
                    targetPlayer.getDiscardCardDeck().add(card);
                }
            }
            System.out.println("Removing " + card.getName() + " from " + targetPlayer.getName());
            break;
        }
    }
    //Methods End
}
