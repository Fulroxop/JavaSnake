import java.awt.*;
import javax.swing.*;

public class GameBoard extends JPanel {

    private int boardWidth;
    private int boardHeight;
    private int cellSize;
    private Snake snake;
    private Apple apple;

    public GameBoard() {
        this.boardWidth = 600;
        this.boardHeight = 600;
        this.cellSize = 20;

        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLACK);
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (snake != null) {
            g.setColor(Color.GREEN);
            for (Point p : snake.getBody()) {
                g.fillOval(p.x * cellSize, p.y * cellSize, cellSize, cellSize);
            }
        }

        if (apple != null) {
            g.setColor(Color.RED);
            g.fillOval(apple.getPosition().x * cellSize, apple.getPosition().y * cellSize, cellSize, cellSize);
        }
    }
}
