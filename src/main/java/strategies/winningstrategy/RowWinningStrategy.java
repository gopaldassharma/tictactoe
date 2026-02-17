package strategies.winningstrategy;

import model.Board;
import model.Move;
import model.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{
    private final Map<Integer, HashMap<Character, Integer>> rowMaps = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Character aChar = move.getPlayer().getSymbol().getaChar();

        if(!rowMaps.containsKey(row)){
            rowMaps.put(row, new HashMap<>());
        }

        HashMap<Character, Integer> currentRow = rowMaps.get(row);
        if(!currentRow.containsKey(aChar)){
            currentRow.put(aChar, 0);
        }
        currentRow.put(aChar, currentRow.get(aChar)+1);
        return currentRow.get(aChar).equals(board.getDimension());
    }
}
