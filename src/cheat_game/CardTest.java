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
        Card newCard, newCard2, newCard3;
        
        do{
            // create a new deck.
            Deck deck = new Deck();

            System.out.println("What would you like to do?");
            System.out.println("1 - Create a new card");
            System.out.println("2 - compareTo cards");
            System.out.println("3 - Shuffle deck");
            System.out.println("4 - DifferenceValue");
            demonstrateMethods = scan.nextInt();
            
            switch(demonstrateMethods){
                case 1:
                    //create a new card
                    newCard = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
                    System.out.println("\nNew card = " + newCard);
                    System.out.println("Callin getRank returns: ");
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
