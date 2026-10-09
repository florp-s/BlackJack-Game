public class Card {
    private String rank;
    private String suit;
    private boolean valid;

    public Card(String s) {
        valid = validateCard(s);
    }

    public Card(String rank, String suit) {
        this.rank = rank.toUpperCase();
        this.suit = suit.toUpperCase();
        this.valid = validateCard(this.rank + "-" + this.suit);
    }

    private boolean validateCard(String s) {
        if (s == null || s.isEmpty() || !s.contains("-")) {
            System.out.println("Invalid entry");
            return false;
        }
        String[] parts = s.split("-");
        if (parts.length != 2) {
            System.out.println("Invalid entry");
            return false;
        }
        String rankPart = parts[0].toUpperCase();
        String suitPart = parts[1].toUpperCase();
        if (!CardUtil.isValidRank(rankPart)) {
            System.out.println("Invalid card rank");
            return false;
        }
        if (!CardUtil.isValidSuit(suitPart)) {
            System.out.println("Invalid card suit");
            return false;
        }
        this.rank = rankPart;
        this.suit = suitPart;
        return true;
    }

    public boolean isValid() { return valid; }

    public int getNumCardValues() {
        return isAce() ? 2 : 1;
    }

    public int getCardValue(int index) {
        if (!valid) return -1;
        if (isAce()) {
            if (index == 1) return 1;
            else if (index == 2) return 11;
            else return -1;
        }
        switch (rank) {
            case "J":
            case "Q":
            case "K": return 10;
            default:
                try {
                    return Integer.parseInt(rank);
                } catch (NumberFormatException e) {
                    return -1;
                }
        }
    }

    public boolean isJack() { return "J".equals(rank); }

    public boolean isAce() { return "A".equals(rank); }

    public int getCardSuit() {
        if (!valid) return -1;
        switch (suit) {
            case "C": return 1;
            case "D": return 2;
            case "H": return 3;
            case "S": return 4;
            default: return -1;
        }
    }

    public int getCardRank() {
        if (!valid) return -1;
        switch (rank) {
            case "A": return 14;
            case "K": return 13;
            case "Q": return 12;
            case "J": return 11;
            default:
                try {
                    return Integer.parseInt(rank);
                } catch (NumberFormatException e) {
                    return -1;
                }
        }
    }

    public String getRank() { return rank; }
    public String getSuit() { return suit; }

    public String toString() {
        if (!valid) return "Invalid card";
        String rankName = CardUtil.rankToWord(rank);
        String suitName = CardUtil.suitToWord(suit);
        if (isAce()) return rankName + " of " + suitName + " (value = 1 or 11)";
        return rankName + " of " + suitName + " (value = " + getCardValue(1) + ")";
    }
}
