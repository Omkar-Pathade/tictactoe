package com.practice.tictactoe.strategies.gamewinningstrategy;


import com.practice.tictactoe.models.Board;
import com.practice.tictactoe.models.Cell;
import com.practice.tictactoe.models.Player;

public interface GameWinningStrategy {
    boolean isWinner(Board board,
                     Player lastMovePlayer,
                     Cell moveCell);
}
