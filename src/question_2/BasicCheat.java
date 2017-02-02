package question_2;

import java.util.*;

public class BasicCheat implements CardGame{
    private BasicPlayer[] players;
    private int nosPlayers;
    
    // use this to change amount of players
    public static final int MINPLAYERS=2;
    
    private int currentPlayer;
    private Hand discards;
    private Bid currentBid;
    
    public BasicCheat(){
        this(MINPLAYERS);
    }
    public BasicCheat(int n){
        nosPlayers=n;
        players=new BasicPlayer[nosPlayers];
        
        // change BASIC to strategy youd like all players to play
        for(int i=0;i<nosPlayers;i++)
                players[i]=(new BasicPlayer(new StrategyFactory().setStrategy
                                        (StrategyFactory.Strategies.BASIC),this));
       
        // --------- use this to set a specific strategy to player 'x'
        // --------- change YYYYYYYY to the desired strategy,
        // --------- HUMAN,MY,BASIC,THINKER
        /*players[x] = (new BasicPlayer(new StrategyFactory().setStrategy
                                (StrategyFactory.Strategies.YYYYYYY),this));*/
        
        currentBid=new Bid();
        currentBid.setRank(Card.Rank.TWO);
        
        currentPlayer = 0;
    }

    @Override
    public boolean playTurn(){
        System.out.println("Previous Bid = "+currentBid);
        currentBid=players[currentPlayer].playHand(currentBid);
        
        System.out.println("Player bid   = "+currentBid);
        
        
         //Add hand played to discard pile
        discards.add(currentBid.getHand());
        
        //Offer all other players the chance to call cheat
        boolean cheat=false;
        for(int i=0;i<players.length && !cheat;i++){
            if(i!=currentPlayer){
                cheat=players[i].callCheat(currentBid);
                if(cheat){
                    System.out.println("Player called cheat by Player "+(i+1));
                    if(isCheat(currentBid)){	
//CHEAT CALLED CORRECTLY
//Give the discard pile of cards to currentPlayer who then has to play again                      
                        players[currentPlayer].addHand(discards);
                        System.out.println("Player cheats!");
                        System.out.println("Adding cards to player "+
                                (currentPlayer+1));

                    }
                    else{
//CHEAT CALLED INCORRECTLY
//Give cards to caller i who is new currentPlayer
                        System.out.println("Player Honest");
                        currentPlayer=i;
                        players[currentPlayer].addHand(discards);
                        System.out.println("Adding cards to player "+
                                (currentPlayer+1));
                    }
//If cheat is called, current bid reset to an empty bid with rank two whatever 
//the outcome
                    currentBid=new Bid();
//Discards now reset to empty	
                    discards=new Hand();
                }
            }
        }
        
        /**
         * if the strategy implemented is thinker strategy then 
         * reset the discard pile
         */
        for(BasicPlayer player : players){
            if(player.getStrategy().getClass().toString()
                        .equals("class question_2.ThinkerStrategy")){
                
                ThinkerStrategy thinkStrat 
                                = (ThinkerStrategy) player.getStrategy();
                thinkStrat.resetDiscards();
            }
        }
        
        
        if(!cheat){
//Go to the next player       
            System.out.println("\nNo Cheat Called");

            currentPlayer=(currentPlayer+1)%nosPlayers;
        }
        return true;
    }
    public int winner(){
        for(int i=0;i<nosPlayers;i++){
            if(players[i].cardsLeft()==0)
                    return i;
        }
        return -1;

    }
    public void initialise(){
            //Create Deck of cards
            Deck d=new Deck();
            d.shuffle();
            //Deal cards to players
            Iterator<Card> it=d.iterator();
            int count=0;
            while(it.hasNext()){
                    players[count%nosPlayers].addCard(it.next());
                    count++;
            }
            //Initialise Discards
            discards=new Hand();
            
            // player with 2 of clubs always starts
            Card twoClubs = new Card(Card.Rank.TWO, Card.Suit.CLUBS);
            int i = 0;
            for(Player p : players){
                //loop through players hand to check for 2 of clubs
                for(Object c : p.getHand().hand){
                    Card card = (Card) c;
                    //System.out.println(i + ": " + card);
                    if(card.compareTo(twoClubs) == 0){
                        currentPlayer = i;
                    }
                }
                i++;
            };
            currentBid=new Bid();
            currentBid.setRank(Card.Rank.TWO);
    }
    public void playGame(){
            initialise();
            int c=0;
            Scanner in = new Scanner(System.in);
            boolean finished=false;
            while(!finished){
                    //Play a hand
                    System.out.println("Cheat turn for player " 
                                                    +(currentPlayer+1));
                    System.out.println("--------------------- -");
                    playTurn();
                    System.out.println("\nCurrent discards :\n"+discards);
                    c++;
                    System.out.println("               Turn "+c+ " Complete. Press any key to continue or enter Q to quit");
                    String str=in.nextLine();
                    if(str.equals("Q")||str.equals("q")||str.equals("quit"))
                            finished=true;
                    int w=winner();
                    // added >= instead of > because player 1 cannot win
                    // without it.
                    if(w>=0){
                            System.out.println("The Winner is Player "+(w+1));
                            finished=true;
                    }

            }
    }
    public static boolean isCheat(Bid b){
            for(Object c:b.getHand()){
                    Card card = (Card) c;
                    if(card.getRank()!=b.r)
                            return true;
            }
            return false;
    }
    public static void main(String[] args){
            BasicCheat cheat=new BasicCheat();
            cheat.playGame();
    }
}
