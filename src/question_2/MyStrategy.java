package question_2;

import java.util.Random;

/**
 * mystrategy is a strategy that can be used by a player to determine
 * how to play the cheatGame. involves a smart way of cheating
 * @author Jake McVey
 */
public class MyStrategy implements Strategy{
    boolean smartCheat = false;
    
    /**
     * decides on whether to cheat. returns true if its impossible not 
     * to cheat, false otherwise.
     * smartCheat will check if it can play more of the nextRank(), and if so
     * play 3 random high cards and claim they are of rank nextRank(). 
     * assuming there are 2 players this will allow us to dump our hand 
     * quicker. will probably not work with more than 2 players
     * @param b the bid of the previous player
     * @param h the current players hand
     * @return true or false whether to cheat.
     */
    @Override
    public boolean cheat(Bid b, Hand h) {
        Card.Rank bidRank = b.getRank();
                
        //return true if its impossible to not call cheat
        if(h.countRank(bidRank) < 1 
                    && h.countRank(bidRank.getNext()) < 1)
            return true;
        
        // if there are more of the next rank than current rank
        if(h.countRank(bidRank.getNext()) > h.countRank(bidRank))
            return smartCheat = true;
        return false;
    }

    /**
     * decides on the bid to be bid by the current player.
     * has several checks to determine if the player should cheat, and 
     * subsequently whether they should 'smart cheat'. smart cheat will not
     * work as well if there are more than 2 players
     * 
     * @param b the bid of the previous player
     * @param h the hand of the current player
     * @param cheat whether or not to cheat decided in the boolean cheat() 
     * method.
     * @return the bid the player will be entering
     */
    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        Card.Rank lastBid = b.getRank();
        Hand playerHand = h;
        Bid returnBid;
        Hand returnHand = new Hand();
        
        // has to cheat
        if(cheat){
            if(!smartCheat){
                //randomly chose a card to cheat with
                Random rand = new Random();
                int randNum = rand.nextInt(h.handSize());
                Card cardToRemove;

                cardToRemove = playerHand.remove(randNum);
                returnHand.add(cardToRemove);

                returnBid = new Bid(returnHand, lastBid.getNext());
            }else{
                int howManyOfNextRank = h.countRank(lastBid.getNext());
                int counter = 0;
                
                for(Object c : h){
                    Card card = (Card) c;
                    
                    // card isnt the same as the card after the current
                    // bid, add it to the return hand
                    if(card.getRank().ordinal() != lastBid.getNext().ordinal() 
                                            && counter < howManyOfNextRank){
                        returnHand.add(card);
                        counter ++;
                    }
                }
                returnBid = new Bid(returnHand, lastBid.getNext());
            }
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
     * only calls cheat if the last bid was physically impossible
     * @param h the hand of the current player
     * @param b the bid of the last player
     * @return true or false whether to call cheat
     */
    @Override
    public boolean callCheat(Hand h, Bid b) {
        Card.Rank lastBid = b.getRank();
        
        return(h.numOfEachNumber[lastBid.ordinal()] + b.getCount() > 4);
    }
    
}
