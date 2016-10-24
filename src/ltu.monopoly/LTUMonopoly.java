package monopoly;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Random;

import ltu.monopoly.players.Player;
import ltu.monopoly.gameboard.*;


public class LTUMonopoly {
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	public Player[] players;
	public GameBoard gameBoard;
	public Tiles tilesObject = new Tiles();
	public ArrayList<Tile> tiles = tilesObject.tiles;
	public Random random = new Random();

	public static void main(String argv[]) {
		if(argv.length < 1 ||argv.length > 4) {
			System.out.println("Syntax: LTUMonopoly [Player 1-4 initials]\nexample: LTUMonopoly F.L J.H K.S");
			System.exit(0);
		} else {
			new LTUMonopoly(argv);
		}
	}

	public LTUMonopoly(String[] playerNames) {
		//There are always 4 players
		players = new Player[4];

		//Fill up with computer players
		int computerID = 1;
		for(int i=0; i<4; i++) {
			if(playerNames.length >= (i+1)) {
				players[i] = new Player(playerNames[i]);
			} else {
				//C.1 = computer 1, etc.
				players[i] = new Player("C." + computerID, true);
				computerID++;
			}
		}
		this.tilesObject.tiles.get(0).playerArr.add(players[0]);
		this.tilesObject.tiles.get(0).playerArr.add(players[1]);
		this.tilesObject.tiles.get(0).playerArr.add(players[2]);
		this.tilesObject.tiles.get(0).playerArr.add(players[3]);
		gameBoard = new GameBoard(this.players, this.tiles);



		//New game is started: show the gameBoard.
		gameBoard.paintGameBoard();
		System.out.println("");
		printInstructions();
		System.out.println("\n");

		int i=0;
		while(true) {
			makeMove(players[i]);
			i++;
			if(i>3) {
				i=0;
			}
		}
	}

	//Roll dice, move, draw cards, pay rent, or offer to buy property
	public void makeMove(Player player) {
		/*
			Rules:
				* 1. In the beginning of your turn you may opt to buy unowned property
				* 2. Roll d6 dice and move that number of steps.
				* 3. If the tile you land on is owned, pay the rent
				* 4. Increase or decrease your knowledge accordingly.
				* 5. Pay any costs involved in arriving to the tile (e.g. PARTY)
				* 6. Draw a Chance card on the CHANCE tile and follow the text
				* exit 1. Lose the game if you are all out of study-time
				* exit 2. Win the game if you have >= 200 knowledge and stand at EXAM
				* Property can not be sold once bought
		*/

		if(player.stillPlaying) {
			System.out.println("It is now the turn of " + player.name);

			// Player character - let them press enter before their turn begins.
			if(!player.computer) {
				//Print the game-board for player characters.
				gameBoard.paintGameBoard();
				System.out.println(player.name + "Press [enter] to continue. (study-time: "+
					player.money+", knowledge: " + player.knowledge + ")");
				try{
					bufferedReader.readLine();
				} catch(Exception e){};
			}
		} else { // this current player has already lost the game, check if there are any players left playing
			boolean someoneIsStillPlaying = false;
			for(int i=0; i<players.length; i++) {
				if(players[i].stillPlaying) {
					someoneIsStillPlaying = true;
				}
			}
			if(someoneIsStillPlaying) {
				return;
			} else {
				System.out.println("There are no more players, everyone lost");
				System.exit(0);
			}
		}

		// The player is currently on the EXAM tile but had not studied enough (knowledge <= 200) - skip one turn
		// Or, the player is cramming for the exam in the Library (result of a Chance card)
		// Or, the player has passed out after a serious party (result of a Chance card)
		if(player.skipOneTurn) {
			player.skipOneTurn = false;
			System.out.println(player.name + " skips one turn");
			return;
		}

		int pos = player.position;
		Tile currentTile = tiles.get(pos);
		// 1. In the beginning of your turn you may opt to buy unowned property
		// First check if the current tile is unowned - offer player to buy
		if (currentTile.forSale && currentTile.owner == null) {
			boolean canAfford = (player.money > currentTile.price) ? true : false;

			if (!player.computer) {
				System.out.print("Do you want to buy " + currentTile.tileName + " for " + currentTile.price +
					" and rent " + currentTile.rent + "? [y/n] \nYou currently have " + player.money + " study-time\n");
				String choice = "";

				try {
					while(!((choice = bufferedReader.readLine()).equals("y") || choice.equals("n"))){
						System.out.println("Yes [y] or No [n]");
					}
				} catch (Exception e) {
						System.out.println(e);
				};

				if (choice.equals("y")) {
					buyTile(player, currentTile);
				}
			} else if (player.computer && canAfford) {
				buyTile(player, currentTile);
			}
		}


		// 2. Roll dice and move player;
		int newPosition = rollDice(player);
		movePlayer(player, newPosition);

		// if(!player.computer) {
		// 	System.out.println(player.name + "rolls a " + roll + " and lands on " + tileNames[player.position]);
		// }

	}


