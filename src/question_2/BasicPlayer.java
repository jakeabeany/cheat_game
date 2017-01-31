package question_2;

/**
 * how the player should play.
 * @author Jake McVey
 */
public class BasicPlayer implements Player{
    Strategy playerStrategy;
    CardGame game;
    Hand hand;
    
    /**
     * constructor for a player.
     * @param strategy the strategy for the player's playstyle
     * @param game reference to the current game the players in
     */
    public BasicPlayer(Strategy strategy, CardGame game){
        this.playerStrategy = strategy;
        this.game = game;
        this.hand = new Hand();
    }
    
    /**
     * add a card to the players hand
     * @param c the card to add
     */
    @Override
    public void addCard(Card c) {
        hand.add(c);
    }
    
    /**
     * add a hand of cards to the player's hand
     * @param h the hand of cards to add.
     */
    @Override
    public void addHand(Hand h) {
        hand.add(h);
    }
    
    /**
     * count the amount of cards left in the players hand
     * @return the number of cards in players hand.
     */
    @Override
    public int cardsLeft() {
        return hand.handSize();
    }
    
    /**
     * initialise the game
     * @param g the game to start
     */
    @Override
    public void setGame(CardGame g) {
        this.game = g;
    }
    
    public Hand getHand(){
        return this.hand;
    }
    
    /**
     * set the strategy of the game
     * @param s the strategy to play
     */
    @Override
    public void setStrategy(Strategy s) {
        this.playerStrategy = s;
    }
    
    /**
     * decides what the player should bid based on the last bid made
     * @param b the bid of the previous player
     * @return the current players bid
     */
    @Override
    public Bid playHand(Bid b) {
        boolean shouldCheat = this.playerStrategy.cheat(b, hand);
        
        return playerStrategy.chooseBid(b, hand, shouldCheat);
    }
    
    /**
     * gets the strategy implemented by the player
     * @return the strategy the player is playing
     */
    @Override
    public Strategy getStrategy(){
        return this.playerStrategy;
    }
    /**
     * call cheat on the last player
     * @param b the bid of the last player
     * @return  true or false whether to call cheat
     */
    @Override
    public boolean callCheat(Bid b) {
        return playerStrategy.callCheat(hand, b);
    }
    
}
