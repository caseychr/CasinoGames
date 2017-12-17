import com.CasinoGames.BlackJack.PlayGame;
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

import static com.CasinoGames.Participant.House.house;

public class Main
{
    Card card = new Card();
    CLogger cLogger = new CLogger();
    Functions functions = new Functions();
    Scanner scanner = new Scanner(System.in);
    String decision;
    Double betAmount;



    public static void main(String[] args)
    {
        PlayGame pg = new PlayGame();

        Participant participant = new Participant("Matt", 150);

        /*Participant participant2 = new Participant("Fred", 100);
        Participant participant3 = new Participant("CrapBag", 500);
        ArrayList<Participant> participants = new ArrayList<>();
        participants.add(participant);participants.add(participant2);participants.add(participant3);
        pg.play(participants);*/

        pg.play(participant);


    }
}
