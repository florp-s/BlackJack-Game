import java.util.Random;

public final class CardUtil {
    private CardUtil() {}

    public static final String[] VALID_SUITS = {"C", "D", "H", "S"};
    public static final String[] VALID_RANKS = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

    public static boolean isValidRank(String rank) {
        if (rank == null) return false;
        for (String r : VALID_RANKS) {
            if (r.equalsIgnoreCase(rank)) return true;
        }
        return false;
    }

    public static boolean isValidSuit(String suit) {
        if (suit == null) return false;
        for (String s : VALID_SUITS) {
            if (s.equalsIgnoreCase(suit)) return true;
        }
        return false;
    }

    public static int cardsPerDeck() {
        return VALID_SUITS.length * VALID_RANKS.length;
    }

    public static Card[] generateDeck() {
        Card[] deck = new Card[cardsPerDeck()];
        int index = 0;
        for (String suit : VALID_SUITS) {
            for (String rank : VALID_RANKS) {
                deck[index++] = new Card(rank, suit);
            }
        }
        return deck;
    }

    public static String suitToWord(String suit) {
        if (suit == null) return "unknown";
        switch (suit.toUpperCase()) {
            case "C": return "clubs";
            case "D": return "diamonds";
            case "H": return "hearts";
            case "S": return "spades";
            default: return "unknown";
        }
    }

    public static String rankToWord(String rank) {
        if (rank == null) return "unknown";
        switch (rank.toUpperCase()) {
            case "A": return "Ace";
            case "J": return "Jack";
            case "Q": return "Queen";
            case "K": return "King";
            default: return rank;
        }
    }

    private static final String[] RANKS = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    private static final String[] SUITS = {"C","D","H","S"};
    private static final Random RNG = new Random();

    public static boolean isValidCardString(String s) {
        if (s == null) return false;
        s = s.trim().toUpperCase();
        if (!s.contains("-")) return false;
        String[] parts = s.split("-");
        if (parts.length != 2) return false;
        String rankPart = parts[0];
        String suitPart = parts[1];
        return isValidRank(rankPart) && isValidSuit(suitPart);
    }

    public static String randomCardString() {
        String rank = RANKS[RNG.nextInt(RANKS.length)];
        String suit = SUITS[RNG.nextInt(SUITS.length)];
        return rank + "-" + suit;
    }

    public static String cardValueText(String cardStr) {
        if (!isValidCardString(cardStr)) return "N/A";
        String[] parts = cardStr.toUpperCase().split("-");
        String rankPart = parts[0];
        if (rankPart.equals("A"))  return "1 or 11";
        if (rankPart.equals("K"))  return "10";
        if (rankPart.equals("Q"))  return "10";
        if (rankPart.equals("J"))  return "10";
        if (rankPart.equals("10")) return "10";
        return rankPart;
    }
}
