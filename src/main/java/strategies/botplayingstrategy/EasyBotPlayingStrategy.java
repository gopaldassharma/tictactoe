package strategies.botplayingstrategy;

import model.Board;
import model.Cell;
import model.CellState;
import model.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {
        // Iterate over the board and make the move in first empty cell

        for(List<Cell> row : board.getBoard()){
            for(Cell cell : row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(null, cell);
                }
            }
        }
        return null;
    }
}
