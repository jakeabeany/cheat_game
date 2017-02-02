package question_2;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 * deck class creates a deck of cards. with all 52 cards and no
 * duplicates.
 * @author nsw14ntu
 */
public class Deck implements Iterable, Serializable{
    private final ArrayList<Card> deck = new ArrayList<>();;
    static final long serialVersionUID = 101;
    
    public void serialize(){
        String filename = "deck.ser";
        try{
        FileOutputStream fos =
        new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(this);
        out.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * create a new deck of cards.
     */
    public Deck(){
        // create deck of cards
        for(Card.Suit suit : Card.Suit.values())
            for(Card.Rank rank : Card.Rank.values())
                this.deck.add(new Card(rank, suit));
        
        
    }
    
    /**
     *  shuffle the deck of cards.
     */
    public void shuffle(){
        Random r = new Random();
        int randNum;
        // loop through the dekc and swap each card to a random position
        for(int i = 0; i < deck.size(); i++){
            randNum = r.nextInt(deck.size());
            Collections.swap(deck, i, randNum);
        }
    }
    
    /** 
     * remove the top card from the deck
     * @return the card that was removed
     */
    public Card deal(){
        return deck.remove(0);
    }
    
    /**
     * get the size of the deck
     * @return the amount of cards remaining in the deck
     */
    public int size(){
        return deck.size();
    }
    
    /**
     * creates a new deck
     * @return the new deck
     */
    public Deck newDeck(){
        return new Deck();
    }
    
    /**
     * @return an instance of the odd even iterator
     */
    @Override
    public Iterator<Card> iterator() {
        return new OddEvenIterator(deck);
    }
    
    /**
     * loop through the odd cards in the deck, (position 1,3,5,etc)
     * then loop through the even positions, (0,2,4,6,etc)
     */
    private static class OddEvenIterator implements Iterator<Card>{
        private int nextCard;
        private boolean loopedOdds;
        private ArrayList<Card> deck = new ArrayList<>();
        
        /**
         * constructor for the odd even iterator
         * @param myDeck the deck to be iterated
         */
        private OddEvenIterator(ArrayList<Card> myDeck) {
            this.deck = myDeck;
            this.nextCard = -2;
            this.loopedOdds = false;
        }
        
        /**
         * check if the card has another card after it
         * @return true or false if theres a next card
         */
        @Override
        public boolean hasNext() {
            if(nextCard < deck.size() - 2)
                return true;
            else if(!loopedOdds){
                nextCard = -1;
                loopedOdds = true;
                return true;
                
            }
            return false;
        }
        
        /**
         * gets the next card after the current one
         * @return the next card
         */
        @Override 
        public Card next() {
            return deck.get(nextCard+=2);
        }
    }
    
    /**
     * tostring for the deck
     * @return prints each card in the deck followed by an endline
     */
    @Override
    public String toString(){
        StringBuilder printDeck = new StringBuilder();
        for(Card card : deck)
            printDeck.append(card + "\n");
        
        return printDeck.toString();
    }
}
