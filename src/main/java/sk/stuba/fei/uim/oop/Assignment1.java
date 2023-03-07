package sk.stuba.fei.uim.oop;

public class Assignment1 {
    public static void main(String[] args) {
        Game game = new Game();
        game.initCards();
        game.printPlayers();

        //game.printCards();
        game.drawCards(1);

        for(int a = 0; a < 4; a++) {
            Player player = game.players.get(a);
            player.printCards();
            System.out.println();
        }






    }
}
