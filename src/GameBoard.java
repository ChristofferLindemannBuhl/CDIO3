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
        fieldList.add(new BuyableField(spaceIndex, "Burger Café", 0, 1, 1, 2));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Pizzeria", 0, 1, 1, 2));
        spaceIndex++;
        fieldList.add(new ChanceField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Candy Shop", 1, 1, 1, 2));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Ice Cream Shop", 1, 1, 1, 2));
        spaceIndex++;
        fieldList.add(new JailField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Museum", 2, 2, 1, 2));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Library", 2, 2, 1, 2));
        spaceIndex++;
        fieldList.add(new ChanceField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Skate Park", 3, 2, 2, 3));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Swimming Pool", 3, 2, 2, 3));
        spaceIndex++;
        fieldList.add(new ParkingField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Arcade", 4, 3, 2, 3));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Cinema", 4, 3, 2, 3));
        spaceIndex++;
        fieldList.add(new ChanceField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Toy Store",5, 3, 3, 4));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Pet Store", 5, 3, 3, 4));
        spaceIndex++;
        fieldList.add(new GoToJailField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Bowling alley", 6, 4, 3, 4));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Zoo", 6, 4, 3, 4));
        spaceIndex++;
        fieldList.add(new ChanceField(spaceIndex));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Water Park", 7, 5, 4, 5));
        spaceIndex++;
        fieldList.add(new BuyableField(spaceIndex, "Beach", 7, 5, 4, 5));
    }

    public List<BuyableField> getBuyableFields() {
        List<BuyableField> buyableFieldsList = new ArrayList<>();
        for (Field field : fieldList) {
            if (field.getClass() == BuyableField.class) {
                buyableFieldsList.add((BuyableField)(field));
            }
        }
        return buyableFieldsList;
    }

    public List<Field> getFields() {
        return fieldList;
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

        boardMap.append("X----------------x----------------x----------------x----------------x----------------x----------------x----------------X"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "            " + playerPositionIndexer() + " | " + playerPositionIndexer() + buyableFieldOwnerFormatter() + playerPositionIndexer() + " | " + playerPositionIndexer() + buyableFieldOwnerFormatter() + playerPositionIndexer() + " | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " | " + playerPositionIndexer() + buyableFieldOwnerFormatter() + playerPositionIndexer() + " | " + playerPositionIndexer() + buyableFieldOwnerFormatter() + playerPositionIndexer() + " | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("|                |" + buyableFieldHousingFormatter() + "|" + buyableFieldHousingFormatter() + "|                |" + buyableFieldHousingFormatter() + "|" + buyableFieldHousingFormatter() + "|                |"); boardMap.append("\n");
        boardMap.append("|" + startFieldNameFormatter() + "|" + buyableFieldNameFormatter() + "|" + buyableFieldNameFormatter() + "|" + chanceFieldNameFormatter() + "|" + buyableFieldNameFormatter() + "|" + buyableFieldNameFormatter() + "|" + jailFieldNameFormatter() + "|"); boardMap.append("\n");
        boardMap.append("|                |" + buyableFieldValueFormatter() + "|" + buyableFieldValueFormatter() + "|                |" + buyableFieldValueFormatter() + "|" + buyableFieldValueFormatter() + "|                |"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "            " + playerPositionIndexer() + " | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("X----------------x----------------x----------------x----------------x----------------x----------------x----------------X"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + buyableFieldOwnerFormatter() + playerPositionIndexer() + " |                                                                                    | " + playerPositionIndexer() + buyableFieldOwnerFormatter() + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("|" + buyableFieldHousingFormatter() + "|                                                                                    |" + buyableFieldHousingFormatter() + "|"); boardMap.append("\n");
        boardMap.append("|" + buyableFieldNameFormatter() + "|                                                                                    |" + buyableFieldNameFormatter() + "|"); boardMap.append("\n");
        boardMap.append("|" + buyableFieldValueFormatter() + "|                                                                                    |" + buyableFieldValueFormatter() + "|"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "            " + playerPositionIndexer() + " |                                                                                    | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("x----------------x                                                                                    x----------------x"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + buyableFieldOwnerFormatter() + playerPositionIndexer() + " |                                                                                    | " + playerPositionIndexer() + buyableFieldOwnerFormatter() + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("|" + buyableFieldHousingFormatter() + "|                                                                                    |" + buyableFieldHousingFormatter() + "|"); boardMap.append("\n");
        boardMap.append("|" + buyableFieldNameFormatter() + "|                                                                                    |" + buyableFieldNameFormatter() + "|"); boardMap.append("\n");
        boardMap.append("|" + buyableFieldValueFormatter() + "|                                                                                    |" + buyableFieldValueFormatter() + "|"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "            " + playerPositionIndexer() + " |                                                                                    | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("x----------------x                                                                                    x----------------x"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "            " + playerPositionIndexer() + " |                                                                                    | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("|                |                                                                                    |                |"); boardMap.append("\n");
        boardMap.append("|" + chanceFieldNameFormatter() + "|                                                                                    |" + chanceFieldNameFormatter() + "|"); boardMap.append("\n");
        boardMap.append("|                |                                                                                    |                |"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "            " + playerPositionIndexer() + " |                                                                                    | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("x----------------x                                                                                    x----------------x"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + buyableFieldOwnerFormatter() + playerPositionIndexer() + " |                                                                                    | " + playerPositionIndexer() + buyableFieldOwnerFormatter() + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("|" + buyableFieldHousingFormatter() + "|                                                                                    |" + buyableFieldHousingFormatter() + "|"); boardMap.append("\n");
        boardMap.append("|" + buyableFieldNameFormatter() + "|                                                                                    |" + buyableFieldNameFormatter() + "|"); boardMap.append("\n");
        boardMap.append("|" + buyableFieldValueFormatter() + "|                                                                                    |" + buyableFieldValueFormatter() + "|"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "            " + playerPositionIndexer() + " |                                                                                    | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("x----------------x                                                                                    x----------------x"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + buyableFieldOwnerFormatter() + playerPositionIndexer() + " |                                                                                    | " + playerPositionIndexer() + buyableFieldOwnerFormatter() + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("|" + buyableFieldHousingFormatter() + "|                                                                                    |" + buyableFieldHousingFormatter() + "|"); boardMap.append("\n");
        boardMap.append("|" + buyableFieldNameFormatter() + "|                                                                                    |" + buyableFieldNameFormatter() + "|"); boardMap.append("\n");
        boardMap.append("|" + buyableFieldValueFormatter() + "|                                                                                    |" + buyableFieldValueFormatter() + "|"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "            " + playerPositionIndexer() + " |                                                                                    | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("X----------------x----------------x----------------x----------------x----------------x----------------x----------------X"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "            " + playerPositionIndexer() + " | " + playerPositionIndexer() + buyableFieldOwnerFormatter() + playerPositionIndexer() + " | " + playerPositionIndexer() + buyableFieldOwnerFormatter() + playerPositionIndexer() + " | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " | " + playerPositionIndexer() + buyableFieldOwnerFormatter() + playerPositionIndexer() + " | " + playerPositionIndexer() + buyableFieldOwnerFormatter() + playerPositionIndexer() + " | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("|                |" + buyableFieldHousingFormatter() + "|" + buyableFieldHousingFormatter() + "|                |" + buyableFieldHousingFormatter() + "|" + buyableFieldHousingFormatter() + "|" + parkingLotFieldMoneyFormatter() + "|"); boardMap.append("\n");
        boardMap.append("|" + goToJailFieldNameFormatter() + "|" + buyableFieldNameFormatter() + "|" + buyableFieldNameFormatter() + "|" + chanceFieldNameFormatter() + "|" + buyableFieldNameFormatter() + "|" + buyableFieldNameFormatter() + "|" + parkingLotFieldNameFormatter() + "|"); boardMap.append("\n");
        boardMap.append("|                |" + buyableFieldValueFormatter() + "|" + buyableFieldValueFormatter() + "|                |" + buyableFieldValueFormatter() + "|" + buyableFieldValueFormatter() + "|                |"); boardMap.append("\n");
        boardMap.append("| " + playerPositionIndexer() + "            " + playerPositionIndexer() + " | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " | " + playerPositionIndexer() + "            " + playerPositionIndexer() + " |"); boardMap.append("\n");
        boardMap.append("X----------------x----------------x----------------x----------------x----------------x----------------x----------------X"); boardMap.append("\n");

        return boardMap.toString();
    }

    private String startFieldNameFormatter() {
        return "      START     ";
    }
    private String chanceFieldNameFormatter() {
        return "     CHANCE     ";
    }
    private String jailFieldNameFormatter() {
        return "      JAIL      ";
    }
    private String goToJailFieldNameFormatter() {
        return "   GO TO JAIL   ";
    }
    private String parkingLotFieldNameFormatter() {
        return "   PARKING LOT  ";
    }
    private String parkingLotFieldMoneyFormatter() {
        String formattedString = "";

        String moneyToGetString = String.valueOf(ParkingField.getMoneyOnParkingLot());
        int noOfCharatersInMoney = moneyToGetString.length();

        int noOfSpacesOnRightSide = (int)Math.floor((16 - noOfCharatersInMoney) / 2.0);
        int noOfSpacesOnEachLeftSide = noOfSpacesOnRightSide;
        if (noOfCharatersInMoney % 2 == 1) { // hvis ulige
            noOfSpacesOnEachLeftSide++;
        }
        for (int i = 0; i < noOfSpacesOnEachLeftSide - 1; i++) {
            formattedString += " ";
        }
        formattedString += "$" + moneyToGetString;
        for (int i = 0; i < noOfSpacesOnRightSide; i++) {
            formattedString += " ";
        }

        return formattedString;
    }

    private final int[] buyableFieldFormatOrder = {1, 2, 4, 5, 23, 7, 22, 8, 20, 10, 19, 11, 17, 16, 14, 13};

    int currentFieldNameInOrder = 0;
    private String buyableFieldNameFormatter() {
        String formattedString = "";

        String fieldNameString = fieldList.get(buyableFieldFormatOrder[currentFieldNameInOrder]).name;
        int noOfCharatersInFieldName = fieldNameString.length();

        int noOfSpacesOnRightSide = (int)Math.floor((16 - noOfCharatersInFieldName) / 2.0);
        int noOfSpacesOnEachLeftSide = noOfSpacesOnRightSide;
        if (noOfCharatersInFieldName % 2 == 1) { // hvis ulige
            noOfSpacesOnEachLeftSide++;
        }
        for (int i = 0; i < noOfSpacesOnEachLeftSide; i++) {
            formattedString += " ";
        }
        formattedString += fieldNameString;
        for (int i = 0; i < noOfSpacesOnRightSide; i++) {
            formattedString += " ";
        }

        // Opdater currentFieldNameInOrder så det passer med det næste felt
        currentFieldNameInOrder++;
        if (currentFieldNameInOrder > 15)
            currentFieldNameInOrder = 0;

        return formattedString;
    }
    int currentFieldHousingInOrder = 0;
    private String buyableFieldHousingFormatter() {
        String formattedString = "";

        int noOfHouses = 0;
        int noOfHotels = 0;

        for (BuyableField field : getBuyableFields()) {
            if (field.getSpace() == buyableFieldFormatOrder[currentFieldHousingInOrder]) {
                noOfHouses = field.getNoOfHouses();
                noOfHotels = field.getNoOfHotels();
            }
        }

        String housingString = "";
        if (noOfHotels > 0) {
            for (int i = 0; i < noOfHotels; i++) {
                housingString += "O";
            }
        }
        if (noOfHouses > 0) {
            for (int i = 0; i < noOfHouses; i++) {
                housingString += "o";
            }
        }
        
        int noOfCharatersInHousingString = housingString.length();

        int noOfSpacesOnRightSide = (int)Math.floor((16 - noOfCharatersInHousingString) / 2.0);
        int noOfSpacesOnEachLeftSide = noOfSpacesOnRightSide;
        if (noOfCharatersInHousingString % 2 == 1) { // hvis ulige
            noOfSpacesOnEachLeftSide++;
        }
        for (int i = 0; i < noOfSpacesOnEachLeftSide; i++) {
            formattedString += " ";
        }
        formattedString += housingString;
        for (int i = 0; i < noOfSpacesOnRightSide; i++) {
            formattedString += " ";
        }

        // Opdater currentFieldHousingInOrder så det passer med det næste felt
        currentFieldHousingInOrder++;
        if (currentFieldHousingInOrder > 15)
            currentFieldHousingInOrder = 0;

        return formattedString;
    }

    int currentFieldValueInOrder = 0;
    private String buyableFieldValueFormatter() {
        String formattedString = "";

        int fieldValue = 0;
        String fieldValueString = "";
        for (BuyableField field : getBuyableFields()) {
            if (field.getSpace() == buyableFieldFormatOrder[currentFieldValueInOrder]) {
                if (field.getOwner() == null) {
                    fieldValue = field.PRICE_BUY;
                    fieldValueString = "Price: $" + fieldValue;
                } else {
                    fieldValue = field.getLandingFee();
                    fieldValueString = "Fee: $" + fieldValue;
                }
            }
        }

        int noOfCharatersInValueString = fieldValueString.length();

        int noOfSpacesOnRightSide = (int)Math.floor((16 - noOfCharatersInValueString) / 2.0);
        int noOfSpacesOnEachLeftSide = noOfSpacesOnRightSide;
        if (noOfCharatersInValueString % 2 == 1) { // hvis ulige
            noOfSpacesOnEachLeftSide++;
        }
        for (int i = 0; i < noOfSpacesOnEachLeftSide; i++) {
            formattedString += " ";
        }
        formattedString += fieldValueString;
        for (int i = 0; i < noOfSpacesOnRightSide; i++) {
            formattedString += " ";
        }

        // Opdater currentFieldHousingInOrder så det passer med det næste felt
        currentFieldValueInOrder++;
        if (currentFieldValueInOrder > 15)
            currentFieldValueInOrder = 0;

        return formattedString;
    }
    int currentFieldOwnerInOrder = 0;
    private String buyableFieldOwnerFormatter() {
        String formattedString = "            ";

        for (BuyableField field : getBuyableFields()) {
            if (field.getSpace() == buyableFieldFormatOrder[currentFieldOwnerInOrder]) {
                Player fieldOwner = field.getOwner();
                if (fieldOwner != null) {
                    formattedString = "  Owner: " + getPlayerSymbol(fieldOwner.getID()) + "  ";
                }
            }
        }

        // Opdater currentFieldNameInOrder så det passer med det næste felt
        currentFieldOwnerInOrder++;
        if (currentFieldOwnerInOrder > 15)
            currentFieldOwnerInOrder = 0;

        return formattedString;
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
