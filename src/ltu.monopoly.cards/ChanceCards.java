package ltu.monopoly.cards;
import java.util.ArrayList;
import java.util.Optional;
import ltu.monopoly.players.Player;

public class ChanceCards extends AbstractChanceCards {
  public ArrayList<AbstractChanceCard> chanceCards;

  public ChanceCards (ArrayList<AbstractChanceCard> chanceCards) {
    this.chanceCards = chanceCards;
  }

  public Optional<Integer> drawCard(Player player) {
    int cardIndex = random.nextInt(this.chanceCards.size());
    AbstractChanceCard drawnCard = this.chanceCards.get(cardIndex);
    return drawnCard.cardFunctionality(player);
  }

}
