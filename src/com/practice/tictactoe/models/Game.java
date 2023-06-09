package com.practice.tictactoe.models;


import com.practice.tictactoe.exceptions.InvalidGameConstructionParametersException;
import com.practice.tictactoe.strategies.gamewinningstrategy.GameWinningStrategy;
import com.practice.tictactoe.strategies.gamewinningstrategy.BasicGameWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private GameWinningStrategy gameWinningStrategy;
    private Player winner;
    private Stack<Move> gameOrder = new Stack<Move>();

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    private Game() {
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public void undo() {
        if (gameOrder.isEmpty()) {
            System.out.println("Nothing To Undo");
        } else {
            Move myMove = gameOrder.peek();
            board.getBoard().get(myMove.getCell().getRow()).get(myMove.getCell().getCol()).setCellState(CellState.EMPTY);
            board.getBoard().get(myMove.getCell().getRow()).get(myMove.getCell().getCol()).setPlayer(null);
            int indx = moves.size() - 1;
            moves.remove(indx);
            gameOrder.pop();
            nextPlayerIndex += 1;
            nextPlayerIndex %= players.size();
        }
    }

    public void makeNextMove() {
        Player playerToMove = players.get(nextPlayerIndex);

        System.out.println("It is " + playerToMove.getName() + "'s move.");

        Move move = playerToMove.decideMove(this.board);

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        System.out.println("Move happened at: " + row + "," + col + ".");

        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(playerToMove);

        Move finalMove = new Move(
                playerToMove,
                board.getBoard().get(row).get(col)
        );

        this.moves.add(finalMove);
        this.gameOrder.add(finalMove);

        if (gameWinningStrategy.isWinner(
                board, playerToMove, finalMove.getCell()
        )) {
            gameStatus = GameStatus.ENDED;
            winner = playerToMove;
        }

        nextPlayerIndex += 1;
        nextPlayerIndex %= players.size();

    }

    public void displayBoard() {
        this.board.display();
    }

    public Board getBoard() {
        return board;
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

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private boolean valid() throws InvalidGameConstructionParametersException {
            if (this.dimension < 3) {
                throw new InvalidGameConstructionParametersException("Enter Dimension >= 3");
            }
            if (this.players.size() != this.dimension - 1) {
                throw new InvalidGameConstructionParametersException("Players can be Dimension -1");
            }

            return true;
        }

        public Game build() throws InvalidGameConstructionParametersException {
            try {
                valid();
            } catch (Exception e) {
                throw new InvalidGameConstructionParametersException(e.getMessage());
            }

            Game game = new Game();
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayers(players);
            game.setMoves(new ArrayList<>());
            game.setBoard(new Board(dimension));
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new BasicGameWinningStrategy(dimension));

            return game;
        }
    }
}
