package strategies.winningstrategy;

import model.Board;
import model.Move;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{
    private final Map<Character, Integer > leftDiagonalMap = new HashMap<>(); // starting from 0,0
    private final Map<Character, Integer > rightDiagonalMap = new HashMap<>(); // starting from 0, n-1

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Character achar = move.getPlayer().getSymbol().getaChar();

        // Condition for left diagonal, row==col
        if(row==col){
            if(!leftDiagonalMap.containsKey(achar)){
                leftDiagonalMap.put(achar, 0);
            }
            leftDiagonalMap.put(achar,leftDiagonalMap.get(achar)+1);
        }

        // Condition for right diagonal, row+col==N-1
        if(row==col){
            if(!rightDiagonalMap.containsKey(achar)){
                rightDiagonalMap.put(achar, 0);
            }
            rightDiagonalMap.put(achar,rightDiagonalMap.get(achar)+1);
        }
        if((row==col && leftDiagonalMap.get(achar)==board.getDimension()) || (row+col== board.getDimension()-1 && leftDiagonalMap.get(achar)==board.getDimension())){
            return true;
        }
        return false;
    }
}
