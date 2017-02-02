package question_2;

import java.util.Random;

/**
 * thinker strategy is a strategy that can be used by a player. involves
 * random choice making sometimes
 * @author Jake McVey
 */
public class ThinkerStrategy implements Strategy{
    Hand discardedCards = new Hand();
    private final int RANDOM_PERCENTAGE = 18; 
    // used to cheat a certain % of the time
    Random rand = new Random();
    
    /**
     * cheat will always return true if it is not possible to not cheat.
     * will also return true to cheat RANDOM_PERCENTAGE / 100 times.
     * 
     * @param b the bid of the previous player
     * @param h the hand of the current player
     * @return true or false whether to cheat
     */
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
    /**
     * if cheating it will more likely choose highwer cards to bid with.
     * if not cheating it will generally play all cards of the best rank
     * to play, but will RANDOM_PERCENTAGE/100 times bid less than possible.
     * 
     * @param b the bid of the previous player
     * @param h the hand of the current player
     * @param cheat whether or not to cheat
     * @return 
     */
    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        Card.Rank lastBid = b.getRank(), rankToGet;
        Hand returnHand = new Hand();
        int n = 0;
        if(cheat){
            int randNumber = rand.nextInt(100)+1;
            
            Card cardToRemove;
            h.sortDescending();
            
            int j = rand.nextInt((h.handSize() / 2) + 1);
            
            if(randNumber > 70){
                cardToRemove = h.remove(j);
            }else{
                //cardToRemove = h.remove(h.handSize() - j - 1);
                cardToRemove = h.remove(rand.nextInt((h.handSize() 
                            - (h.handSize()/2)) + (h.handSize()/2)));
            }
            
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
    
    /**
     * discardedCards keeps track of cards that have been discarded. then
     * it will look through these cards, as well as the cards in hand
     * currently in order to decide whether or not to call cheat on a player.
     * 
     * it will also call cheat a small percentage of the time absed on cards
     * that have been played before.
     * 
     * @param h the hand of the current player
     * @param b the bid of the previous player
     * @return true or false whether to call cheat
     */
    @Override
    public boolean callCheat(Hand h, Bid b) {
        Hand bidHand = b.h;
        Card.Rank bidRank = b.r;
        
        int bidHandSize = bidHand.handSize();
        int countRankInHand    = h.countRank(bidRank);
        int countRankInDiscard = discardedCards.countRank(bidRank);
        int discardHandSize = discardedCards.handSize();
        
        //impossible bid
        if((4 - countRankInHand - countRankInDiscard) < bidHandSize){
            return true;
        }else{
            // none of the bid card discarded
            if(discardHandSize == 0 || countRankInDiscard == 0){
                return false;
            }
            int pc = (countRankInDiscard / discardHandSize) * 100;
            boolean check = (new Random().nextInt(100)+1)<=pc;
            return check;
        }
    }
    
    /**
     * remove all cards from the discardedCards.
     * used if cheat is called because all discarded cards will be placed
     * into a players hand.
     */
    public void resetDiscards(){
        discardedCards.remove(discardedCards);
    }
}
