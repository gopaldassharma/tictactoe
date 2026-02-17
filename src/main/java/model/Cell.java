package model;

public class Cell {
    private int row;
    private int col;
    private CellState cellState;
    private Player player;

    public void display(){
        if(player==null){
             //cell is Empty
            System.out.print("| -- |");
        }
        else{
            System.out.print("| "+player.getSymbol().getaChar()+ " |");
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellState = CellState.EMPTY;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

}
