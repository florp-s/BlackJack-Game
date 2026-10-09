import java.util.Random;

public class Shoe extends CardSource {
    private Card[] cards;
    private int nextCardIndex;
    private final int numDecks;

    public Shoe() {
        this(2);
    }

    public Shoe(int numDecks) {
        if (numDecks <= 0) numDecks = 1;
        this.numDecks = numDecks;
        initializeShoe();
    }

    private void initializeShoe() {
        int totalCards = numDecks * CardUtil.cardsPerDeck();
        cards = new Card[totalCards];
        int index = 0;
        for (int d = 0; d < numDecks; d++) {
            for (Card c : CardUtil.generateDeck()) {
                cards[index++] = new Card(c.getRank(), c.getSuit());
            }
        }
        nextCardIndex = 0;
    }

    public int numCardsInShoe() {
        return cards.length;
    }

    public int numCardsRemaining() {
        return cards.length - nextCardIndex;
    }

    public Card dealCard() {
        if (nextCardIndex >= cards.length) return null;
        return cards[nextCardIndex++];
    }

    public void shuffle() {
        Random rand = new Random();
        for (int i = cards.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
        nextCardIndex = 0;
    }

    public void takeInventory() {
        System.out.println("Inventory of undealt cards (" + numCardsRemaining() + " total):");
        for (int i = nextCardIndex; i < cards.length; i++) {
            System.out.println(cards[i]);
        }
    }

    public void reset() {
        initializeShoe();
    }

    public void prepForNextHand() {
        int MIN_CARDS = 10;
        if (numCardsRemaining() < MIN_CARDS) {
            shuffle();
        }
    }
}
