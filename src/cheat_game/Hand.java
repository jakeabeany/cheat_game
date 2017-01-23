/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheat_game;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
/**
 *
 * @author nsw14ntu
 */
public class Hand implements Iterable{
    Collection<Card> hand;
    int[] numOfEachNumber, numOfEachSuit;
    static final long serializedVersionUID = 102;
    
    public Hand(){
        initializeCounts();
        this.hand = new ArrayList<Card>();
    }
    
    public Hand(Card[] cards){
        initializeCounts();
        for(Card card : cards){
            hand.add(card);
        }
    }
    
    public Hand(Hand differentHand){
        initializeCounts();
        for(Object card : differentHand.hand){
            this.hand.add((Card) card);
        }
    }
    
    private void initializeCounts(){
        numOfEachNumber = new int[13];
        numOfEachSuit = new int[4];
    }
    
    public void add(Card card){
        hand.add(card);
        numOfEachNumber[card.getRank().ordinal()]++;
    }
    
    public void add(Hand handToAdd){
        for(Object card : handToAdd.hand){
            Card addCard = (Card) card;
            hand.add(addCard);
            numOfEachNumber[addCard.getRank().ordinal()]++;
        }
    }
    
    public void add(Collection<Card> cardCol){
        for(Card addCard : cardCol){
            hand.add(addCard);
            numOfEachNumber[addCard.getRank().ordinal()]++;
        }
    }
    
    public int handSize(){
        return hand.size();
    }
    
    public boolean remove(Card cardToRemove){
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
    
    public Card remove(int index){
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
    
    public boolean remove(Hand otherHand){
        int removedCards = 0;
        Iterator itr = otherHand.hand.iterator();
        
        while(itr.hasNext()){
            Card otherHandCard = (Card) itr.next();
            if(hand.contains(otherHandCard)){
                hand.remove(otherHandCard);
                removedCards++;
            }
        }
        
        if(removedCards == otherHand.handSize())
            return true;
        return false;
    }
    
    public void sortAscending(){
        Collections.sort((ArrayList<Card>) hand);
    }
    
    public void sortDescending(){
        Comparator compareDescending = new Card.CompareDescending();
        Collections.sort((ArrayList<Card>) hand, compareDescending);
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
