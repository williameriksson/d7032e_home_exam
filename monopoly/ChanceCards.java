package monopoly;
import java.util.ArrayList;
import java.util.Random;
import java.util.Optional;


public abstract class ChanceCards {

  private ArrayList<ChanceCard> chanceCards;
  private Random random = new Random();

  public ChanceCards (ArrayList<ChanceCard> chanceCards) {
    this.chanceCards = chanceCards;
  }

  public Optional<Integer> drawCard(Player player) {
    int cardIndex = random.nextInt(this.chanceCards.size());
    ChanceCard drawnCard = this.chanceCards.get(cardIndex);
    return drawnCard.cardFunctionality(player);
  }

}
