import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CardGUI extends JDialog {
    private JComboBox<String> rankBox;
    private JComboBox<String> suitBox;
    private JTextField valueField;
    private JButton randomBtn;
    private JButton submitBtn;
    private JButton cancelBtn;

    private String chosenCardString = null;

    private static final String[] RANKS =
        { "A","2","3","4","5","6","7","8","9","10","J","Q","K" };
    private static final String[] SUITS =
        { "C","D","H","S" };

    public CardGUI() {
        setTitle("Deal a Card");
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        rankBox = new JComboBox<>(RANKS);
        suitBox = new JComboBox<>(SUITS);
        valueField = new JTextField("N/A");
        valueField.setEditable(false);

        randomBtn = new JButton("Random");
        submitBtn = new JButton("Submit");
        cancelBtn = new JButton("Cancel");

        JPanel fieldsPanel = new JPanel(new GridLayout(3,2,5,5));
        fieldsPanel.add(new JLabel("Rank:"));
        fieldsPanel.add(rankBox);
        fieldsPanel.add(new JLabel("Suit:"));
        fieldsPanel.add(suitBox);
        fieldsPanel.add(new JLabel("Value:"));
        fieldsPanel.add(valueField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(randomBtn);
        buttonPanel.add(submitBtn);
        buttonPanel.add(cancelBtn);

        getContentPane().setLayout(new BorderLayout(10,10));
        getContentPane().add(fieldsPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        ActionListener updateListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateValueDisplay();
            }
        };
        rankBox.addActionListener(updateListener);
        suitBox.addActionListener(updateListener);

        randomBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                makeRandomCard();
            }
        });

        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitCard();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chosenCardString = null;
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public String getCardString() {
        return chosenCardString;
    }

    private String currentCardString() {
        String rank = (String) rankBox.getSelectedItem();
        String suit = (String) suitBox.getSelectedItem();
        return rank + "-" + suit;
    }

    private void updateValueDisplay() {
        String cardStr = currentCardString();
        if (CardUtil.isValidCardString(cardStr)) {
            String valText = CardUtil.cardValueText(cardStr);
            valueField.setText(valText);
        } else {
            valueField.setText("N/A");
        }
    }

    private void makeRandomCard() {
        String randStr = CardUtil.randomCardString(); // e.g. "Q-D"
        String[] parts = randStr.split("-");
        String rankPart = parts[0];
        String suitPart = parts[1];

        rankBox.setSelectedItem(rankPart);
        suitBox.setSelectedItem(suitPart);

        updateValueDisplay();
    }

    private void submitCard() {
        String cardStr = currentCardString();
        if (CardUtil.isValidCardString(cardStr)) {
            chosenCardString = cardStr;
        } else {
            chosenCardString = null;
        }
        dispose();
    }
}
