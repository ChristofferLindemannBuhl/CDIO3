public class Player {
    private int playerID; // Bliver sat når spillerne bliver oprettet i starten af spillet
    private String name;
    private Dice[] dice;
    private Wallet wallet;
    private int position = 0;
    private int turnsLeftInJail = 0;

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
        int oldPlayerPosition = position;
        // Placér spilleren på den nye position
        position = newPlayerPosition;

        Game.board.updatePlayerPositions(); // Opdatér board-interface
        Game.print(Game.board.toString()); // Printer boardet
        Game.showPlayerRoll(); // Vi viser hvad spilleren har slået i konsollen.
        Game.board.runFieldLogic(this); // Kør logikken for det felt spilleren landede på
    }

    private void passedStart() {
        wallet.addMoney(Dicegame.MONEY_FOR_PASSING_START);
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
}
