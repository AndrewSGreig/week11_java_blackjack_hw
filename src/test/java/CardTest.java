import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {

    Card card;

    @Before
    public void setup(){
        card = new Card(Suit.CLUBS, Rank.KING);
    }

    @Test
    public void cardHasRank() {
        assertEquals(Rank.KING, card.getRank());
    }

    @Test
    public void cardHasSuit() {
        assertEquals(Suit.CLUBS, card.getSuit());
    }

    @Test
    public void cardHasValue(){
        assertEquals(10, card.getValue());
    }

    @Test
    public void cardShowsName(){
        assertEquals("KING of CLUBS", card.cardName());
    }
}
