/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question_2;
import java.util.Scanner;
/**
 *
 * @author Jake McVey
 */
public class HumanStrategy implements Strategy{
    Scanner scan = new Scanner(System.in);
    @Override
    public boolean cheat(Bid b, Hand h) {
        String shouldCheat;
        System.out.println("The last bid was " + b.toString() + ".");
        System.out.println("Your hand is " + h.toString() + ".\n");
        
        System.out.println("Do you want to cheat? 'yes' or 'no'");
        shouldCheat = scan.nextLine();
        
        return(shouldCheat.equals("yes"));
    }

    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        Bid returnBid;
        int chosenRankToBid, i = 0;
        
        
        //print last bid and possible bid options
        System.out.println("The last bid was " + b.toString() + ".");
        Card.Rank possibleChoices[] = new Card.Rank[2];
        possibleChoices[0] = b.getRank();
        possibleChoices[1] = b.getRank().getNext();
        System.out.println("You can play either a " + possibleChoices[0] 
                + " or a " + possibleChoices[1]);
        
        
        //print hand and ask for rank to play
        System.out.println("Your hand is " + h.toString() + ".\n");
        System.out.println("What card rank would you like to play?");
        
        for(Object c : h){
            Card card = (Card) c;
            System.out.println(card.getRank().ordinal() + "<- " + card + 
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

    @Override
    public boolean callCheat(Hand h, Bid b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
