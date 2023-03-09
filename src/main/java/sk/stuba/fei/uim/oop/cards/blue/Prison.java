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
        int index = KeyboardInput.readInt("Enter player index");
        Player targetPlayer = playerOnTurn.getEnemyPlayers().get(index);
        targetPlayer.getBlueCards().add(this);
    }

    @Override
    public boolean blueCardAbility(Player playerOnTurn, List<Card> cardsInStack) {
        boolean result = this.cardProbabilityOfSuccess(0.25);
        return result;
    }
}
