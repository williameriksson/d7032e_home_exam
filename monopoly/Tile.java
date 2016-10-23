package monopoly;
import java.util.ArrayList;


class Tile {
  String tileName;
  int reward = 0;
  int penalty = 0;
  int price = 0;
  int rent = 0;
  int knowledge = 0;
  Player owner = null;
  boolean forSale = false;
  TileFeature tileFeature = null;
  ArrayList<Player> playerArr= new ArrayList<Player>();

  Tile (String tileName) {
    this.tileName = tileName;
  }

  Tile (String tileName, TileFeature feature) {
    this.tileName = tileName;
    this.tileFeature = feature;
  }

}
