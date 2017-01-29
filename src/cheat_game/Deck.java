/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheat_game;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author nsw14ntu
 */
public class Deck implements Iterable, Serializable{
    ArrayList<Card> myDeck = new ArrayList<>();;
    static final long serialVersionUID = 101;
    private String filename;
    
    
    public Deck() throws FileNotFoundException, IOException{
        // create deck of cards
        for(Card.Suit suit : Card.Suit.values())
            for(Card.Rank rank : Card.Rank.values())
                this.myDeck.add(new Card(rank, suit));
        
        
        /********************************************************/
        /*            create ser file with oddEven deck         */
        /********************************************************/
        ArrayList<Card> serializedDeck = new ArrayList<>();
        Iterator itr = iterator();
        while(itr.hasNext()){
            Card card = (Card) itr.next();
            serializedDeck.add(card);
        }
        
        this.filename = "deck.ser";
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        
        out.writeObject(serializedDeck);
        out.close();
    }
    
    public void shuffle(){
        Random r = new Random();
        int randNum;
        for(int i = 0; i < myDeck.size(); i++){
            randNum = r.nextInt(myDeck.size());
            Collections.swap(myDeck, i, randNum);
        }
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
