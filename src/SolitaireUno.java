
import java.util.Scanner;

public class SolitaireUno extends CardGame
{

    public static void main(String[] args)
    {
        SolitaireUno newGame = new SolitaireUno();
        Scanner input = new Scanner(System.in);

        System.out.println(newGame.displayDescription());

        newGame.shuffle(); // shuffle once

        // Deal cards to player and computer
        Card[] playersCards = newGame.dealPlayer();
        Card[] computersCards = newGame.dealComputer();

        //to store new deck each round
        Card[] newDeck = newGame.getRemainingDeck();

        System.out.println("\nHere are your cards:");
        int playerIndex = 0;

        for (Card card : playersCards)
        {
            System.out.println(playerIndex + 1 + ") " + card);
            playerIndex++;
        }

        Card currentCard = newDeck[0]; // first undealt card
        System.out.println("\n       Starting card is: " + currentCard);

        //main gameplay
        while(cardsRemaining(computersCards) > 0 && cardsRemaining(playersCards) > 0)
        {
            boolean playerPickedUp = false;
            boolean computerPickedUp = false;

            //prompt for player
            System.out.print("""
                    
                    Which card would you like to put down? \
                    
                    (1 - highest #, 0 to pick up, 99 to pass ONLY if the deck is empty) ->\s""");
            int cardChoice = Integer.parseInt(input.nextLine());


            boolean validPlay = false;
            while(!validPlay)
            {
                if (cardChoice > playersCards.length && cardChoice != 99 || cardChoice < 0)
                {
                    System.out.print("""
                            
                            You entered a value outside your card range, please enter a\
                            \s
                            number between 1 and the amount of cards you have ->\s""");

                    cardChoice = Integer.parseInt(input.nextLine());
                }

                else
                {
                    validPlay = true;
                }
            }

            if (cardChoice == 0 && !isDeckEmpty(newDeck)) {
                System.out.println("\nYou didn't play a card and picked up");
                playersCards = addCard(playersCards, newDeck);

                newDeck = updateDeck(newDeck);
                playerPickedUp = true;

            }

            else if (cardChoice == 0 && isDeckEmpty(newDeck)) {
                System.out.println("\nDeck is exhausted");
                System.out.println("No more cards left to draw!");

            }

            else if (cardChoice == 99 && isDeckEmpty(newDeck)) {
                System.out.println("\nPlayer passed");

            }

            else {
                System.out.println("\n       Card currently visible: " + playersCards[cardChoice - 1]);
                currentCard = playersCards[cardChoice - 1];

                playersCards = removeCard(playersCards, cardChoice - 1);
            }


            //computer playing
            boolean computerPlayed = false;
            for (int i = 0; i < computersCards.length; i++)
            {
                if (computersCards[i].getValue().getRank() < currentCard.getValue().getRank() &&
                        computersCards[i].getValue().getRank() == currentCard.getValue().getRank() - 1)
                {
                    System.out.println("\nComputer played: " + computersCards[i]);

                    currentCard = computersCards[i];
                    System.out.println("\n       Card currently visible: " + computersCards[i]);

                    computersCards = removeCard(computersCards, i);

                    computerPlayed = true;
                    break;
                }

                //computer can play to start the order again even if we reached low-est card (Ace)
                if(currentCard.getValue().getRank() == 1)
                {
                    //allows computer to play a king
                    if(computersCards[i].getValue().getRank() == 13)
                    {
                        System.out.println("\nComputer played: " + computersCards[i]);

                        currentCard = computersCards[i];

                        System.out.println("\n       Card currently visible: " + computersCards[i]);
                        computersCards = removeCard(computersCards, i);

                        computerPlayed = true;
                        break;
                    }
                }
            }
            if (!computerPlayed && !isDeckEmpty(newDeck))
            {
                System.out.println("\nComputer couldn't play a card and picked up.");
                computersCards = addCard(computersCards, newDeck);

                newDeck = updateDeck(newDeck);
                computerPickedUp = true;
            }

            else if (!computerPlayed && isDeckEmpty(newDeck))
            {
                System.out.println("\nComputer passed");
            }

            //show hand each round
            System.out.println("\nYour Deck: ");
            int playerIndex2 = 0;

            for (Card card : playersCards)
            {
                System.out.println(playerIndex2 + 1 + ") " + card);
                playerIndex2++;
            }

            if(computerPickedUp && playerPickedUp)
            {
                System.out.println("\n       Card currently visible: " + currentCard);
            }
        }

        if(computersCards.length == 0)
        {
            System.out.println("\n\nYou Lose! You've been bested by the machine :(");
        }
        else if(playersCards.length == 0)
        {
            System.out.println("\n\nYou Win! You beat the computer! Congrats! :)");
        }
    }






    //very useful methods
    @Override
    String displayDescription()
    {
        return """
                
                SolitaireUno:\s
                A simplified, turn-based version of \
                Uno\s
                with ascending/descending rules. Players will be dealt 10 cards.\s
                They \
                must then place a card in descending order based on\s
                the card currently visible.\
                 Whoever plays all their cards wins!
                """;
    }

    // Overridden deal method to follow abstract class contract (not used directly)
    @Override
    Card[] deal()
    {
        // Default deal returns 10 cards from top of deck
        Card[] dealt = new Card[10];

        for (int i = 0; i < 10; i++)
        {
            dealt[i] = cardDeck[i];
            cardDeck[i] = null;
        }
        return dealt;
    }

    // Custom method to deal to the player
    public Card[] dealPlayer()
    {
        return deal(); // just use the overridden method
    }

    // Custom method to deal to the computer
    public Card[] dealComputer()
    {
        Card[] dealt = new Card[10];

        for (int i = 10; i < 20; i++)
        {
            dealt[i - 10] = cardDeck[i];
            cardDeck[i] = null;
        }
        return dealt;
    }

    public static int cardsRemaining(Card[] hand)
    {
        int count = 0;

        for (Card card : hand)
        {
            if (card != null) { count++; }
        }
        return count;
    }

    public static Card[] removeCard(Card[] hand, int removeIndex)
    {
        Card[] newHand = new Card[hand.length - 1];
        int newIndex = 0;

        for (int i = 0; i < hand.length; i++)
        {
            if (i != removeIndex) { newHand[newIndex++] = hand[i]; }
        }

        return newHand;
    }

    //takes a card from the remaining deck and adds to current hand
    public static Card[] addCard(Card[] hand, Card[] deck)
    {
        Card[] newHand = new Card[hand.length + 1];

        for(int i = 0; i < hand.length; i++)
        {
            newHand[i] = hand[i];
        }

        //add card from deck
        newHand[newHand.length - 1] = deck[0];

        return newHand;
    }

    public static Card[] updateDeck(Card[] deck)
    {
        Card[] newDeck = new Card[deck.length - 1];

        for(int i = 0; i < newDeck.length; i++)
        {
            newDeck[i] = deck[i + 1];
        }

        return newDeck;
    }

    public static boolean isDeckEmpty(Card[] deck)
    {
        for(Card card: deck)
        {
            if(card != null) { return false; }
        }

        return true;
    }

    // Remove nulls from deck (cards that were dealt)
    public Card[] getRemainingDeck()
    {
        int count = 0;

        for (Card card : cardDeck) { if (card != null) count++; }

        Card[] updated = new Card[count];
        int index = 0;

        for (Card card : cardDeck)
        {
            if (card != null) { updated[index++] = card; }
        }
        return updated;
    }
}
