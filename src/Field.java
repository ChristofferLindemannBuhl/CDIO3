import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class Field {
    protected final String name;
    protected final int space;

    public Field(int space, String name) {
        this.space = space;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSpace() {
        return space;
    }

    protected abstract void landingLogic(Player player);

    protected void initialLandingMessage(Player player) {
        Game.print(player.getName() + " landed on " + name);
    }
}

class BuyableField extends Field {
    protected final int PRICE_BUY, PRICE_HOUSE, PRICE_HOTEL, FIELD_FAMILY_ID;
    private int landingFee = 0, sellValue = 0, noOfHouses = 0, noOfHotels = 0;
    private Player owner;

    public BuyableField(int space, String name, int FIELD_FAMILY_ID, int PRICE_BUY, int PRICE_HOUSE, int PRICE_HOTEL) {
        super(space, name);
        this.FIELD_FAMILY_ID = FIELD_FAMILY_ID;
        this.PRICE_BUY = PRICE_BUY;
        this.PRICE_HOUSE = PRICE_HOUSE;
        this.PRICE_HOTEL = PRICE_HOTEL;
    }

    public void landingLogic(Player player) {
        updateLandingFee();

        initialLandingMessage(player);
        // LOGIK FORTSÆTTER:...
        if (owner == null) {
            if (player.wallet().getMoney() >= PRICE_BUY) {
                player.wallet().substractMoney(PRICE_BUY);
                owner = player;
                Game.print("You just bought " + name + " for $" + PRICE_BUY);
            } else {
                Game.print("You do not have $" + PRICE_BUY + " to buy " + name);
            }
        } else {
            if (owner != player) {
                if (noOfHouses > 0) {
                    Game.print("You landed on " + owner + "'s " + name + " with " + noOfHouses + " house(s). You need to pay them $" + landingFee + " in fees.");
                } else if (noOfHotels > 0) {
                    Game.print("You landed on " + owner + "'s " + name + " with " + noOfHotels + " hotel(s). You need to pay them $" + landingFee + " in fees.");
                } else {
                    Game.print("You landed on " + owner + "'s " + name + ". You need to pay them $" + landingFee + " in fees.");
                }

                if (player.wallet().getMoney() < landingFee) { // Spilleren bliver nødt til at sælge grunde, hvis de har nogle
                    Game.print("\nYou do not have the money for the fee.");
                    int totaltPlayerValue = player.getTotalValue();
                    // Hvis de har nok penge ved at sælge grunde
                    if (totaltPlayerValue >= landingFee) {
                        while(player.wallet().getMoney() < landingFee) {
                            Game.print("You have $" + player.wallet().getMoney() + ". You are missing $" + (landingFee - player.wallet().getMoney()) + ". Choose a property to sell.");
                            for (BuyableField ownedField : player.getOwnedFields()) {
                                Game.print("(" + ownedField.space + ") - " + ownedField.name + ": $" + ownedField.sellValue);
                            }
                            boolean understoodInput = false;
                            while (!understoodInput) {
                                String input = Game.scanner.nextLine();
                                for (BuyableField fieldToSell : player.getOwnedFields()) {
                                    if (input.toLowerCase().equals(String.valueOf(fieldToSell.space))) {
                                        List<BuyableField> buyableFields = Game.board.getBuyableFields();
                                        // SÆLGER FELTET
                                        for (int i = 0; i < buyableFields.size(); i++) {
                                            if (buyableFields.get(i).space == fieldToSell.space) {
                                                buyableFields.get(i).sellField();
                                                understoodInput = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (!understoodInput) {
                                    Game.print("Could not find field at space: '" + input + "'. Please write the space number from the list above.");
                                }
                            }
                        }
                    } else {
                        // Hvis de ikke har nok, er de gået bankerot og spiller slutter
                        Game.print("Your total value is $" + player.getTotalValue() + ". You are missing $" + (landingFee - player.getTotalValue()) + ". The game is therefore over.");
                        Game.setGameIsOver();
                    }
                }
                // Når koden er nået her til, burde spillet enten være slut, eller spilleren har nok til at betale bøden.
                player.wallet().substractMoney(landingFee);
                owner.wallet().addMoney(landingFee);
            } else {
                for (BuyableField otherField : Game.board.getBuyableFields()) {
                    if (otherField.space != this.space && otherField.FIELD_FAMILY_ID == this.FIELD_FAMILY_ID) {
                        if (otherField.owner != null) {
                            if (otherField.owner == player) {
                                // Spilleren kan købe et hus
                                if (noOfHouses < 4 && noOfHotels == 0) { // HUS
                                    if (player.wallet().getMoney() >= PRICE_HOUSE) {
                                        player.wallet().substractMoney(PRICE_HOUSE);
                                        noOfHouses++;
                                        Game.print("You just bought a house on " + name + " for $" + PRICE_HOUSE + ".");
                                        Game.print("You currently have " + noOfHouses + " houses on " + name + ".");
                                    } else {
                                        Game.print("You do not have $" + PRICE_HOUSE + " to buy a house on " + name + ".");
                                    }
                                } else { // HOTEL
                                    if (player.wallet().getMoney() >= PRICE_HOTEL) {
                                        player.wallet().substractMoney(PRICE_HOTEL);
                                        noOfHotels++;
                                        if (noOfHouses == 4) {
                                            Game.print("You just bought a hotel on " + name + " for $" + PRICE_HOTEL + ", and demolished the houses.");
                                        }
                                        Game.print("You just bought a hotel on " + name + " for $" + PRICE_HOTEL + ".");
                                        Game.print("You currently have " + noOfHotels + " hotels on " + name + ".");
                                    } else {
                                        Game.print("You do not have $" + PRICE_HOTEL + " to buy a hotel on " + name + ".");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        updateSellValue();
    }

    private void updateLandingFee() {
        int houseFeeMultiplier = (int)Math.round(noOfHouses * 0.5 * PRICE_HOUSE);
        int hotelFeeMultiplier = 0;
        if (noOfHotels > 0)
            hotelFeeMultiplier = (int)Math.round(4 * 0.5 * PRICE_HOUSE + noOfHotels * 0.75 * PRICE_HOTEL);

        landingFee = (int)Math.ceil(PRICE_BUY * 0.5) + houseFeeMultiplier + hotelFeeMultiplier;
    }

    private void updateSellValue() {
        updateLandingFee();
        sellValue = landingFee * 2;
    }

    public int getSellValue() {
        return sellValue;
    }

    protected void sellField() {
        owner.wallet().addMoney(sellValue);
        // Reset field
        owner = null;
        landingFee = 0;
        sellValue = 0;
        noOfHouses = 0;
        noOfHotels = 0;
    }

    public Player getOwner() {
        return owner;
    }

    public int getNoOfHotels() {
        return noOfHotels;
    }
    public int getNoOfHouses() {
        return noOfHouses;
    }
}

class GoToJailField extends Field {
    public GoToJailField(int space) {
        super(space, "Go to jail.");
    }

    public void landingLogic(Player player) {
        initialLandingMessage(player);
        // LOGIK FORTSÆTTER:...
        if (!player.hasGetOutOfJailFreeCard) {
            player.goToJail();
        } else {
            player.hasGetOutOfJailFreeCard = false;
            Game.print("but " + player.getName() + " had a get out of jail free card and can move next turn");
        }

    }
}

class JailField extends Field {
    public JailField(int space) {
        super(space, "Jail");
    }

    public void landingLogic(Player player) {
        // Dette skal kun printes hvis spilleren besøger jail
        initialLandingMessage(player);
        Game.print(player.getName() + " visited the people in jail.");
        // LOGIK FORTSÆTTER:...

    }
}

class StartField extends Field {
    public StartField(int space) {
        super(space, "Start");
    }

    public void landingLogic(Player player) {
        initialLandingMessage(player);
        // OBS! Logik for at lande/passere start bliver kaldt i "Player.move()" og skal
        // ikke give penge her. Logik umiddelbart slut her.
    }
}

class ParkingField extends Field {
    private int moneyToGet = Dicegame.PARKING_LOT_START_MONEY;
    public ParkingField(int space) {
        super(space, "Parking");
    }

    public void landingLogic(Player player) {
        initialLandingMessage(player);
        // LOGIK FORTSÆTTER:...
        Game.print("You found $" + moneyToGet);
        player.wallet().addMoney(moneyToGet);
        moneyToGet += Dicegame.PARKING_LOT_INCREMENT_MONEY;
    }
}

class ChanceField extends Field {
    public ChanceField(int space) {
        super(space, "Chance");
    }

    public void landingLogic(Player player) {
        initialLandingMessage(player);
        // LOGIK FORTSÆTTER:...

        drawChanceCard();
    }

    public void drawChanceCard() {
        // initialize random number generator
        Random random = new Random();
        // Random værdi mellem 1 og 10
        int cardValue = random.nextInt(Dicegame.NO_OF_CHANCE_CARDS) + 1;
        // Variables
        var currentPlayer = Game.getCurrentPlayer();
        int playerInput;

        System.out.println("\nChance card:");

        switch (cardValue) {
            case 1: // 1: Ryk frem til start. (modtager xxx penge i "Player.move ->
                    // Player.passedStart")
                System.out.println("Move to start and receive money for passing start."); // Tekst til kortet
                currentPlayer.setPosition(0);
                break;

            case 2: // 2: Ryk 5 felter frem
                System.out.println("Move 5 fields ahead!"); // Tekst til kortet
                currentPlayer.move(5);
                break;

            case 3: // 3: Ryk 1 frem eller tag et kort mere
                System.out.println("Press 0 to move 1 field ahead, or,\npress 1 to draw another card."); // Tekst til
                                                                                                         // kortet
                while (true) {
                    playerInput = Game.scanner.nextInt();
                    if (playerInput == 0) { // 0 = ryk 1 frem:
                        currentPlayer.move(1);
                        break;
                    } else if (playerInput == 1) { // 1 = tag et kort mere
                        drawChanceCard();
                        break;
                    } else {
                        System.out.println("Please enter a valid number (0-1).");
                    }
                }
                break;

            case 4: // 4: Du har spist for meget slik betal 1 penge-penge
                System.out.println("You ate too much candy. Pay $1."); // Tekst til kortet
                currentPlayer.wallet().substractMoney(1);
                break;

            case 5: // 5: Get out of jail free card
                System.out.println("Its your lucky day. Get out of jail for free."); // Tekst til kortet
                // Jail logic on player
                currentPlayer.hasGetOutOfJailFreeCard = true;
                break;

            case 6: // 6: Go to jail
                System.out.println("You were speeding. Go to jail."); // Tekst til kortet
                currentPlayer.goToJail(); // jail
                break;

            case 7: // 7: Det er din fødselsdag. Modtag $1 penge fra alle deltagere
                System.out.println("Its your birthday. Receive $1 from every person."); // Tekst til kortet
                Player[] players = Game.getPlayers();
                for (int i = 0; i < Dicegame.NO_OF_PLAYERS; i++) {
                    if (currentPlayer.getID() != i) {
                        players[i].wallet().substractMoney(1);
                    }
                }
                currentPlayer.wallet().addMoney(Dicegame.NO_OF_PLAYERS - 1);
                break;

            case 8: // 8: Du har lavet alle dine lektier modtag $3 fra banken.
                System.out.println("You made all your homework. Receive $3."); // Tekst til kortet
                currentPlayer.wallet().addMoney(3);
                break;

            case 9:// 9: Ryk frem til en random felt og få feltet gratis
                int freeProperty;
                do {
                    // Genererer et tilfældigt tal mellem 0 og 23 (inklusive)
                    freeProperty = random.nextInt(24);
                } while (freeProperty % 3 == 0); // Undgå tal: 0, 3, 6, 9, 12, 15, 18, 21 - Mega klogt tænkt makker
                                                 // 5Head
                System.out.println("Move to " + freeProperty + " and receive it for free!"); // Tekst til kortet
                currentPlayer.setPosition(freeProperty);
                // Receive property for free logic
                break;

            case 10:
                System.out.println("Move 3 fields back."); // Tekst til kortet
                currentPlayer.move(-3);
                break;
        }

    }
}
