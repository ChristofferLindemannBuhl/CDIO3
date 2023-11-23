class GameBoard{

    /* Checkliste:
        Hvert felt skal have en atribut
        Get og set metoder? (kravspecifikationen)
        Størrelsen på boardet skal justeres til at håndtere 4 spillere
     */

    private char[][] board;
    private int fields = 24; // Set the actual size of the board
    private int dimension = 7;

    public GameBoard() {
        this.board = new char[fields][Dicegame.NO_OF_PLAYERS];
        initializeBoard();
    }

    private void initializeBoard() {
        // Sætter alle felterne tomme
        for (int i = 0; i < fields; i++) {
            for (int j = 0; j < Dicegame.NO_OF_PLAYERS; j++) {
                board[i][j] = ' ';
            }
        }
        //Player starts here
        for (int j = 0; j < Dicegame.NO_OF_PLAYERS; j++) {
            board[0][j] = getPlayerSymbol(j);
        }
        //Field attributes

    }

    public String toString(){
        String boardInterfaceString = "";

        switch (Dicegame.NO_OF_PLAYERS) {
            case 2:
                //Adding top outline with corners
                boardInterfaceString += 'X';
                for(int i = 0; i < 3*dimension - 1; i++){
                    boardInterfaceString += '-';
                }
                boardInterfaceString += "X\n";
                
                // Adds first top row
                boardInterfaceString += '|';
                for (int i = 0; i < dimension; i++) {
                    boardInterfaceString += this.board[i][0] + "" + this.board[i][1] + "|";
                }

                // Adds Second top row
                boardInterfaceString += "\n|";
                for (int i = 0; i < dimension; i++) {
                    boardInterfaceString += " " + "" + " " + "|";
                }

                //Adding the middle line by line

                boardInterfaceString += "\n|--x--------------x--|";

                boardInterfaceString += "\n|" + this.board[23][0] + "" + this.board[23][1] + "|              |" + this.board[7][0] + "" + this.board[7][1] + "|";
                boardInterfaceString += "\n|" + " " + "" + " " + "|              |" + " " + "" + " " + "|";
                boardInterfaceString += "\n|--|              |--|";

                boardInterfaceString += "\n|" + this.board[22][0] + "" + this.board[22][1] + "|              |" + this.board[8][0] + "" + this.board[8][1] + "|";
                boardInterfaceString += "\n|" + " " + "" + " " + "|              |" + " " + "" + " " + "|";
                boardInterfaceString += "\n|--|              |--|";

                boardInterfaceString += "\n|" + this.board[21][0] + "" + this.board[21][1] + "|              |" + this.board[9][0] + "" + this.board[9][1] + "|";
                boardInterfaceString += "\n|" + " " + "" + " " + "|              |" + " " + "" + " " + "|";
                boardInterfaceString += "\n|--|              |--|";

                boardInterfaceString += "\n|" + this.board[20][0] + "" + this.board[20][1] + "|              |" + this.board[10][0] + "" + this.board[10][1] + "|";
                boardInterfaceString += "\n|" + " " + "" + " " + "|              |" + " " + "" + " " + "|";
                boardInterfaceString += "\n|--|              |--|";

                boardInterfaceString += "\n|" + this.board[19][0] + "" + this.board[19][1] + "|              |" + this.board[11][0] + "" + this.board[11][1] + "|";
                boardInterfaceString += "\n|" + " " + "" + " " + "|              |" + " " + "" + " " + "|";
                

                boardInterfaceString += "\n|--x--------------x--|";

                // Adds first bottom row
                boardInterfaceString += "\n|";
                for (int i = 18; i >= 12; i--) {
                    boardInterfaceString += this.board[i][0] + "" + this.board[i][1] + "|";
                }

                // Adds second bottom row
                boardInterfaceString += "\n|";
                for (int i = 18; i >= 12; i--) {
                    boardInterfaceString += " " + "" + " " + "|";
                }

                //Adding bottom outline with corners
                boardInterfaceString += "\nX";
                for(int i = 0; i < 3*dimension - 1; i++){
                    boardInterfaceString += '-';
                }
                boardInterfaceString += "X";
                break;
            
            case 3:

                //Adding top outline with corners
                boardInterfaceString += 'X';
                for(int i = 0; i < 3*dimension - 1; i++){
                    boardInterfaceString += '-';
                }
                boardInterfaceString += "X\n";
                
                // Adds first top row
                boardInterfaceString += '|';
                for (int i = 0; i < dimension; i++) {
                    boardInterfaceString += this.board[i][0] + "" + this.board[i][1] + "|";
                }

                // Adds Second top row
                boardInterfaceString += "\n|";
                for (int i = 0; i < dimension; i++) {
                    boardInterfaceString += this.board[i][2] + "" + " " + "|";
                }

                //Adding the middle line by line

                boardInterfaceString += "\n|--x--------------x--|";

                boardInterfaceString += "\n|" + this.board[23][0] + "" + this.board[23][1] + "|              |" + this.board[7][0] + "" + this.board[7][1] + "|";
                boardInterfaceString += "\n|" + this.board[23][2] + "" + " " + "|              |" + this.board[7][2] + "" + " " + "|";
                boardInterfaceString += "\n|--|              |--|";

                boardInterfaceString += "\n|" + this.board[22][0] + "" + this.board[22][1] + "|              |" + this.board[8][0] + "" + this.board[8][1] + "|";
                boardInterfaceString += "\n|" + this.board[22][2] + "" + " " + "|              |" + this.board[8][2] + "" + " " + "|";
                boardInterfaceString += "\n|--|              |--|";

                boardInterfaceString += "\n|" + this.board[21][0] + "" + this.board[21][1] + "|              |" + this.board[9][0] + "" + this.board[9][1] + "|";
                boardInterfaceString += "\n|" + this.board[21][2] + "" + " " + "|              |" + this.board[9][2] + "" + " " + "|";
                boardInterfaceString += "\n|--|              |--|";

                boardInterfaceString += "\n|" + this.board[20][0] + "" + this.board[20][1] + "|              |" + this.board[10][0] + "" + this.board[10][1] + "|";
                boardInterfaceString += "\n|" + this.board[20][2] + "" + " " + "|              |" + this.board[10][2] + "" + " " + "|";
                boardInterfaceString += "\n|--|              |--|";

                boardInterfaceString += "\n|" + this.board[19][0] + "" + this.board[19][1] + "|              |" + this.board[11][0] + "" + this.board[11][1] + "|";
                boardInterfaceString += "\n|" + this.board[19][2] + "" + " " + "|              |" + this.board[11][2] + "" + " " + "|";
                

                boardInterfaceString += "\n|--x--------------x--|";

                // Adds first bottom row
                boardInterfaceString += "\n|";
                for (int i = 18; i >= 12; i--) {
                    boardInterfaceString += this.board[i][0] + "" + this.board[i][1] + "|";
                }

                // Adds second bottom row
                boardInterfaceString += "\n|";
                for (int i = 18; i >= 12; i--) {
                    boardInterfaceString += this.board[i][2] + "" + " " + "|";
                }

                //Adding bottom outline with corners
                boardInterfaceString += "\nX";
                for(int i = 0; i < 3*dimension - 1; i++){
                    boardInterfaceString += '-';
                }
                boardInterfaceString += "X";
                break;

            case 4:

                //Adding top outline with corners
                boardInterfaceString += 'X';
                for(int i = 0; i < 3*dimension - 1; i++){
                    boardInterfaceString += '-';
                }
                boardInterfaceString += "X\n";
                
                // Adds first top row
                boardInterfaceString += '|';
                for (int i = 0; i < dimension; i++) {
                    boardInterfaceString += this.board[i][0] + "" + this.board[i][1] + "|";
                }

                // Adds Second top row
                boardInterfaceString += "\n|";
                for (int i = 0; i < dimension; i++) {
                    boardInterfaceString += this.board[i][2] + "" + this.board[i][3] + "|";
                }

                //Adding the middle line by line

                boardInterfaceString += "\n|--x--------------x--|";

                boardInterfaceString += "\n|" + this.board[23][0] + "" + this.board[23][1] + "|              |" + this.board[7][0] + "" + this.board[7][1] + "|";
                boardInterfaceString += "\n|" + this.board[23][2] + "" + this.board[23][3] + "|              |" + this.board[7][2] + "" + this.board[7][3] + "|";
                boardInterfaceString += "\n|--|              |--|";

                boardInterfaceString += "\n|" + this.board[22][0] + "" + this.board[22][1] + "|              |" + this.board[8][0] + "" + this.board[8][1] + "|";
                boardInterfaceString += "\n|" + this.board[22][2] + "" + this.board[22][3] + "|              |" + this.board[8][2] + "" + this.board[8][3] + "|";
                boardInterfaceString += "\n|--|              |--|";

                boardInterfaceString += "\n|" + this.board[21][0] + "" + this.board[21][1] + "|              |" + this.board[9][0] + "" + this.board[9][1] + "|";
                boardInterfaceString += "\n|" + this.board[21][2] + "" + this.board[21][3] + "|              |" + this.board[9][2] + "" + this.board[9][3] + "|";
                boardInterfaceString += "\n|--|              |--|";

                boardInterfaceString += "\n|" + this.board[20][0] + "" + this.board[20][1] + "|              |" + this.board[10][0] + "" + this.board[10][1] + "|";
                boardInterfaceString += "\n|" + this.board[20][2] + "" + this.board[20][3] + "|              |" + this.board[10][2] + "" + this.board[10][3] + "|";
                boardInterfaceString += "\n|--|              |--|";

                boardInterfaceString += "\n|" + this.board[19][0] + "" + this.board[19][1] + "|              |" + this.board[11][0] + "" + this.board[11][1] + "|";
                boardInterfaceString += "\n|" + this.board[19][2] + "" + this.board[19][3] + "|              |" + this.board[11][2] + "" + this.board[11][3] + "|";
                

                boardInterfaceString += "\n|--x--------------x--|";

                // Adds first bottom row
                boardInterfaceString += "\n|";
                for (int i = 18; i >= 12; i--) {
                    boardInterfaceString += this.board[i][0] + "" + this.board[i][1] + "|";
                }

                // Adds second bottom row
                boardInterfaceString += "\n|";
                for (int i = 18; i >= 12; i--) {
                    boardInterfaceString += this.board[i][2] + "" + this.board[i][3] + "|";
                }

                //Adding bottom outline with corners
                boardInterfaceString += "\nX";
                for(int i = 0; i < 3*dimension - 1; i++){
                    boardInterfaceString += '-';
                }
                boardInterfaceString += "X";
                break;
        
            default:
                boardInterfaceString = "Error";
                break;
        }

        return boardInterfaceString;
    }

    //Currently have a bug where the players can only move 1 turn.
    public void movePlayer(Player player, int amountToMove) {
        // Move player
        int newPlayerPosition = (player.getPosition() + amountToMove) % fields;

        // Make previous spot empty
        board[player.getPosition()][player.getID()] = ' ';
    
        // Determine the symbol based on the player's turn
        char playerSymbol = getPlayerSymbol(player.getID());
    
        // Place player in the new spot with the correct symbol
        board[newPlayerPosition][player.getID()] = playerSymbol;

        player.setPosition(newPlayerPosition);
    }

    private char getPlayerSymbol(int playerIndex) {
        char[] playerSymbols = {'1', '2', '3', '4'};
        return playerSymbols[playerIndex];
    }

}

