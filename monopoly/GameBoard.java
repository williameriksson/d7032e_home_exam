package monopoly;
import java.util.ArrayList;

class GameBoard {
  Player[] players;
  ArrayList<Tile> tiles;
  int nrOfTiles;
  int nrHorizTiles = 5;
  int middleTiles = 4;
  int tileHeight = 5;
  int tileWidth = 9;

  GameBoard (Player[] players, ArrayList<Tile> tiles) {
    this.players = players;
    this.tiles = tiles;
    this.nrOfTiles = tiles.size();


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
    String fullAsterix = new String(new char[this.tileWidth]).replace("\0", "*");
    String partAsterix = new String(new char[this.tileWidth - 1]).replace("\0", "*");
    String emptyBoxLine = new String(new char[this.tileWidth - 2]).replace("\0", " ");

    String border = new String(new char[this.tileWidth * this.nrHorizTiles + this.nrHorizTiles + 1]).replace("\0", "*") + "\n";

    // Print the top border
    System.out.print(border);

    ArrayList<Tile> tempTopTiles = new ArrayList<Tile>(tiles.subList(0, this.nrHorizTiles));
    printFullTileRow(tempTopTiles);

    System.out.print(border);



  }

  private void printFullTileRow (ArrayList<Tile> tileArr) {
    String emptyTile = "*" + new String(new char[this.tileWidth]).replace("\0", " ");

    for (int i = 0; i < tileArr.size(); i++) {
      Tile tile = tileArr.get(i);

      String tileName = tile.tileName;
      int tileNameLen = tileName.length();
      int emptySpace = this.tileWidth - tileNameLen;

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

        if (tile.tilePlayers.size() > j) {
          Player player = tile.tilePlayers.get(j);
          String playerName = player.name;
          int playerNameLen = playerName.length();
          int emptySpace = this.tileWidth - playerNameLen;

          String lSpace = new String(new char[ emptySpace / 2 ]).replace("\0", " ");
          String rSpace = new String(new char[ emptySpace / 2 + emptySpace % 2 ]).replace("\0", " ");

          System.out.print("*" + lSpace + playerName + rSpace);
        } else {
          System.out.print(emptyTile);
        }

        if (i == this.nrHorizTiles - 1) {
          System.out.print("* \n");
        }
      }
    }
  }

  private void printMiddleTileRow(ArrayList<Tile> tileArr) {


    Tile leftTile = tileArr.get(0);
    Tile rightTile = tileArr.get(1);

    String leftTileName = leftTile.tileName;
    String rightTileName = rightTileName.tileName;

    int leftTileNameLen = leftTileName.length();
    int rightTileNameLen = rightTileName.length();

    int leftEmptySpace = this.tileWidth - leftTileNameLen;
    int rightEmptySpace = this.tileWidth - rightTileNameLen;

    String lSpace = new String(new char[ leftEmptySpace / 2 ]).replace("\0", " ");
    String rSpace = new String(new char[ leftEmptySpace / 2 + leftEmptySpace % 2 ]).replace("\0", " ");

    System.out.print("*" + lSpace + leftTileName + rSpace + "*");

    String middleSpace = new String(new char[ leftEmptySpace / 2 ]).replace("\0", " ");




    if (i == tileArr.size() - 1) {
      System.out.print("* \n");
    }



  }


}
