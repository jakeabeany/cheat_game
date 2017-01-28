/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question_2;
import java.util.Scanner;
/**
 *
 * @author Jake McVey
 */
public class HumanStrategy implements Strategy{
    Scanner scan = new Scanner(System.in);
    @Override
    public boolean cheat(Bid b, Hand h) {
        String shouldCheat;
        System.out.println("The last bid was " + b.toString() + ".");
        System.out.println("Your hand is " + h.toString() + ".\n");
        
        System.out.println("Do you want to cheat? 'yes' or 'no'");
        shouldCheat = scan.nextLine();
        
        return(shouldCheat.equals("yes"));
    }

    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        System.out.println("The last bid was " + b.toString() + ".");
        System.out.println("Your hand is " + h.toString() + ".\n");
        
        Card.Rank possibleChoices[] = new Card.Rank[2];
        possibleChoices[0] = b.getRank();
        possibleChoices[1] = b.getRank().getNext();
        
        
    }

    @Override
    public boolean callCheat(Hand h, Bid b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
