import controller.GameController;
import exception.InvalidMoveException;
import model.*;
import strategies.winningstrategy.ColWinningStrategy;
import strategies.winningstrategy.DiagonalWinningStrategy;
import strategies.winningstrategy.RowWinningStrategy;
import strategies.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        System.out.println("GAME STARTS");
        Scanner scanner = new Scanner(System.in);
//        int dimension = scanner.nextInt();
        List<Player> playerList = new ArrayList<>();
        GameController gameController = new GameController();

        int dimension = 3;
        playerList.add(
                new Player(new Symbol('X'), "Gopal", PlayerType.HUMAN));
        playerList.add(
                new Player(new Symbol('O'), "Sanskriti", PlayerType.HUMAN));

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
            System.out.println("Do you Want to undo ? Y or N");
            String isUndo = scanner.next();
            if(isUndo.equalsIgnoreCase("Y")){
                gameController.undo(game);
                continue;
            }
            gameController.makeMove(game);

        }
        gameController.printBoard(game);
        if(gameController.gameState(game).equals(GameState.ENDED)){
            System.out.println(gameController.getWinner(game).getName() + "has won the game");
        }
        else{
            System.out.println("GAME DRAWN");
        }

    }
}
