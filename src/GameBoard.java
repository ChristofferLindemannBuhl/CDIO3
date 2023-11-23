import java.util.ArrayList;
import java.util.List;

class GameBoard{

    /* Checkliste:
        Hvert felt skal have en atribut
        Get og set metoder? (kravspecifikationen)
        Størrelsen på boardet skal justeres til at håndtere 4 spillere
     */

    private char[][] board;
    private List<Field> fieldList = new ArrayList<>();

    public GameBoard() {
        this.board = new char[Dicegame.NO_OF_FIELDS][Dicegame.NO_OF_PLAYERS];
        initializeBoard();
    }

    private void initializeBoard() {
        // Sætter alle felterne tomme
        for (int i = 0; i < Dicegame.NO_OF_FIELDS; i++) {
            for (int j = 0; j < Dicegame.NO_OF_PLAYERS; j++) {
                board[i][j] = ' ';
            }
        }
        //Player starts here
        for (int j = 0; j < Dicegame.NO_OF_PLAYERS; j++) {
            board[0][j] = getPlayerSymbol(j);
        }
        //Field attributes
        int spaceIndex = 0;
        fieldList.add(new StartField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Burgerbaren", 0, 0, 0));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Pizzeriaet", 0, 0, 0));
        spaceIndex++;
        fieldList.add(new ChanceField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Slikbutikken", 0, 0, 0));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Iskiosken", 0, 0, 0));
        spaceIndex++;
        fieldList.add(new JailField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Museet", 0, 0, 0));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Biblioteket", 0, 0, 0));
        spaceIndex++;
        fieldList.add(new ChanceField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Skaterparken", 0, 0, 0));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Swimmingpoolen", 0, 0, 0));
        spaceIndex++;
        fieldList.add(new ParkingField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Spillehallen", 0, 0, 0));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Biografen", 0, 0, 0));
        spaceIndex++;
        fieldList.add(new ChanceField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Legetøjsbutikken", 0, 0, 0));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Dyrehandlen", 0, 0, 0));
        spaceIndex++;
        fieldList.add(new GoToJailField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Bowlinghallen", 0, 0, 0));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Zoologisk Have", 0, 0, 0));
        spaceIndex++;
        fieldList.add(new ChanceField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Vandlandet", 0, 0, 0));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Strandpromenaden", 0, 0, 0));
    }

    public String toString(){
        String boardInterfaceString = "";

        switch (Dicegame.NO_OF_PLAYERS) {
            case 2:
                //Adding top outline with corners
                boardInterfaceString += 'X';
                for(int i = 0; i < 3 * Dicegame.BOARD_WIDTH - 1; i++){
                    boardInterfaceString += '-';
                }
                boardInterfaceString += "X\n";
                
                // Adds first top row
                boardInterfaceString += '|';
                for (int i = 0; i < Dicegame.BOARD_WIDTH; i++) {
                    boardInterfaceString += this.board[i][0] + "" + this.board[i][1] + "|";
                }

                // Adds Second top row
                boardInterfaceString += "\n|";
                for (int i = 0; i < Dicegame.BOARD_WIDTH; i++) {
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
                for(int i = 0; i < 3*Dicegame.BOARD_WIDTH - 1; i++){
                    boardInterfaceString += '-';
                }
                boardInterfaceString += "X";
                break;
            
            case 3:

                //Adding top outline with corners
                boardInterfaceString += 'X';
                for(int i = 0; i < 3*Dicegame.BOARD_WIDTH - 1; i++){
                    boardInterfaceString += '-';
                }
                boardInterfaceString += "X\n";
                
                // Adds first top row
                boardInterfaceString += '|';
                for (int i = 0; i < Dicegame.BOARD_WIDTH; i++) {
                    boardInterfaceString += this.board[i][0] + "" + this.board[i][1] + "|";
                }

                // Adds Second top row
                boardInterfaceString += "\n|";
                for (int i = 0; i < Dicegame.BOARD_WIDTH; i++) {
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
                for(int i = 0; i < 3*Dicegame.BOARD_WIDTH - 1; i++){
                    boardInterfaceString += '-';
                }
                boardInterfaceString += "X";
                break;

            case 4:

                //Adding top outline with corners
                boardInterfaceString += 'X';
                for(int i = 0; i < 3*Dicegame.BOARD_WIDTH - 1; i++){
                    boardInterfaceString += '-';
                }
                boardInterfaceString += "X\n";
                
                // Adds first top row
                boardInterfaceString += '|';
                for (int i = 0; i < Dicegame.BOARD_WIDTH; i++) {
                    boardInterfaceString += this.board[i][0] + "" + this.board[i][1] + "|";
                }

                // Adds Second top row
                boardInterfaceString += "\n|";
                for (int i = 0; i < Dicegame.BOARD_WIDTH; i++) {
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
                for(int i = 0; i < 3*Dicegame.BOARD_WIDTH - 1; i++){
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

    public void updatePlayerPositions(Player[] players) {
        // Nulstil board
        for (int p = 0; p < Dicegame.NO_OF_PLAYERS; p++) {
            for (int f = 0; f < Dicegame.NO_OF_FIELDS; f++) {
                board[f][p] = ' '; // Symbolet er spillerens ID konverteret til en char
            }
        }
        // Indsæt spiller-positioner
        for (int p = 0; p < Dicegame.NO_OF_PLAYERS; p++) {
            board[players[p].getPosition()][p] = (char)((players[p].getID() + 1) + '0'); // Symbolet er spillerens ID konverteret til en char
        }
    }

    public void runFieldLogic(Player currentPlayer) {
        // Kør logikken for det felt spilleren landede på
        for (Field field : fieldList) {
            if (field.getSpace() == currentPlayer.getPosition()) {
                field.landingLogic(currentPlayer);
            }
        }
    }

    private char getPlayerSymbol(int playerIndex) {
        char[] playerSymbols = {'1', '2', '3', '4'};
        return playerSymbols[playerIndex];
    }

}

