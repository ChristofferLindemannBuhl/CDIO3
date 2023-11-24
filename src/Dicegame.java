// Er starten på programmet, da den indeholder main(). Her definerer vi spillets attributter, som er af simpel type.
// Vi definerer altså ikke felterne her, men i Field.java.
public class Dicegame {
    static Game game;
    static int NO_OF_PLAYERS,
            START_MONEY;
    static final int MAX_NO_OF_PLAYERS = 4,
            NO_OF_DICE = 1,
            NO_OF_FIELDS = 24,
            BOARD_WIDTH = 7,
            NO_OF_CHANCE_CARDS = 10,
            MONEY_FOR_PASSING_START = 2,
            TURNS_IN_JAIL = 2;

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
