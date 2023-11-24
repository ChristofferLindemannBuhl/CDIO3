// Håndterer selve spillets logik

public class Game {
    public static java.util.Scanner scanner;
    static boolean runTheTest = false;

    public static GameBoard board;
    static Player[] players;
    private int playerTurn;
    static Player currentPlayer;
    public static boolean gameIsOver = false;

    public Game() {
        scanner = new java.util.Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equals("RunTest1")) {
            new WalletIllegalArgumentExceptionTest1().walletTest();
        } else if (input.equals("RunTest2")) {
            new WalletCalculatorTrueTest2().walletTest();
        } else if (input.equals("s") || input.equals("S")) {
            if (runTheTest)
                runTest();
            else {
                initializeGame();
            }
        }
    }

    public void initializeGame() {
        // runTest();
        gameIsOver = false;
        initializePlayers();
        board = new GameBoard();
        startGame();
    }

    private void initializePlayers() {
        boolean testing = false;

        if (!testing) {
            print("\nEnter the number of players (2-4):\n");
            while (true) {
                String input = Game.scanner.nextLine();
                if (isNumeric(input)) {
                    int noOfPlayers = Integer.parseInt(input);
                    if (noOfPlayers >= 2 && noOfPlayers <= 4) {
                        Dicegame.NO_OF_PLAYERS = Integer.parseInt(input);
                        break;
                    } else {
                        print("Invalid number of players. Please enter a number between 2 and 4.\n");
                    }
                } else {
                    print("Could not find a number. Please enter a number between 2 and 4.\n");
                }
            }

            switch (Dicegame.NO_OF_PLAYERS) {
                case 2:
                    Dicegame.START_MONEY = 20;
                    break;

                case 3:
                    Dicegame.START_MONEY = 18;
                    break;

                case 4:
                    Dicegame.START_MONEY = 16;
                    break;
            }

            players = new Player[Dicegame.NO_OF_PLAYERS];
            for (int i = 0; i < Dicegame.NO_OF_PLAYERS; i++) {
                print("\nPlease enter the name for Player " + (i + 1) + ".");
                players[i] = new Player(scanner.nextLine(), i);
            }
        } else {
            Dicegame.NO_OF_PLAYERS = 2;
            Dicegame.START_MONEY = 20;
            players = new Player[Dicegame.NO_OF_PLAYERS];
            players[0] = new Player("Marlene", 0);
            players[1] = new Player("Katrine", 1);
        }
    }

    private void startGame() {
        board.updatePlayerPositions();
        print(board.toString());
        playerStats();
        takePlayerTurn();
    }

    private void takePlayerTurn() { // Alt hvad der sker på en tur, sker her fra. Rækkefølgen af handlinger i en tur skal styres her fra.
        currentPlayer = players[playerTurn];
        print("\n" + " ---------- " + currentPlayer.getName() + "'s turn" + " ----------");

        if (currentPlayer.isInJail()) {
            // Spilleren er i fængsel
            print(currentPlayer.getName() + " is in jail for " + currentPlayer.getTurnsLeftInJail() + " more turn(s). Skipping player...");
        } else {
            // Der bliver holdt styr på, hvis tur det er, i variablen playerTurn.
            print("Type 'r' to roll the dice.");
            waitForRollInput(); // Her venter koden til spilleren har givet inputet 'roll'.
            // Der er nu blevet rullet med terninger.

            currentPlayer.move(currentPlayer.getSumOfDice());

            board.updatePlayerPositions(); // Opdatér board-interface
            print(Game.board.toString()); // Printer boardet
            print("(You can see what happened above the board.)");

            playerStats(); //Printer spillers penge osv
        }
        if (gameIsOver) { // Hvis
            gameOver();
        } else {
            //Næste spillers tur
            nextPlayerTurn();
        }
    }

    // #region Funktioner i turn-logikken - Nok lidt ligegyldigt at kigge særligt
    // meget mere på.
    private void waitForRollInput() {
        String input = scanner.nextLine();
        if (input.toLowerCase().equals("r"))
            currentPlayer.rollDice();
        else {
            print("Could not find a command for input: '" + input + "'. Type 'r' to roll the dice.");
            waitForRollInput();
        }
    }

    public static void showPlayerRoll() {
        /*String diceValuesString = new String();
        for (int i = 0; i < Dicegame.NO_OF_DICE; i++) {
            if (i > 0) {
                diceValuesString += "\n";
            }
            diceValuesString += "Dice " + (i + 1) + ": " + currentPlayer.getDieValue(i);
        }
        print("\n" + diceValuesString);*/

        print("Roll: " + currentPlayer.getSumOfDice());
    }

    private void playerStats() {
        // Vis spillerens stats
        print("\n\n---------------------- Player Stats ----------------------");
        for (Player player : players) {
            print(player.getStats(gameIsOver));
        }
        print("----------------------------------------------------------");
    }

    private void nextPlayerTurn() {
        incrementPlayerTurn();
        // Næste tur starter
        takePlayerTurn();
    }

    private void incrementPlayerTurn() {
        playerTurn++;
        // Hvis playerTurn er større end antallet af spillere, så gå tilbage til
        // spilleren på plads 0.
        if (playerTurn >= Dicegame.NO_OF_PLAYERS)
            playerTurn = 0;
    }

    public static void setGameIsOver() {
        gameIsOver = true;
    }

    private void gameOver() {
        EndMessage();
    }

    private void EndMessage() {
        print("\n\n******************* GAME OVER *******************");

        playerStats();

        print("\n\nType 'p' to play again. Type 'q' to quit the game.");

        String input = scanner.nextLine();
        if (input.toLowerCase().equals("p"))
            initializeGame();
        else if (input.toLowerCase().equals("q"))
            scanner.close();
        else {
            print("Could not find a command for input: '" + input + "'.");
            EndMessage();
        }
    }

    public static Player getCurrentPlayer(){
        return currentPlayer;
    }

    public static Player[] getPlayers(){
        return players;
    }

    // #endregion

    public void runTest() {
        var test = new Test();
        print(test.testThrow(1000));
        print(test.alike(1000));
    }

    public static void print(String string) {
        System.out.println(string);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int number = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
