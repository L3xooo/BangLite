package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;


public class Bang extends BrownCard {
    private static final String CARD_NAME = "Bang";
    private static final int DAMAGE = 1;

    public Bang() {
        super(CARD_NAME);
    }
    public int getDamage() { return DAMAGE; }
    @Override
    public boolean canPlay(Player playerOnTurn) {
        return true;
    }
    @Override
    public void playCard(Player playerOnTurn) {
        playerOnTurn.printEnemyPlayers();
        int playerIndex = choosingPlayer(playerOnTurn);
        Player targetPlayer = playerOnTurn.getEnemyPlayers().get(playerIndex);
        super.playCard(playerOnTurn);
        this.cardAbility(targetPlayer);
    }

    @Override
    public void cardAbility(Player targetPlayer) {
        int cardIndex = targetPlayer.checkBlueCard(Barrel.class);
        if (cardIndex != -1) {
            if (targetPlayer.getBlueCards().get(cardIndex).blueCardAbility(targetPlayer)) {
                return;
            }
        }
        cardIndex = targetPlayer.checkCard(Missed.class);
        if(cardIndex == -1) {
            targetPlayer.decreaseHealth(this.getDamage());
            System.out.println("Player: " + targetPlayer.getName() + " received damage from Bang! Player health dropped to " + targetPlayer.getHealth());
            targetPlayer.checkDeath();
            if (targetPlayer.getDeath()) {
                targetPlayer.playerDied();
            }
        } else {
            targetPlayer.getPlayerCards().get(cardIndex).playCard(targetPlayer);
        }
    }
}
