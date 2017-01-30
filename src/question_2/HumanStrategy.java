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
        String shouldCheat;
        System.out.println(" -------------------------------- ");
        //sort hand for easier reading
        h.sortAscending();
        System.out.println("Your hand is \n" + h.toString() + ".\n");
        
        System.out.println("Do you want to cheat? 'yes' or 'no'");
        shouldCheat = scan.nextLine();
        
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
        int chosenRankToBid, i = 0;
        
        
        //print last bid and possible bid options
        Card.Rank possibleChoices[] = new Card.Rank[2];
        possibleChoices[0] = b.getRank();
        possibleChoices[1] = b.getRank().getNext();
        System.out.println("You can legally play either "
                + "a " + possibleChoices[0] + " or a " + possibleChoices[1]);
        
        
        //ask player for rank to play
        System.out.println("What card rank would you like to play?");

        for(Object c : h){
            Card card = (Card) c;
            System.out.println(card.getRank().ordinal() + "<- " + card.getRank() + 
                    ". Have: " + h.numOfEachNumber[card.getRank().ordinal()]);
            i++;
        }
        chosenRankToBid = scan.nextInt();
        
        //assign chosen rank to play to bidRank
        Card.Rank bidRank = Card.Rank.values()[chosenRankToBid];
        
        //remove cards to play from hand
        Hand returnHand = new Hand();
        for(Object c : h){
            Card card = (Card) c;
            if(card.getRank().equals(bidRank))
                returnHand.add(card);
        }
        h.remove(returnHand);
        
        //if the chosen rank to bid is not possible then ask the user
        //which rank to claim they played
        boolean isPoss = false;
        //ask user which card to announce theyre playing
        for(int j = 0; j < possibleChoices.length; j++){
            if(bidRank.equals(possibleChoices[j]))
                isPoss = true;
        }
        
        //chosen rank is not possible, prompt new rank
        if(!isPoss){
            System.out.println("Which rank would you like to claim "
                    + "you played?");
            
            System.out.println(possibleChoices[0].ordinal() 
                                    + "<- " + possibleChoices[0]);
            System.out.println(possibleChoices[1].ordinal() 
                                    + "<- " + possibleChoices[1]);
            
            chosenRankToBid = scan.nextInt();
            bidRank = Card.Rank.values()[chosenRankToBid];
        }
        
        
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
        //System.out.println(" -------------------------------- ");
        System.out.println("Your hand is \n" + h.toString() + ".\n");
        
        System.out.println("Do you want to call cheat? 'yes' or 'no' <<");
        callCheat = scan.next();
        
        return(callCheat.equals("yes"));
    }
    
}
