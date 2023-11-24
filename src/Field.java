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
    private final int PRICE_BUY, PRICE_HOUSE, PRICE_HOTEL;
    private Player owner;
    public BuyableField (int space, String name, int PRICE_BUY, int PRICE_HOUSE, int PRICE_HOTEL) {
        super(space, name);
        this.PRICE_BUY = PRICE_BUY;
        this.PRICE_HOUSE = PRICE_HOUSE;
        this.PRICE_HOTEL = PRICE_HOTEL;
    }

    public void landingLogic(Player player) {
        initialLandingMessage(player);
        // LOGIK FORTSÆTTER:...
        // HVIS SPILLER HAR PENGE TIL FELTET, KØB DET. HVIS NOGEN EJER DET, BETAL DEM. HVIS SPILLEREN EJER DET I FORVEJEN, SPØRG OM DE VIL KØBE HUSE/HOTELLER.

    }
}

class GoToJailField extends Field {
    public GoToJailField (int space) {
        super(space, "Go to jail.");
    }
    public void landingLogic(Player player) {
        initialLandingMessage(player);
        // LOGIK FORTSÆTTER:...
        if(!player.hasGetOutOfJailFreeCard){
            player.goToJail();
        }
        else{
            player.hasGetOutOfJailFreeCard = false;
            Game.print("but " + player.getName() + " had a get out of jail free card and can move next turn");
        }


    }
}

class JailField extends Field {
    public JailField (int space) {
        super(space, "Jail");
    }
    public void landingLogic(Player player) {
        //Dette skal kun printes hvis spilleren besøger jail
        initialLandingMessage(player);
        Game.print(player.getName() + " visited the people in jail.");
        // LOGIK FORTSÆTTER:...

    }
}

class StartField extends Field {
    public StartField (int space) {
        super(space, "Start");
    }
    public void landingLogic(Player player) {
        initialLandingMessage(player);
        // OBS! Logik for at lande/passere start bliver kaldt i "Player.move()" og skal ikke give penge her. Logik umiddelbart slut her.
    }
}

class ParkingField extends Field {
    public ParkingField (int space) {
        super(space, "Parking");
    }
    public void landingLogic(Player player) {
        initialLandingMessage(player);
        // LOGIK FORTSÆTTER:...

    }
}

class ChanceField extends Field {
    public ChanceField (int space) {
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

        switch(cardValue){
            case 1: //1: Ryk frem til start. (modtager xxx penge i "Player.move -> Player.passedStart")
                System.out.println("Move to start and receive money for passing start.");  // Tekst til kortet
                currentPlayer.setPosition(0);
                break;

            case 2: //2: Ryk 5 felter frem
                System.out.println("Move 5 fields ahead!");  // Tekst til kortet
                currentPlayer.move(5);
                break;

            case 3: //3: Ryk 1 frem eller tag et kort mere
                System.out.println("Press 0 to move 1 field ahead, or,\npress 1 to draw another card.");  // Tekst til kortet
                while(true){
                    playerInput = Game.scanner.nextInt();
                    if(playerInput == 0){   //0 = ryk 1 frem:
                        currentPlayer.move(1);
                        break;
                    }
                    else if(playerInput == 1){  //1 = tag et kort mere
                        drawChanceCard();
                        break;
                    }
                    else{
                        System.out.println("Please enter a valid number (0-1).");
                    }
                }
                break;

            case 4: //4: Du har spist for meget slik betal 1 penge-penge
                System.out.println("You ate too much candy. Pay $1.");  // Tekst til kortet
                currentPlayer.wallet().substractMoney(1);
                break;

            case 5: //5: Get out of jail free card
                System.out.println("Its your lucky day. Get out of jail for free.");  // Tekst til kortet
                // Jail logic on player
                currentPlayer.hasGetOutOfJailFreeCard = true;
                break;

            case 6: //6: Go to jail
                System.out.println("You were speeding. Go to jail.");  // Tekst til kortet
                currentPlayer.goToJail();   //jail
                break;

            case 7: //7: Det er din fødselsdag. Modtag $1 penge fra alle deltagere
                System.out.println("Its your birthday. Receive $1 from every person.");  // Tekst til kortet
                Player[] players = Game.getPlayers();
                for(int i = 0; i < Dicegame.NO_OF_PLAYERS; i++){
                    if(currentPlayer.getID() != i){
                        players[i].wallet().substractMoney(1);
                    }
                }
                currentPlayer.wallet().addMoney(Dicegame.NO_OF_PLAYERS - 1);
                break;

            case 8: //8: Du har lavet alle dine lektier modtag $3 fra banken.
                System.out.println("You made all your homework. Receive $3.");  // Tekst til kortet
                currentPlayer.wallet().addMoney(3);
                break;

            case 9://9: Ryk frem til en random felt og få feltet gratis
                int freeProperty;
                do {
                    // Genererer et tilfældigt tal mellem 0 og 23 (inklusive)
                    freeProperty = random.nextInt(24);
                } while (freeProperty % 3 == 0); // Undgå tal: 0, 3, 6, 9, 12, 15, 18, 21 - Mega klogt tænkt makker 5Head
                System.out.println("Move to " + freeProperty + " and receive it for free!");  // Tekst til kortet
                currentPlayer.setPosition(freeProperty);
                //Receive property for free logic
                break;

            case 10:
                System.out.println("Move 3 fields back.");  // Tekst til kortet
                currentPlayer.move(-3);
                break;
        }

    }
}
