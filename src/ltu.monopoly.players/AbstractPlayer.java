package ltu.monopoly.players;
import java.util.ArrayList;


// Abstract player class, contains basic properties of a player.
public abstract class AbstractPlayer {
  public String name;
  public int position = 0;
  public int money = 200;
  public boolean computer = false;
  public boolean stillPlaying = true;
  public int skipTurns = 0;

  public AbstractPlayer(String name) {
    //Add spaces to make the gameboard printout pretty
    this.name = name;
  }
  public AbstractPlayer(String name, boolean computer) {
    //Add spaces to make the gameboard printout pretty
    this.name = name;
    this.computer = computer;
  }

  public void setStillPlaying(boolean playing) {
    stillPlaying = playing;
  }

}
