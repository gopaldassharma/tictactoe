package model;

import java.util.Scanner;

public class Player {
    private Symbol symbol;
    private String name;
    private PlayerType playerType;
    public static Scanner scanner = new Scanner(System.in);
    public Symbol getSymbol() {
        return symbol;
    }

    public Player(Symbol symbol, String name, PlayerType playerType) {
        this.symbol = symbol;
        this.name = name;
        this.playerType = playerType;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    Move makeMove(Board board){
        // Take row, col in input from the user
        System.out.println("Please enter the row number where you want to make the move");
        int row = scanner.nextInt();
        System.out.println("Please enter the col number where you want to make the move");
        int col = scanner.nextInt();

        return new Move(this, new Cell(row, col));
    }

}
