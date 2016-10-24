package ltu.monopoly.cards;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import ltu.monopoly.gameboard.Tile;
import ltu.monopoly.players.Player;

public abstract class AbstractChanceCard {

  ArrayList<Tile> tiles;

  public AbstractChanceCard(ArrayList<Tile> tiles) {
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
