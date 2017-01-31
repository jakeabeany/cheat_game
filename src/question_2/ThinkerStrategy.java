/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question_2;

import java.util.Random;

/**
 *
 * @author Jake McVey
 */
public class ThinkerStrategy implements Strategy{
    Hand discardedCards;
    private final int RANDOM_PERCENTAGE = 18; 
    // used to cheat a certain % of the time
    Random rand = new Random();
        
    @Override
    public boolean cheat(Bid b, Hand h) {
        Card.Rank bidRank = b.getRank();
                
        //return true if its impossible to not call cheat
        if(h.countRank(bidRank) < 1 
                    && h.countRank(bidRank.getNext()) < 1)
            return true;
        
        int randNum = rand.nextInt(100)+1;
        
        // cheat some of the time when you dont have to
        if(randNum < RANDOM_PERCENTAGE)
            return true;
        return false;
    }

    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        Card.Rank lastBid = b.getRank(), rankToGet;
        Hand returnHand = new Hand();
        int n = 0;
        if(cheat){
            //randomly chose a card to cheat with
            int randNum = rand.nextInt(h.handSize());
            Card cardToRemove = h.remove(randNum);
            
            returnHand.add(cardToRemove);
            rankToGet = b.getRank().getNext();
        }else{// IS NOT CHEATING
            
            //find out which rank of card to play
            if(h.countRank(lastBid) 
                            < h.countRank(lastBid.getNext()))
                rankToGet = lastBid.getNext();
            else
                rankToGet = lastBid;
            
            //sometimes play a random amount of cards instead of all
            int randNum = rand.nextInt(100) + 1;
            //dont play all possible cards, get a random number of cards
            //between 1 - countRank(rankToGet)
            if(randNum < RANDOM_PERCENTAGE){
                n = rand.nextInt(h.countRank(rankToGet));
            }
            
            
            // loop through players hand and populate returnHand with cards
            // of the rank which we will be biddding
            for(Object c : h){
                Card card = (Card) c;
                if(card.getRank().equals(rankToGet) 
                                            && n < h.countRank(rankToGet)){
                    returnHand.add(card);
                    n++;
                }
            }
        }
        
        // remove the bid cards from the players hand.
        h.remove(returnHand);

        Bid returnBid = new Bid(returnHand, rankToGet);
        
        return returnBid;
    }
    

    @Override
    public boolean callCheat(Hand h, Bid b) {
        
        return false;
    }
    
}
