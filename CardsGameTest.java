import java.util.Random;

public class CardsGameTest {
   
   //execute application.
   public static void main(String[] args) {
   
   DeckOfCards myDeck = new DeckOfCards();
   myDeck.shuffle(); //shuffle cards.
   
       //Deal 5 random cards.	
       for (int i = 0; i < 5; i++) {
      	   myDeck.dealCard();
      	   System.out.println("Deal a card: " + myDeck.dealCard());  
      }//end of for loop
   }//end main method
}//end of CardsGameTest class

class Card {

   private String face;
   private String suit;
   
   public Card(String cardFace, String cardSuit) {
      face = cardFace;
      suit = cardSuit;
   }//end two-argument Card constructor.
   
   //return string representation of card.
   public String toString() {
      return face + " of " + suit;
   }//end toString method.
}// end class card.

class DeckOfCards {
    private Card deck []; //array of card objects
    private int currentCard;  //index of next card to be dealt.
    private final int NUMBER_OF_CARDS = 52;  //Constant # of cards.
    private Random randomNumbers;  //random # generator.

   //Constructor fills deck of cards.
   public DeckOfCards () {
   	String faces [] = { "Ace", "Two", "Three", "Four", "Five", "Six",
   			"Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
   	String suit [] = {"Hearts", "Diamonds", "Clubs", "Spades"};
   	
   	deck = new Card[NUMBER_OF_CARDS];  //create an array of card objects.
   	currentCard = 0;  //Set currentCard so first card dealt is deck[0].
   	randomNumbers = new Random(); //create random number.
   	
   	//Fill deck with card objects.
   	for (int count = 0; count < deck.length; count++) {
   		deck[count] = new Card(faces[count % 13], suit[count / 13]);
   	} //end for loop	
   }//end DeckOfCards constructor.

   //Shuffle deck of Cards
   public void shuffle() {
   	
   	//after shuffling, dealing should start at deck[0] again.
   	currentCard = 0;  //initialize currentCard again.
   	
   	//for every Card, pick another random Card and swap places "shuffling"
   	for (int first = 0; first < deck.length; first++) {
   		//select a random number between 0 and 51
   		int second = randomNumbers.nextInt(NUMBER_OF_CARDS);
   		
   		//swap current card w/ a random card
   		Card temp = deck[first];
   		deck[first] = deck[second];
   		deck[second] = temp;
   	}//end for loop		
   }//end shuffle method.

   //deal one Card
   public Card dealCard() {
	
   	//see if cards remain to be dealt.
   	if (currentCard < deck.length) {
   		return deck[currentCard++]; //return currentCard in array.
   	} else {
   		return null; //if all cards are dealt.
   	}//end conditional.
   }//end method dealCard.
}//end class DeckOfCards.