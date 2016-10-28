package ltu.monopoly.cards;
import java.util.ArrayList;
import java.util.Optional;
import ltu.monopoly.players.Player;
import ltu.monopoly.gameboard.Tile;

public class LibraryCard extends AbstractChanceCard {

  public LibraryCard (ArrayList<Tile> tiles) {
    super(tiles);
  }

  @Override
  public Optional<Integer> cardFunctionality (Player player) {
    System.out.println(player.name + " has decided to cram for the exam in the LIBRARY this turn and the next");

    int libraryPosition = getTileIndexByName("LIBRARY");
    Tile libraryTile = tiles.get(libraryPosition);

		player.knowledge += 4 * libraryTile.knowledge;
		player.skipOneTurn = true;
		return Optional.of(new Integer(libraryPosition));

  }
}
