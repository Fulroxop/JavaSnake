import java.awt.Point;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Point> body;
    private Point direction;
    private int width;
    private int height;
    private int cellSize;
    private boolean isAlive;

    public Snake(int boardWidth, int boardHeight, int cellSize) {
        this.width = boardWidth / cellSize;
        this.height = boardHeight / cellSize;
        this.cellSize = cellSize;
        this.direction = new Point(1, 0);
        this.body = new ArrayList<>();
        this.body.add(new Point(3, 0));
        this.body.add(new Point(2, 0));
        this.body.add(new Point(1, 0));
        this.isAlive = true;
    }

    public void move() {
        // calculate the new head position
        Point head = body.get(0);
        int x = (int) head.getX() + (int) direction.getX();
        int y = (int) head.getY() + (int) direction.getY();
        Point newHead = new Point(x, y);

        // check if the new head position is out of bounds
        if (newHead.getX() < 0 || newHead.getX() >= width || newHead.getY() < 0 || newHead.getY() >= height) {
            isAlive = false;
            return;
        }

        // check if the new head position overlaps with the body
        for (int i = 1; i < body.size(); i++) {
            if (newHead.equals(body.get(i))) {
                isAlive = false;
                return;
            }
        }

        // check if the new head position overlaps with the apple
        Point apple = Game.getInstance().getApple().getPosition();
        if (newHead.equals(apple)) {
            body.add(0, newHead);
        } else {
            body.remove(body.size() - 1);
            body.add(0, newHead);
        }
    }

    public void setDirection(Point direction) {
        if (!this.direction.equals(new Point((int) -direction.getX(), (int) -direction.getY()))) {
            this.direction = direction;
        }
    }

    public ArrayList<Point> getBody() {
        return body;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void grow() {
        Point lastBodyPart = body.get(body.size() - 1);
        int x = (int) lastBodyPart.getX();
        int y = (int) lastBodyPart.getY();
        Point newBodyPart = new Point(x, y);
        body.add(newBodyPart);
    }

    public boolean intersects(Point point) {
        for (Point bodyPart : body) {
            if (bodyPart.equals(point)) {
                return true;
            }
        }
        return false;
    }
}
