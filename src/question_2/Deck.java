/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question_2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author nsw14ntu
 */
public class Deck implements Iterable, Serializable{
    ArrayList<Card> myDeck = new ArrayList<>();;
    static final long serialVersionUID = 101;
    private String filename;
    
    
    public Deck(){
        // create deck of cards
        for(Card.Suit suit : Card.Suit.values())
            for(Card.Rank rank : Card.Rank.values())
                this.myDeck.add(new Card(rank, suit));
        
        
    }
    
    public void shuffle(){
        
    }
    
    public Card deal(){
        return myDeck.remove(0);
    }
    
    public int size(){
        return myDeck.size();
    }
    
    public Deck newDeck() throws IOException{
        return new Deck();
    }
    
    @Override
    public Iterator<Card> iterator() {
        return new OddEvenIterator(myDeck);
    }
    
    public static class OddEvenIterator implements Iterator<Card>{
        private int nextCard;
        private boolean loopedOdds;
        private ArrayList<Card> test = new ArrayList<>();
        
        
        private OddEvenIterator(ArrayList<Card> myDeck) {
            this.test = myDeck;
            this.nextCard = -2;
            this.loopedOdds = false;
        }

        @Override
        public boolean hasNext() {
            if(nextCard < test.size() - 2)
                return true;
            else if(!loopedOdds){
                nextCard = -1;
                loopedOdds = true;
                return true;
                
            }
            return false;
        }

        @Override 
       public Card next() {
            return test.get(nextCard+=2);
        }
        
    }
    
    @Override
    public String toString(){
        StringBuilder printDeck = new StringBuilder();
        for(Card card : myDeck)
            printDeck.append(card + "\n");
        
        return printDeck.toString();
    }
}
