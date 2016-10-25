package ltu.monopoly.gameboard;
import java.util.ArrayList;
import java.util.Optional;
import ltu.monopoly.cards.*;

public class Tiles {
  public ArrayList<Tile> tiles;

  public Tiles() {

    Tile start = new Tile("START");
    start.reward = 4;

    Tile stil = new Tile("StiL");
    stil.forSale = true;
    stil.price = 6;
    stil.rent = 2;

    Tile chance1 = new Tile("CHANCE");


    Tile philm = new Tile("Philm");
    philm.forSale = true;
    philm.price = 6;
    philm.rent = 2;

    Tile party = new Tile("PARTY");
    party.penalty = 18;
    party.knowledge = -8;

    Tile a109 = new Tile("A109");
    a109.forSale = true;
    a109.price = 10;
    a109.rent = 3;
    a109.knowledge = 3;

    Tile a117 = new Tile("A117");
    a117.forSale = true;
    a117.price = 10;
    a117.rent = 3;
    a117.knowledge = 3;

    Tile library = new Tile("LIBRARY");
    library.knowledge = 8;

    Tile b234ske = new Tile("B234Ske");
    b234ske.forSale = true;
    b234ske.price = 10;
    b234ske.rent = 3;
    b234ske.knowledge = 3;

    Tile chance2 = new Tile("CHANCE");


    Tile e632 = new Tile("E632");
    e632.forSale = true;
    e632.price = 10;
    e632.rent = 3;
    e632.knowledge = 3;

    Tile exam = new Tile("EXAM");
    exam.tileFeature = new ExamFeature();

    Tile a209 = new Tile("A209");
    a209.forSale = true;
    a209.price = 20;
    a209.rent = 5;
    a209.knowledge = 4;

    Tile a210 = new Tile("A210");
    a210.forSale = true;
    a210.price = 20;
    a210.rent = 5;
    a210.knowledge = 4;

    tiles = new ArrayList<Tile>() {{
      add(start);
      add(stil);
      add(chance1);
      add(philm);
      add(party);
      add(a109);
      add(a117);
      add(library);
      add(b234ske);
      add(chance2);
      add(e632);
      add(exam);
      add(a209);
      add(a210);
    }};

    ArrayList<AbstractChanceCard> chanceCardList = new ArrayList<AbstractChanceCard>();

    LibraryCard libraryCard = new LibraryCard(tiles);
    FallenIllCard fallenIllCard = new FallenIllCard(tiles);
    VerbalExamCard verbalExamCard = new VerbalExamCard(tiles);
    WorkShopCard workShopCard = new WorkShopCard(tiles);
    PartyCard partyCard = new PartyCard(tiles);

    chanceCardList.add(libraryCard);
    chanceCardList.add(fallenIllCard);
    chanceCardList.add(verbalExamCard);
    chanceCardList.add(workShopCard);
    chanceCardList.add(partyCard);

    ChanceCards ltuChanceCards = new ChanceCards(chanceCardList);
    ChanceFeature chanceFeature = new ChanceFeature(ltuChanceCards);

    chance1.tileFeature = chanceFeature;
    chance2.tileFeature = chanceFeature;
  }

}
