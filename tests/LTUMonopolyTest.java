package ltu.monopoly;

import static org.junit.Assert.*;
import org.junit.Test;



public class LTUMonopolyTest {
  String[] names = {"AAA"};
  LTUMonopoly ltuMonopoly;

  public LTUMonopolyTest() {
    ltuMonopoly = new LTUMonopoly(names);
  }

  @Test
  public void verifyMakeMove () {
    int oldPos = ltuMonopoly.players[1].position;
    ltuMonopoly.makeMove(ltuMonopoly.players[1]);
    int newPos = ltuMonopoly.players[1].position;
    assertTrue(newPos != oldPos || newPos == 0);
  }
}
