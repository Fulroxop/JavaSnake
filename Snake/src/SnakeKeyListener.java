import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeKeyListener implements KeyListener {

    private Snake snake;

    public SnakeKeyListener(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        Point direction = null;
        if (keyCode == KeyEvent.VK_LEFT) {
            direction = new Point(-1, 0);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            direction = new Point(1, 0);
        } else if (keyCode == KeyEvent.VK_UP) {
            direction = new Point(0, -1);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            direction = new Point(0, 1);
        }
        if (direction != null) {
            snake.setDirection(direction);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // ne rien faire
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // ne rien faire
    }
}
