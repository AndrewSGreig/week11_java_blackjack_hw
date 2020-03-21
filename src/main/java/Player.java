import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<Card>();
    }

    public String getName() {
        return name;
    }

    public int cardCount(){
        return this.hand.size();
    }

    public String showCard(int index){
        return this.hand.get(index).cardName();
    }

    public void takeCard(Card card){
        this.hand.add(card);
    }

    public int handTotal(){
        int total = 0;
        for(Card card : this.hand){
            total += card.getValue();
        }
        return total;
    }

    public Boolean checkForBlackjack(int runningTotal){
        Boolean blackjack = false;
        if (runningTotal == 21){
            blackjack = true;
        }
        return blackjack;
    }




//    public int calulatePlayerRunningTotal(int total){
////can't declare total as empty as above because we want the running total
//// thought about passing the existing total in
//        for(Card card : this.hand){
//            total += card.getValue();
//        }
//        return total;
//    }
}
