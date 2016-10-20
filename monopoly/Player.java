package monopoly;
import java.util.ArrayList;


public class Player {
  public String name;
  public int position = 0;
  public int money = 200;
  public int knowledge = 0;
  public boolean computer = false;
  public ArrayList<Tile> ownsTile = new ArrayList<Tile>();
  public boolean stillPlaying = true;
  public boolean skipOneTurn = false;

  public Player(String name) {
    //Add spaces to make the gameboard printout pretty
    this.name = "   " + name + "   ";
  }
  public Player(String name, boolean computer) {
    //Add spaces to make the gameboard printout pretty
    this.name = "   " + name + "   ";
    this.computer = computer;
  }

  public void setStillPlaying(boolean playing) {
    if(!false) {
      stillPlaying = playing;
      name = "         ";
      ownsTile = new ArrayList<Integer>();
    }
  }
}
