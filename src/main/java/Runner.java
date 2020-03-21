import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Runner {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Deck deck = new Deck();
        Game game = new Game(deck);

        System.out.println("Welcome to Blackjack!");
//        System.out.println("How many players would you like to play?");
//
//        String input = scanner.next();
//        int players = parseInt(input);
        int players = 2;
        for(int i = 0; i < players; i++){
            String prompt = String.format("Player %s, enter your name: ", (i + 1));
            System.out.println(prompt);
            String playerName = scanner.next();
            Player player = new Player(playerName);
            game.addPlayer(player);
        }

//        System.out.println("How many cards are we playing with?");
//        int noOfCards = parseInt(scanner.next());
        int noOfCards = 2;
        game.start(noOfCards);

        for(Player player: game.getPlayers()){
            String output = String.format("%s has:", player.getName());
            System.out.println(output);
            for(int i = 0; i < player.cardCount(); i ++){
                System.out.println(player.showCard(i));
            }
            System.out.println(String.format("Hand total: %s", player.handTotal()));
            if (player.checkForBlackjack(player.cardCount())) {
                System.out.println(player.getName() + " HAS BLACKJACK AND WINS THE GAME!!!!!");
            }else{
                System.out.println(player.getName() + " HAS NOT GOT BLACKJACK");
            }
            System.out.println(player.getName() + " 'STICK' or 'TWIST'?");
            String playerShot = scanner.next();
            playerShot = playerShot.toUpperCase();
            if(playerShot.equals("TWIST")){
                game.twist(player);
                System.out.println(String.format("Hand total: %s", player.handTotal()));
                System.out.println("draw a new card and add it to players hand");
            }else if (playerShot.equals("STICK")){
                //// Progress To next player
                System.out.println(String.format("%s player has selected STICK", player.getName()));
            }else{
                //INVALID ENTRY
                System.out.println("Invalid entry!");
                System.out.println("You pillock, you can't follow simple instructions!");
                System.out.println("Now you cannot win this game!");
            }


        }


        if(game.checkDraw()){
            System.out.println("It's a draw!");
        } else {
            Player winner = game.checkWinner();
            String winnerName = winner.getName();
            String output = String.format("%s wins!", winnerName);
            System.out.println(output);

        }

    }
}
