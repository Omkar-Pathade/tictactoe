package com.practice.tictactoe;

import com.practice.tictactoe.controllers.GameController;
import com.practice.tictactoe.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GameController gameController = new GameController();

        System.out.println("Insert the dimension of the game?");
        int dimension = in.nextInt();

        System.out.println("Do you need a Bot in Game? y/n");
        String isBotString = in.next();

        List<Player> players = new ArrayList<>();

        int toIterate = dimension - 1;

        if (isBotString.equals("y")) {
            toIterate = dimension - 2;
        }

        for (int i = 0; i < toIterate; i++) {
            System.out.println("What is the name of the player? " + i);
            String playerName = in.next();

            System.out.println("What is the symbol for this player" + i);
            char playerSymbol = in.next().charAt(0);

            players.add(new Player(playerName, playerSymbol, PlayerType.HUMAN));
        }

        if (isBotString.equals("y")) {
            System.out.println("What is the name of the bot? ");
            String botName = in.next();

            System.out.println("What is the symbol for this bot");
            char botSymbol = in.next().charAt(0);

            players.add(new Bot(botName, botSymbol, BotDifficultyLevel.EASY));
        }

        Game game = gameController.createGame(
                dimension, players
        );

        while (gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            System.out.println("Current board");

            gameController.displayBoard(game);

            System.out.println("Undo move? y/n");
            String input = in.next();

            if (input.equals("y")) {
                gameController.undo(game);
            } else {
                gameController.executeNextMove(game);
            }
        }

        System.out.println("Game has ended: ");

        if (!game.getGameStatus().equals(GameStatus.DRAW)) {
            System.out.println("Winner is: ." + gameController.getWinner(game).getName());
            gameController.displayBoard(game);
        }

    }
}