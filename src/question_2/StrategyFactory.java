package question_2;

/**
 * strategy factory takes an enum input referencing the strategy type and
 * returns a new instance of that strategy
 * @author Jake McVey
 */
public class StrategyFactory {
    /**
     * the possible strategies that can be implemented
     */
    public enum Strategies{
        MY,
        BASIC,
        HUMAN,
        THINKER
    }
    
    /**
     * set strategy takes input of type Strategies and returns an instance
     * of that strategy
     * @param strat the desired strategy
     * @return an instance of that strategy
     */
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
