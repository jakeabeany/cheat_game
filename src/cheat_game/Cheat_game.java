/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheat_game;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;


/**
 *
 * @author nsw14ntu
 */
public class Cheat_game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
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
        
        
        
        Card[] testhand = new Card[5];
        
        //for(int i = 0; i < testhand.length; i++){
        //    testhand[i] = deck.deal();
        //}
        
        //for(Card card : testhand){
        //    System.out.println(card.toString());
        //}
        Card kys = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
        Card kms = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS);
        Card kcs = new Card(Card.Rank.EIGHT, Card.Suit.SPADES);
        
        Hand cunt = new Hand();
        Hand goat = new Hand();
        
        goat.addCard(kms);
        goat.addCard(kcs);
        
        cunt.addCard(kys);
        cunt.addCard(kys);
        cunt.addCard(kys);
        cunt.addCard(kcs);
        cunt.addCard(kms);
        
        //System.out.println(cunt.countSuit(Card.Suit.CLUBS));
        //System.out.println(cunt.isFlush());
        cunt.removeAllCards(goat);
        System.out.println(" -- " + cunt.toString());
        
        Iterator itr = cunt.iterator();
        while(itr.hasNext()){
            Card card = (Card) itr.next();
            System.out.println(card.toString());
        }
        
        //Hand nowThen = new Hand(cunt);
        
        
//        for(int i = 0; i<13; i++){
//            System.out.println("num " + i + "'s " + cunt.numOfEachNumber[i]);
//        }
//        
//        System.out.println(cunt.toString());
//        System.out.println(cunt.handSize());
        //cunt = new Hand(testhand);
        
        
        
        
        
        
        //System.out.println(Card.difference(oCard, myCard));
        
        //System.out.println(myCard.rank + " of " + myCard.suit);
        
        //System.out.println(myCard.rank.getNext());
        
        //System.out.println(myCard.compareTo(oCard));
        
        //System.out.println(myCard.toString());
        
        //System.out.println(oCard.getRank());
    }
    
}
