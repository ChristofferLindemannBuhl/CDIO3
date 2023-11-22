class GameBoard{

    /* Checkliste:
        Hvert felt skal have en atribut
        Get og set metoder
        Hvordan skal det interegere med players?
        Størrelsen på boardet skal justeres til at håndtere 4 spillere
     */

    private char[] board;
    private int fields = 24; // Set the actual size of the board
    private int dimension = 7;

    public GameBoard() {
        this.board = new char[fields];
        initializeBoard();
    }

    private void initializeBoard() {
        // Sætter alle felterne tomme
        for (int i = 0; i < fields; i++) {
            board[i] = ' ';
        }

        //Player starts here
        board[0] = 'P';

        //Field attributes

    }

    public String toString(){
        String board = "";

        //Adding top outline with corners
        board += 'X';
        for(int i = 0; i < 2*dimension - 1; i++){
            board += '-';
        }
        board += "X\n";
        
        // Adds top row
        board += '|';
        for (int i = 0; i < dimension; i++) {
            board += this.board[i] + "|";
        }

        //Adding the middle line by line
        board += "\n|-x---------x-|";
        board += "\n|" + this.board[23] + "|         |" + this.board[7] + "|";
        board += "\n|-|         |-|";
        board += "\n|" + this.board[22] + "|         |" + this.board[8] + "|";
        board += "\n|-|         |-|";
        board += "\n|" + this.board[21] + "|         |" + this.board[9] + "|";
        board += "\n|-|         |-|";
        board += "\n|" + this.board[20] + "|         |" + this.board[10] + "|";
        board += "\n|-|         |-|";
        board += "\n|" + this.board[19] + "|         |" + this.board[11] + "|";
        board += "\n|-x---------x-|\n";

        // Adds bottom row
        board += '|';
        for (int i = 18; i >= 12; i--) {
            board += this.board[i] + "|";
        }

        //Adding bottom outline with corners
        board += "\nX";
        for(int i = 0; i < 2*dimension - 1; i++){
            board += '-';
        }
        board += "X";
        
        return board;
    }
    
    public void movePlayer(int moves) {
        // Finds player
        int currentPlayerIndex = findPlayer();
    
        // Move player
        int nextPlayerIndex = (currentPlayerIndex + moves) % fields;
    
        // Make previous spot empty
        board[currentPlayerIndex] = ' ';
        // Place player in the new spot
        board[nextPlayerIndex] = 'P';
    }
    
    private int findPlayer() {
        // Find player's current position/index on the board
        for (int i = 0; i < fields; i++) {
            if (board[i] == 'P') {
                return i;
            }
        }
        return -1; // Player not found
    }




    public static void main(String[] args) {
        
        var board = new GameBoard();

        System.out.println(board.toString());
        board.movePlayer(3);
        System.out.println(board.toString());
    }
}

