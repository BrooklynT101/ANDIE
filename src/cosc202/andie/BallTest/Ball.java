package cosc202.andie.BallTest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {
    private int x, y;
    private int xSpeed, ySpeed;
    private static final int DIAMETER = 30;
    private static final int SPEED = 7;
    private Rectangle bounds;
    private Color color;

    public Ball(int initialX, int initialY, Color color) {
        this.x = initialX;
        this.y = initialY;
        this.xSpeed = SPEED;
        this.ySpeed = SPEED;
        this.color = color;
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;

        bounds = new Rectangle(0, 0, Main.frame.getWidth(), Main.frame.getHeight());

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
