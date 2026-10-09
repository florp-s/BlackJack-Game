

import java.util.*;

public class Blackjack
{

   /** This is my test comment
    * 
    */
 
   public static void main(String[] args)
   {
       boolean  continueGame = true;
       String   userResp;
       int      playerMaxValue;
       

        //CardSource source = (CardSource)new GUISource();
        CardSource source = (CardSource)new ConsoleSource();
        //CardSource source = (CardSource)new Shoe(4);
       
       Dealer   dealer  = new Dealer(source);
       Player   player  = new Player(source);
       
       Scanner  scanObj = new Scanner(System.in);
       
       System.out.println();
       System.out.println();
       System.out.println("Welcome to Professor M's Casino");
       System.out.println("Bets at Blackjack table must be in $10.00 increments");
       
       // Play the game if user wants to continue
       while (continueGame)
       {
          System.out.println();
          System.out.println("Starting a new game of Blackjack");
          
          player.placeBet();
          //shoe.prepForNextHand();
           
          dealer.hand.newHand();
          player.hand.newHand();
          
          // Initial two cards in casino sequence
          dealer.dealInitCard();
          player.dealInitCard();
          dealer.dealInitCard();
 
          
          // Determine if the house has won by default
          if (dealer.hand.isBlackjack())
          {
              System.out.println();             
              System.out.println("Sorry--House wins by default");
              player.tallyLoss();
          }
          else  // Continue playing
          {          
              player.dealInitCard();          
              while (player.hand.continueHand())
              {
                 if ((!player.hand.isBlackjack()) && 
                     (!player.hand.is21()) && (!player.hand.isBust()))
                 {
                    System.out.println();             
                    System.out.print("Do you want another hit? >> ");
                    userResp = scanObj.nextLine().toLowerCase();   
                    if (!(userResp.charAt(0) == 'y') )
                        player.hand.endHand();
                    else
                        player.hand.getNextCard();
                        player.evalPosition();
                 }
             }    
          
             if ((!player.hand.isBust()) && (!player.hand.isBlackjack()))
             {
                System.out.println();
                System.out.println("Now its the house's turn");   
             
                if (player.hand.getHandValue(2) > player.hand.getHandValue(1))
                    playerMaxValue = player.hand.getHandValue(2);
                else
                    playerMaxValue = player.hand.getHandValue(1);
                 
                dealer.takeTurn(playerMaxValue);
                if ((!(dealer.hand.isBust())) && 
                    (dealer.hand.getHandValue(1) >= playerMaxValue))
                {
                     System.out.println();             
                     System.out.println("House wins");    
                     player.tallyLoss();
                }
                else
                {
                    System.out.println();             
                    System.out.println("Congrats--you won!");
                    player.tallyWin();
                }
             }
             else if (player.hand.isBlackjack())
             {
                 System.out.println();                            
                 System.out.println("Congrats--you won!");
                 player.tallyWin();
             }
             else
             {
                 System.out.println();             
                 System.out.println("House wins");
                 player.tallyLoss();
             }     
          }
          
          player.hand.print();
          dealer.hand.print();
          
          // Determine if user wants to continue
          System.out.println();             
          System.out.print("Do you want to play a new hand? >> ");
          userResp = scanObj.nextLine().toLowerCase();
          if (!(userResp.charAt(0) == 'y') )
          {
              continueGame = false;
              System.out.println("Nice playing with you!!");
          }           
       }    
    }      
}
