package controller;

import exception.InvalidMoveException;
import model.Game;
import model.GameState;
import model.Player;
import strategies.winningstrategy.WinningStrategy;

import java.util.List;

public class GameController {
    // Make Move
    // Undo
    // checkWinner
    // gameState

    public Game startGame(int dimension, List<Player> playerList, List<WinningStrategy> winningStrategies){
        return Game.getBuilder().setDimension(dimension)
                .setPlayers(playerList)
                .setWinningStrategies(winningStrategies)
                .build();
    }
    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();
    }
    public GameState gameState(Game game){
        return game.getGameState();
    }
    public Player getWinner(Game game){
        return game.getWinner();
    }
    public void printBoard(Game game){
        game.printBoard();
    }
    public void undo(Game game){

    }
}
