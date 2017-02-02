/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question_2;

/**
 *
 * @author Jake McVey
 */
public class StrategyFactory {
    public enum Strategies{
        MY,
        BASIC,
        HUMAN,
        THINKER
    }
    
    public Strategy setStrategy(Strategies strat){
        Strategy returnStrat;
        switch(strat){
            case MY:
                returnStrat = new MyStrategy();
                break;
            case BASIC:
                returnStrat = new BasicStrategy();
                break;
            case HUMAN:
                returnStrat = new HumanStrategy();
                break; 
            case THINKER:
                returnStrat = new ThinkerStrategy();
                break;
            default:
                returnStrat = null;
                break;
        }
        return returnStrat;      
    }
}
