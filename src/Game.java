// Håndterer selve spillets logik

public class Game {
    java.util.Scanner scanner;
    static boolean runTheTest = false;

    Field[] fields;
    GameBoard board;
    Player[] players;
    private int playerTurn;
    Player currentPlayer;

    
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
            Dicegame.noOfPlayers = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            
            if (Dicegame.noOfPlayers >= 2 && Dicegame.noOfPlayers <= 4) {
                break; // Break out of the loop if the number of players is valid
            } else {
                print("Invalid number of players. Please enter a number between 2 and 4.\n");
            }
        }

        players = new Player[Dicegame.noOfPlayers];
        for (int i = 0; i < Dicegame.noOfPlayers; i++) {
            print("Please enter the name for Player " + (i + 1) + ".");
            players[i] = new Player(scanner.nextLine());

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

        showPlayerRoll(); // Vi viser hvad spilleren har slået i konsollen.
        
        board.movePlayer(currentPlayer.getSumOfDice()); //Rykker spilleren 

       print(board.toString());    //Printer boardet

        playerStats();  //Printer spillers penge osv

        nextPlayerTurn();   //Næste spillers tur
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
        for (int i = 0; i < Dicegame.noOfDice; i++) {
            if (i > 0)
                diceValuesString += "\n";
            diceValuesString += "Dice " + (i + 1) + ": " + currentPlayer.getDieValue(i);
        }
        print("\n" + diceValuesString);
        print("Dice Sum: " + currentPlayer.getSumOfDice() + "\n");
    }

    private void playerStats() {
        // Vis spillerens stats
        print(currentPlayer.getPlayerName() + ", you now have " + currentPlayer.wallet().getMoney()
                + " money.");
        // Check om spilleren har vundet
        if (currentPlayer.checkForMoneyToWin())
            playerWon();
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
        if (playerTurn >= Dicegame.noOfPlayers)
            playerTurn = 0;
    }


    private void playerWon() {
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
