
import java.util.*;
public class Player
{

	private int netAmount;
	public int bet;
	public Hand hand;
	private Scanner scanObj;
	
	public Player(CardSource source)
	{
		netAmount = 0;
		hand = new Hand("Player",source);
		scanObj = new Scanner(System.in);
	}
	   
	public void placeBet()
	{
	    boolean validBet = false;
	    
	    while (!(validBet))
	    {
	       System.out.println();
	       System.out.print("Place your bet ($10.00 min) >> $");
           if ((scanObj.hasNextInt())) 
           {
               bet = scanObj.nextInt();
               if ((bet % 10 == 0) && ((bet / 10) > 0))
                   validBet = true;
                }
           if (!validBet)
           {
              scanObj.nextLine();
              System.out.println("Invalid bet -- Try again");
            }
         }
	}
	
	public int getNetAmount()
	{
	    return netAmount;
	}
	
	public void tallyLoss()
	{
	    netAmount = netAmount - bet;
	    reportNet();
	}
	
	public void tallyWin()
	{
	    netAmount = netAmount + bet;
	    reportNet();
	}	  
	
	private void reportNet()
	{
	    System.out.println();
	    if (netAmount > 0)
	        System.out.println("Your winnings are: $" + netAmount);
	    else if (netAmount < 0)
	        System.out.println("Your losses are: $" + Math.abs(netAmount));
	    else
	        System.out.println("You are free and clear");        
	}
	       
	
   public void evalPosition()
   {   
     if (hand.getHandValue(1) == hand.getHandValue(2))
         System.out.println("The value of your hand is " + hand.getHandValue(1));
      else   
         System.out.println("The value of your hand is " 
         + hand.getHandValue(1) + " or " + hand.getHandValue(2));
         
      if (hand.isBlackjack())
         System.out.println("Congrats--You have Blackjack!!");
      else if (hand.is21())
         System.out.println("You have 21!!");
       else if (hand.isBust())
          System.out.println("Sorry--You have busted.");   
   }
   
   public void dealInitCard()
   {
       hand.getNextCard();
       evalPosition();
   }
    
}
