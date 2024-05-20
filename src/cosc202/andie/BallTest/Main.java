package cosc202.andie.BallTest;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Color;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static Random r = new Random();
    private static final int TEST_NUMBER = 50000;
    private static final int INITIAL_DELAY = 1; // 2 seconds in milliseconds
    protected static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("Bouncing Balls");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        BallPanel ballPanel = new BallPanel();
        frame.add(ballPanel);

        // Add the first ball initially
        addBall(ballPanel);

        // Timer to add a new ball every 2 seconds
        Timer timer = new Timer(INITIAL_DELAY, new ActionListener() {
            private int ballCount = 1; // Start with 1 because we already added the first ball

            @Override
            public void actionPerformed(ActionEvent e) {
                if (ballCount < TEST_NUMBER) {
                    addBall(ballPanel);
                    ballCount++;
                    System.out.println("Ball Count: "+ballCount);
                } else {
                    ((Timer) e.getSource()).stop(); // Stop the timer when the desired number of balls is reached
                }
            }
        });
        timer.start();

        frame.setVisible(true);
    }

    private static void addBall(BallPanel ballPanel) {
        int x = r.nextInt(35, frame.getWidth() - 35);
        int y = r.nextInt(35, frame.getHeight() - 35);
        Color color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        Ball ball = new Ball(x, y, color);
        ballPanel.addBall(ball);
    }
}
