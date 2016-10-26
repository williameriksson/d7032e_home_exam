package ltu.monopoly.gameboard;

import static org.junit.Assert.*;
import org.junit.Test;

public class TileTest {
  Tile tile1 = new Tile("TestTile");
  ExamFeature examFeature = new ExamFeature();
  Tile tile2 = new Tile("TestTile2", examFeature);
  public TileTest() {

  }

  @Test
  public void testTileProperties() {
    assertEquals(tile1.tileName, "TestTile");
    assertEquals(tile1.knowledge, 0);
    assertEquals(tile1.reward, 0);
    assertEquals(tile1.penalty, 0);
    assertEquals(tile1.price, 0);
    assertEquals(tile1.rent, 0);
    assertNull(tile1.owner);
    assertNull(tile1.tileFeature);
  }

  @Test
  public void testTilePropertiesWithFeature() {
    assertEquals(tile2.tileName, "TestTile2");
    assertEquals(tile2.knowledge, 0);
    assertEquals(tile2.reward, 0);
    assertEquals(tile2.penalty, 0);
    assertEquals(tile2.price, 0);
    assertEquals(tile2.rent, 0);
    assertNull(tile2.owner);
    assertNotNull(tile2.tileFeature);
  }
}