	public void printInstructions() {
		System.out.println("Currency: Study-time (Time is money, start with 200)");
		System.out.println("Tiles:");
		System.out.println("\tSTART: Collect 4");
		System.out.println("\tStiL/Philm: Go to the gym/cinema [buy: 6, rent 2]");
		System.out.println("\tCHANCE: Draw a CHANCE card");
		System.out.println("\tPARTY: Have a huge party [pay: 18, Decrease knowledge by 8]");
		System.out.println("\tA109/A117/B234Ske/E632: Attend a lecture [buy: 10, rent 3, Increase knowledge by 3]");
		System.out.println("\tLIBRARY: Study [Increase knowledge by 8]");
		System.out.println("\tEXAM: Win if knowledge >=200 / Skip one turn.");
		System.out.println("\tA209/A210: Attend a workshop [buy: 20, rent 5, Increase knowledge by 4]");
		System.out.println("Win by collecting 200 knowlede and go to the EXAM tile. Lose by running out of study-time");
	}

	private void buyTile (Player player, Tile tile) {
		boolean canAfford = (player.money > tile.price) ? true : false;

		if (tile.owner != null) {
			System.out.println(tile.tileName + " is already owned by " + tile.owner.name);
			return;
		}
		if (!tile.forSale) {
			System.out.println(tile.tileName + " is not for sale");
			return;
		}

		if (canAfford) {
			player.money -= tile.price;
			tile.owner = player;
			tile.forSale = false;
			System.out.println(player.name + " bought " + tile.tileName);
		} else {
				System.out.println(player.name + " could not afford " + tile.tileName);
		}
	}

	private void checkTileForPenalty(Player player) {
		// 3. If the tile you land on is owned, pay the rent
		 Tile tile = this.tiles.get(player.position);
		 Player owner = tile.owner;
		//board[][get, pay, buy, rent, knowledge]
		if(owner != null) {
			player.money -= tile.rent;
			owner.money += tile.rent;
			if(player.money < 0) {
				// exit 1. Lose the game if you are all out of study-time
				System.out.println(player.name + " Could not afford to pay the rent and has lost");
				player.setStillPlaying(false);
			} else {
				System.out.println(player.name + " paid the rent to " + owner.name +
					" and has " + player.money + " study-time left");
			}
		}
	}

	private int rollDice(Player player) {
		int roll = random.nextInt(6) + 1;
		int lastPosition = this.tiles.size() - 1;
		System.out.println(player.name + " rolls a " + Integer.toString(roll));

		if (roll + player.position > lastPosition) {
			return roll - (lastPosition - player.position) - 1;
		}
		return roll + player.position;
	}

	public void movePlayer(Player player, int position) {
		// 2. Roll d6 dice and move that number of steps.

		if (position < player.position) {
			Tile startTile = this.tiles.get(0);
			player.money += startTile.reward;
		}

		// 3. Check if the tile brings any penalty to the player;
		checkTileForPenalty(player);

		Tile currentTile = this.tiles.get(player.position);
		ArrayList<Player> currentPlayerArr = currentTile.playerArr;

		// Remove the player from the current tile
		for (int i = 0; i < currentPlayerArr.size(); i++) {
				if (currentPlayerArr.get(i) == player) {
					currentPlayerArr.remove(i);
					break;
				}
		}

		player.position = position;
		Tile destinationTile = this.tiles.get(player.position);
		destinationTile.playerArr.add(player);
		System.out.println(player.name + " moves to " + destinationTile.tileName);
		player.money += destinationTile.reward;
		// Increase or decrease your knowledge accordingly.
		player.knowledge += destinationTile.knowledge;
		// Pay any costs involved in arriving to the tile (e.g. PARTY)
		player.money -= destinationTile.penalty;

		if (destinationTile.tileFeature != null) {
			Optional<Integer> newPositionFromCard = destinationTile.tileFeature.run(player);
			newPositionFromCard.ifPresent(nPos -> movePlayer(player, nPos));
		}

		if(player.money < 0) {
			// exit 1. Lose the game if you are all out of study-time
			System.out.println(player.name + " Could not afford to pay for the party and has lost");
			player.setStillPlaying(false);
		}
	}
}
