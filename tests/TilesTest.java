package ltu.monopoly.gameboard;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class TilesTest {
  Tiles tileObj = new Tiles();
  ArrayList<Tile> tiles = tileObj.tiles;

  public TilesTest() {

  }

  @Test
  public void testNoDuplicateTiles() {
    for (int i = 0; i < tiles.size(); i++) {
      for (int j = i + 1; j < tiles.size(); j++) {
        assertNotSame(tiles.get(i), tiles.get(j));
      }
    }
  }
}
