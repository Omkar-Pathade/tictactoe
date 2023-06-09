package com.practice.tictactoe.strategies.botplayingstrategy;


import com.practice.tictactoe.models.Board;
import com.practice.tictactoe.models.Move;
import com.practice.tictactoe.models.Player;

public interface BotPlayingStrategy {

    Move decideMove(Player player, Board board);
}
