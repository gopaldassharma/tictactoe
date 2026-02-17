package strategies.winningstrategy;

import model.Board;
import model.Cell;
import model.Player;

public interface WinningStrategy {
    boolean checkWinner(Board board, Player player, Cell cell);
}
