package ltu.monopoly.gameboard;
import java.util.Optional;
import ltu.monopoly.players.Player;

// Interface that enables for tiles to have
// special features.
public interface InterfaceTileFeature {

  public Optional<Integer> run(Player player);
}
