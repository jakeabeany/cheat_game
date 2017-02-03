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
import java.util.Comparator;
import java.util.Scanner;


/**
 * main method for question 1, card/hand/deck classes.
 * @author nsw14ntu
 */
public class CardTest {
    public static void main(String[] args) 
                        throws FileNotFoundException, IOException {
        
        Scanner scan = new Scanner(System.in);
        int demonstrateMethods = 0, x = 0;
        String anotherTest;
        boolean anotherMethod = true;
        Card newCard, newCard2, newCard3, newCard4;
        
        do{
            // create a new deck.
            Deck deck = new Deck();

            System.out.println("What would you like to do?");
            System.out.println("1 - Create a new card");
            System.out.println("2 - compareTo cards");
            System.out.println("3 - Shuffle deck");
            System.out.println("4 - DifferenceValue");
            System.out.println("5 - Deck deal + size");
            System.out.println("6 - Hand methods");
            demonstrateMethods = scan.nextInt();
            
            switch(demonstrateMethods){
                case 1:
                    //create a new card
                    newCard = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
                    System.out.println("\nNew card = " + newCard);
                    System.out.println("Calling getRank returns: ");
                    System.out.println(newCard.getRank());
                    System.out.println("Calling getSuit returns: ");
                    System.out.println(newCard.getSuit());
                    System.out.println("Calling toString returns: ");
                    System.out.println(newCard.toString());
                    break;
                    
                case 2:
                    //compare 2 cards
                    System.out.println("\nWill create 3 new cards: ");
                    newCard = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
                    newCard2 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
                    newCard3 = new Card(Card.Rank.SEVEN, Card.Suit.HEARTS);
                    
                    System.out.println("Card 1: " + newCard);
                    System.out.println("Card 2: " + newCard2);
                    System.out.println("Card 3: " + newCard3);
                    
                    System.out.println("Card1 compareTo Card2 = " 
                                            + newCard.compareTo(newCard2));
                    System.out.println("Card1 compareTo Card3 = " 
                                            + newCard.compareTo(newCard3));
                    System.out.println("Card2 compareTo Card3 = " 
                                            + newCard2.compareTo(newCard3));
                    break;
                    
                case 3:
                    System.out.println("\nUnshuffled deck: ");
                    System.out.println(deck);
                    
                    deck.shuffle();
                    
                    System.out.println("Shuffled deck: ");
                    System.out.println(deck);
                    break;
                
                case 4:
                    System.out.println("\nThe difference in value between: ");
                    newCard = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS);
                    newCard2 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS);
                    System.out.println(newCard + "\n" + newCard2);
                    System.out.print("is: " + Card.differenceValue
                                                        (newCard,newCard2));
                    System.out.println("");
                    System.out.println("The difference in rank is: ");
                    System.out.println(Card.difference(newCard, newCard2));
                    break;
                case 5:
                    System.out.println("The size of a new deck is " 
                            + deck.size());
                    System.out.println("deck.deal() returns: " 
                                                + deck.deal().toString());
                    break;
                case 6:
                    System.out.println("Create a new hand and add a card: ");
                    Hand hand = new Hand();
                    Hand hand2 = new Hand();
                    newCard = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS);
                    newCard2 = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS);
                    newCard3 = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS);
                    newCard4 = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS);
                    hand.add(newCard);
                    System.out.println("Created Card newCard = " + newCard);
                    System.out.println("Created Hand hand");
                    System.out.println("hand.add(newCard) --");
                    System.out.println("Hand size is now " + hand.handSize());
                    System.out.println("\nAdding 2,3,4 of diamonds to hand2--");
                    hand2.add(newCard3);
                    hand2.add(newCard4);
                    hand2.add(newCard2);
                    System.out.println("hand2.handSize() = " 
                                                        + hand2.handSize());
                    System.out.println("hand.add(hand2) -- ");
                    hand.add(hand2);
                    System.out.println("hand.handSize() = " 
                                                        + hand.handSize());
                    System.out.println("hand.remove(0) returns " 
                                                            + hand.remove(0));
                    System.out.println("hand.isStraight returns: " 
                                                        + hand.isStraight());
                    System.out.println("hand.isFlush() returns: " 
                                                        + hand.isFlush());
                    
                    System.out.println("\nPrint hand = " + hand.toString());
                    System.out.println("\nhand.countRank(THREE) returns: " 
                                        + hand.countRank(Card.Rank.THREE));
                    System.out.println("hand.countSuit(DIAMONDS) returns: " 
                                    + hand.countSuit(Card.Suit.DIAMONDS));
                default:
                    break;
            }
            
            
            System.out.println("\nWould you like to do another test? 'y','n'");
            anotherTest = scan.next();
            if(anotherTest.equals("n")) anotherMethod = false;
            System.out.println(" ----------------------------------------- ");
        }while(anotherMethod);
        
    }
    
}
