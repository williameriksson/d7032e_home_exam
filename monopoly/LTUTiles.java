package monopoly;
import java.util.ArrayList;


class LTUTiles {

  Tile start = New Tile("START");
  start.reward = 40;

  Tile stil = New Tile("StiL");
  stil.price = 6;
  stil.rent = 2;

  Tile chance1 = new Tile("CHANCE");

  Tile philm = new Tile("Philm");
  philm.price = 6;
  philm.rent = 2;

  Tile party = new Tile("PARTY");
  party.penalty = 18;
  party.knowledge = -8;

  Tile a109 = new Tile("A109");
  a109.price = 10;
  a109.rent = 3;
  a109.knowledge = 3;

  Tile a117 = new Tile("A117");
  a109.price = 10;
  a109.rent = 3;
  a109.knowledge = 3;

  Tile library = new Tile("LIBRARY");
  library.knowledge = 8;

  Tile b234ske = new Tile("B234Ske");
  b234ske.price = 10;
  b234ske.rent = 3;
  b234ske.knowledge = 3;

  Tile chance2 = new Tile("CHANCE");

  Tile e632 = new Tile("E632");
  e632.price = 10;
  e632.rent = 3;
  e632.knowledge = 3;

  Tile exam = new Tile("EXAM");

  Tile a209 = new Tile("A209");
  a209.price = 20;
  a209.rent = 5;
  a209.knowledge = 4;

  Tile a210 = new Tile("A210");
  a210.price = 20;
  a210.rent = 5;
  a210.knowledge = 4;

  ArrayList<Tile> tiles = new ArrayList<Tile>() {{
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
  
  LTUTiles() {

  }

}
