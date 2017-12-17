package com.CasinoGames.Participant;

import java.util.ArrayList;

public class House extends Participant
{
    public static House house = new House(150_000);

    public static House getInstance()
    {
        return house;
    }

    public House(double chipAmount)
    {
        super(chipAmount);
        setFullName("House");
        hand = new ArrayList<>();
        handCount = 0;
    }

    @Override
    public String toString()
    {
        return "House\nHand: "+getHand()+", Count: "+getHandCount();
    }
}
