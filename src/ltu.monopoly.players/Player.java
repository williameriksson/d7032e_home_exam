package ltu.monopoly.players;
import java.util.ArrayList;

// In the LTU monopoly we use knowledge, so add it to the player class
// which extends the AbstractPlayer class.
public class Player extends AbstractPlayer {
  public int knowledge = 0;

  public Player (String name) {
    super(name);
  }

  public Player (String name, boolean computer) {
    super(name, computer);
  }

}
