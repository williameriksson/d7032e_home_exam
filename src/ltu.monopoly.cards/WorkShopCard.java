package ltu.monopoly.cards;
import java.util.ArrayList;
import java.util.Optional;
import ltu.monopoly.players.Player;
import ltu.monopoly.gameboard.Tile;

public class WorkShopCard extends AbstractChanceCard {

  public WorkShopCard (ArrayList<Tile> tiles) {
    super(tiles);
  }

  @Override
  public Optional<Integer> cardFunctionality (Player player) {
    System.out.println(player.name + "\"PWNZ\" at the workshops.\n" +
                        player.name + "gains ownership of A209 and A210, moves to A209 and collects the knowledge");

    int a209Index = getTileIndexByName("A209");
    int a210Index = getTileIndexByName("A210");
    Tile a209 = tiles.get(a209Index);
    Tile a210 = tiles.get(a210Index);

    // Force change the ownership of A209 and A210 to the player.
    a209.owner = player;
    a210.owner = player;

    // Return the position of A209.
		return Optional.of(new Integer(a209Index));

  }
}
