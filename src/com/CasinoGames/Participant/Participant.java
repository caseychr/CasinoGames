package com.CasinoGames.Participant;

import com.CasinoGames.GameLogger.CLogger;

import java.util.ArrayList;
import java.util.logging.Level;

public class Participant
{
    private String fullName;
    protected double chipAmount;
    protected double bet;
    protected ArrayList<String> hand;
    protected int handCount;
    protected CLogger cLogger = new CLogger();

    public Participant(double chipAmount, double bet)
    {
        hand = new ArrayList<>();
    }

    public Participant(double chipAmount)
    {
        this.fullName = fullName;
        this.chipAmount = chipAmount;
        hand = new ArrayList<>();
    }

    public Participant(String fullName, double chipAmount)
    {
        this.fullName = fullName;
        this.chipAmount = chipAmount;
        hand = new ArrayList<>();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getChipAmount() {
        return chipAmount;
    }

    public void setChipAmount(double chipAmount) {
        this.chipAmount = chipAmount;
    }

    public double getBet()
    {
        return bet;
    }

    public void setBet(double bet)
    {
        this.bet = bet;
        cLogger.log(Level.INFO, this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), "Bet made is " + getBet());
    }

    public ArrayList<String> getHand() {
        return hand;
    }

    /*public void setHand(ArrayList<String> hand) {
        this.hand = hand;
    }*/

    public int getHandCount() {
        return handCount;
    }

    public void setHandCount(int handCount) {
        this.handCount = handCount;
    }

    @Override
    public String toString()
    {
        return "Name: "+getFullName()+"\nAmount in Chips: "+getChipAmount();
    }

    public String printHand()
    {
        return "Name: "+getFullName()+" Hand: "+getHand()+"     Count: "+getHandCount()+"\n";
    }

    public String printBet()
    {
        return "Name: "+getFullName()+" Hand: "+getHand()+"     Count: "+getHandCount()+"\nBet: "+getBet()+"\n";
    }

    public String printChipCount()
    {
        return getFullName()+"'s Chip Count: "+getChipAmount();
    }
}
