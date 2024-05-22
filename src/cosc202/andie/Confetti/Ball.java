package cosc202.andie.Confetti;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
    private int x, y;
    private int xSpeed, ySpeed;
    private int DIAMETER;
    private static final int SPEED = 7;
    private Rectangle bounds;
    private Color color;
    private int widthBounds;
    private int heightBounds;
    private Random r = new Random();

    public Ball(int widthBounds, int heightBounds, Color color) {
        this.widthBounds = widthBounds;
        this.heightBounds = heightBounds;
        //r.nextInt(origin, bound)
        this.x = r.nextInt(35, widthBounds - 35);
        this.y = r.nextInt(35, heightBounds - 35);
        this.xSpeed = SPEED;
        this.ySpeed = SPEED;
        this.color = color;
        this.DIAMETER = r.nextInt(15, 50);
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;

        bounds = new Rectangle(0, 0, widthBounds, heightBounds);

        if (x <= bounds.x || x >= bounds.width - DIAMETER) {
            xSpeed = -xSpeed;
        }
        if (y <= bounds.y || y >= bounds.height - DIAMETER) {
            ySpeed = -ySpeed;
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
