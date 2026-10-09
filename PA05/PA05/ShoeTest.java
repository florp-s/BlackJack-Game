public class ShoeTest {
    public static void main(String[] args) {
        Shoe shoe = new Shoe(2);
        shoe.shuffle();

        System.out.println("Total cards in shoe: " + shoe.numCardsInShoe());
        System.out.println("Cards remaining: " + shoe.numCardsRemaining());
        System.out.println();

        System.out.println("Dealing 5 cards:");
        for (int i = 0; i < 5; i++) {
            Card c = shoe.dealCard();
            if (c != null) System.out.println(c);
        }

        System.out.println();
        System.out.println("Cards remaining after dealing: " + shoe.numCardsRemaining());
        System.out.println();
        shoe.takeInventory();
    }
}
