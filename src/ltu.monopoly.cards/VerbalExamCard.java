package ltu.monopoly.cards;
import java.util.ArrayList;
import java.util.Optional;
import ltu.monopoly.players.Player;
import ltu.monopoly.gameboard.Tile;

public class VerbalExamCard extends AbstractChanceCard {

  public VerbalExamCard (ArrayList<Tile> tiles) {
    super(tiles);
  }

  @Override
  public Optional<Integer> cardFunctionality (Player player) {
    System.out.println(player.name + " has been given a VERBAL EXAM and moves to EXAM without losing a turn");

    int examPosition = getTileIndexByName("EXAM");
    Tile examTile = tiles.get(examPosition);
    player.skipOneTurn = false;

		return Optional.of(new Integer(examPosition));

  }
}
