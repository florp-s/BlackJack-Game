public class ShoeTester {
    public static void main(String[] args) {
        Shoe shoe = new Shoe(1);   

        System.out.println("Total cards in shoe: " + shoe.numCardsInShoe());
        System.out.println("Cards remaining: " + shoe.numCardsRemaining());
        System.out.println();

        
        System.out.println("Dealing 5 cards:");
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + shoe.dealCard());
        }
        System.out.println("Cards remaining: " + shoe.numCardsRemaining());
        System.out.println();

        
        System.out.println("Calling prepForNextHand()...");
        shoe.prepForNextHand();
        System.out.println("Cards remaining AFTER prepForNextHand: " + shoe.numCardsRemaining());
        System.out.println();

        
        while (shoe.numCardsRemaining() > 0) {
            shoe.dealCard();
        }
        System.out.println("After draining shoe:");
        System.out.println("Cards remaining: " + shoe.numCardsRemaining());
        System.out.println();

        
        System.out.println("Calling prepForNextHand() when shoe is empty...");
        shoe.prepForNextHand();
        System.out.println("Cards remaining AFTER reshuffle: " + shoe.numCardsRemaining());
    }
}
