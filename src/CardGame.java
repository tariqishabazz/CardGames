/*
    Create an abstract CardGame class similar to the one described in this chapter.
*/
import java.util.Random;

public abstract class CardGame
{

    //The class contains a virtual deck of 52 playing cards that uses a Card class
    // that holds a suit and value for each Card object.
    public Card[] cardDeck = new Card [52];

    //The class contains a constructor that
    //  initializes the deck of cards with appropriate values (for example, King of Hearts),
    public CardGame()
    {
        int deckIndex = 0;

        //building card deck

        //outer loop loops through all the suits in the Suits enum
        for(int i = 0; i < Card.Suits.values().length; i++) //will loop 3 times since we have 4 suits
        {
            //inner loop loops through all the values in Values enum
            for(int t = 0; t < Card.Values.values().length; t++) //will loop 13 times since we have 13 values
            {
                //making a new card using the enum values at i and t indexes
                Card newCard = new Card(Card.Suits.values()[i], Card.Values.values()[t]);

                cardDeck[deckIndex] = newCard; //setting the newly made card as the value of the index deckIndex
                deckIndex++; //incrementing deckIndex to prepare for next card
            }
        }
    }


    //It also contains an integer field that holds the number of cards dealt to a player in a particular game.
    private int numberOfCardsDealt;


    //The class also contains two abstract methods: displayDescription(),
    //which displays a brief description of the game in each of the child classes
    abstract String displayDescription();


    //deal(), which deals the appropriate number of Card objects to one player of a game.
    abstract Card[] deal();

    //a shuffle() method that randomly arranges the positions of the Cards in the array.
    void shuffle()
    {
        Random random = new Random();
        for(int z = cardDeck.length - 1; z > 0; z--)
        {
            int randomIndex = random.nextInt((z + 1)); //we do the + 1 so the card at that index can be included in the swap

            //swap cards
            Card temp = cardDeck[z];
            cardDeck[z] = cardDeck[randomIndex];
            cardDeck[randomIndex] = temp;
        }
    }
}

