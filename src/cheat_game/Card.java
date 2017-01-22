/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheat_game;
import java.io.Serializable;
import java.util.Comparator;
/**
 *
 * @author nsw14ntu
 */
public class Card implements Serializable, Comparable<Card>{
    static final int serialVersionUID = 100;
    private final Rank rank;
    private final Suit suit;
    
    Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }
    
    enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }
    
    enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9),
            TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);
            
        final int value;
        Rank(int val){
            value = val;
        }
        
        final static Rank[] vals = values();
        
        public Rank getNext(){
            return vals[(this.ordinal()+1) % vals.length];
        }
    }
    
    @Override
    public int compareTo(Card card){
        if(this.rank.compareTo(card.rank) != 0)
            return this.rank.compareTo(card.rank);
        else
            return this.suit.compareTo(card.suit);
    }
    
    public static class CompareDescending implements Comparator<Card> {
        @Override
        public int compare(Card firstCard, Card otherCard){
            return otherCard.rank.compareTo(firstCard.rank);
        }
    }
    
    public static class CompareSuit implements Comparator<Card> {
        @Override
        public int compare(Card firstCard, Card otherCard){
            int cmp = firstCard.suit.compareTo(otherCard.suit);
            
            // same suit
            if(cmp == 0)
                return firstCard.rank.compareTo(otherCard.rank);
            
            return cmp;
        }
    }
    
    
    public static int difference(Card firstCard, Card otherCard){
        if(firstCard.rank.ordinal() > otherCard.rank.ordinal())
            return firstCard.rank.ordinal() - otherCard.rank.ordinal();
        
        return otherCard.rank.ordinal() - firstCard.rank.ordinal();
    }
    
    public static int differenceValue(Card firstCard, Card otherCard){
        if(firstCard.rank.value > otherCard.rank.value)
            return firstCard.rank.value-otherCard.rank.value;
            
        return otherCard.rank.value-firstCard.rank.value;
    }
    
    Rank getRank(){
        return this.rank;
    }
    
    Suit getSuit(){
        return this.suit;
    }
    
    @Override
    public String toString(){
        StringBuilder printCard = new StringBuilder();
        printCard.append(rank + " of " + suit);
        
        return printCard.toString();
    }
}