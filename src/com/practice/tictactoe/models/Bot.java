package com.practice.tictactoe.models;


import com.practice.tictactoe.factories.BotPlayingStrategyFactory;
import com.practice.tictactoe.strategies.botplayingstrategy.BotPlayingStrategy;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, char aSymbol, BotDifficultyLevel difficultyLevel) {
        super(name, aSymbol, PlayerType.BOT);
        this.botDifficultyLevel = difficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getStrategyForDifficultyLevel(difficultyLevel);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move decideMove(Board board) {
        return botPlayingStrategy.decideMove(this, board);
    }
}
