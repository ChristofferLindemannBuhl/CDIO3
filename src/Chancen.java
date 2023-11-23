import java.util.Random;

class Chancen{

    java.util.Scanner scanner;
    private int cardValue;
    private int cards = 10;

    // initialize random number generator
    Random random = new Random();

    public void drawChancenCard(){
        // Random værdi mellem 1 og 10
        cardValue = random.nextInt(cards) + 1;

        var currentPlayer =  Game.getCurrentPlayer();
        var currentPlayerPosition = currentPlayer.getPosition();
        var amountOfPlayers = Dicegame.NO_OF_PLAYERS;

        scanner = new java.util.Scanner(System.in);
        int playerInput = 0;

        switch(cardValue){

            case 1: //1: Ryk frem til start. modtag xxx penge

                System.out.println("Move to start and recieve money for passing start");  //Tekst til kortet

                currentPlayer.setPosition(0);
                currentPlayer.wallet().addMoney(2); //2 penge for at passere start

            break;

            case 2: //2: Ryk 5 felter frem

                System.out.println("Move 5 fields ahead");  //Tekst til kortet
                currentPlayer.setPosition(currentPlayerPosition + 5);

            break;
            
            case 3: //3: Ryk 1 frem eller tag et kort mere

                System.out.println("Pres 1 to move 1 field ahead or\npres 2 to draw another card");  //Tekst til kortet

                playerInput = scanner.nextInt();
                while(true){
                    if(playerInput == 0){   //0 = ryk 1 frem:
                        currentPlayer.setPosition(currentPlayerPosition + 1);
                        break;
                    }
                    else if(playerInput == 1){  //1 = tag et kort mere
                        drawChancenCard();
                        break;
                    }
                    System.out.println("Please enter a valid number (0-1)");
                }
                

            break;

            case 4: //4: Du har spist for meget slik betal 1 penge-penge

                System.out.println("You ate too much candy. Pay $1");  //Tekst til kortet
                currentPlayer.wallet().substractMoney(1);

            break;

            case 5: //5: Get out of jail free card

                System.out.println("Its your lucky day. Get out of jail for free");  //Tekst til kortet
                // Jail logic on player

            break;

            case 6: //6: Go to jail

                System.out.println("You were speeding. Go to jail");  //Tekst til kortet
                currentPlayer.setPosition(6);   //jail
                //Jail logic on player

            break;

            case 7: //7: Det er din fødselsdag. modtag xxx-penge fra alle deltagere
                
                System.out.println("Its your birthday. Receive $1 from every person");  //Tekst til kortet
                
                for(int i = 1; i < amountOfPlayers; i++){
                    if(currentPlayer.getID() != i){
                        //Players needs to pay to currentPlayer
                    }
                }
            break;

            case 8: //8: Du har lavet alle dine lektier modtag xxx-penge fra banken.

                System.out.println("You made all your homework");  //Tekst til kortet
                currentPlayer.wallet().addMoney(3); 

            break;

            case 9://9: Ryk frem til en random felt og få feltet gratis
                int freeProberty = random.nextInt(24) + 1;  //Tilføj logik så man ikke kan eje fx start og jail.
                
                System.out.println("Move to " + freeProberty + "and receive it for free!");  //Tekst til kortet
                //Recieve proberty for freelogik

            break;

            case 10://????

            break;

        }
        
    }

}