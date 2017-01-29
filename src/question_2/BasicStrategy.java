package question_2;

import java.util.Random;

/**
 * basic strategy to play the cheat game
 * @author Jake McVey
 */
public class BasicStrategy implements Strategy{
    /**
     * 
     * @param b the bid of the previous player
     * @param h the hand of the current player
     * @return returns true if there is no way for the 
     *  player to play a valid card
     */
    @Override
    public boolean cheat(Bid b, Hand h) {
        Card.Rank bidRank = b.getRank();
        Hand playerHand = h;
        
        return(playerHand.countRank(bidRank) < 1 
                && playerHand.countRank(bidRank.getNext()) < 1);
    }
    
    /**
     * chooseBid decides what to bid when given a hand and the previous bid
     * will always play lowest rank possible unless the next highest rank
     * has more cards to be played.
     * @param b the bid of the previous player
     * @param h the hand of the current player
     * @param cheat true or false should the player cheat
     * @return the current players bid
     */
    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        Card.Rank lastBid = b.getRank();
        Hand playerHand = h;
        Bid returnBid;
        Hand returnHand = new Hand();
        
        // has to cheat
        if(cheat){
            //randomly chose a card to cheat with
            Random rand = new Random();
            int randNum = rand.nextInt(h.handSize());
            Card cardToRemove;
            
            cardToRemove = playerHand.remove(randNum);
            
            returnHand.add(cardToRemove);
            
            returnBid = new Bid(returnHand, lastBid.getNext());
        }else{// doesnt have to cheat
            Card.Rank rankToGet;
            
            //find out which rank of card to play
            if(playerHand.countRank(lastBid) 
                            < playerHand.countRank(lastBid.getNext()))
                rankToGet = lastBid.getNext();
            else
                rankToGet = lastBid;
            
            // loop through players hand and populate returnHand with cards
            // of the rank which we will be biddding
            for(Object c : playerHand){
                Card card = (Card) c;
                if(card.getRank().equals(rankToGet)){
                    returnHand.add(card);
                }
            }
            
            // remove the bid cards from the players hand.
            playerHand.remove(returnHand);
            
            returnBid = new Bid(returnHand, rankToGet);
            
        }
        return returnBid;
    }
    
    /**
     * decides whether or not to call cheat. will only call cheat if the
     * last players bid is impossible.
     * @param h current players hand
     * @param b last players bid
     * @return true or false whether to cheat
     */
    @Override
    public boolean callCheat(Hand h, Bid b) {
        Card.Rank lastBid = b.getRank();
        
        return(h.numOfEachNumber[lastBid.ordinal()] + b.getCount() > 4);
    }
    
}
