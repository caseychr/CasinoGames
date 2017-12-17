package com.CasinoGames.BlackJack;

import com.CasinoGames.Cards.Card;
import com.CasinoGames.Cards.EmptyDeckException;
import com.CasinoGames.GameFunctions.Functions;
import com.CasinoGames.GameLogger.CLogger;
import com.CasinoGames.Participant.House;
import com.CasinoGames.Participant.Participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;

import static com.CasinoGames.Participant.House.house;

public class PlayGame
{
    public Card card = new Card();
    CLogger cLogger = new CLogger();
    Functions functions = new Functions();
    Scanner scanner = new Scanner(System.in);
    String decision;
    Double betAmount;

    public void initializeHand(Participant participant)
    {
        card.pullCard(participant);
        card.pullCard(participant);
        functions.cardCount(participant);
        System.out.println(participant.printBet());
        cLogger.log(Level.INFO, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), participant.getFullName()+"'s hand has been initialized");
    }

    public void participantPlayHand(Participant participant)
    {
        do {
            try {
                System.out.print("What would you like to do "+participant.getFullName()+"? ");
                decision = scanner.next();
                System.out.println(" ");

                if (decision.equalsIgnoreCase("hit") || decision.equalsIgnoreCase("h")) {
                    card.pullCard(participant);
                    functions.cardCount(participant);
                    System.out.println(participant.printBet());
                    if (functions.didBust(participant.getHandCount()) == true) {
                        System.out.println("Hand: " + participant.getHand() + "\nCount: " + participant.getHandCount());
                        System.out.println(participant.getFullName() + " BUSTED!");
                    }
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }
        }
        while (functions.didBust(participant.getHandCount()) == false && (!decision.equalsIgnoreCase("s") &&
                !decision.equalsIgnoreCase("stay")));
    }

    public void housePlayHand(House house, Participant participant)
    {
        while(participant.getHandCount() > house.getHandCount() && functions.didBust(participant.getHandCount())==false)
        {
            card.pullCard(house);
            functions.cardCount(house);
            System.out.println(house.toString());
        }
    }

    public void play(Participant participant)
    {
        System.out.println("Welcome to the table " + participant.getFullName() + "!");
        System.out.println(" ");
        System.out.print("Would you like to play BlackJack? ");
        decision = scanner.next();
        System.out.println(" ");

        if(functions.keepPlaying(decision)==false)
        {
            System.out.println("OK. GoodLuck to you "+participant.getFullName()+"!");
            System.exit(0);
        }

        card.arrayToAL();
        Collections.shuffle(card.deckAL);
        System.out.println(card.deckAL);

        do
        {
            try
            {
                functions.checkIfValidBet(participant, scanner);
            } catch (InputMismatchException e)
            {
                e.printStackTrace();
            }

            /*Participant initializes their hand*/
            initializeHand(participant);

            /*House plays their hand*/
            participantPlayHand(participant);

            /*House initializes their hand*/
            initializeHand(house);

            /*House plays their hand*/
            housePlayHand(house, participant);

            /*Figure out who wins*/
            functions.determineWinner(participant, house);

            /*Clear all hands for next round*/
            functions.clearHands(participant);
            functions.clearHands(house);

            if(participant.getChipAmount()<=0)
            {
                System.out.println(participant.getFullName()+" has run out of money. Good luck to you.");
                System.exit(0);
            }

            /*Refill and shuffle deck if less than 4 cards left*/
            if(card.deckAL.size()<4)
            {
                card.deckAL.clear();
                card.arrayToAL();
                Collections.shuffle(card.deckAL);
            }

            System.out.println(participant.printChipCount());
            System.out.print("Would you like to keep playing? ");
            decision = scanner.next();
            System.out.println(" ");

        }while(functions.keepPlaying(decision)==true );

        System.out.println("OK. GoodLuck to you "+participant.getFullName()+"!");
    }
}
