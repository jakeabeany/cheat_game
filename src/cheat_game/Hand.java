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
    int handValue;
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
    
    public void add(Card addCard){
        hand.add(addCard);
        incrementValues(addCard);
    }
    
    public void add(Hand handToAdd){
        for(Object card : handToAdd.hand){
            Card addCard = (Card) card;
            incrementValues(addCard);
        }
    }
    
    public void add(Collection<Card> cardCol){
        for(Card addCard : cardCol){
            hand.add(addCard);
            incrementValues(addCard);
        }
    }
    
    public void incrementValues(Card card){
        numOfEachNumber[card.getRank().ordinal()]++;
        numOfEachSuit[card.getSuit().ordinal()]++;
        handValue += card.getRank().value;
    }
    
    public void decrementValues(Card card){
        numOfEachNumber[card.getRank().ordinal()]--;
        numOfEachSuit[card.getSuit().ordinal()]--;
        handValue -= card.getRank().value;
    }
    
    public int handSize(){
        return hand.size();
    }
    
    public boolean remove(Card cardToRemove){
        for(Object card : hand){
            Card cardToTest = (Card) card;
            if(cardToTest.equals(cardToRemove)){
                hand.remove(cardToTest);
                decrementValues(cardToTest);
                return true;
            }
        }
        return false;
    }
    
    public Card remove(int index){
        int count = 0;
        for(Object card: hand){
            Card cardToTest = (Card) card;
            if(count == index){
                hand.remove(cardToTest);
                decrementValues(cardToTest);
                return cardToTest;
            }
            count++;
        }
        return null;
    }
    
    public boolean remove(Hand otherHand){
        int removedCards = 0;
        
        for(Object card : otherHand){
            Card cardToTest = (Card) card;
            if(hand.contains(cardToTest)){
                hand.remove(cardToTest);
                removedCards++;
                decrementValues(cardToTest);
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
        return true;
    }
    
    public boolean isStraight(Hand testHand){
        testHand.sortAscending();
        int currentCardOrdinal = 0;
        for(Object card : testHand){
            Card cardToTest = (Card) card;
            currentCardOrdinal = cardToTest.getRank().ordinal();
            
        }
        return false;
    }
   
    public int countRank(Card.Rank rank){
        int numOfRank = 0;
        for(Object card : hand){
            Card cardToTest = (Card) card;
            if(cardToTest.getRank() == rank)
                numOfRank++;
        }
        return numOfRank;
    }
    
    public int countSuit(Card.Suit suit){
        int numOfSuit = 0;
        for(Object card : hand){
            Card cardToTest = (Card) card;
            if(cardToTest.getSuit() == suit)
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
