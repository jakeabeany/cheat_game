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
public class BasicStrategy implements Strategy{

    @Override
    public boolean cheat(Bid b, Hand h) {
        Card.Rank bidRank = b.getRank();
        Hand playerHand = h;
        
        return(playerHand.countRank(bidRank) < 1 
                && playerHand.countRank(bidRank.getNext()) < 1);
    }

    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        Card.Rank lastBid = b.getRank();
        Hand playerHand = h;
        Bid returnBid;
        Hand returnHand = new Hand();
        
        // has to cheat
        if(cheat){
            Random rand = new Random();
            int randNum = rand.nextInt(h.handSize());
            Card cardToRemove;
            
            cardToRemove = playerHand.remove(randNum);
            
            returnHand.add(cardToRemove);
            
            returnBid = new Bid(returnHand, lastBid.getNext());
        }else{// doesnt have to cheat
            
            Card.Rank rankToGet;
            
            //find out which rank to remove from player hand
            if(playerHand.countRank(lastBid) < playerHand.countRank(lastBid.getNext()))
                rankToGet = lastBid.getNext();
            else
                rankToGet = lastBid;
            
            // loop through player hand and populate return hand with cards
            // of the rank which we will be biddding
            for(Object c : playerHand){
                Card card = (Card) c;
                if(card.getRank().equals(rankToGet))
                    returnHand.add(card);
            }
            
            // remove the bid cards from the players hand.
            playerHand.remove(returnHand);
            
            returnBid = new Bid(returnHand, rankToGet);
            
        }
        return returnBid;
    }

    @Override
    public boolean callCheat(Hand h, Bid b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
