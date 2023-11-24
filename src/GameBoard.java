import java.util.ArrayList;
import java.util.List;

class GameBoard{

    /* Checkliste:
        Hvert felt skal have en atribut
        Get og set metoder? (kravspecifikationen)
        Størrelsen på boardet skal justeres til at håndtere 4 spillere
     */

    private String[][] board;
    private List<Field> fieldList = new ArrayList<>();

    public GameBoard() {
        this.board = new String[Dicegame.NO_OF_FIELDS][Dicegame.MAX_NO_OF_PLAYERS];
        initializeBoard();
    }

    private void initializeBoard() {
        // Sætter alle felterne tomme
        for (int i = 0; i < Dicegame.NO_OF_FIELDS; i++) {
            for (int j = 0; j < Dicegame.MAX_NO_OF_PLAYERS; j++) {
                board[i][j] = " ";
            }
        }

        // Player starts here
        for (int j = 0; j < Dicegame.NO_OF_PLAYERS; j++) {
            board[0][j] = getPlayerSymbol(j);
        }

        //Field attributes
        int spaceIndex = 0;
        fieldList.add(new StartField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Burger Café", 1, 0, 0));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Pizzeria", 1, 0, 0));
        spaceIndex++;
        fieldList.add(new ChanceField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Candy Shop", 1, 0, 0));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Ice Cream Shop", 1, 0, 0));
        spaceIndex++;
        fieldList.add(new JailField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Museum", 2, 0, 0));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Library", 2, 0, 0));
        spaceIndex++;
        fieldList.add(new ChanceField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Skate Park", 2, 0, 0));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Swimming Pool", 2, 0, 0));
        spaceIndex++;
        fieldList.add(new ParkingField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Arcade", 3, 0, 0));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Cinema", 3, 0, 0));
        spaceIndex++;
        fieldList.add(new ChanceField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Toy Store",3, 0, 0));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Pet Store", 3, 0, 0));
        spaceIndex++;
        fieldList.add(new GoToJailField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Bowling alley", 4, 0, 0));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Zoo", 4, 0, 0));
        spaceIndex++;
        fieldList.add(new ChanceField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Water Park", 5, 0, 0));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Beach", 5, 0, 0));
    }

    public String toString(){
        switch (Dicegame.NO_OF_PLAYERS) {
            case 2:
                for (int f = 0; f < Dicegame.NO_OF_FIELDS; f++) {
                    board[f][2] = " ";
                    board[f][3] = " ";
                }
                break;
            case 3:
                for (int f = 0; f < Dicegame.NO_OF_FIELDS; f++) {
                    board[f][3] = " ";
                }
                break;
        }

        indexerCount = 0;
        cellFieldCount = 0;
        lastCellRowCounter = 0;

        StringBuilder boardMap = new StringBuilder();
        boardMap.append("X-------x-------x-------x-------x-------x-------x-------X"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("x-------x-------x-------x-------x-------x-------x-------x"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |                                       | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |                                       | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("x-------x                                       x-------x"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |                                       | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |                                       | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("x-------x                                       x-------x"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |                                       | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |                                       | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("x-------x                                       x-------x"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |                                       | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |                                       | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("x-------x                                       x-------x"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |                                       | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |                                       | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("x-------x-------x-------x-------x-------x-------x-------x"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " | " + playerPositionIndexer() + "   " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("X-------x-------x-------x-------x-------x-------x-------X"); boardMap.append("\n");

        return boardMap.toString();
    }

    int indexerCount = 0;
    int cellFieldCount = 0;
    double lastCellRowCounter = 0;
    private String playerPositionIndexer() {
        int fieldIndex = 0;
        int cellField = (int)Math.floor(cellFieldCount / 2.0); // 0 eller 1

        if (indexerCount < 28) {        // cell 1
            fieldIndex = (int)Math.floor((indexerCount % 14) / 2.0);
        } else if (indexerCount < 36) { // cell 2
            switch (cellField) {
                case 0:
                    fieldIndex = 23;
                    break;
                case 1:
                    fieldIndex = 7;
                    break;
            }
        } else if (indexerCount < 44) { // cell 3
            switch (cellField) {
                case 0:
                    fieldIndex = 22;
                    break;
                case 1:
                    fieldIndex = 8;
                    break;
            }
        } else if (indexerCount < 52) { // cell 4
            switch (cellField) {
                case 0:
                    fieldIndex = 21;
                    break;
                case 1:
                    fieldIndex = 9;
                    break;
            }
        } else if (indexerCount < 60) { // cell 5
            switch (cellField) {
                case 0:
                    fieldIndex = 20;
                    break;
                case 1:
                    fieldIndex = 10;
                    break;
            }
        } else if (indexerCount < 68) { // cell 6
            switch (cellField) {
                case 0:
                    fieldIndex = 19;
                    break;
                case 1:
                    fieldIndex = 11;
                    break;
            }
        } else if (indexerCount < 96) { // cell 7
            fieldIndex = 18 - ((int)Math.floor(lastCellRowCounter));
            lastCellRowCounter += 0.5;
            if (lastCellRowCounter >= 7)
                lastCellRowCounter = 0;
        }

        if (indexerCount >= 28 && indexerCount < 68) {
            cellFieldCount++;
        }

        String mapSymbol = "";
        if (indexerCount < 14) {        // cell 1
            mapSymbol = board[fieldIndex][((indexerCount) % 2)]; // Player 1 og 2
        } else if (indexerCount < 28) {
            mapSymbol = board[fieldIndex][((indexerCount) % 2) + 2]; // Player 3 og 4
        } else if (indexerCount < 32) { // cell 2
            mapSymbol = board[fieldIndex][((indexerCount) % 2)];
        } else if (indexerCount < 36) {
            mapSymbol = board[fieldIndex][((indexerCount) % 2) + 2];
        } else if (indexerCount < 40) { // cell 3
            mapSymbol = board[fieldIndex][((indexerCount) % 2)];
        } else if (indexerCount < 44) {
            mapSymbol = board[fieldIndex][((indexerCount) % 2) + 2];
        } else if (indexerCount < 48) { // cell 4
            mapSymbol = board[fieldIndex][((indexerCount) % 2)];
        } else if (indexerCount < 52) {
            mapSymbol = board[fieldIndex][((indexerCount) % 2) + 2];
        } else if (indexerCount < 56) { // cell 5
            mapSymbol = board[fieldIndex][((indexerCount) % 2)];
        } else if (indexerCount < 60) {
            mapSymbol = board[fieldIndex][((indexerCount) % 2) + 2];
        } else if (indexerCount < 64) { // cell 6
            mapSymbol = board[fieldIndex][((indexerCount) % 2)];
        } else if (indexerCount < 68) {
            mapSymbol = board[fieldIndex][((indexerCount) % 2) + 2];
        } else if (indexerCount < 82) { // cell 7
            mapSymbol = board[fieldIndex][((indexerCount) % 2)];
        } else {
            mapSymbol = board[fieldIndex][((indexerCount) % 2) + 2];
        }

        //Game.print("cellfield " + cellField + ", at " + indexerCount + ", has fieldindex: " + fieldIndex);

        indexerCount++;
        if (indexerCount > 96) {
            indexerCount = 0;
            lastCellRowCounter = 0;
        }
        if (cellFieldCount > 3)
            cellFieldCount = 0;

        return mapSymbol;
    }

    public void updatePlayerPositions() {
        Player[] players = Game.getPlayers();
        // Nulstil board
        for (int p = 0; p < Dicegame.NO_OF_PLAYERS; p++) {
            for (int f = 0; f < Dicegame.NO_OF_FIELDS; f++) {
                board[f][p] = " "; // Symbolet er spillerens ID konverteret til en char
            }
        }
        // Indsæt spiller-positioner
        for (int p = 0; p < Dicegame.NO_OF_PLAYERS; p++) {
            board[players[p].getPosition()][p] = getPlayerSymbol(players[p].getID());
        }
    }

    public void runFieldLogic(Player currentPlayer) {
        // Kør logikken for det felt spilleren landede på
        for (Field field : fieldList) {
            if (field.getSpace() == currentPlayer.getPosition()) {
                field.landingLogic(currentPlayer);
                break; // Skal break, ellers opstår der en bug, hvor der bliver fundet to spaces der matcher spillerens position når spilleren lander på en chance der rykker dem til et andet felt.
            }
        }
    }

    private String getPlayerSymbol(int playerIndex) {
        /*String[] playerSymbols = {"1", "2", "3", "4"};
        return playerSymbols[playerIndex];*/
        return String.valueOf(Game.getPlayers()[playerIndex].getName().charAt(0));
    }
}
