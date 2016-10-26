package ltu.monopoly.cards;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;
import ltu.monopoly.gameboard.*;

public class ChanceCardTest {

  Tiles tilesObj = new Tiles();
  ArrayList<Tile> tiles = tilesObj.tiles;
  FallenIllCard fallenIllCard = new FallenIllCard(tiles);

  public ChanceCardTest() {

  }

  @Test
  public void checkGetIndex() {
    int index = fallenIllCard.getTileIndexByName("START");
    assertEquals(index, 0);
  }
}
