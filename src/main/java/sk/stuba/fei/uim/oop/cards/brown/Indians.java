package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public class Indians extends Card {
    int damage;
    public Indians(String name,String type) {
        super(name,type);
        this.damage = 1;
    }
    public void cardAbility(List<Player> players,Player playerOnTurn){
        for(int a = 0; a < players.size(); a++) {
            if(playerOnTurn == players.get(a)) {
                continue;
            } else {
                players.get(a).decreaseHealth();
            }
        }
    }
}
