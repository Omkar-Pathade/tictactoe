package com.practice.tictactoe.strategies.gamewinningstrategy;



import com.practice.tictactoe.models.Board;
import com.practice.tictactoe.models.Cell;
import com.practice.tictactoe.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BasicGameWinningStrategy implements GameWinningStrategy {
    private List<HashMap<Character, Integer>> rowSymbolCounts = new ArrayList<>();
    private List<HashMap<Character, Integer>> colSymbolCounts = new ArrayList<>();

    private HashMap<Character, Integer> topLeftDiagonalSymbolCounts = new HashMap<>();
    private HashMap<Character, Integer> topRightDiagonalSymbolCounts = new HashMap<>();



    public BasicGameWinningStrategy(int dimension) {
        for(int i=0;i<dimension;i++){
            rowSymbolCounts.add(new HashMap<>());
            colSymbolCounts.add(new HashMap<>());
        }
    }

    public boolean isCellOnTopLeftDiagonal(int row, int col) {
        return row == col;
    }

    public boolean isCellOnTopRightDiagonal(int row, int col, int size) {
        return row + col == size - 1;
    }

    @Override
    public boolean isWinner(Board board, Player lastMovePlayer, Cell moveCell) {
        char symbol = moveCell.getPlayer().getSymbol();
        int row = moveCell.getRow();
        int col = moveCell.getCol();
        int size = board.getBoard().size();

        if(!rowSymbolCounts.get(row).containsKey(symbol)){
            rowSymbolCounts.get(row).put(symbol, 0);
        }

        rowSymbolCounts.get(row).put(
                symbol, rowSymbolCounts.get(row).get(symbol) + 1
        );

        if(!colSymbolCounts.get(col).containsKey(symbol)){
            colSymbolCounts.get(col).put(symbol, 0);
        }

        colSymbolCounts.get(col).put(
                symbol, colSymbolCounts.get(col).get(symbol) + 1
        );

        if(isCellOnTopLeftDiagonal(row, col)){
            if(!topLeftDiagonalSymbolCounts.containsKey(symbol)){
                topLeftDiagonalSymbolCounts.put(symbol, 0);
            }

            topLeftDiagonalSymbolCounts.put(
                    symbol, topLeftDiagonalSymbolCounts.get(symbol) + 1
            );
        }

        if(isCellOnTopRightDiagonal(row, col, size)){
            if(!topRightDiagonalSymbolCounts.containsKey(symbol)){
                topRightDiagonalSymbolCounts.put(symbol, 0);
            }

            topRightDiagonalSymbolCounts.put(
                    symbol, topRightDiagonalSymbolCounts.get(symbol) + 1
            );
        }

        if(rowSymbolCounts.get(row).get(symbol) == size ||
                colSymbolCounts.get(col).get(symbol) == size){
            return true;
        }

        if(isCellOnTopRightDiagonal(row, col, size) && topRightDiagonalSymbolCounts.get(symbol) == size) return true;
        if(isCellOnTopLeftDiagonal(row, col) && topLeftDiagonalSymbolCounts.get(symbol) == size) return true;


        return false;
    }
}
