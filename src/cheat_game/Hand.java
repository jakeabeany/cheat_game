/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheat_game;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/**
 *
 * @author nsw14ntu
 */
public class Hand implements Iterable{
    Collection hand = new ArrayList<>();
    public int[] numOfEachNumber = new int[13];
    static final long serializedVersionUID = 102;
    
    public Hand(){
        this.hand = new ArrayList<Card>();
    }
    
    public Hand(Card[] cards){
        for(Card card : cards){
            hand.add(card);
            numOfEachNumber[card.getRank().ordinal()]++;
        }
    }
    
    public Hand(Hand differentHand){
        
    }
    
    public void addCard(Card card){
        numOfEachNumber[card.getRank().ordinal()]++;
        hand.add(card);
    }
    
    public int handSize(){
        return hand.size();
    }
    
    public boolean removeCard(Card cardToRemove){
        Iterator itr = hand.iterator();
        while(itr.hasNext()){
            Card card = (Card) itr.next();
            if(card.equals(cardToRemove)){
                hand.remove(card);
                return true;
            }
        }
        return false;
    }
    
    public boolean removeAllCards(Hand newHand){
        Iterator itr = hand.iterator();
        Iterator itr2 = newHand.iterator();
        int removedCards = 0;
        // loop through the newHand 
        while(itr2.hasNext()){
            Card checkCard = (Card) itr2.next();
            while(itr.hasNext()){
                Card handCard = (Card) itr.next();
                if(handCard.equals(checkCard)){
                    hand.remove(handCard);
                    removedCards++;
                }
            }
        }
        if(removedCards == newHand.handSize())
            return true;
        return false;
    }
    
    public Card removeCardAt(int index){
        int count = 0;
        Iterator itr = hand.iterator();
        while(itr.hasNext()){
            Card card = (Card) itr.next();
            if(count == index){
                hand.remove(card);
                return card;
            }
            count++;
        }
        return null;
    }
    
    public boolean isFlush(){
        Iterator itr = hand.iterator();
        Card firstCard = (Card) itr.next();
        Card.Suit testSuit = firstCard.getSuit();
        while(itr.hasNext()){
            Card card = (Card) itr.next();
            if(testSuit != card.getSuit())
                return false;
        }
        return true;
    }
    
    
    public int countRank(Card.Rank rank){
        Iterator itr = hand.iterator();
        int numOfRank = 0;
        while(itr.hasNext()){
            Card card = (Card) itr.next();
            if(card.getRank() == rank)
                numOfRank++;
        }
        return numOfRank;
    }
    
    public int countSuit(Card.Suit suit){
        Iterator itr = hand.iterator();
        int numOfSuit = 0;
        while(itr.hasNext()){
            Card card = (Card) itr.next();
            if(card.getSuit() == suit)
                numOfSuit++;
        }
        return numOfSuit;
    }

    @Override
    public Iterator<Card> iterator() {
        Iterator<Card> it = new Iterator<Card>(){
            private int index = 0;
            
            @Override
            public boolean hasNext() {
                if(index < hand.size())
                    return true;
                return false;
            }

            @Override
            public Card next() {
                return (Card) hand.toArray()[index++];
            }
        };
        return it;
    }
    
    @Override
    public String toString(){
        StringBuilder printHand = new StringBuilder();
        for(Object card : hand)
            printHand.append(card + "\n");
        
        return printHand.toString();
    }
}
