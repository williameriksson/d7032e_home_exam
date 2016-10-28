package ltu.monopoly.gameboard;

import java.util.ArrayList;
import ltu.monopoly.players.Player;

// In the LTU monopoly we want to use knowledge, so we add it
// along with owner, tileFeature and a player list to the
// Tile class that extends the AbstractTile class.
public class Tile extends AbstractTile {
  public int knowledge = 0;
  public Player owner = null;
  public InterfaceTileFeature tileFeature = null;
  public ArrayList<Player> playerArr= new ArrayList<Player>();

  public Tile (String tileName) {
    super(tileName);
  }

  public Tile (String tileName, InterfaceTileFeature feature) {
    super(tileName);
    this.tileFeature = feature;
  }

}
