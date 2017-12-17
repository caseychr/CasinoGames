package com.CasinoGames.GameFunctions;

import com.CasinoGames.Cards.Card;
import com.CasinoGames.GameLogger.CLogger;
import com.CasinoGames.Participant.House;
import com.CasinoGames.Participant.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class Functions
{
    //public Card card = new Card();
    //Functions functions = new Functions();
    public CLogger cLogger = new CLogger();
    private String decision = " ";

    public void clearHands(Participant participant)
    {
        participant.getHand().clear();
        participant.setHandCount(0);
        cLogger.log(Level.INFO, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "All hands and counts have been cleared");
    }

    public void cardCount(Participant participant)
    {
        int count = 0;
        for(String card : participant.getHand())
        {
            if (card.substring(0, 1).equalsIgnoreCase("J") || card.substring(0, 1).equalsIgnoreCase("Q")
                    || card.substring(0, 1).equalsIgnoreCase("K") || card.substring(0, 1).equalsIgnoreCase("1"))
                count += 10;
            else if (card.substring(0, 1).equalsIgnoreCase("A")) {
                if (count <= 10)
                    count += 11;
                else
                    count += 1;
            } else
                count += Integer.parseInt(card.substring(0, 1));
        }
        participant.setHandCount(count);
    }

    /* Find out if participant/house busted or not */
    public boolean didBust(int count)
    {
        boolean busted;
        if(count>21)
            busted = true;
        else
            busted = false;

        //cLogger.log(Level.INFO, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), Object.class.getSimpleName()+" busted = "+busted);
        return busted;
    }

    /*Decide who won the game, award them chips, and record in log*/
    public void determineWinner(Participant participant, House house)
    {
        if((participant.getHandCount()> house.getHandCount() && didBust(participant.getHandCount())==false) || (didBust(participant.getHandCount())==false && didBust(house.getHandCount())==true))
        {
            System.out.println(participant.getFullName()+" Wins!");
            participant.setChipAmount(participant.getChipAmount() + participant.getBet());
            cLogger.log(Level.INFO, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), participant.getFullName()+" Wins! Gambler: "+
                    participant.getChipAmount()+" House: "+house.getChipAmount());
        }
        else if((house.getHandCount() > participant.getHandCount() && didBust(house.getHandCount())==false) || (didBust(participant.getHandCount())==true && didBust(house.getHandCount())==false))
        {
            System.out.println(participant.getFullName()+" Lost! House wins...");
            participant.setChipAmount(participant.getChipAmount() - participant.getBet());
            cLogger.log(Level.INFO, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "House Wins! Gambler: "+participant.getChipAmount()+
                    " House: "+house.getChipAmount());
        }
        else
        {
            System.out.println("Draw.");
            cLogger.log(Level.INFO, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "Gambler: "+participant.getChipAmount()+" House: "+
                    house.getChipAmount());
        }
    }

    /*Ask the user if they would like to keep playing or leave the table*/
    public boolean keepPlaying(String decision)
    {
        boolean keepPlaying = false;
        if(decision.equalsIgnoreCase("leave") || decision.equalsIgnoreCase("no") || decision.equalsIgnoreCase("n"))
        {
            keepPlaying = false;
        }
        else
            keepPlaying = true;

        cLogger.log(Level.INFO, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "decision = "+decision+" keepPlaying = "+keepPlaying);
        return keepPlaying;
    }

    public void checkIfValidBet(Participant participant, Scanner scanner)
    {
        System.out.println("OK! How much would you like to bet "+participant.getFullName()+"?\n(You have " + participant.getChipAmount() + " in chips)");

        double amount = scanner.nextDouble();
        if(amount>participant.getChipAmount())
        {
            System.out.println(participant.getFullName()+" tried to bet more than they had. Please bet a valid amount or leave the table.");
            decision = scanner.next();
            if(decision.equalsIgnoreCase("leave"))
            {
                System.out.println("Goodluck to you "+participant.getFullName()+"!");
                System.exit(0);
            }
            else if(Double.parseDouble(decision)>participant.getChipAmount())
            {
                System.out.println("Still trying to bet more than you have! Leave the table!");
                System.exit(0);
            }
            else
            {
                participant.setBet(Double.parseDouble(decision));
            }
        }
        else
        {
            participant.setBet(amount);
        }
        System.out.println("");
    }


}
