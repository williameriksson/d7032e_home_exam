package ltu.monopoly.gameboard;
import java.util.ArrayList;
import java.util.Collections;
import ltu.monopoly.players.Player;

public class GameBoard {
  Player[] players;
  ArrayList<Tile> tiles;
  int nrOfTiles;
  int nrHorizTiles = 5;
  int nrMiddleRows = 2;
  int tileHeight = 5;
  int tileWidth = 9;
  String border;

  public GameBoard (Player[] players, ArrayList<Tile> tiles) {
    this.players = players;
    this.tiles = tiles;
    nrOfTiles = tiles.size();
    border = new String(new char[tileWidth * nrHorizTiles + nrHorizTiles + 1]).replace("\0", "*") + "\n";

  }

  public void paintGameBoard() {


  /*
    ***************************************************
    *  START  *  StiL   * CHANCE  *  Philm  *  PARTY  *
    *         *         *         *         *         *
    *    0    *    1    *    2    *    3    *    4    *
    *         *         *         *         *         *
    *         *         *         *         *         *
    ***************************************************
    *  A210   *                             *  A109   *
    *         *                             *         *
    *   13    *                             *    5    *
    *         *                             *         *
    *         *                             *         *
    ***********                             ***********
    *  A209   *                             *  A117   *
    *         *                             *         *
    *   12    *                             *    6    *
    *         *                             *         *
    *         *                             *         *
    ***************************************************
    *  EXAM   *  E632   * CHANCE  * B234Ske * LIBRARY *
    *         *         *         *         *         *
    *   11    *   10    *    9    *    8    *    7    *
    *         *         *         *         *         *
    *         *         *         *         *         *
    ***************************************************

    Currency: Study-time (Time is money)
    START: Collect 40
    StiL/Philm: Go to the gym/cinema [buy: 6, rent 2]
    CHANCE: Draw a CHANCE card
    PARTY: Have a huge party [pay: 18, Decrease knowledge by 8]
    A109/A117/B234Ske/E632: Attend a lecture [buy: 10, rent 3, Increase knowledge by 3]
    LIBRARY: Study [Increase knowledge by 8]
    EXAM: Win if knowledge >=200 / Skip one turn
    A209/A210: Attend a workshop [buy: 20, rent 5, Increase knowledge by 4]
  */

    // Print the top border
    System.out.print(border);

    // Print the top tiles
    ArrayList<Tile> tempTopTiles = new ArrayList<Tile>(tiles.subList(0, nrHorizTiles));
    printFullTileRow(tempTopTiles);

    // Print border for the bottom of the top tiles
    System.out.print(border);

    // Print the middle tiles. The boolean "lastMidTiles" is there to tell
    // the function "printMiddleTileRow" if it should print a full border
    // or just a border for the single tile.
    boolean lastMidTiles = false;
    for (int i = 0; i < nrMiddleRows; i++) {
      ArrayList<Tile> tempMidTiles = new ArrayList<Tile>();

      tempMidTiles.add(tiles.get(tiles.size() - 1 - i));
      tempMidTiles.add(tiles.get(nrHorizTiles + i));

      if (i == nrMiddleRows - 1 ) {
        lastMidTiles = true;
      }
      printMiddleTileRow(tempMidTiles, lastMidTiles);
    }

    // Print the bottom tiles
    ArrayList<Tile> tempBotTiles = new ArrayList<Tile>(tiles.subList(
                                                        nrHorizTiles + nrMiddleRows,
                                                        nrOfTiles - nrMiddleRows));

    // We need to reverse the list to get the correct printing order
    Collections.reverse(tempBotTiles);
    printFullTileRow(tempBotTiles);

    // Print the bottom border
    System.out.print(border);



  }

