package ltu.monopoly.players;
import java.util.ArrayList;

public class Player extends AbstractPlayer {
  public int knowledge = 0;

  public Player (String name) {
    super(name);
  }

  public Player (String name, boolean computer) {
    super(name, computer);
  }

}
