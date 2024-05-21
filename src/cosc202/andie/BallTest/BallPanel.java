package cosc202.andie.BallTest;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BallPanel extends JPanel {
    protected List<Ball> balls;
    private Timer movementTimer;
    private Timer generationTimer;
    private int frameWidth, frameHeight;
    private Random r = new Random();
    private int ballCount = 0;
    @SuppressWarnings("unused")
    private int maxBalls;
    private boolean testing = true;

    public BallPanel(int frameWidth, int frameHeight,int generationDelay, int maxBalls) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.maxBalls = maxBalls;
        balls = new ArrayList<>();

        //timer for moving the balls
        movementTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Ball ball : balls) {
                    ball.move();
                }
                repaint();
            }
        });
        movementTimer.start();

        //timer for ball genreation
        generationTimer = new Timer(generationDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ballCount < maxBalls) {
                    addBall();
                    ballCount++;
                    if (testing) System.out.println("Ball Count: " + ballCount);
                } else {
                    generationTimer.stop(); // Stop the timer when the desired number of balls is reached
                }
            }
        });
    }

    public void addBall() {
        Color color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        Ball ball = new Ball(frameWidth, frameHeight, color);
        balls.add(ball);
    }

    public void startGeneratingBalls() {
        generationTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball ball : balls) {
            ball.draw(g); // draw each ball
        }
    }
}
