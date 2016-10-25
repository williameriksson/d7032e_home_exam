package ltu.monopoly;
import java.util.Arrays;

public class Main {

  public static void main(String argv[]) {

    // Check that number of human players are between 1 and 4.
    if (argv.length < 1 ||argv.length > 4) {
      System.out.println("Syntax: LTUMonopoly [Player 1-4 initials]\nexample: LTUMonopoly F.L J.H K.S");
      System.exit(0);
    }

    // Check for identical names.
    for (int i = 0; i < argv.length; i++) {
      for (int j = i + 1; j < argv.length; j++) {
        if (argv[i].equals(argv[j])) {
          System.out.println("Two players cannot have the same name.");
          System.exit(0);
        }
      }
    }

    // Create a new LTUMonopoly and start the game.
    LTUMonopoly ltuMonopoly = new LTUMonopoly(argv);
    ltuMonopoly.startGame();
  }

}
