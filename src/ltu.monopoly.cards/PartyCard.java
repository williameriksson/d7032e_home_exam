package ltu.monopoly.cards;
import java.util.ArrayList;
import java.util.Optional;
import ltu.monopoly.players.Player;
import ltu.monopoly.gameboard.Tile;

public class PartyCard extends AbstractChanceCard {

  public PartyCard (ArrayList<Tile> tiles) {
    super(tiles);
  }

  @Override
  public Optional<Integer> cardFunctionality (Player player) {
    System.out.println(player.name + " has passed out after a serious party.\n"+
                        player.name + " moves to PARTY, pays the costs, decreases knowledge, and skips one turn");

    int partyPosition = getTileIndexByName("PARTY");
    player.skipOneTurn = true;

		return Optional.of(new Integer(partyPosition));

  }
}
