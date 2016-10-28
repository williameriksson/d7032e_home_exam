package ltu.monopoly.cards;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.Assert.*;
import org.junit.Test;
import ltu.monopoly.gameboard.*;
import ltu.monopoly.players.*;

public class ChanceCardTest {

  Tiles tilesObj = new Tiles();
  ArrayList<Tile> tiles = tilesObj.tiles;
  FallenIllCard fallenIllCard = new FallenIllCard(tiles);
  LibraryCard libraryCard = new LibraryCard(tiles);
  PartyCard partyCard = new PartyCard(tiles);
  VerbalExamCard verbalExamCard = new VerbalExamCard(tiles);
  WorkShopCard workShopCard = new WorkShopCard(tiles);
  Player testPlayer = new Player("TestPlayer");

  public ChanceCardTest () {

  }

  @Test
  public void checkGetIndex () {
    int index = fallenIllCard.getTileIndexByName("START");
    assertEquals(index, 0);
  }

  @Test
  public void verifyFallenIllCard () {
    Optional<Integer> optPos = fallenIllCard.cardFunctionality(testPlayer);
    int newPos = optPos.get();
    assertEquals(newPos, 0);
  }

  @Test
  public void verifyLibraryCard () {
    Optional<Integer> optPos = libraryCard.cardFunctionality(testPlayer);
    int newPos = optPos.get();
    int libraryPos = libraryCard.getTileIndexByName("LIBRARY");
    assertEquals(newPos, libraryPos);
  }


  @Test
  public void verifyPartyCard () {
    Optional<Integer> optPos = partyCard.cardFunctionality(testPlayer);
    int newPos = optPos.get();
    int partyPos = verbalExamCard.getTileIndexByName("PARTY");
    assertEquals(newPos, partyPos);
  }

  @Test
  public void verifyVerbalExamCard () {
    Optional<Integer> optPos = verbalExamCard.cardFunctionality(testPlayer);
    int newPos = optPos.get();
    int examPos = verbalExamCard.getTileIndexByName("EXAM");
    assertEquals(newPos, examPos);
  }

  @Test
  public void verifyWorkShopCard () {
    Optional<Integer> optPos = workShopCard.cardFunctionality(testPlayer);
    int newPos = optPos.get();
    int workShopPos = verbalExamCard.getTileIndexByName("A209");
    assertEquals(newPos, workShopPos);
  }

}
