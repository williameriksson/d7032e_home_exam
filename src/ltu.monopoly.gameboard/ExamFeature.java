package ltu.monopoly.gameboard;

import java.util.Optional;
import ltu.monopoly.players.Player;

// The special feature for the EXAM tile.
public class ExamFeature implements InterfaceTileFeature {

  @Override
  public Optional<Integer> run (Player player) {
    if (player.knowledge >= 200) {
      System.out.println(player.name + " PASSED THE EXAM AND WINS THE GAME! CONGRATULATIONS!");
      System.exit(0);
    } else {
      System.out.println(player.name + " had not studied enough for the exam and have to take a re-exam.");
      player.skipOneTurn = true;
    }
    // This feature does not move the player, so return Optional.empty();
    return Optional.empty();
  }
}
