package question_2;

public class Bid {
		Hand h;
		Card.Rank r;
                /**
                 * initialise a new bid
                 * r changed from ACE to TWO since thats where games
                 * should start.
                 */
		public Bid(){
                    h=new Hand();
                    r=Card.Rank.TWO; 
		}
		public Bid(Hand hand,Card.Rank bidRank){
			h=hand;
			r=bidRank;
		}
		public void setHand(Hand hand){ h=hand;}
		public void setRank(Card.Rank rank){ r=rank;}
		
		public Hand getHand(){ return h;}
		public int getCount(){ return h.handSize();}
		public Card.Rank getRank(){ return r;}
		public String toString(){
			return h.handSize()+" x "+r;
		}
		
}	