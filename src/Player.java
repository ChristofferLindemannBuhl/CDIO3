public class Player {
    private int playerID; // Bliver sat når spillerne bliver oprettet i starten af spillet
    private String name;
    private Dice[] dice;
    private Wallet wallet;
    private int position = 0;

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

    public boolean checkForMoneyToWin() {
        return wallet.getMoney() == Dicegame.MONEY_TO_WIN;
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

    public String getPlayerName() {
        return name;
    }

    public String getStats() {
        return name + " got " + wallet.getMoney() + " money.";
    }

    // Player wallet
    public Wallet wallet() {
        return wallet;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int newPosition) {
        position = newPosition;
    }
    public int getID() {
        return playerID;
    }
}
