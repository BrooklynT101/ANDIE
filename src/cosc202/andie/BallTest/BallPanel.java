package cosc202.andie.BallTest;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BallPanel extends JPanel {
    protected List<Ball> balls;
    private Timer timer;

    public BallPanel() {
        balls = new ArrayList<>();
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Ball ball : balls) {
                    ball.move();
                }
                repaint();
            }
        });
        timer.start();
    }

    public void addBall(Ball ball) {
        balls.add(ball);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball ball : balls) {
            ball.draw(g);
        }
    }
}
