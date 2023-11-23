// Er starten på programmet, da den indeholder main(). Her definerer vi spillets attributter, som er af simpel type.
// Vi definerer altså ikke felterne her, men i Field.java.
public class Dicegame {
    static Game game;
    static int NO_OF_PLAYERS = 2;
    static int NO_OF_DICE = 2;
    static int START_MONEY = 20;
    static int MONEY_TO_WIN = 3000;
    static int NO_OF_FIELDS = 11;

    public static void main(String[] args) {
        System.out.print("\nWELCOME TO THE NEW GAME \n\nType 's' to start or 'q' to quit\n");
        game = new Game();
    }

}

// ----------- Mangler -----------
/*
 * 
 * Beskrivelser for felter i Field.initializeFields() i Field.java.
 * 
 *
 * 
 * 
 */
