//game where user is offered an initial card, and they have to guess
// whether the next card is higher or lower than the current one

import java.util.Scanner;

public class CardGuess extends CardGame
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        //stores the points to be shown afterward
        int points = 0;

        //create game object
        CardGuess newGame = new CardGuess();


        //shuffle deck for game
        newGame.shuffle();

        //displays game description
        System.out.println(newGame.displayDescription());

        for(int i = 0; i < newGame.cardDeck.length - 1; i++ )
        {
            Card card1 = new Card(newGame.cardDeck[i].getSuit(), newGame.cardDeck[i].getValue());
            System.out.println("\nCurrent card: " + card1 + " (" + (i + 1) + ") ");

            Card card2 = new Card(newGame.cardDeck[i + 1].getSuit(), newGame.cardDeck[i + 1].getValue());

            //main prompt
            System.out.print("\nDo you think the next card is higher(h) or lower(l) than the current one -> ");


                //setting guess to all lowercase
                String userGuess = input.nextLine().toLowerCase();

                if((userGuess.equalsIgnoreCase("higher") && (card1.getValue().getRank() < card2.getValue().getRank())) || (userGuess.equalsIgnoreCase("h") && (card1.getValue().getRank() < card2.getValue().getRank())))
                {
                    System.out.println("Correct, you received a point.");
                    points++;
                }
                else if(userGuess.equalsIgnoreCase("lower") && card1.getValue().getRank() > card2.getValue().getRank() || userGuess.equalsIgnoreCase("l") && card1.getValue().getRank() > card2.getValue().getRank())
                {
                    System.out.println("Correct, you received a point.");
                    points++;
                }
                else if ((userGuess.equalsIgnoreCase("lower") && (card1.getValue().getRank() == card2.getValue().getRank())) || (userGuess.equalsIgnoreCase("l") && (card1.getValue().getRank() == card2.getValue().getRank())) || (userGuess.equalsIgnoreCase("h") && (card1.getValue().getRank() == card2.getValue().getRank())) || (userGuess.equalsIgnoreCase("higher") && (card1.getValue().getRank() == card2.getValue().getRank())))
                {
                    System.out.println("\nHahaha this was a trick question, the values are the same.");
                    System.out.println("You didn't lose any points.");
                }
                else
                {
                    System.out.println("Sorry, that is wrong");

                    if(points > 0)
                    {
                        points--;
                    }
                }
        }
        System.out.println("\nYou earned " + points + " points.");
    }

    @Override
    String displayDescription()
    {
        return """
                Welcome to the Game! \
                
                
                Here, you will be given a card, and you must \
                guess if the next card dealt will be higher or lower than the \
                one currently on screen.\s
                For each correct guess, you will get a point. For \
                each wrong guess, you will lose a point.\s
                Your points will be displayed \
                at the end when all cards have been shown.\
                \s
                
                Starting... now!\s""";
    }

    //not being used
    @Override
    Card[] deal()
    {
        return null;
    }
}


