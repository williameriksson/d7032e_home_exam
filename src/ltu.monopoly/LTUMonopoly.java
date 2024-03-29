package ltu.monopoly;

import java.util.ArrayList;
import java.util.Optional;
import java.util.ListIterator;

import ltu.monopoly.players.Player;
import ltu.monopoly.gameboard.Tile;
import ltu.monopoly.gameboard.GameBoard;

// This class is the game engine. It handles the player moves, the consequences
// of stepping on tiles, buying tiles, removing players that lost from the
// game etc.

class LTUMonopoly extends AbstractMonopoly {

	LTUMonopoly(String[] playerNames) {
		super(playerNames);
	}


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
	@Override
	protected void makeMove(Player player) {

		if(player.stillPlaying) {
			System.out.println("It is now the turn of " + player.name);

			// Player character - let them press enter before their turn begins.
			if(!player.computer) {
				//Print the game-board for player characters.
				gameBoard.paintGameBoard();
				System.out.println(player.name + " Press [enter] to continue. (study-time: "+
					player.money+", knowledge: " + player.knowledge + ")");
				try{
					bufferedReader.readLine();
				} catch(Exception e) {
					System.out.println(e);
				};
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
		if(player.skipTurns > 0) {
			player.skipTurns--;
			System.out.println(player.name + " skips one turn");
			return;
		}

		// Get the tile that the player stands on.
		int pos = player.position;
		Tile currentTile = tiles.get(pos);
		// In the beginning of your turn you may opt to buy unowned property
		// First check if the current tile is unowned and buyable- offer player to buy
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
			} else if (player.computer && canAfford) { // Computer always buy if possible.
				buyTile(player, currentTile);
			}
		}


		// Roll dice and move the player to the new position;
		int newPosition = rollDice(player);
		movePlayer(player, newPosition);

	}

	@Override
	protected void printInstructions() {
		System.out.println("Currency: Study-time (Time is money, start with 200)");
		System.out.println("Tiles:");
		System.out.println("\tSTART: Collect 2 if you end up or passes START.");
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
		// Calculate if the player can affor to buy the tile.
		boolean canAfford = (player.money > tile.price) ? true : false;

		// Ensure that nobody owns the tile already.
		if (tile.owner != null) {
			System.out.println(tile.tileName + " is already owned by " + tile.owner.name);
			return;
		} // Ensure that the tile is buyable.
		if (!tile.forSale) {
			System.out.println(tile.tileName + " is not for sale");
			return;
		}

		// If the player can afford the tile, buy it.
		if (canAfford) {
			player.money -= tile.price;
			tile.owner = player;
			System.out.println(player.name + " bought " + tile.tileName);
		} else {
				System.out.println(player.name + " could not afford " + tile.tileName);
		}
	}

	private void checkTileForPenalty(Player player) {
		// 3. If the tile you land on is owned, pay the rent
		 Tile tile = tiles.get(player.position);
		 Player owner = tile.owner;

		// If the tile is owned by someone else, pay the rent.
		if(owner != null && owner != player) {
			player.money -= tile.rent;
			owner.money += tile.rent;
			if(player.money <= 0) {
				// Lose the game if you are all out of study-time
				System.out.println(player.name + " could not afford to pay the rent.");
				player.setStillPlaying(false);
			} else {
				System.out.println(player.name + " paid the rent to " + owner.name +
					" and has " + player.money + " study-time left");
			}
		}
	}

	private int rollDice(Player player) {
		// Roll a d6 dice (from 1 to 6)
		int roll = random.nextInt(6) + 1;
		int lastPosition = tiles.size() - 1;
		System.out.println(player.name + " rolls a " + Integer.toString(roll));

		// Return the correct new position if the player rolls past the
		// last tile.
		if (roll + player.position > lastPosition) {
			return roll - (lastPosition - player.position) - 1;
		}
		// Else, just return the new position.
		return roll + player.position;
	}

	private void movePlayer(Player player, int position) {

		// Check if the player moves past the START tile,
		// give the player the reward in that case.
		if (position < player.position) {
			Tile startTile = tiles.get(0);
			player.money += startTile.reward;
		}

		// Check if the tile brings any penalty to the player;
		checkTileForPenalty(player);

		Tile currentTile = tiles.get(player.position);
		ArrayList<Player> currentPlayerArr = currentTile.playerArr;

		// Remove the player from the current tile
		removePlayerFromTile(player);

		// Add the player to the new Tile;
		player.position = position;
		Tile destinationTile = tiles.get(player.position);
		destinationTile.playerArr.add(player);
		System.out.println(player.name + " moves to " + destinationTile.tileName);

		// Increas or decreas player's money (study-time) accordingly;
		player.money += destinationTile.reward;
		// Increase or decrease your knowledge accordingly.
		player.knowledge += destinationTile.knowledge;
		// Pay any costs involved in arriving to the tile (e.g. PARTY)
		player.money -= destinationTile.penalty;

		// Check if the tile has any special feature, in that case
		// execute it and move to the next tile if the feature requests it.
		if (destinationTile.tileFeature != null) {
			Optional<Integer> newPositionFromCard = destinationTile.tileFeature.run(player);
			newPositionFromCard.ifPresent(nPos -> movePlayer(player, nPos));
		}

		if(player.money <= 0) {
			// Lose the game if you are all out of study-time
			System.out.println(player.name + " is all out of study-time and has lost");
			removePlayerFromGame(player);
		}
	}

	// Method for removing a player from a tile.
	private void removePlayerFromTile (Player player) {
		ArrayList<Tile> tiles = tilesObject.tiles;
		ListIterator tilesIterator = tiles.listIterator();

		while (tilesIterator.hasNext()) {
			Tile tile = (Tile) tilesIterator.next();
			ListIterator playerIterator = tile.playerArr.listIterator();
			while (playerIterator.hasNext()) {
				Player aPlayer = (Player) playerIterator.next();
				if (aPlayer == player) {
					playerIterator.remove();
				}
			}
		}
	}

	// Method for removing a player from the game when the player
	// lost.
	private void removePlayerFromGame (Player player) {
		removePlayerFromTile(player);
		player.setStillPlaying(false);
		ListIterator tilesIterator = tiles.listIterator();

		while (tilesIterator.hasNext()) {
			Tile tile = (Tile) tilesIterator.next();
			if (tile.owner == player) {
				tile.owner = null;
			}
		}
	}

}
