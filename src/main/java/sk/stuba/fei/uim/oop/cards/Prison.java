package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.player.Player;

public class Prison extends BlueCard{

    private static final String CARD_NAME = "Prison";

    public Prison() {
        super(CARD_NAME);
    }

    @Override
    public boolean canPlay(Player playerOnTurn) {
        for (int a = 0; a < playerOnTurn.getEnemyPlayers().size(); a++){
            Player player = playerOnTurn.getEnemyPlayers().get(a);
            if (player.checkBlueCard(Prison.class) == -1) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void playCard(Player playerOnTurn) {
        playerOnTurn.printEnemyPlayers();
        while(true) {
            int playerIndex = choosingPlayer(playerOnTurn);
            Player targetPlayer = playerOnTurn.getEnemyPlayers().get(playerIndex);
            if (targetPlayer.checkBlueCard(Prison.class) != -1) {
                System.out.println("Player: " + targetPlayer.getName() + " has already prison in blue cards!");
            } else {
                super.playCard(playerOnTurn);
                System.out.println("Player: " + targetPlayer.getName() +" was arrested to the prison!");
                targetPlayer.getBlueCards().add(this);
                break;
            }
        }
    }

    public boolean blueCardAbility(Player playerOnTurn) {
        playerOnTurn.getBlueCards().remove(this);
        playerOnTurn.getDiscardCardDeck().add(this);
        if (this.getRand().nextInt(4) == 0) {
            System.out.printf("Player: %s escaped the prison!\n",playerOnTurn.getName());
            return true;
        } else {
            System.out.printf("Player: %s didn't escaped the prison! You lost your turn!\n",playerOnTurn.getName());
            return false;
        }
    }
}
