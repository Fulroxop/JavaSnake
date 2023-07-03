import java.awt.Point;
import java.util.Random;

public class Apple {
    private Point position;
    private int width;
    private int height;

    public Apple(int boardWidth, int boardHeight, int cellSize) {
        this.width = boardWidth / cellSize;
        this.height = boardHeight / cellSize;
        generatePosition();
    }

    public void generatePosition() {
        Random rand = new Random();
        int x = rand.nextInt(width);
        int y = rand.nextInt(height);
        position = new Point(x, y);
    }

    public Point getPosition() {
        return position;
    }
}
