package ltu.monopoly.gameboard;

import java.util.Optional;
import ltu.monopoly.players.Player;
import ltu.monopoly.cards.ChanceCards;

// The special feature for the CHANCE tiles.
public class ChanceFeature implements InterfaceTileFeature {
  ChanceCards cards;
  public ChanceFeature (ChanceCards cards) {
    this.cards = cards;
  }

  @Override
  public Optional<Integer> run (Player player) {
    Optional<Integer> newPosition = cards.drawCard(player);
    return newPosition;
  }
}
