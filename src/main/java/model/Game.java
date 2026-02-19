package model;

import exception.InvalidMoveException;
import strategies.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextMovePlayerIndex;
    private List<WinningStrategy> winningStrategies;
    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
        this.board = new Board(dimension);
        this.players = players;
        this.nextMovePlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.winningStrategies = winningStrategies;
    }

    public static Builder getBuilder(){
        return new Builder();
    }
    public Board getBoard() {
        return board;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }

    public void printBoard(){
        board.printBoard();
    }

    private boolean validateMove(Move move){
         int row = move.getCell().getRow();
         int col = move.getCell().getCol();

         if(row<0 || row>= board.getDimension() || col<0 || col>= board.getDimension()){
             return false;
         }
         // whether the cell at which player is trying to make a move is empty or not
        if(!board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
            return false;
        }
        return true;
    }
    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextMovePlayerIndex);
        System.out.println("This is "+currentPlayer.getName()+"'s move");

        // Player will choose ṭhe move that they want to make
        Move move = currentPlayer.makeMove(board);

        // Game will validate if the move made is valid or not
        if(!validateMove(move)){
            throw new InvalidMoveException("Invalid Move, Please retry");
        }

        // Move is valid so apply it to the board
        int row  = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);
        nextMovePlayerIndex = (nextMovePlayerIndex+1)%players.size();
        Move finalMove = new Move(currentPlayer, cell);
        moves.add(finalMove);
        if(checkWinner(finalMove)){
            winner = currentPlayer;
            gameState = GameState.ENDED;
        }
        else if(moves.size() == board.getDimension() * board.getDimension()){
            gameState = GameState.DRAW;
        }

    }
    private boolean checkWinner(Move move){
        for(WinningStrategy winningStrategy :winningStrategies){
            if(winningStrategy.checkWinner(board, move)){
                return true;
            }
        }
        return false;
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        private Builder(){
            this.players = new ArrayList<>();
            this.dimension = 0;
            this.winningStrategies = new ArrayList<>();
        }
        private void validations(){
            if(dimension<=0){
                throw new RuntimeException("Dimensions cannot be negative");
            }
            validateBotCount();
            validateUniqueSymbols();
        }
        private void validateBotCount(){
            int count = 0;
            for(Player player : players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    count ++;
                }
                if(count>1){
                    throw new RuntimeException("Only one BOT is allowed in a game");
                }
            }
        }
        private void validateUniqueSymbols(){
            Set<Character> symbols = new HashSet<>();
            for(Player player:players){
                if(symbols.contains(player.getSymbol().getaChar())){
                    throw new RuntimeException("Symbol for each player should be unique");
                }
                else{
                    symbols.add(player.getSymbol().getaChar());
                }
            }
        }
        public Game build(){
            validations();
            return new Game(dimension, players, winningStrategies);
        }
        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }
    }

}
