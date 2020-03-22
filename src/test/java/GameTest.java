import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    Player player1;
    Player player2;
    Deck deck;
    Game game;
    Card highCard;
    Card lowCard;

    @Before
    public void setup(){
        player1 = new Player("Colin");
        player2 = new Player("Louise");
        deck = new Deck();
        game = new Game(deck);
        game.addPlayer(player1);
        game.addPlayer(player2);

        highCard = new Card(Suit.SPADES, Rank.KING);
        lowCard = new Card(Suit.SPADES, Rank.TWO);
    }


    @Test
    public void gameHasPlayers(){
        assertEquals(2, game.playerCount());
    }

    @Test
    public void gameCanStart(){
        game.start(1);
        assertEquals(1, player1.cardCount());
        assertEquals(1, player2.cardCount());
    }

    @Test
    public void gameCanDealMultipleCards(){
        game.start(20);
        assertEquals(20, player1.cardCount());
        assertEquals(20, player2.cardCount());
    }

    @Test
    public void gameCanCheckDraw(){
        player1.takeCard(highCard);
        player2.takeCard(highCard);
        assertTrue(game.checkDraw());
    }

    @Test
    public void gameCanCheckWinner(){
        player1.takeCard(highCard);
        player2.takeCard(lowCard);
        assertEquals(player1, game.checkWinner());
    }

    @Test
    public void gameCanAllowPlayerToTwist(){
        player1.takeCard(highCard);
        player1.takeCard(highCard);
        game.twist(player1);
        assertEquals(3,player1.cardCount());
    }

    @Test
    public void gameCanAllowForMultipleTwists(){
        player1.takeCard(lowCard);
        player1.takeCard(lowCard);
        game.twist(player1);
        game.twist(player1);
        game.twist(player1);
        game.twist(player1);
    }

    @Test
    public void gamePicksUpOnInvalidEntry(){
        game.invalidEntry(player1, "Bananas");
        assertEquals(0,player1.cardCount());
    }

    @Test
    public void gameInformsWhenPlayerIsBust(){
        player1.takeCard(highCard);
        player1.takeCard(highCard);
        player1.takeCard(highCard);
        player1.takeCard(highCard);

        assertNotEquals("player1", game.checkWinner());
    }

    @Test
    public void testGameShotSelection(){
        player1.takeCard(lowCard);
         game.gameShotSelection(player1, "TWIST");
         assertEquals(2, player1.cardCount());
         player2.takeCard(lowCard);
         game.gameShotSelection(player2,"STICK");
         assertEquals(1,player2.cardCount());
    }

}
