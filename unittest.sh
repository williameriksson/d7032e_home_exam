echo "Running unittests..."
java -javaagent:./lib/org.jacoco.agent-0.7.7.jar -cp ./lib/org.junit4-4.3.1.jar:./bin org.junit.runner.JUnitCore ltu.monopoly.gameboard.TileTest ltu.monopoly.gameboard.TilesTest ltu.monopoly.cards.ChanceCardTest
echo "Generating report..."
java -jar ./lib/org.jacoco.examples-0.7.7.jar .
