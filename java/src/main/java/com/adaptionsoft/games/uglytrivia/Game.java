package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    List<String> players = new ArrayList<>();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList<String> popQuestions = new LinkedList<>();
	LinkedList<String> scienceQuestions = new LinkedList<>();
	LinkedList<String> sportsQuestions = new LinkedList<>();
	LinkedList<String> rockQuestions = new LinkedList<>();
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast("Science Question " + i);
			sportsQuestions.addLast("Sports Question " + i);
			rockQuestions.addLast("Rock Question " + i);
    	}
    }

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName, PrintStream out) {
		
		
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;

		showAddedPlayer(playerName, players.size(), out);
		return true;
	}

	private void showAddedPlayer(String playerName, int size, PrintStream out) {
		out.println(playerName + " was added");
		out.println("They are player number " + size);
	}

	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll, PrintStream out) {
		showDiceRoll(players.get(currentPlayer), roll, out);

		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				showPlayerOutOfPenaltyBox(players.get(currentPlayer), out);
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

				showNewPlayerLocation(players.get(currentPlayer), places[currentPlayer], out);
				showCurrentCategory(out);
				String question = askQuestion();
				showQuestion(question, out);
			} else {
				out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}
			
		} else {
		
			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

			showNewPlayerLocation(players.get(currentPlayer), places[currentPlayer], out);
			showCurrentCategory(out);
			String question = askQuestion();
			showQuestion(question, out);
		}
		
	}

	private void showCurrentCategory(PrintStream out) {
		out.println("The category is " + currentCategory());
	}

	private void showNewPlayerLocation(String currentPlayerName, int currentPlayerLocation, PrintStream out) {
		out.println(currentPlayerName + "'s new location is " + currentPlayerLocation);
	}

	private void showPlayerOutOfPenaltyBox(String currentPlayerName, PrintStream out) {
		out.println(currentPlayerName + " is getting out of the penalty box");
	}

	private void showDiceRoll(String currentPlayerName, int roll, PrintStream out) {
		out.println(currentPlayerName + " is the current player");
		out.println("They have rolled a " + roll);
	}

	private String askQuestion() {
		String question = "";
		if (currentCategory() == "Pop") {
			question = popQuestions.removeFirst();
		}
		if (currentCategory() == "Science") {
			question = scienceQuestions.removeFirst();
		}
		if (currentCategory() == "Sports") {
			question = sportsQuestions.removeFirst();
		}
		if (currentCategory() == "Rock") {
			question = rockQuestions.removeFirst();
		}
		return question;
	}

	private void showQuestion(String question, PrintStream out) {
		out.println(question);
	}

	private String currentCategory() {
		if (places[currentPlayer] == 0) return "Pop";
		if (places[currentPlayer] == 4) return "Pop";
		if (places[currentPlayer] == 8) return "Pop";
		if (places[currentPlayer] == 1) return "Science";
		if (places[currentPlayer] == 5) return "Science";
		if (places[currentPlayer] == 9) return "Science";
		if (places[currentPlayer] == 2) return "Sports";
		if (places[currentPlayer] == 6) return "Sports";
		if (places[currentPlayer] == 10) return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered(PrintStream out) {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				out.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				out.println(players.get(currentPlayer)
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			out.println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			out.println(players.get(currentPlayer)
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(PrintStream out){
		out.println("Question was incorrectly answered");
		out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
