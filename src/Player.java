import java.util.ArrayList;
import java.util.List;

public class Player {
    private int playerID; // Bliver sat når spillerne bliver oprettet i starten af spillet
    private String name;
    private Dice[] dice;
    private Wallet wallet;
    private int position = 0;
    private int turnsLeftInJail = 0;
    public boolean hasGetOutOfJailFreeCard = false;

    public Player(String name, int playerID) {
        this.name = name;
        this.wallet = new Wallet();
        this.playerID = playerID;

        initializePlayerDice();
    }

    private void initializePlayerDice() {
        dice = new Dice[Dicegame.NO_OF_DICE];
        for (int i = 0; i < Dicegame.NO_OF_DICE; i++) {
            dice[i] = new Dice();
        }
    }

    public void rollDice() {
        for (int i = 0; i < Dicegame.NO_OF_DICE; i++) {
            dice[i].rollDie();
        }
    }

    public int getDieValue(int die) {
        return dice[die].getValue();
    }

    public int getSumOfDice() {
        int sum = 0;
        for (int i = 0; i < Dicegame.NO_OF_DICE; i++) {
            sum += dice[i].getValue();
        }
        return sum;
    }
    public int getID() {
        return playerID;
    }
    public String getName() {
        return name;
    }

    public String getStats(boolean showTotalPlayerValue) {
        String stats = "              ---------- " + name + " ----------";
        stats += "\n|" + "BACKPACK" + "|";
        stats += "\n - Money: $" + wallet.getMoney() + ".";
        if (hasGetOutOfJailFreeCard)
            stats += "\n - Get out of jail card.";

        List<BuyableField> ownedFields = getOwnedFields();
        if (ownedFields.size() > 0) {
            stats += "\n|" + "PROPERTIES" + "|";
            for (BuyableField ownedField : ownedFields) {
                stats += "\n * ";
                if (ownedField.getNoOfHotels() > 0)
                    stats += ownedField.name + ": " + ownedField.getNoOfHotels() + " hotel(s). Value: $" + ownedField.getSellValue() + ".";
                else if (ownedField.getNoOfHouses() > 0)
                    stats += ownedField.name + ": " + ownedField.getNoOfHouses() + " house(s). Value: $" + ownedField.getSellValue() + ".";
                else
                    stats += ownedField.name + ": Value: $" + ownedField.getSellValue() + ".";
            }
        }
        if (isInJail()) {
            stats += "\n\n|" + "TURNS LEFT IN JAIL" + "|";
            stats += "\n" + turnsLeftInJail;
        }
        if (showTotalPlayerValue) {
            stats += "\n\n|" + "TOTAL SCORE" + "|";
            stats += "\n$" + getTotalValue() + ".";
        }
        return stats;
    }

    // Player wallet
    public Wallet wallet() {
        return wallet;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int newPlayerPosition) {
        if (newPlayerPosition < position)
            passedStart();
        // Kør logik for at spilleren bevæger sig
        processPlayerMovement(newPlayerPosition);
    }
    public void move(int amountToMove) {
        if (position + amountToMove > Dicegame.NO_OF_FIELDS)
            passedStart();
        int newPlayerPosition = (position + amountToMove) % Dicegame.NO_OF_FIELDS;
        // Kør logik for at spilleren bevæger sig
        processPlayerMovement(newPlayerPosition);
    }

    private void processPlayerMovement(int newPlayerPosition) {
        // Placér spilleren på den nye position
        position = newPlayerPosition;

        Game.showPlayerRoll(); // Vi viser hvad spilleren har slået i konsollen.
        Game.board.runFieldLogic(this); // Kør logikken for det felt spilleren landede på
    }

    private void passedStart() {
        wallet.addMoney(Dicegame.MONEY_FOR_PASSING_START);
        Game.print("You passed start and received $" + Dicegame.MONEY_FOR_PASSING_START);
    }

    public boolean isInJail() {
        if (turnsLeftInJail > 1) {
            turnsLeftInJail--;
            return true;
        }
        return false;
    }
    public void goToJail() {
            setPosition(6);
            turnsLeftInJail = Dicegame.TURNS_IN_JAIL + 1; // Plusser med én, så der står man har en tur mere i fængsel, når det er ens sidste tur i fængslet.

    }
    public int getTurnsLeftInJail() {
        return turnsLeftInJail;
    }

    public int getTotalValue() {
        int totaltPlayerValue = wallet().getMoney();
        for (BuyableField ownedField : getOwnedFields()) {
            totaltPlayerValue += ownedField.getSellValue();
        }
        return totaltPlayerValue;
    }

    public List<BuyableField> getOwnedFields() {
        List<BuyableField> ownedFields = new ArrayList<>();
        for (BuyableField field : Game.board.getBuyableFields()) {
            if (field.getOwner() == this) {
                ownedFields.add(field);
            }
        }
        return ownedFields;
    }
}