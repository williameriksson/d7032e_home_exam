package ltu.monopoly.gameboard;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math.*;
import ltu.monopoly.players.Player;

public class GameBoard {
  private ArrayList<Tile> tiles;
  private int nrOfTiles;
  private int nrHorizTiles;
  private int nrMiddleRows;
  private final int TILEHEIGHT = 5;
  private final int TILEWIDTH = 9;
  private String border;

  public GameBoard (ArrayList<Tile> tiles) {

    if ((tiles.size() % 2) != 0) {
      System.out.println("The number of tiles must be even.");
      System.exit(0);
    }

    if (tiles.size() < 4) {
      System.out.println("Please add atleast 4 tiles.");
      System.exit(0);
    }

    nrOfTiles = tiles.size();


      nrHorizTiles = 2;
      nrMiddleRows = 0;
      boolean s = true;
      for (int i = 6; i <= nrOfTiles; i += 2) {
        if (s) {
          nrHorizTiles++;
        } else {
          nrMiddleRows++;
        }
        s = !s;
      }


    // if (nrOfTiles == 4) {
    //   nrHorizTiles = 2;
    //   nrMiddleRows = 0;
    // } else {
    //   nrHorizTiles = 3;
    //   nrMiddleRows = 0;
    //   boolean s = true;
    //   for (int i = 8; i <= nrOfTiles; i += 2) {
    //     if (s) {
    //       nrMiddleRows++;
    //     } else {
    //       nrHorizTiles++;
    //     }
    //     s = !s;
    //   }
    //
    // }
    // System.out.println(nrHorizTiles);
    // System.out.println(nrMiddleRows);
    // TODO: make nrHorizTiles and nrMiddleRows automatic.
    this.tiles = tiles;
    border = new String(new char[TILEWIDTH * nrHorizTiles + nrHorizTiles + 1]).replace("\0", "*") + "\n";

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
    String emptyTile = "*" + new String(new char[TILEWIDTH]).replace("\0", " ");

    for (int i = 0; i < tileArr.size(); i++) {
      Tile tile = tileArr.get(i);

      String tileName = tile.tileName;
      int tileNameLen = tileName.length();
      int emptySpace = TILEWIDTH - tileNameLen;

      String lSpace = new String(new char[ emptySpace / 2 ]).replace("\0", " ");
      String rSpace = new String(new char[ emptySpace / 2 + emptySpace % 2 ]).replace("\0", " ");

      System.out.print("*" + lSpace + tileName + rSpace);

      if (i == tileArr.size() - 1) {
        System.out.print("* \n");
      }
    }


    for (int j = 0; j < TILEHEIGHT - 1; j++) {
      for (int i = 0; i < tileArr.size(); i++) {
        Tile tile = tileArr.get(i);

        if (tile.playerArr.size() > j) {
          Player player = tile.playerArr.get(j);
          String playerName = player.name;
          int playerNameLen = playerName.length();
          int emptySpace = TILEWIDTH - playerNameLen;

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
    String emptyTile = "*" + new String(new char[TILEWIDTH]).replace("\0", " ");
    String bottomOfTile = new String(new char[TILEWIDTH + 2]).replace("\0", "*");

    Tile leftTile = tileArr.get(0);
    Tile rightTile = tileArr.get(1);

    String leftTileName = leftTile.tileName;
    String rightTileName = rightTile.tileName;

    int leftTileNameLen = leftTileName.length();
    int rightTileNameLen = rightTileName.length();

    int leftEmptySpace = TILEWIDTH - leftTileNameLen;
    int rightEmptySpace = TILEWIDTH - rightTileNameLen;

    String lSpace = new String(new char[ leftEmptySpace / 2 ]).replace("\0", " ");
    String rSpace = new String(new char[ leftEmptySpace / 2 + leftEmptySpace % 2 ]).replace("\0", " ");

    System.out.print("*" + lSpace + leftTileName + rSpace + "*");

    String middleSpace = new String(new char[TILEWIDTH * (nrHorizTiles - 2) + (nrHorizTiles - 3)]).replace("\0", " ");

    System.out.print(middleSpace);

    lSpace = new String(new char[ rightEmptySpace / 2 ]).replace("\0", " ");
    rSpace = new String(new char[ rightEmptySpace / 2 + rightEmptySpace % 2 ]).replace("\0", " ");

    System.out.print("*" + lSpace + rightTileName + rSpace + "* \n");

    for (int j = 0; j < TILEHEIGHT - 1; j++) {

      if (leftTile.playerArr.size() > j) {
        Player player = leftTile.playerArr.get(j);
        String playerName = player.name;
        int playerNameLen = playerName.length();
        int emptySpace = TILEWIDTH - playerNameLen;

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
        int emptySpace = TILEWIDTH - playerNameLen;

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
