public class GUISource extends CardSource {
    @Override
    public Card dealCard() {
        CardGUI dlg = new CardGUI();
        String cardText = dlg.getCardString();

        if (cardText == null) {
            return null;
        }

        if (CardUtil.isValidCardString(cardText)) {
            return new Card(cardText);
        } else {
            return null;
        }
    }
}
