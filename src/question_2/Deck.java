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
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author nsw14ntu
 */
public class Deck implements Iterable, Serializable{
    private final ArrayList<Card> deck = new ArrayList<>();;
    static final long serialVersionUID = 101;
    private String filename;
    
    
    public Deck(){
        // create deck of cards
        for(Card.Suit suit : Card.Suit.values())
            for(Card.Rank rank : Card.Rank.values())
                this.deck.add(new Card(rank, suit));
        
        
    }
    
    public void shuffle(){
        Collections.shuffle(deck);
    }
    
    public Card deal(){
        return deck.remove(0);
    }
    
    public int size(){
        return deck.size();
    }
    
    public Deck newDeck() throws IOException{
        return new Deck();
    }
    
    @Override
    public Iterator<Card> iterator() {
        return new OddEvenIterator(deck);
    }
    
    private static class OddEvenIterator implements Iterator<Card>{
        private int nextCard;
        private boolean loopedOdds;
        private ArrayList<Card> deck = new ArrayList<>();
        
        
        private OddEvenIterator(ArrayList<Card> myDeck) {
            this.deck = myDeck;
            this.nextCard = -2;
            this.loopedOdds = false;
        }

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

        @Override 
       public Card next() {
            return deck.get(nextCard+=2);
        }
        
       @Override
       public void remove(){
           deck.remove(nextCard);
       }
    }
    
    @Override
    public String toString(){
        StringBuilder printDeck = new StringBuilder();
        for(Card card : deck)
            printDeck.append(card + "\n");
        
        return printDeck.toString();
    }
}
