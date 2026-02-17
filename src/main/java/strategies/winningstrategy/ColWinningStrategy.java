package strategies.winningstrategy;

import model.Board;
import model.Move;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy{
    private final Map<Integer, HashMap<Character, Integer>> colMaps = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        Character aChar = move.getPlayer().getSymbol().getaChar();
        int col = move.getCell().getCol();

        if(!colMaps.containsKey(col)){
            colMaps.put(col, new HashMap<>());
        }
        HashMap<Character, Integer> currColMap = colMaps.get(col);
        if(!currColMap.containsKey(aChar)){
            currColMap.put(aChar, 0);
        }
        currColMap.put(aChar, currColMap.get(aChar)+1);

        return (currColMap.get(aChar).equals(board.getDimension()));
    }
}
