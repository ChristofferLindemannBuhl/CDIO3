// Håndterer selve spillets logik

public class Game {
    java.util.Scanner scanner;
    static boolean runTheTest = false;

    GameBoard board;
    public static GameBoard board;
    static Player[] players;
    private int playerTurn;
    static Player currentPlayer;


    public Game() {
        scanner = new java.util.Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equals("RunTest1")) {
            new WalletIllegalArgumentExceptionTest1().walletTest();
        } else if (input.equals("RunTest2")) {
            new WalletCalculatorTrueTest2().walletTest();
        } else if (input.equals("Start") || input.equals("start")) {
            if (runTheTest)
                runTest();
            else {
                initializeGame();
            }
        }
    }

    public void initializeGame() {
        // runTest();
        initializePlayers();
        board = new GameBoard();
        startGame();
    }

    private void initializePlayers() {
        while (true) {
            print("Enter the number of players (2-4): ");
            Dicegame.NO_OF_PLAYERS = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            
            if (Dicegame.NO_OF_PLAYERS >= 2 && Dicegame.NO_OF_PLAYERS <= 4) {
                break; // Break out of the loop if the number of players is valid
            } else {
                print("Invalid number of players. Please enter a number between 2 and 4.\n");
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
            print("Please enter the name for Player " + (i + 1) + ".");
            players[i] = new Player(scanner.nextLine(), i);
        }
    }


    private void startGame() {
        takePlayerTurn();
    }

    private void takePlayerTurn() { // Alt hvad der sker på en tur, sker her fra.
        currentPlayer = players[playerTurn];
        // Der bliver holdt styr på, hvis tur det er, i variablen playerTurn.
        print("\n" + currentPlayer.getPlayerName() + "'s turn. Type 'roll' to throw the dice.");

        waitForRollInput(); // Her venter koden til spilleren har givet inputet 'roll'.

        // Der er nu blevet rullet med terninger.

        currentPlayer.move(currentPlayer.getSumOfDice());

        showPlayerRoll(); // Vi viser hvad spilleren har slået i konsollen.
        
        board.updatePlayerPositions(players); // Opdatér board-interface
      
        print(board.toString()); //Printer boardet

        board.runFieldLogic(currentPlayer); // Kør logikken for det felt spilleren landede på

        playerStats(); //Printer spillers penge osv

        if (checkGameEndingConditions()) { // Hvis
            playerWonMessage();
        }

        nextPlayerTurn(); //Næste spillers tur
    }

    // #region Funktioner i turn-logikken - Nok lidt ligegyldigt at kigge særligt
    // meget mere på.
    private void waitForRollInput() {
        String input = scanner.nextLine();
        if (input.toLowerCase().equals("roll"))
            currentPlayer.rollDice();
        else {
            print("Could not find a command for input: '" + input + "'. Type 'roll' to throw the dice.");
            waitForRollInput();
        }
    }

    private void showPlayerRoll() {
        String diceValuesString = new String();
        for (int i = 0; i < Dicegame.NO_OF_DICE; i++) {
            if (i > 0)
                diceValuesString += "\n";
            diceValuesString += "Dice " + (i + 1) + ": " + currentPlayer.getDieValue(i);
        }
        print("\n" + diceValuesString);
        print("Dice Sum: " + currentPlayer.getSumOfDice() + "\n");
    }

    private void playerStats() {
        // Vis spillerens stats
        /*
        print(currentPlayer.getPlayerName() + ", you now have " + currentPlayer.wallet().getMoney() + " money.");
        */
    }

    private boolean checkGameEndingConditions() {
        // Check om spilleren har vundet
        boolean playerWon = false;

        /*
        if (currentPlayer.checkForMoneyToWin())
            playerWon = true;
        */

        return playerWon;
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


    private void playerWonMessage() {
        print(currentPlayer.getPlayerName() + " won! You got the required amount of money to win!");
        EndMessage();
    }

    private void EndMessage() {
        print("--- Game Stats ---");

        for (Player player : players) {
            print(player.getStats());
        }

        print("\nType 'play' to play again. Type 'quit' to quit the game.");

        String input = scanner.nextLine();
        if (input.toLowerCase().equals("play"))
            initializeGame();
        else if (input.toLowerCase().equals("quit"))
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

}
