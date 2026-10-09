Blackjack

A console-based Blackjack game written in Java. Place bets, hit or stay against the dealer, and track your winnings across multiple hands.

The game is built around a swappable card source, so the same game logic can run with cards typed in by hand, cards picked from a Swing GUI dialog, or cards dealt from a shuffled multi-deck shoe.

Features
Play multiple hands in a row with a running total of winnings or losses
Bets in $10 increments (minimum $10)
Automatic hand evaluation, including Aces counting as 1 or 11
Detects Blackjack, 21, and busts
Dealer plays automatically, hitting until it reaches or beats your hand
Hands are printed in sorted order at the end of each round
Three interchangeable card sources: console, GUI, and shoe
Test programs for the Card, Hand, and Shoe classes
Requirements
Java Development Kit (JDK) 8 or newer

Check your version with:

bash
java -version
Getting Started
1. Clone the repository
bash
git clone https://github.com/<your-username>/<your-repo-name>.git
cd <your-repo-name>
2. Compile
bash
javac *.java
3. Run
bash
java Blackjack
How to Play
Enter a bet in $10 increments.
The dealer and player are dealt their opening cards.
If the dealer has Blackjack, the house wins by default.
Otherwise, you are asked whether you want another hit. Answer y to take a card, or anything else to stay.
If you don't bust, the dealer takes its turn.
The winner is announced, your net winnings are updated, and you can choose to play another hand.
Rules in This Version
Blackjack is an Ace plus a Jack as your first two cards.
Number cards are worth their face value, J/Q/K are worth 10, and Aces are worth 1 or 11.
The dealer hits while its hand is worth less than yours and stays once it reaches or beats your total.
If the dealer ties or beats you without busting, the house wins.
If you bust, the house wins. If the dealer busts, you win.
A win or loss changes your net amount by the amount of your bet.
Choosing a Card Source

The game gets its cards through the abstract CardSource class. In Blackjack.java, uncomment the source you want to use:

java
//CardSource source = (CardSource)new GUISource();
CardSource source = (CardSource)new ConsoleSource();
//CardSource source = (CardSource)new Shoe(4);
Source	Description
ConsoleSource	You type each card in at the console. Useful for testing specific hands.
GUISource	A Swing dialog lets you pick a rank and suit, or click Random.
Shoe	Cards are dealt from a shuffled shoe of one or more decks. Call shuffle() before playing, for example Shoe s = new Shoe(4); s.shuffle();.

Cards use the format RANK-SUIT, for example A-S (Ace of Spades) or 10-H (10 of Hearts).

Ranks: A, 2-10, J, Q, K
Suits: C (Clubs), D (Diamonds), H (Hearts), S (Spades)
Project Structure
File	Purpose
Blackjack.java	Main program and game loop
Player.java	Player's hand, betting, and winnings tracking
Dealer.java	Dealer's hand and automatic playing logic
Hand.java	Stores cards in a linked list and tracks value, Blackjack, 21, and bust
Card.java	A single card with validation and value lookups
CardUtil.java	Helper methods for validating, naming, and generating cards
CardSource.java	Abstract class for anything that can deal a card
ConsoleSource.java	Card source that reads from the console
GUISource.java / CardGUI.java	Card source that uses a Swing dialog
Shoe.java	Multi-deck shoe with shuffle, deal, and inventory
CardTest.java, TestCard.java	Test programs for the Card class
HandTester.java	Runs sample hands (Blackjack, bust, soft hands, 21)
ShoeTest.java, ShoeTester.java	Test programs for the Shoe class
Running the Tests

Each test file has its own main method. For example:

bash
java CardTest
java HandTester
java ShoeTester
