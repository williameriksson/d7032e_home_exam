package ltu.monopoly;

import java.util.Random;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ltu.monopoly.players.Player;
import ltu.monopoly.gameboard.Tile;
import ltu.monopoly.gameboard.Tiles;
import ltu.monopoly.gameboard.GameBoard;

abstract class AbstractMonopoly {
  protected BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
  protected int nrOfPlayers = 4;
	protected Player[] players;
	protected GameBoard gameBoard;
	protected Tiles tilesObject = new Tiles();
	protected ArrayList<Tile> tiles = tilesObject.tiles;
	protected Random random = new Random();

  AbstractMonopoly (String[] playerNames) {
		players = new Player[nrOfPlayers];
    //Fill up with computer players
		int computerID = 1;
		for(int i = 0; i < nrOfPlayers; i++) {
			if (i < playerNames.length) {
				players[i] = new Player(playerNames[i]);
        continue;
			}
			//C.1 = computer 1, etc.
			players[i] = new Player("C." + computerID, true);
			computerID++;
		}
  }

  protected void startGame () {

		Tile startTile = tilesObject.tiles.get(0);
		for (Player player : players) {
			startTile.playerArr.add(player);
		}

		gameBoard = new GameBoard(players, tiles);

		//New game is started: show the gameBoard.
		// gameBoard.paintGameBoard();
		System.out.println("");
		printInstructions();
		System.out.println("\n");

		int i=0;
		while(true) {
			makeMove(players[i]);
			i++;
			if (i > nrOfPlayers - 1) {
				i = 0;
			}
		}

  }

  private void printInstructions() {
    System.out.println("No game instructions specified, override this default text.");
  }

  protected void makeMove(Player player) {

  }

}
