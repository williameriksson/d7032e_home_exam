package monopoly;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public abstract class ChanceCard {
  ArrayList<Tile> tiles;

  public ChanceCard(ArrayList<Tile> tiles) {
    if (tiles == null) {
      System.out.print("Yes it is null..........");
    }
    this.tiles = tiles;
  }

  public abstract void cardFunctionality (Player player);

  public int getTileIndexByName(String name) throws NoSuchElementException {
    for (Tile tile : this.tiles) {
      if (tile.tileName.equals(name)) {
        return this.tiles.indexOf(tile);
      }
    }
    throw new NoSuchElementException();
  }

}