  private void printFullTileRow (ArrayList<Tile> tileArr) {
    String emptyTile = "*" + new String(new char[tileWidth]).replace("\0", " ");

    for (int i = 0; i < tileArr.size(); i++) {
      Tile tile = tileArr.get(i);

      String tileName = tile.tileName;
      int tileNameLen = tileName.length();
      int emptySpace = tileWidth - tileNameLen;

      String lSpace = new String(new char[ emptySpace / 2 ]).replace("\0", " ");
      String rSpace = new String(new char[ emptySpace / 2 + emptySpace % 2 ]).replace("\0", " ");

      System.out.print("*" + lSpace + tileName + rSpace);

      if (i == tileArr.size() - 1) {
        System.out.print("* \n");
      }
    }


    for (int j = 0; j < 4; j++) {
      for (int i = 0; i < tileArr.size(); i++) {
        Tile tile = tileArr.get(i);

        if (tile.playerArr.size() > j) {
          Player player = tile.playerArr.get(j);
          String playerName = player.name;
          int playerNameLen = playerName.length();
          int emptySpace = tileWidth - playerNameLen;

          String lSpace = new String(new char[ emptySpace / 2 ]).replace("\0", " ");
          String rSpace = new String(new char[ emptySpace / 2 + emptySpace % 2 ]).replace("\0", " ");

          System.out.print("*" + lSpace + playerName + rSpace);
        } else {
          System.out.print(emptyTile);
        }

        if (i == nrHorizTiles - 1) {
          System.out.print("* \n");
        }
      }
    }
  }

  private void printMiddleTileRow(ArrayList<Tile> tileArr, boolean lastMidTiles) {
    String emptyTile = "*" + new String(new char[tileWidth]).replace("\0", " ");
    String bottomOfTile = new String(new char[tileWidth + 2]).replace("\0", "*");

    Tile leftTile = tileArr.get(0);
    Tile rightTile = tileArr.get(1);

    String leftTileName = leftTile.tileName;
    String rightTileName = rightTile.tileName;

    int leftTileNameLen = leftTileName.length();
    int rightTileNameLen = rightTileName.length();

    int leftEmptySpace = tileWidth - leftTileNameLen;
    int rightEmptySpace = tileWidth - rightTileNameLen;

    String lSpace = new String(new char[ leftEmptySpace / 2 ]).replace("\0", " ");
    String rSpace = new String(new char[ leftEmptySpace / 2 + leftEmptySpace % 2 ]).replace("\0", " ");

    System.out.print("*" + lSpace + leftTileName + rSpace + "*");

    String middleSpace = new String(new char[tileWidth * (nrHorizTiles - 2) + 2]).replace("\0", " ");

    System.out.print(middleSpace);

    lSpace = new String(new char[ rightEmptySpace / 2 ]).replace("\0", " ");
    rSpace = new String(new char[ rightEmptySpace / 2 + rightEmptySpace % 2 ]).replace("\0", " ");

    System.out.print("*" + lSpace + rightTileName + rSpace + "* \n");

    for (int j = 0; j < 4; j++) {

      if (leftTile.playerArr.size() > j) {
        Player player = leftTile.playerArr.get(j);
        String playerName = player.name;
        int playerNameLen = playerName.length();
        int emptySpace = tileWidth - playerNameLen;

        lSpace = new String(new char[ emptySpace / 2 ]).replace("\0", " ");
        rSpace = new String(new char[ emptySpace / 2 + emptySpace % 2 ]).replace("\0", " ");

        System.out.print("*" + lSpace + playerName + rSpace + "*");
      } else {
        System.out.print(emptyTile + "*");
      }
      System.out.print(middleSpace);



      if (rightTile.playerArr.size() > j) {
        Player player = rightTile.playerArr.get(j);
        String playerName = player.name;
        int playerNameLen = playerName.length();
        int emptySpace = tileWidth - playerNameLen;

        lSpace = new String(new char[ emptySpace / 2 ]).replace("\0", " ");
        rSpace = new String(new char[ emptySpace / 2 + emptySpace % 2 ]).replace("\0", " ");

        System.out.print("*" + lSpace + playerName + rSpace + "* \n");
      } else {
        System.out.print(emptyTile + "* \n");
      }

    }
      if (lastMidTiles) {
        System.out.print(border);
      } else {
        System.out.print(bottomOfTile);
        System.out.print(middleSpace);
        System.out.print(bottomOfTile + "\n");
      }

  }


}
