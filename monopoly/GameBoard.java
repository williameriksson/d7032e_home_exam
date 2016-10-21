package monopoly;
import java.util.ArrayList;

class GameBoard {
  Player[] players;
  ArrayList<Tile> tiles;
  int nrOfTiles;
  int topTiles = 5; //change this later.
  int botTiles = 5;
  int middleTiles = 4;
  int tileHeight = 7;
  int tileWidth = 11;

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
    String[][] boardTiles = new String[this.nrOfTiles + 2][];
    String fullAsterix = new String(new char[tileWidth]).replace("\0", "*");
    String partAsterix = new String(new char[tileWidth - 1]).replace("\0", "*");
    String emptyBoxLine = new String(new char[tileWidth - 2]).replace("\0", " ");

    // Print the top part of the gameboard
    for (int i = 0; i < topTiles; i++) {
      String name = this.tiles.get(i).tileName;
      int nameLength = name.length();

      String lSpace;
      String rSpace;

      if (nameLength == this.tileWidth - 2) {
        lSpace = new String(new char[0]).replace("\0", " ");
        rSpace = new String(new char[0]).replace("\0", " ");
      } else {
        int emptySpace = this.tileWidth - nameLength - 2;
        lSpace = new String(new char[ emptySpace / 2 ]).replace("\0", " ");
        rSpace = new String(new char[ emptySpace / 2 + emptySpace % 2 ]).replace("\0", " ");
      }

      if (i == 0) {
        // The first tile is a special case..
        boardTiles[i] = new String[] {fullAsterix, "*" + lSpace + name + rSpace + "*", "*" + emptyBoxLine + "*", fullAsterix};
      } else {
        boardTiles[i] = new String[] {partAsterix, lSpace + name + rSpace + "*", emptyBoxLine + "*", partAsterix};
      }

    }

    // Print the middle part of the gameboard
    int middleIndex = topTiles;
    for (int i = 0; i <= middleTiles / 2; i++) {

      String leftName = this.tiles.get(middleIndex).tileName;
      int leftNameLength = leftName.length();
      String leftLSpace;
      String leftRSpace;

      if (leftNameLength == this.tileWidth - 2) {
        leftLSpace = new String(new char[0]).replace("\0", " ");
        leftRSpace = new String(new char[0]).replace("\0", " ");
      } else {
        int emptySpace = this.tileWidth - leftNameLength - 2;
        leftLSpace = new String(new char[ emptySpace / 2 ]).replace("\0", " ");
        leftRSpace = new String(new char[ emptySpace / 2 + emptySpace % 2 ]).replace("\0", " ");
      }

      String rightName = this.tiles.get(nrOfTiles - i - 1).tileName;
      int rightNameLength = rightName.length();
      String rightLSpace;
      String rightRSpace;

      if (rightNameLength == this.tileWidth - 2) {
        rightLSpace = new String(new char[0]).replace("\0", " ");
        rightRSpace = new String(new char[0]).replace("\0", " ");
      } else {
        int emptySpace = this.tileWidth - rightNameLength - 2;
        rightLSpace = new String(new char[ emptySpace / 2 ]).replace("\0", " ");
        rightRSpace = new String(new char[ emptySpace / 2 + emptySpace % 2 ]).replace("\0", " ");
      }

      // If we're on the last pieces of the middle part
      if (i == (middleTiles / 2) - 1) {
        boardTiles[this.nrOfTiles - i - 1] = new String[] {"", "*" + rightLSpace + rightName + rightRSpace + "*", "*" + emptyBoxLine + "*", ""};
        boardTiles[middleIndex + i] = new String[] {"", leftLSpace + leftName + leftRSpace + "*",  emptyBoxLine + "*", ""};
      } else {
        boardTiles[this.nrOfTiles - i - 1] = new String[] {"", "*" + rightLSpace + rightName + rightRSpace + "*", "*" + emptyBoxLine + "*", fullAsterix};
        boardTiles[middleIndex + i] = new String[] {"", leftLSpace + leftName + leftRSpace + "*",  emptyBoxLine + "*", partAsterix + ""};
      }

    }


