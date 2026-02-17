import controller.GameController;
import model.*;
import strategies.winningstrategy.ColWinningStrategy;
import strategies.winningstrategy.DiagonalWinningStrategy;
import strategies.winningstrategy.RowWinningStrategy;
import strategies.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("GAME STARTS");
        Scanner scanner = new Scanner(System.in);
//        int dimension = scanner.nextInt();
        List<Player> playerList = new ArrayList<>();
        GameController gameController = new GameController();

        int dimension = 3;
        playerList.add(
                new Player(new Symbol('X'), "Gopal", PlayerType.HUMAN));
        playerList.add(
                new Player(new Symbol('O'), "Sharma", PlayerType.BOT));

        List<WinningStrategy> winningStrategies = List.of(
                new RowWinningStrategy(),
                new ColWinningStrategy(),
                new DiagonalWinningStrategy()
        );
        Game game = gameController.startGame(dimension, playerList, winningStrategies);
        //gameController.printBoard(game);

        //Play the game

        while(gameController.gameState(game).equals(GameState.IN_PROGRESS)){
            // show the board
            // make a move
            gameController.printBoard(game);
            System.out.println("Do you Want to undo ?");
            String isUndo = scanner.next();
            if(isUndo.equalsIgnoreCase("Y")){
                gameController.undo(game);
                continue;
            }
            gameController.makeMove(game);

        }
        System.out.println("DEBUG");

    }
}
