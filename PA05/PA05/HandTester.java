public class HandTester {

    
    private static class TestSource extends CardSource {
        private Card[] cards;
        private int index;

        public TestSource(Card... cards) {
            this.cards = cards;
            this.index = 0;
        }

        @Override
        public Card dealCard() {
            if (index < cards.length) {
                return cards[index++];
            }
            return null; 
        }
    }

    private static void runScenario(String name, Card... cards) {
        System.out.println("=== " + name + " ===");
        CardSource src = new TestSource(cards);
        Hand hand = new Hand("TestPlayer", src);

        
        for (int i = 0; i < cards.length; i++) {
            hand.getNextCard();
        }

        hand.print();
        System.out.println("isBlackjack: " + hand.isBlackjack());
        System.out.println("is21:        " + hand.is21());
        System.out.println("isBust:      " + hand.isBust());
        System.out.println("continue:    " + hand.continueHand());
        System.out.println();
    }

    public static void main(String[] args) {
        
        runScenario("Blackjack (J-H, A-S)",
                new Card("J-H"),
                new Card("A-S"));

        
        runScenario("Ace, non-bust alt",
                new Card("3-D"),
                new Card("A-S"),
                new Card("5-C"));

        
        
        runScenario("Bust hand",
                new Card("10-H"),
                new Card("9-S"),
                new Card("5-D"));

        
        runScenario("No ace hand",
                new Card("7-C"),
                new Card("8-D"));

        
        runScenario("21 but not blackjack",
                new Card("7-C"),
                new Card("7-D"),
                new Card("7-S"));
    }

}
