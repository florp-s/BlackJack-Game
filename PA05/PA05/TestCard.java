public class TestCard {
    public static void main(String[] args) {

        // choose one:
        // CardSource source = new GUISource();
        // CardSource source = new ConsoleSource();
        Shoe shoe = new Shoe(4);
        shoe.shuffle();
         CardSource source = shoe;

        while (true) {
            Card myCard = source.dealCard();
            if (myCard == null) {
                System.out.println("No card returned. Exiting.");
                break;
            }

            int numValues = myCard.getNumCardValues();
            int value1 = myCard.getCardValue(1);
            int value2 = myCard.getCardValue(2);
            int suit = myCard.getCardSuit();
            int rank = myCard.getCardRank();
            boolean isJack = myCard.isJack();
            boolean isAce = myCard.isAce();

            System.out.println("Card: " + myCard);
            System.out.println(" rank code: " + rank);
            System.out.println(" suit code: " + suit);
            System.out.println(" values: " + numValues);
            if (numValues == 1) {
                System.out.println(" value1: " + value1);
            } else {
                System.out.println(" value1: " + value1 + ", value2: " + value2);
            }
            System.out.println(" isJack: " + isJack);
            System.out.println(" isAce: " + isAce);
            System.out.println("--------------------------------");
        }
    }
}
