//Create a Card class that holds a suit and value for each Card object. Save the file as Card.java.

public class Card
{
    public enum Suits { Diamonds, Clubs, Hearts, Spades }
    public enum Values
    {
        Ace(1),
        Two(2),
        Three(3),
        Four(4),
        Five(5),
        Six(6),
        Seven(7),
        Eight(8),
        Nine(9),
        Ten(10),
        Jack(11),
        Queen(12),
        King(13);

        public final int Value; // Declare a private final field to store the value

        Values(int Value)
        { // Define a constructor to accept the value
            this.Value = Value;
        }

        public int getRank()
        { // Define a getter method to access the value
            return Value;
        }
    }


    //constructor
    public Card(Suits newSuit, Values newValue)
    {
        this.Suit = newSuit;
        this.Value = newValue;
    }

    public Card() {}

    //fields
    private Suits Suit;
    private Values Value;


    //Getters and Setters
    public void setSuit(Suits suit) { this.Suit = suit; }

    public Suits getSuit() { return Suit; }

    public Values getValue() { return Value; }

    public void setValue(Values value) { this.Value = value; }


    //defining my own toString() method for easier card output
    @Override
    public String toString()
    {
        return Value + " of " + Suit;
    }
}
