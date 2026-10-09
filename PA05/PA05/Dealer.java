

public class Dealer
{ 
   public Hand hand;
   
   public Dealer(CardSource source)
   {
		hand = new Hand("Dealer",source);
   }
	
   public void evalPosition()
   {
      if (hand.getHandValue(1) == hand.getHandValue(2))
         System.out.println("The value of the dealer's hand is " + hand.getHandValue(1));
      else   
         System.out.println("The value of the dealer's hand is " 
         + hand.getHandValue(1) + " or " 
         + hand.getHandValue(2));
         
      if (hand.isBlackjack())
         System.out.println("Dealer has blackjack!!");
      else if (hand.is21())
         System.out.println("Dealer has 21!!");
      else if (hand.isBust())
          System.out.println("Dealer has busted.");
   }
   
   public void dealInitCard()
   {
       hand.getNextCard();
       evalPosition();
   }
    
   public void takeTurn(int playerHandValue)
   {
       while (hand.continueHand())
       {
          if (hand.getHandValue(1) < playerHandValue)
          {
              System.out.println("Dealer elects a hit");
              hand.getNextCard();
              evalPosition();
           }
           else
           {
               System.out.println("Dealer elects to stay");
               break;
            }
        }   
    }           
}
