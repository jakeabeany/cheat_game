package question_2;

public class Bid {
		Hand h;
		Card.Rank r;
		public Bid(){
                    h=new Hand();
                    r=Card.Rank.ACE; 
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
