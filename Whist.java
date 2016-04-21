import java.util.Scanner;

public class Whist {

   public static void main(String[] args)
   {
      System.out.println("This program lets you play the card game wist!");
      System.out.println("All the cards are delt evenly to the 4 players.");
      System.out.println("The goal of the game is to either take or avoid");
      System.out.println("taking 'tricks'.");
      System.out.println();
      
      int teamAscore = 0;     // Team A's score
      int teamBscore = 0;     // Team B's score
      
      boolean gameOver = false;
      
      while (gameOver == false){
         if(teamAscore >= 13){
            System.out.println("  TEAM A WINS!!!  ");
            System.out.println("Final score is: Team A: " + teamAscore + "  Team B: " + teamBscore);
            gameOver = true;
         }else if(teamBscore >= 13){
            System.out.println("  TEAM B WINS!!!  ");
            System.out.println("Final score is: Team A: " + teamAscore + "  Team B: " + teamBscore);
            gameOver = true;
         }else{
            System.out.println("Current score is: Team A: " + teamAscore + "  Team B: " + teamBscore);
            int[] scores = play();
            if (scores[0] > scores[1]){
               System.out.println("Team A won that round with " + scores[0] + " point(s).");
               System.out.println();
               teamAscore += scores[0];
            }else{
               System.out.println("Team B won that round with " + scores[1] + " point(s).");
               System.out.println();
               teamBscore += scores[1];
            }
         }
      }
   
   }  // end main()
   

