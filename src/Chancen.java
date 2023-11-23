import java.util.Random;
class Chancen{

    public Chancen(){

    }

    public int cardValue;   //Make private when done testing
    private int cards = 10;

    // initialize random number generator
    Random random = new Random();

    public void drawChancenCard(){
        cardValue = random.nextInt(cards) + 1;

        switch(cardValue){

            case 1: //1: Ryk frem til start. modtag xxx penge

            break;

            case 2: //2: Ryk 5 felter frem

            break;
            
            case 3: //3: Ryk 1 frem eller tag et kort mere

            break;

            case 4: //4: Du har spist for meget slik betal xxx-penge

            break;

            case 5: //5: Get out of jail free card

            break;

            case 6: //6: Ryk frem til xxx-felt

            break;

            case 7: //7: Det er din fødselsdag. modtag xxx-penge fra alle deltagere

            break;

            case 8: //8: Du har lavet alle dine lektier modtag xxx-penge fra banken.

            break;

            case 9://9: Ryk frem til xxx-felt og få feltet gratis

            break;

            case 10://????

            break;

        }
        
    }

    //Test
    /*
    public static void main(String[] args) {
        
        var chancen = new Chancen();

        System.out.println(chancen.cardValue);
        chancen.drawChancenCard();
        System.out.println(chancen.cardValue);
        chancen.drawChancenCard();
        System.out.println(chancen.cardValue);
        chancen.drawChancenCard();
        System.out.println(chancen.cardValue);

    }*/
}