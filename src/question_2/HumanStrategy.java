package question_2;
import java.util.Scanner;
/**
 * human strategy allows a real player to play the game
 * @author Jake McVey
 */
public class HumanStrategy implements Strategy{
    Scanner scan = new Scanner(System.in);
    
    /**
     * ask the player if they want to cheat
     * @param b the bid of the last player
     * @param h the current players hand
     * @return true or false the players decision to cheat
     */
    @Override
    public boolean cheat(Bid b, Hand h) {
        Card.Rank bidRank = b.getRank();
        //sort hand for easier reading
        h.sortAscending();
        
        //return true if its impossible to not call cheat
        if(h.countRank(bidRank) < 1 
                    && h.countRank(bidRank.getNext()) < 1)
            return true;
        
        String shouldCheat;
        
        System.out.println("\nYour hand is:");
        for(int i=0;i<13;i++){
           if(h.numOfEachNumber[i] > 0){
               
               System.out.println(h.numOfEachNumber[i] + " x " 
                               + Card.Rank.values()[i]);

           }
        }       
        
        System.out.println("\nThere are legal moves you can make.");
        System.out.println("But do you want to cheat? 'yes' or 'no'");
        shouldCheat = scan.next();
        
        return(shouldCheat.equals("yes"));
    }
    
    /**
     * query the player to find out what they want to bid
     * @param b the previous players bid
     * @param h current players hand
     * @param cheat whether or not they elected to cheat
     * @return the players bid
     */
    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        Bid returnBid;
        int chosenRankToBid, n = 0, numToPlay = 0;
        Card.Rank bidRank;
        Hand returnHand;
        
        //print last bid and possible bid options
        Card.Rank possibleChoices[] = new Card.Rank[2];
        possibleChoices[0] = b.getRank();
        possibleChoices[1] = b.getRank().getNext();
        System.out.println("\nYou can legally play either "
                + "a " + possibleChoices[0] + " or a " + possibleChoices[1]);
        
        // if the user is cheating ask for a valid card and how many of it
        // they would like to play
        if(!cheat){
            //ask player for rank to play
            System.out.println("\nWhat card rank would you like to play?");

            for(int k=0;k<13;k++){
               if(h.numOfEachNumber[k] > 0){
                   System.out.println(k + " <-- " + h.numOfEachNumber[k] + " x " 
                                            + Card.Rank.values()[k]);

               }
            }
            chosenRankToBid = scan.nextInt();

            //assign chosen rank to play to bidRank
            bidRank = Card.Rank.values()[chosenRankToBid];
            
            boolean shouldContinue = false;
            do{
                //ask player how many of this card they want to play
                System.out.println("\nHow many of this rank do you want to play?");
                numToPlay = scan.nextInt();
                if(numToPlay <= h.numOfEachNumber[chosenRankToBid] && numToPlay != 0)
                    shouldContinue = true;
            }while(!shouldContinue);
            
            
            //remove cards to play from hand
            returnHand = new Hand();
            for(Object c : h){
                Card card = (Card) c;
                if(card.getRank().equals(bidRank) && n < numToPlay){
                    returnHand.add(card);
                    n++;
                }
            }
        }else{
            // loop until player enters a valid amount of cards
            do{
                // ask player how many cards they would ACTUALLY like to play
                System.out.println("\nHow many cards do you want to play? 1-4");
                numToPlay = scan.nextInt();
            }while (numToPlay > 4 || numToPlay == 0);
            
            // print hand and number of each rank
            System.out.println("\nCards you can play: ");
            for(int k=0;k<13;k++){
               if(h.numOfEachNumber[k] > 0){
                   System.out.println(k + " <-- " + h.numOfEachNumber[k] + " x " 
                                            + Card.Rank.values()[k]);

               }
            }
            
            returnHand = new Hand();
            
            // loop until the player has chosen numToPlay amount of cards
            for(int i = 0; i < numToPlay; i++){
                //ask player for rank to play
                System.out.println("\nCard " + (i+1) 
                            + ". What card rank would you like to play?");
                chosenRankToBid = scan.nextInt();
                
                //assign chosen rank to play to bidRank
                bidRank = Card.Rank.values()[chosenRankToBid];
                
                for(Object c : h){
                    Card card = (Card) c;
                    if(card.getRank().equals(bidRank)){
                        returnHand.add(card);
                        break;
                    }
                }
            }
            
            // ask player which rank to CLAIM they played
            System.out.println("\nWhich rank would you like to claim "
                    + "you played?");
            
            System.out.println(possibleChoices[0].ordinal() 
                                    + "<- " + possibleChoices[0]);
            System.out.println(possibleChoices[1].ordinal() 
                                    + "<- " + possibleChoices[1]);
            
            chosenRankToBid = scan.nextInt();
            bidRank = Card.Rank.values()[chosenRankToBid];
        }
        
        // remove the cards to remove from players hand
        h.remove(returnHand);
        
        returnBid = new Bid(returnHand, bidRank);
        return returnBid;        
    }
    
    /**
     * ask they player if they would like to call cheat
     * @param h the current players hand
     * @param b the bid of the last player
     * @return true or false whether or not the player wants to call cheat
     */
    @Override
    public boolean callCheat(Hand h, Bid b) {
        String callCheat;
        System.out.println("\nYour hand is: ");
        for(int k=0;k<13;k++){
           if(h.numOfEachNumber[k] > 0){
               System.out.println(h.numOfEachNumber[k] + " x " 
                               + Card.Rank.values()[k]);

           }
        }
        
        System.out.println("\nDo you want to call cheat? 'yes' or 'no'");
        callCheat = scan.next();
        
        return(callCheat.equals("yes"));
    }
    
}
