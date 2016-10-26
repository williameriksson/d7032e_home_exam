echo "Compiling code."
javac -d ./bin -cp ./src ./src/ltu.monopoly.players/* ./src/ltu.monopoly.gameboard/* ./src/ltu.monopoly.cards/* ./src/ltu.monopoly/*
echo "Compiling tests."
# javac -d ./bin -cp ./src ./tests/*
javac -sourcepath ./src -d ./bin -cp ./lib/org.junit4-4.3.1.jar:./src:./tests:./bin ./tests/*
#./monopoly ./monopoly/LTUMonopoly.java ./monopoly/Player.java ./monopoly/Tile.java ./monopoly/GameBoard.java ./monopoly/LTUTiles.java ./monopoly/TileFeature.java ./monopoly/ChanceCard.java ./monopoly/ChanceCards.java
# javac -d ./bin -cp ./src ./src/ltu/Main.java ./src/ltu/CalendarImpl.java
