package question_2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
/**
 * the hand class is used to store an amount of cards
 * @author nsw14ntu
 */
public class Hand implements Iterable{
    Collection<Card> hand;
    int[] numOfEachNumber, numOfEachSuit;
    int handValue;
    static final long serializedVersionUID = 102;
    
    /**
     * initialise a hand with no cards in it
     */
    public Hand(){
        initializeCounts();
        this.hand = new ArrayList<Card>();
    }
    
    /**
     * initialise a hand containing the cards from a Card[] array
     * @param cards the card array to be put in the hand
     */
    public Hand(Card[] cards){
        initializeCounts();
        for(Card card : cards){
            hand.add(card);
        }
    }
    
    /**
     * initialise a hand containing the cardds from a different hand
     * @param differentHand the different hand to add to current hand
     */
    public Hand(Hand differentHand){
        initializeCounts();
        for(Object card : differentHand.hand){
            this.hand.add((Card) card);
        }
    }
    
    /**
     * initialise arrays to be used for counting numSuits and number of each
     * rank
     */
    private void initializeCounts(){
        numOfEachNumber = new int[13];
        numOfEachSuit = new int[4];
    }
    
    /**
     * add a single card to the hand
     * @param addCard the card to be added
     */
    public void add(Card addCard){
        hand.add(addCard);
        incrementValues(addCard);
    }
    
    /**
     * add a hand to an existing hand
     * @param handToAdd the hand to be added
     */
    public void add(Hand handToAdd){
        for(Object card : handToAdd.hand){
            Card addCard = (Card) card;
            hand.add(addCard);
            incrementValues(addCard);
        }
    }
    
    /**
     * add a collection of cards to a hand
     * @param cardCol the collection of cards to add
     */
    public void add(Collection<Card> cardCol){
        for(Card addCard : cardCol){
            hand.add(addCard);
            incrementValues(addCard);
        }
    }
    
    /**
     * used to increment the counters if a card is added to a hand
     * @param card the card being added
     */
    public void incrementValues(Card card){
        numOfEachNumber[card.getRank().ordinal()]++;
        numOfEachSuit[card.getSuit().ordinal()]++;
        handValue += card.getRank().value;
    }
    
    /**
     * used to decrement the counters if a card is removed from the hand
     * @param card the card being removed from the hand
     */
    public void decrementValues(Card card){
        numOfEachNumber[card.getRank().ordinal()]--;
        numOfEachSuit[card.getSuit().ordinal()]--;
        handValue -= card.getRank().value;
    }
    
    /**
     * get the size of the hand
     * @return the amount of cards left in the hand
     */
    public int handSize(){
        return hand.size();
    }
    
    /**
     * remove a single card from the hand
     * @param cardToRemove the card to be removed
     * @return true or false whether or not the card was removed
     */
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
    
    /**
     * remove a card from the hand at a certain index
     * @param index the index of the card to be removed
     * @return the card that was removed
     */
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
    
    /**
     * remove a hand of cards from the current hand
     * @param otherHand the hand to be removed
     * @return true or false if all cards from the passed hand were removed.
     */
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
    
    /**
     * sort the cards in the hand into ascending order
     */
    public void sortAscending(){
        Collections.sort((ArrayList<Card>) hand);
    }
    
    /**
     * sort the cards in the hand to descending order
     */
    public void sortDescending(){
        Comparator compareDescending = new Card.CompareDescending();
        Collections.sort((ArrayList<Card>) hand, compareDescending);
    }
    
    
    /**
     * check if the hand is a straight.
     * a straight is an unbroken chain of cards increasing in rank
     * with no duplicated.
     * eg, 4 5 6 7 | or | jack queen king ace two
     * @return true or false whether its a straight
     */
    public boolean isStraight(){
        // return false if any duplicated
        for(int i = 0; i < 13; i++)
            if(numOfEachNumber[i] > 1)
                return false;
        
        this.sortDescending();
        boolean onFirstCard = true;
        int onLastCard = this.handSize(), i = 1;
        Card firstCard = null, lastCard = null;
        
        // get first and last card of the hand.
        for(Object card : hand){
            if(onFirstCard){
                firstCard = (Card) card;
                onFirstCard = false;
            }
            if(onLastCard == i){
                lastCard = (Card) card;
            }
            i++;
        }
        
        int straightTest = (firstCard.getRank().ordinal() 
                - lastCard.getRank().ordinal());
        
        if(straightTest == (this.handSize()-1))
            return true;
        return false;
    }
    
    /**
     * check if the hand is a flush
     * a flush is when all cards in the hand are the same suit.
     * @return true or false whether or not its a flush
     */
    public boolean isFlush(){
        // get first card in the hand
        boolean isFirstCard = true;
        Card firstCard = null, testCard = null;
        
        for(Object card : hand){
            if(isFirstCard){
                firstCard = (Card) card;
                isFirstCard = false;
            }else{
                testCard = (Card) card;
                if(!firstCard.getSuit().equals(testCard.getSuit()))
                    return false;
            }
        }
        return true;
    }
    
    /**
     * count the number of a given rank in the hand
     * @param rank the rank to be counted
     * @return the number cards of the given rank in the hand
     */
    public int countRank(Card.Rank rank){
        int numOfRank = 0;
        for(Object card : hand){
            Card cardToTest = (Card) card;
            if(cardToTest.getRank() == rank)
                numOfRank++;
        }
        return numOfRank;
    }
    /**
     * count the number of a given suit in the hand
     * @param suit the suit to be counted
     * @return the number of cards of the given suit in the hand
     */
    public int countSuit(Card.Suit suit){
        int numOfSuit = 0;
        for(Object card : hand){
            Card cardToTest = (Card) card;
            if(cardToTest.getSuit() == suit)
                numOfSuit++;
        }
        return numOfSuit;
    }
    
    /**
     * iterator used to iterate though the hand
     * @return 
     */
    @Override
    public Iterator<Card> iterator() {
        Iterator<Card> it = new Iterator<Card>(){
            private int index = 0;
            
            /**
             * check if there is a next card after the current in the hand
             * @return true or false if hasNext
             */
            @Override
            public boolean hasNext() {
                if(index < hand.size())
                    return true;
                return false;
            }
            
            /**
             * get the next card in the hand
             * @return the next card
             */
            @Override
            public Card next() {
                return (Card) hand.toArray()[index++];
            }
        };
        return it;
    }
    
    /**
     * a toString to display the cards in the hand
     * @return the return string
     */
    @Override
    public String toString(){
        StringBuilder printHand = new StringBuilder();
        for(Object card : hand)
            printHand.append(card + "\n");
        
        return printHand.toString();
    }
}
