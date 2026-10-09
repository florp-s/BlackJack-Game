import java.util.Scanner;

public class ConsoleSource extends CardSource {
    private Scanner in;

    public ConsoleSource() {
        in = new Scanner(System.in);
    }

    @Override
    public Card dealCard() {
        System.out.print("Enter a card (ex: AH, 10S, QC). Type Q to quit: ");
        String s = in.nextLine().trim().toUpperCase();

        if (s.equals("Q")) {
            return null;
        }

        if (CardUtil.isValidCardString(s)) {
            return new Card(s);
        } else {
            System.out.println("Invalid card. Returning null.");
            return null;
        }
    }
}
