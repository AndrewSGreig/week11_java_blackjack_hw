import java.util.ArrayList;

public class Game {

    ArrayList<Player> players;
    Deck deck;

    public Game(Deck deck) {
        this.players = new ArrayList<Player>();
        this.deck = deck;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int playerCount(){
        return this.players.size();
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void start(int amountOfCards){
        for(Player player:this.players){
            for (int i = 0; i < amountOfCards; i ++){
                Card card = deck.dealOne();
                player.takeCard(card);
            }
        }
    }

    public boolean checkDraw(){
        boolean drawgame = true;
        int handTotal = this.players.get(0).handTotal();
        for(Player player: this.players){
            if(player.handTotal() != handTotal){
                drawgame = false;
            }
        }
        return drawgame;
    }

    public Player checkWinner(){
        int highest = 0;
        Player winner = null;
        for(Player player:this.players){
            if(player.handTotal() > highest){

                if(player.handTotal() > 21){
                    System.out.println(player.getName() + " is BUST");
                }else {
                    highest = player.handTotal();
                    winner = player;
                }
            }
        }
        return winner;
    }

    public void twist(Player activePlayer){
        Card card = deck.dealOne();
        activePlayer.takeCard(card);
        for (int i = 0; i < activePlayer.cardCount(); i++){
            System.out.println(activePlayer.showCard(i));
        }

    }


    public void gameShotSelection(Player player, String playerShot) {
        if(playerShot.equals("TWIST")){
            this.twist(player);
            System.out.println(String.format("Hand total: %s", player.handTotal()));
            System.out.println("draw a new card and add it to players hand");
        }else if (playerShot.equals("STICK")){
            //// Progress To next player
            System.out.println(String.format("%s player has selected STICK", player.getName()));
        }else{
            //INVALID ENTRY
            this.invalidEntry(player, playerShot);

            System.out.println("You pillock, you can't follow simple instructions!");
            System.out.println("Now you cannot win this game!");
        }

    }

    public void invalidEntry(Player activePlayer, String entry){

        if((!entry.equals("TWIST")) || (!entry.equals("STICK"))) { //this logic is already done within the runner but is needed for test purposes
            System.out.println("Invalid entry! User entered: " + entry);
            activePlayer.clearHand();
        }
    }

}
