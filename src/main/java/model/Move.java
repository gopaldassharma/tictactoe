package model;

public class Move {
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public Move(Player player, Cell cell) {
        this.player = player;
        this.cell = cell;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    private Cell cell;
}
