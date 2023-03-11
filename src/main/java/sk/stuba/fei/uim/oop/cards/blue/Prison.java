package sk.stuba.fei.uim.oop.cards.blue;
import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.List;

public class Prison extends Card{
    //Constructors Start
    public Prison(String name,String type) {
        super(name,type);
    }
    //Constructors End

    //Methods Start
    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardDeck,List<Player> players) {
        playerOnTurn.printEnemyPlayers();
        while(true) {
            int index = KeyboardInput.readInt("Enter player index or -1 for exit");
            if (index == -1) {
                break;
            }
            if (index < 0 || index >= playerOnTurn.getEnemyPlayers().size()) {
                System.out.println("Index out of bounds. Please enter correct index!");
                continue;
            }
            Player targetPlayer = playerOnTurn.getEnemyPlayers().get(index);
            if (playerOnTurn.checkCard(playerOnTurn.getBlueCards(),Prison.class) != -1) {
                System.out.println("Player has already barrel in blue cards!");
            } else {
                playerOnTurn.getPlayerCards().remove(this);
                targetPlayer.getBlueCards().add(this);
                break;
            }
        }
    }
    @Override
    public void blueCardAbility(Player playerOnTurn, List<Card> cardDeck,List<Player> players) {
        boolean result = this.cardProbabilityOfSuccess(0.25);
        if (!result) { //neusiel
            System.out.println(playerOnTurn.getName() + " didnt escaped the prison! You lost you turn!");
            playerOnTurn.setIsInPrison(true);
        } else {
            System.out.println(playerOnTurn.getName() + " escaped the prison!");
        }
        cardDeck.add(this);
        playerOnTurn.getBlueCards().remove(this);
    }
    //Methods End
}
