package monopoly;
import java.util.ArrayList;


public abstract class AbstractTile {
  public String tileName;
  public int reward = 0;
  public int penalty = 0;
  public int price = 0;
  public int rent = 0;
  public boolean forSale = false;

  public AbstractTile (String tileName) {
    this.tileName = tileName;
  }

}