    // Print the bottom tiles of the gameboard
    int botIndex = middleIndex + (middleTiles / 2);
    for (int i = 0; i < botTiles; i++) {
      String name = this.tiles.get(botIndex + i).tileName;
      int nameLength = name.length();

      String lSpace;
      String rSpace;

      if (nameLength == this.tileWidth - 2) {
        lSpace = new String(new char[0]).replace("\0", " ");
        rSpace = new String(new char[0]).replace("\0", " ");
      } else {
        int emptySpace = this.tileWidth - nameLength - 2;
        lSpace = new String(new char[ emptySpace / 2 ]).replace("\0", " ");
        rSpace = new String(new char[ emptySpace / 2 + emptySpace % 2 ]).replace("\0", " ");
      }

      if (i == botTiles - 1) {
        // The bottom left tile is a special case..
        boardTiles[botIndex + i] = new String[] {fullAsterix, "*" + lSpace + name + rSpace + "*", "*" + emptyBoxLine + "*", fullAsterix};
      } else {
        boardTiles[botIndex + i] = new String[] {partAsterix, lSpace + name + rSpace + "*", emptyBoxLine + "*", partAsterix};
      }

    }

    // boardTiles[7] = new String[] {"**********", " LIBRARY *", "         *", "**********"};
    // boardTiles[8] = new String[] {"**********", " B234Ske *", "         *", "**********"};
    // boardTiles[9] = new String[] {"**********", " CHANCE  *", "         *", "**********"};
    // boardTiles[10] = new String[] {"**********", "  E632   *", "         *", "**********"};
    // boardTiles[11] = new String[] {"***********", "*  EXAM   *", "*         *", "***********"};

    //Empty board tiles
    String emptyLineSpace = new String(new char[tileWidth - 1]).replace("\0", " ");
    boardTiles[this.boardTiles.size() - 2] = new String[] {emptyLineSpace, emptyLineSpace, emptyLineSpace, emptyLineSpace};
    boardTiles[this.boardTiles.size() - 1] = new String[] {emptyBoxLine + "*", emptyBoxLine + "*", emptyBoxLine + "*", emptyBoxLine + "*"};

    int[] printorder = new int[this.nrOfTiles + (this.middleTiles / 2) *  3];

    int index = 0;
    for (index; index < this.topTiles; index++) {
      printorder[index] = index;
    }

    for (int i = 0; i <= this.middleTiles / 2; i++) {
      printorder[index + i] = this.tiles.size() - i - 1;
      printorder[index + i] =
    }



    int[] printorder = new int[] {0, 1, 2, 3, 4, 13, 14, 14, 15, 5, 12, 14, 14, 15, 6, 11, 10, 9, 8, 7};

    for(int i=0; i<printorder.length; i=i+5) { //there are 5 tiles in each row
      for(int line=0; line<7; line++) { //each tile consists of 5 to 7 lines
        for(int tile=0; tile<5; tile++) { //print all 5 tiles in the row
          if(line==0 && boardTiles[printorder[i+tile]][0].equals("")) {
            line++; //Don't add the first line for tile 13, 5, 12, 6
          }
          if(line==6 && boardTiles[printorder[i+tile]][3].equals("")) {
            break; //Don't add the last line for tile 12, 6
          }

          //Print tiles in the right order
          if(line < 2) { //print the stars and the tile name
            System.out.print(boardTiles[printorder[i+tile]][line]);
          } else if(line < 6) { //Print the lines allocated to players
            if(this.players[line-2].position == printorder[i+tile]) {
              // player 1-4 is located at the current tile being printed
              int stars = boardTiles[printorder[i+tile]][2].replaceAll("\\s+","").length();
              if(stars==1) {
                System.out.print(this.players[line-2].name + "*");
              } else {
                System.out.print("*" + this.players[line-2].name + "*");
              }
            } else { //No player is located at this tile
              System.out.print(boardTiles[printorder[i+tile]][2]);
            }
          } else {
            System.out.print(boardTiles[printorder[i+tile]][3]);
          }

          if(((i+tile+1) % 5) == 0) { //create a newline after every 5th tile
            System.out.println("");
          }
        }
      }
    }
  }
}
