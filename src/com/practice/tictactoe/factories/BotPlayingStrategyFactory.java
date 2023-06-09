package com.practice.tictactoe.factories;


import com.practice.tictactoe.models.BotDifficultyLevel;
import com.practice.tictactoe.strategies.botplayingstrategy.BotPlayingStrategy;
import com.practice.tictactoe.strategies.botplayingstrategy.BasicBotPlayingStrategy;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getStrategyForDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        if(difficultyLevel.equals(BotDifficultyLevel.EASY))
            return new BasicBotPlayingStrategy();
        return null;
    }
}
