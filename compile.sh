echo "Compiling code."
javac -d ./bin -cp ./src ./src/ltu.monopoly.players/* ./src/ltu.monopoly.gameboard/* ./src/ltu.monopoly.cards/* ./src/ltu.monopoly/*
echo "Compiling tests."
javac -sourcepath ./src -d ./bin -cp ./lib/org.junit4-4.3.1.jar:./src:./tests:./bin ./tests/*
