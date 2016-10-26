package ltu.monopoly;

import static org.junit.Assert.*;
import org.junit.Test;



public class LTUMonopolyTest {
  String[] names = {"AAA", "BBB", "CCC", "DDD"};
  LTUMonopoly ltuMonopoly;

  public LTUMonopolyTest() {
    ltuMonopoly = new LTUMonopoly(names);
  }

  @Test
  public void justATest () {

    assertEquals(1,1);
  }
}
