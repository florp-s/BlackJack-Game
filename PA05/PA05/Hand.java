import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hand {

    private static class Node {
        Card card;
        Node next;
        Node(Card c) { this.card = c; }
    }

    private String identity;
    private CardSource source;

    private boolean continueHand;
    private int value;
    private int altValue;
    private int numCards;

    private boolean jackFound;
    private boolean aceFound;
    private boolean blackjack;
    private boolean have21;
    private boolean bust;

    private Node head, tail;

    public Hand(String identity, CardSource source) {
        this.identity = identity;
        this.source = source;
        newHand();
    }

    public void newHand() {
        head = tail = null;
        continueHand = true;
        value = 0;
        altValue = 0;
        numCards = 0;
        jackFound = false;
        aceFound = false;
        blackjack = false;
        have21 = false;
        bust = false;
    }

    public void endHand() {
        continueHand = false;
    }

    public void getNextCard() {
        if (!continueHand) return;

        Card next = source.dealCard();
        if (next == null || !next.isValid()) {
            continueHand = false;
            return;
        }

        append(next);
        updateState(next);
    }

    private void append(Card c) {
        Node n = new Node(c);
        if (head == null) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        numCards++;
    }

    private void updateState(Card c) {
        int primaryAdd = c.isAce() ? 1 : c.getCardValue(1);
        value += primaryAdd;

        if (c.isAce()) aceFound = true;
        if (c.isJack()) jackFound = true;

        if (aceFound) {
            int possible = value + 10;
            if (possible <= 21)
                altValue = possible;
            else
                altValue = value;
        } else {
            altValue = value;
        }

        if (numCards == 2 && aceFound && jackFound)
            blackjack = true;

        have21 = (value == 21) || (altValue == 21);

        bust = (value > 21) && (altValue > 21);

        if (blackjack || have21 || bust)
            continueHand = false;
    }

    public boolean isBlackjack() { return blackjack; }
    public boolean is21() { return have21; }
    public boolean isBust() { return bust; }
    public boolean continueHand() { return continueHand; }

    public int getHandValue(int index) {
        if (index == 1) return value;
        if (index == 2) return altValue;
        return 0;
    }

    private boolean sort() {
        try {
            ArrayList<Card> list = new ArrayList<>();
            Node cur = head;
            while (cur != null) {
                list.add(cur.card);
                cur = cur.next;
            }

            Collections.sort(list, new Comparator<Card>() {
                @Override
                public int compare(Card a, Card b) {
                    int r = Integer.compare(a.getCardRank(), b.getCardRank());
                    if (r != 0) return r;
                    return Integer.compare(a.getCardSuit(), b.getCardSuit());
                }
            });

            head = tail = null;
            numCards = 0;

            for (Card c : list) append(c);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void print() {
        sort();

        System.out.println(identity + "'s hand:");
        System.out.print("The cards are: ");
        if (head == null) {
            System.out.println("<none>");
        } else {
            Node cur = head;
            while (cur != null) {
                System.out.print(cur.card.toString());
                cur = cur.next;
                if (cur != null) System.out.print(", ");
            }
            System.out.println();
        }

        if (aceFound && altValue <= 21 && altValue != value)
            System.out.println("The hand is worth " + value + " or " + altValue);
        else
            System.out.println("The hand is worth " + value);

        if (blackjack)
            System.out.println("Congratulations--you have Blackjack!");
        else if (bust)
            System.out.println("The hand has busted");
    }
}
