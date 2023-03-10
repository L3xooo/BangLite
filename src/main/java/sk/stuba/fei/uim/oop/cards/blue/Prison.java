package sk.stuba.fei.uim.oop.cards.blue;
import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.List;

public class Prison extends Card{
    public Prison(String name,String type) {
        super(name,type);
    }

    @Override
    public void cardAbility(Player playerOnTurn, List<Card> cardsInStack) {
        playerOnTurn.printEnemyPlayers();
        while(true) {
            try{
                int index = KeyboardInput.readInt("Enter player index");
                if (index < 0 || index >= playerOnTurn.getEnemyPlayers().size()) {
                    throw new IndexOutOfBoundsException();
                }
                Player targetPlayer = playerOnTurn.getEnemyPlayers().get(index);
                targetPlayer.getBlueCards().add(this);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds. Please enter correct index!");
            }
        }
    }

    @Override
    public int blueCardAbility(Player playerOnTurn, List<Card> cardsInStack,List<Player> players) {
        boolean result = this.cardProbabilityOfSuccess(0.25);
        if (!result) { //neusiel
            //neusiel ide dalsi hrac
            int activeIndex = players.indexOf(playerOnTurn);
            int newIndex = activeIndex+1;
            if (newIndex > players.size()-1) {
                newIndex = 0;
            }
            return newIndex;
        }
        cardsInStack.add(this);
        playerOnTurn.getBlueCards().remove(this);
        return -1;
    }
}
