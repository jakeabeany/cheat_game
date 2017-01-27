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
public class BasicPlayer implements Player{
    Strategy playerStrategy;
    CardGame game;
    Hand hand;
    
    public BasicPlayer(Strategy strategy, CardGame game){
        this.playerStrategy = strategy;
        this.game = game;
        this.hand = new Hand();
    }
    
    @Override
    public void addCard(Card c) {
        hand.add(c);
    }

    @Override
    public void addHand(Hand h) {
        hand.add(h);
    }

    @Override
    public int cardsLeft() {
        return hand.handSize();
    }

    @Override
    public void setGame(CardGame g) {
        this.game = g;
    }

    @Override
    public void setStrategy(Strategy s) {
        this.playerStrategy = s;
    }

    @Override
    public Bid playHand(Bid b) {
        Bid newBid = new Bid(hand, b.r);
        
        return newBid;
    }

    @Override
    public boolean callCheat(Bid b) {
        return false;
    }
    
}