   /**
    * Lets the user play one game of HighLow, and returns the
    * user's score in that game.  The score is the number of
    * correct guesses that the user makes.
    */
   private static int[] play() {
      int[] roundScores = new int[2];
   
      Deck deck = new Deck();  // Get a new deck of cards, and 
                               //   store a reference to it in 
                               //   the variable, deck.
      Hand player1 = new Hand();
      Hand player2 = new Hand();
      Hand player3 = new Hand();
      Hand player4 = new Hand();
      
      Card currentCard;  // The current card, which the user sees.
      Card firstPlayed = null;
      int nextPersonTurn;
      Scanner scanner = new Scanner(System.in);

      //Card nextCard;   // The next card in the deck.  The user tries
                       //    to predict whether this is higher or lower
                       //    than the current card.

      //int correctGuesses ;  // The number of correct predictions the
                            //   user has made.  At the end of the game,
                            //   this will be the user's score.
      
      //char s;
      //char guess;   // The user's guess.  'H' if the user predicts that
                    //   the next card will be higher, 'L' if the user
                    //   predicts that it will be lower.
      
      deck.shuffle();  // Shuffle the deck into a random order before
                       //    starting the game.

      int nextCardToPlayer = 1;
      //correctGuesses = 0;
      
      while(deck.cardsLeft() > 0){
         currentCard = deck.dealCard();
         
         if (nextCardToPlayer == 1){
            player1.addCard(currentCard);
            nextCardToPlayer = 2;
         }else if(nextCardToPlayer == 2){
            player2.addCard(currentCard);
            nextCardToPlayer = 3;
         }else if(nextCardToPlayer == 3){
            player3.addCard(currentCard);
            nextCardToPlayer = 4;
         }else{
            player4.addCard(currentCard);
            nextCardToPlayer = 1;
         }
      }
      
      //Prints players hand
      System.out.println("Player 1 hand ---------------------------");
      player1.sortBySuit();
      player1.printHand();
      System.out.println("---------------------------");
      System.out.println();
      player2.sortBySuit();
      player3.sortBySuit();
      player4.sortBySuit();
      
      while(player1.getCardCount() != 0 && player2.getCardCount() != 0 &&
               player3.getCardCount() != 0 && player4.getCardCount() != 0) {  // Loop ends when all cards have been played
         Card card1played = null, card2played = null, card3played = null, card4played = null;
         nextPersonTurn = 1;
         for(int i = 0; i < 4; i++){
            if(nextPersonTurn == 1){
               card1played = playerTurn(1, player1);
               if(firstPlayed != null && player1.checkSuitExists(firstPlayed.getSuit()) == true){
                  System.out.println("You have to play a card in the same suit that has already been played.");
                  card1played = playerTurn(1, player1);
               }
               nextPersonTurn = 2;
            }else if(nextPersonTurn == 2){
               card2played = playerTurn(2, player2);
               if(firstPlayed != null && player2.checkSuitExists(firstPlayed.getSuit()) == true){
                  System.out.println("You have to play a card in the same suit that has already been played.");
                  card2played = playerTurn(2, player2);
               }
               nextPersonTurn = 3;
            }else if(nextPersonTurn == 3){
               card3played = playerTurn(3, player3);
               if(firstPlayed != null && player3.checkSuitExists(firstPlayed.getSuit()) == true){
                  System.out.println("You have to play a card in the same suit that has already been played.");
                  card3played = playerTurn(3, player3);
               }
               nextPersonTurn = 4;
            }else{
               card4played = playerTurn(4, player4);
               if(firstPlayed != null && player1.checkSuitExists(firstPlayed.getSuit()) == true){
                  System.out.println("You have to play a card in the same suit that has already been played.");
                  card4played = playerTurn(4, player4);
               }
               nextPersonTurn = 1;
            }
            
         }
         
         System.out.println();
         System.out.println("The 4 cards played were:");
         System.out.println("   Player 1: " + card1played);
         System.out.println("   Player 2: " + card2played);
         System.out.println("   Player 3: " + card3played);
         System.out.println("   Player 4: " + card4played);
         System.out.println();
         
         /*System.out.println("Choose a card to play: ");
         int cardPosition = scanner.nextInt();
         while (cardPosition <= 0 || cardPosition > player1.getCardCount()){
            System.out.println("Invalid choice, plese try again.");
            System.out.println("Choose a card to play: ");
            cardPosition = scanner.nextInt();
         }
         System.out.println("   You played: " + player1.getCard(cardPosition - 1));
         card1played = player1.getCard(cardPosition - 1)
         player1.removeCard(cardPosition - 1);
         
         System.out.println("   Player2 played: " + player2.getCard(cardPosition - 1));
         player2.removeCard(cardPosition - 1);
         System.out.println("   Player3 played: " + player3.getCard(cardPosition - 1));
         player3.removeCard(cardPosition - 1);
         System.out.println("   Player4 played: " + player4.getCard(cardPosition - 1));
         player4.removeCard(cardPosition - 1);
         */
      }
         
         /* Get the user's prediction, 'H' or 'L' (or 'h' or 'l'). */
         /*
         System.out.print("Will the next card be higher (H) or lower (L)?  ");
         do {
            String c = scan.next();
            c.toUpperCase();
            s = c.charAt(0);
            System.out.println(s);
            if (s != 'H' && s != 'L') 
               System.out.print("Please respond with H or L:  ");
         } while (s != 'H' && s != 'L');
         */
         /* Get the next card and show it to the user. */
         
         //nextCard = deck.dealCard();
         //System.out.println("The next card is " + nextCard);
         
         /* Check the user's prediction. */
         /*
         if (nextCard.getValue() == currentCard.getValue()) {
            System.out.println("The value is the same as the previous card.");
            System.out.println("You lose on ties.  Sorry!");
            break;  // End the game.
         }
         else if (nextCard.getValue() > currentCard.getValue()) {
            if (s == 'H') {
                System.out.println("Your prediction was correct.");
                correctGuesses++;
            }
            else {
                System.out.println("Your prediction was incorrect.");
                break;  // End the game.
            }
         }
         else {  // nextCard is lower
            if (s == 'L') {
                System.out.println("Your prediction was correct.");
                correctGuesses++;
            }
            else {
                System.out.println("Your prediction was incorrect.");
                break;  // End the game.
            }
         }
         */
         /* To set up for the next iteration of the loop, the nextCard
            becomes the currentCard, since the currentCard has to be
            the card that the user sees, and the nextCard will be
            set to the next card in the deck after the user makes
            his prediction.  */
         
         //currentCard = nextCard;
         //System.out.println();
         //System.out.println("The card is " + currentCard);
         
      //} // end of while loop
      
      //System.out.println();
      //System.out.println("The game is over.");
      //System.out.println("You made " + correctGuesses 
      //                                     + " correct predictions.");
      //System.out.println();
      roundScores[0] = 13;
      roundScores[1] = 0;
      
      return roundScores;
      
   }  // end play()
   
   static Card playerTurn(int playerNum, Hand player){
      Card cardPlayed;
      Scanner scanner2 = new Scanner(System.in);
      System.out.println("Player " + playerNum + ": Choose a card to play: ");
      int cardPosition = scanner2.nextInt();
      while (cardPosition <= 0 || cardPosition > player.getCardCount()){
         System.out.println("Invalid choice, plese try again.");
         System.out.println("Choose a card to play: ");
         cardPosition = scanner2.nextInt();
      }
      System.out.println("   Player " + playerNum + " played: " + player.getCard(cardPosition - 1));
      cardPlayed = player.getCard(cardPosition - 1);
      player.removeCard(cardPosition - 1);
      
      return cardPlayed;
   }
   

} // end class