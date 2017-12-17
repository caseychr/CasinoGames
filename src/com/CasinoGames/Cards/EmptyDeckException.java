package com.CasinoGames.Cards;

public class EmptyDeckException extends Exception
{
    public String getMessage()
    {
        return " EmptyDeckException caught. Refilling and shuffling deck";
    }
}
