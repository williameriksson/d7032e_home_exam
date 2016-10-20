package monopoly;


class GameBoard {
  Player[] players;
  GameBoard (Player[] players) {
    this.players = players;
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

    String[][] boardTiles = new String[16][];
    boardTiles[0] = new String[] {"***********", "*  START  *", "*         *", "***********"};
    boardTiles[1] = new String[] {"**********", "  StiL   *", "         *", "**********"};
    boardTiles[2] = new String[] {"**********", " CHANCE  *", "         *", "**********"};
    boardTiles[3] = new String[] {"**********", "  Philm  *", "         *", "**********"};
    boardTiles[4] = new String[] {"**********", "  PARTY  *", "         *", "**********"};

    boardTiles[13] = new String[] {"", "*  A210   *", "*         *", "***********"}; //6 line tile
    //Add 3 empty tiles (boardTiles[14/15]) in between while printing it on the screen
    boardTiles[5] = new String[] {"", "  A109   *", "         *", "**********"}; //6 line tile

    boardTiles[12] = new String[] {"", "*  A209   *", "*         *", ""}; //5 line tile
    //Add 3 empty tiles (boardTiles[14/15]) in between while printing it on the screen
    boardTiles[6] = new String[] {"", "  A117   *", "         *", ""}; //5 line tile

    boardTiles[11] = new String[] {"***********", "*  EXAM   *", "*         *", "***********"};
    boardTiles[10] = new String[] {"**********", "  E632   *", "         *", "**********"};
    boardTiles[9] = new String[] {"**********", " CHANCE  *", "         *", "**********"};
    boardTiles[8] = new String[] {"**********", " B234Ske *", "         *", "**********"};
    boardTiles[7] = new String[] {"**********", " LIBRARY *", "         *", "**********"};

    //Empty board tiles
    boardTiles[14] = new String[] {"          ", "          ", "          ", "          "};
    boardTiles[15] = new String[] {"         *", "         *", "         *", "         *"};

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
