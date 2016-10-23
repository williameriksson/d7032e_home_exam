package monopoly;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;


public abstract class ChanceCard {
  ArrayList<Tile> tiles;

  public ChanceCard(ArrayList<Tile> tiles) {
    this.tiles = tiles;
  }

  public abstract Optional<Integer> cardFunctionality (Player player);

  public int getTileIndexByName(String name) throws NoSuchElementException {
    for (Tile tile : this.tiles) {
      if (tile.tileName.equals(name)) {
        return this.tiles.indexOf(tile);
      }
    }
    throw new NoSuchElementException();
  }

}
