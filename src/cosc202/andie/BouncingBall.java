package cosc202.andie;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.JPanel;

public class BouncingBall extends JPanel {
    private int x = 0;
    private int y = 0;
    private int xSpeed = 1;
    private int ySpeed = 1;

    public void start() {
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
                repaint();
            }
        });
        timer.start();
    }

    private void move() {
        // Move the ball
        x += xSpeed;
        y += ySpeed;

        // Reverse direction if the ball reaches the window boundaries
        if (x <= 0 || x >= getWidth()) {
            xSpeed = -xSpeed;
        }
        if (y <= 0 || y >= getHeight()) {
            ySpeed = -ySpeed;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);
    }
}
