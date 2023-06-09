package com.practice.tictactoe.strategies.botplayingstrategy;


import com.practice.tictactoe.models.*;

public class BasicBotPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Move decideMove(Player player, Board board) {
        for(int i=0;i<board.getBoard().size();i++){
            for(int j=0;j<board.getBoard().size();j++){
                if(board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    return new Move(
                            player,
                            new Cell(i,j)
                    );
                }
            }
        }
        return null;
    }
}
