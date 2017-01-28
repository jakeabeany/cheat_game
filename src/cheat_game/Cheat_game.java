/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheat_game;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;


/**
 *
 * @author nsw14ntu
 */
public class Cheat_game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
                        throws FileNotFoundException, IOException {
        //System.out.println(myCard.toString());
        
        Deck deck = new Deck();
        
        //System.out.println(deck.toString());
        
        //Collections.shuffle(deck.myDeck);
        
        //System.out.println(deck.toString());
        
        //System.out.println(" -- ");
        
        //System.out.println(deck.deal());
        
        //System.out.println(deck.size());
        
        deck = deck.newDeck();
        Collections.shuffle(deck.myDeck);
        
        //System.out.println(deck.toString());
        
        /*System.out.println("Iterator: \n");
        
        
        Iterator itr = deck.iterator();
        int i = 1;
        while(itr.hasNext()){
            System.out.print(i + " ");
            Card card = (Card) itr.next();
            System.out.println(card);
            i++;
        }*/
        
        
        
        //for(int i = 0; i < testhand.length; i++){
        //    testhand[i] = deck.deal();
        //}
        
        //for(Card card : testhand){
        //    System.out.println(card.toString());
        //}
        Card kys = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
        Card kms = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS);
        
        Card kcs = new Card(Card.Rank.EIGHT, Card.Suit.SPADES);
        Card kks = new Card(Card.Rank.SEVEN, Card.Suit.SPADES);
        Card kps = new Card(Card.Rank.SIX, Card.Suit.SPADES);
        
        Card kls = new Card(Card.Rank.FOUR, Card.Suit.HEARTS);
        
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();
        
        Collection<Card> col1 = new ArrayList<>();
        col1.add(kls);
        
        
        //hand1.add(col1);
       // hand2.add(kms);
        //goat.add(kcs);
        
        hand1.add(kps);
        hand1.add(kms);
        hand1.add(kcs);
        hand1.add(kls);
        hand1.add(kls);
        
        hand2.add(kms);
        hand1.remove(hand2);
        //hand1.add(col1);
        
        //hand1.remove(kms);
        
        Scanner scan = new Scanner(System.in);
        int i = 0;
        for(Object c : hand1){
            Card card = (Card) c;
            System.out.println(card.getRank().ordinal() + " " + card + hand1.numOfEachNumber[card.getRank().ordinal()]);
            i++;
        }
        i = scan.nextInt();
        
        Card.Rank test = Card.Rank.values()[i];
        
        System.out.println(test);
//        System.out.println(hand1);
//        System.out.println(hand1.isFlush());
//        System.out.println(hand1.handValue);
        //System.out.println(hand1.handValue);
        
        //hand1.add(hand2);
        
        //hand1.remove(kms);
        
        
        //System.out.println(hand1.countRank(Card.Rank.ACE));
        //hand1.remove(kms);
        
        //Hand lol = new Hand(goat);
        
        //hand1.remove(goat);
        
        //System.out.println(lol.toString());
        
        //System.out.println(hand1.toString());
        
        //hand1.sortDescending();
        
        //System.out.println("\n" + hand1.toString());
        
        ///hand1.sortAscending();
        
        //System.out.println("\n" + hand1.toString());
        
        //for(int i = 0; i < 13; i++)
        //System.out.println(i + ": " + hand1.numOfEachNumber[i]);
        
        //System.out.println("\n");
        
        //for(int i = 0; i < 4; i++)
        //    System.out.println(Card.Suit.values()[i] + ": " + hand1.numOfEachSuit[i]);
        
        /*
        Iterator itr = hand1.iterator();
        while(itr.hasNext()){
            Card card = (Card) itr.next();
            System.out.println(card.toString());
        }
        */
        
        
        
        
        
        
        
        //System.out.println(Card.difference(oCard, myCard));
        
        //System.out.println(myCard.rank + " of " + myCard.suit);
        
        //System.out.println(myCard.rank.getNext());
        
        //System.out.println(myCard.compareTo(oCard));
        
        //System.out.println(myCard.toString());
        
        //System.out.println(oCard.getRank());
    }
    
}
