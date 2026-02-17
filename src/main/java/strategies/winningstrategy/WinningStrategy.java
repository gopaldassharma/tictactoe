package strategies.winningstrategy;

import model.Board;
import model.Cell;
import model.Move;
import model.Player;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
}
