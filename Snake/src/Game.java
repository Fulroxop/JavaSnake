import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import javax.swing.*;

public class Game implements ActionListener {
    private static final int BOARD_WIDTH = 600;
    private static final int BOARD_HEIGHT = 600;
    private static final int CELL_SIZE = 20;
    private static Game instance;

    private JFrame frame;
    private GameBoard board;
    private Snake snake;
    private Apple apple;
    private Timer timer;
    private int score = 0;
    private ScoreBoard scoreBoard = new ScoreBoard(10);
    private String playerName;
    private boolean isGameOver;


    private Game() {
        frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board = new GameBoard();
        frame.add(board);

        snake = new Snake(BOARD_WIDTH, BOARD_HEIGHT, CELL_SIZE);
        board.setSnake(snake);

        apple = new Apple(BOARD_WIDTH, BOARD_HEIGHT, CELL_SIZE);
        board.setApple(apple);

        timer = new Timer(100, this);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        SnakeKeyListener snakeKeyListener = new SnakeKeyListener(snake);
        frame.addKeyListener(snakeKeyListener);

        isGameOver = false;
        timer.start();
    }

    public static void main(String[] args) {
        instance = new Game();
    }

    public static Game getInstance() {
        return instance;
    }

    public Apple getApple() {
        return apple;
    }

    public void moveApple() {
        apple.generatePosition();
        while (snake.intersects(apple.getPosition())) {
            apple.generatePosition();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!snake.isAlive()) {
            timer.stop();
            isGameOver = true;
            playerName = JOptionPane.showInputDialog(frame, "Game over! Please enter your name:");
            int option = JOptionPane.showOptionDialog(frame, "Game over! Your score is " + score + "\nDo you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (option == JOptionPane.YES_OPTION) {
                restartGame();
            } else {
                scoreBoard.addScore(playerName, score);
                scoreBoard.saveScoresToFile("scores.txt");
                java.util.List<Map.Entry<String, Integer>> topScores = scoreBoard.getTopScores(10);
                System.out.println("Top 10 scores:");
                for (Map.Entry<String, Integer> entry : topScores) {
                    System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
                }
                System.exit(0);
            }
        }

        snake.move();
        if (snake.intersects(apple.getPosition())) {
            score += 10;
            moveApple();
            board.setApple(apple);
        }

        board.repaint();
    }

    private void restartGame() {
        score = 0;
        snake = new Snake(BOARD_WIDTH, BOARD_HEIGHT, CELL_SIZE);
        board.setSnake(snake);
        apple = new Apple(BOARD_WIDTH, BOARD_HEIGHT, CELL_SIZE);
        board.setApple(apple);
        isGameOver = false;
        snake.setDirection(new Point(-1, 0));
        SnakeKeyListener snakeKeyListener = new SnakeKeyListener(snake);
        frame.removeKeyListener(frame.getKeyListeners()[0]);
        frame.addKeyListener(snakeKeyListener);
        timer.restart();
    }
}
