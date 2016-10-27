package ltu.monopoly.cards;
import java.util.ArrayList;
import java.util.Optional;
import ltu.monopoly.players.Player;
import ltu.monopoly.gameboard.Tile;

public class FallenIllCard extends AbstractChanceCard {

  public FallenIllCard (ArrayList<Tile> tiles) {
    super(tiles);
  }

  @Override
  public Optional<Integer> cardFunctionality (Player player) {
    System.out.println(player.name + " has fallen ill. Go to START without collecting any study-time");

    int startPosition = getTileIndexByName("START");
    Tile startTile = tiles.get(startPosition);
    // Remove the reward that the player will obtain from going to START.
    player.money -= startTile.reward;
    // Return the position of START.
		return Optional.of(new Integer(startPosition));

  }
}
