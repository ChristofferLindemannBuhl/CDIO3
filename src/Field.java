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
        Game.print(player.getPlayerName() + " landed on " + name);
    }
}

class BuyableField extends Field {
    private final int PRICE_LAND, PRICE_HOUSE, PRICE_HOTEL;
    private Player owner;
    public BuyableField (int space, String name, int PRICE_LAND, int PRICE_HOUSE, int PRICE_HOTEL) {
        super(space, name);
        this.PRICE_LAND = PRICE_LAND;
        this.PRICE_HOUSE = PRICE_HOUSE;
        this.PRICE_HOTEL = PRICE_HOTEL;
    }

    public void landingLogic(Player player) {
        initialLandingMessage(player);
        // LOGIK FORTSÆTTER:...
        // HVIS SPILLER HAR PENGE TIL FELTET, KØB DET. HVIS NOGEN EJER DET, BETAL DEM. HVIS SPILLEREN EJER DET I FORVEJEN, SPØRG OM DE VIL KØBE HUSE/HOTELLER.

    }
}

class ChanceField extends Field {
    public ChanceField (int space) {
        super(space, "Chance");
    }
    public void landingLogic(Player player) {
        initialLandingMessage(player);
        Game.print(player.getPlayerName() + " draws a chance card!");
        // LOGIK FORTSÆTTER:...

    }
}

class GoToJailField extends Field {
    public GoToJailField (int space) {
        super(space, "Go to jail");
    }
    public void landingLogic(Player player) {
        initialLandingMessage(player);
        // LOGIK FORTSÆTTER:...

    }
}

class JailField extends Field {
    public JailField (int space) {
        super(space, "Jail");
    }
    public void landingLogic(Player player) {
        initialLandingMessage(player);
        Game.print(player.getPlayerName() + " visited the people in jail.");
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