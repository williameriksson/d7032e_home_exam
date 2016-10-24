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
    System.out.println(player.name + "has fallen ill. Go to START without collecting any study-time");

    int startPosition = getTileIndexByName("START");
    Tile startTile = this.tiles.get(startPosition);
    player.money -= startTile.reward;
		return Optional.of(new Integer(startPosition));

  }
}
