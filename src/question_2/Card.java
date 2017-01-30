package question_2;
import java.io.Serializable;
import java.util.Comparator;
/**
 * a class to create a card. a card has a rank and a suit
 * @author nsw14ntu
 */
public class Card implements Serializable, Comparable<Card>{
    static final int serialVersionUID = 100;
    private final Rank rank;
    private final Suit suit;
    
    /**
     * constructor for a card
     * @param rank the rank of the card
     * @param suit the suit of the card
     */
    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }
    
    /**
     * possible suits
     */
    enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }
    
    /**
     * possible ranks. each rank has a name and a value. the value is
     * the value of the card.
     */
    enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9),
            TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);
            
        final int value;
        Rank(int val){
            value = val;
        }
        
        final static Rank[] vals = values();
        
        /**
         * gets the next rank
         * @return the rank after the current rank
         */
        public Rank getNext(){
            return vals[(this.ordinal()+1) % vals.length];
        }
    }
    
    /**
     * compare two cards
     * @param card the card to be compared against
     * @return 0 if the cards are the same, !0 if not
     */
    @Override
    public int compareTo(Card card){
        if(this.rank.compareTo(card.rank) != 0)
            return this.rank.compareTo(card.rank);
        else
            return this.suit.compareTo(card.suit);
    }
    
    /**
     * used for sorting in descending order
     */
    public static class CompareDescending implements Comparator<Card> {
        @Override
        public int compare(Card firstCard, Card otherCard){
            return otherCard.rank.compareTo(firstCard.rank);
        }
    }
    
    /**
     * used for suiting in suit order
     */
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
    
    
    /**
     * the difference in rank between two cards eg, QUEEN and TEN is 2
     * @param firstCard the first card to be compared
     * @param otherCard the second card to be compared
     * @return the difference in their rank
     */
    public static int difference(Card firstCard, Card otherCard){
        if(firstCard.rank.ordinal() > otherCard.rank.ordinal())
            return firstCard.rank.ordinal() - otherCard.rank.ordinal();
        
        return otherCard.rank.ordinal() - firstCard.rank.ordinal();
    }
    
    /**
     * the difference in value between two cards. eg, QUEEN and TEN is 0
     * @param firstCard the first cards to be compared
     * @param otherCard the second card to be compared
     * @return the difference in their values
     */
    public static int differenceValue(Card firstCard, Card otherCard){
        if(firstCard.rank.value > otherCard.rank.value)
            return firstCard.rank.value-otherCard.rank.value;
            
        return otherCard.rank.value-firstCard.rank.value;
    }
    
    /**
     * @return the rank of the card passed in
     */
    Rank getRank(){
        return this.rank;
    }
    
    /**
     * @return the suit of the card passed in
     */
    Suit getSuit(){
        return this.suit;
    }
    
    /**
     * tostring to return the card in a nice way
     * @return RANK of SUIT
     */
    @Override
    public String toString(){
        StringBuilder printCard = new StringBuilder();
        printCard.append(rank + " of " + suit);
        
        return printCard.toString();
    }
}