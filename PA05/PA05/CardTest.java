public class CardTest {
    public static void main(String[] args) {
        System.out.println(" Testing Card class \n");

        Card c1 = new Card("A-S");
        Card c2 = new Card("10-H");
        Card c3 = new Card("Q-D");
        Card c4 = new Card("7-C");
        Card c5 = new Card("1-C");
        Card c6 = new Card("7-K");
        Card c7 = new Card("A-F");
        Card c8 = new Card("10*H");

        Card[] cards = {c1, c2, c3, c4, c5, c6, c7, c8};

        for (Card c : cards) {
            System.out.println(c);
        }

        System.out.println();
        System.out.println(" Testing Card methods ");
        System.out.println("c1 is Ace: " + c1.isAce());
        System.out.println("c1 number of values: " + c1.getNumCardValues());
        System.out.println("c2 value: " + c2.getCardValue(1));
        System.out.println("c3 is Jack: " + c3.isJack());
        System.out.println("c4 suit number: " + c4.getCardSuit());
        System.out.println("c4 rank number: " + c4.getCardRank());

        System.out.println();
        System.out.println(" Testing CardUtil Integration ");
        System.out.println("Cards per deck: " + CardUtil.cardsPerDeck());

        System.out.println();
        System.out.println("Testing Shoe integration ");
        Shoe shoe = new Shoe(1);
        shoe.shuffle();

        System.out.println("Total cards in shoe: " + shoe.numCardsInShoe());
        System.out.println("Cards remaining: " + shoe.numCardsRemaining());
        System.out.println("Dealing 5 cards:");
        for (int i = 0; i < 5; i++) {
            Card dealt = shoe.dealCard();
            if (dealt != null)
                System.out.println(dealt);
        }

        System.out.println();
        System.out.println("Cards remaining after dealing: " + shoe.numCardsRemaining());
        System.out.println();
        shoe.takeInventory();
    }
}
