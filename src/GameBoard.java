class GameBoard{

    /* Checkliste:
        Hvert felt skal have en atribut
        Get og set metoder? (kravspecifikationen)
        Størrelsen på boardet skal justeres til at håndtere 4 spillere
     */

    private char[][] board;
    private int fields = 24; // Set the actual size of the board
    private int dimension = 7;
    private int players;
    private int currentPlayerTurn = 0;

    public GameBoard() {
        this.players = Dicegame.noOfPlayers;
        this.board = new char[fields][players];
        initializeBoard();
    }

    private void initializeBoard() {
        // Sætter alle felterne tomme
        for (int i = 0; i < fields; i++) {
            for (int j = 0; j < players; j++) {
                board[i][j] = ' ';
            }
        }

        //Player starts here
        for (int j = 0; j < players; j++) {
            board[0][j] = getPlayerSymbol(j);
        }

        //Field attributes

    }

    public String toString(){
        String board = "";

        //Adding top outline with corners
        board += 'X';
        for(int i = 0; i < 3*dimension - 1; i++){
            board += '-';
        }
        board += "X\n";
        
        // Adds first top row
        board += '|';
        for (int i = 0; i < dimension; i++) {
            board += this.board[i][0] + "" + this.board[i][1] + "|";
        }

        // Adds Second top row
        board += "\n|";
        for (int i = 0; i < dimension; i++) {
            board += this.board[i][2] + "" + this.board[i][3] + "|";
        }

        //Adding the middle line by line
        board += "\n|--x--------------x--|";

        board += "\n|" + this.board[23][0] + "" + this.board[23][1] + "|              |" + this.board[7][0] + "" + this.board[7][1] + "|";
        board += "\n|" + this.board[23][2] + "" + this.board[23][3] + "|              |" + this.board[7][2] + "" + this.board[7][3] + "|";
        board += "\n|--|              |--|";

        board += "\n|" + this.board[22][0] + "" + this.board[22][1] + "|              |" + this.board[8][0] + "" + this.board[8][1] + "|";
        board += "\n|" + this.board[22][2] + "" + this.board[22][3] + "|              |" + this.board[8][2] + "" + this.board[8][3] + "|";
        board += "\n|--|              |--|";

        board += "\n|" + this.board[21][0] + "" + this.board[21][1] + "|              |" + this.board[9][0] + "" + this.board[9][1] + "|";
        board += "\n|" + this.board[21][2] + "" + this.board[21][3] + "|              |" + this.board[9][2] + "" + this.board[9][3] + "|";
        board += "\n|--|              |--|";

        board += "\n|" + this.board[20][0] + "" + this.board[20][1] + "|              |" + this.board[10][0] + "" + this.board[10][1] + "|";
        board += "\n|" + this.board[20][2] + "" + this.board[20][3] + "|              |" + this.board[10][2] + "" + this.board[10][3] + "|";
        board += "\n|--|              |--|";

        board += "\n|" + this.board[19][0] + "" + this.board[19][1] + "|              |" + this.board[11][0] + "" + this.board[10][1] + "|";
        board += "\n|" + this.board[19][2] + "" + this.board[19][3] + "|              |" + this.board[11][2] + "" + this.board[10][3] + "|";
        

        board += "\n|--x--------------x--|";

        // Adds first bottom row
        board += "\n|";
        for (int i = 18; i >= 12; i--) {
            board += this.board[i][0] + "" + this.board[i][1] + "|";
        }

        // Adds second bottom row
        board += "\n|";
        for (int i = 18; i >= 12; i--) {
            board += this.board[i][2] + "" + this.board[i][3] + "|";
        }

        //Adding bottom outline with corners
        board += "\nX";
        for(int i = 0; i < 3*dimension - 1; i++){
            board += '-';
        }
        board += "X";
        
        return board;
    }
    
    public void movePlayer(int moves) {
        // Find current player's index
        int currentPlayerIndex = findPlayer();
    
        // Move player
        int nextPlayerIndex = (currentPlayerIndex + moves) % fields;
    
        // Make previous spot empty
        board[currentPlayerIndex][currentPlayerTurn] = ' ';
    
        // Determine the symbol based on the player's turn
        char playerSymbol = getPlayerSymbol(currentPlayerTurn);
    
        // Place player in the new spot with the correct symbol
        board[nextPlayerIndex][currentPlayerTurn] = playerSymbol;
    
        // Update the player turn
        playerTurn();
    }
    
    private int findPlayer() {
        // Find player's current position/index on the board
        char playerSymbol = getPlayerSymbol(currentPlayerTurn);

        for (int i = 0; i < fields; i++) {
            for (int j = 0; j < players; j++) {
                if (board[i][j] == playerSymbol) {
                    return i;
                }
            }
        }
        return -1; // Player not found
    }

    private void playerTurn(){
         // Increment the turn for the current player
         this.currentPlayerTurn++;

         // If the turn exceeds the number of players, reset it to 1 (loop back to the first player)
         if (this.currentPlayerTurn > this.players) {
             this.currentPlayerTurn = 1;
         }
     
    }

    private char getPlayerSymbol(int playerIndex) {
        char[] playerSymbols = {'P', 'Q', 'R', 'S'};

        if (playerIndex < playerSymbols.length) {
            return playerSymbols[playerIndex];
        } else {
            // Handle the case when there are more players than symbols (add more symbols or handle differently)
            throw new IllegalArgumentException("Not enough symbols for players");
        }
    }


}

