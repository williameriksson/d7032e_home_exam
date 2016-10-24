package ltu.monopoly.gameboard;
import java.util.Optional;
import ltu.monopoly.players.Player;

public interface InterfaceTileFeature {

  public Optional<Integer> run(Player player);
}
