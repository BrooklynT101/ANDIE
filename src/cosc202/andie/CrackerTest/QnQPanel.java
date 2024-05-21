package cosc202.andie.CrackerTest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.MouseEvent;

import cosc202.andie.Andie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class QnQPanel extends JPanel {
    protected static List<CrackerAndCheese> crackersAndCheeses;
    protected Timer generationTimer;
    protected Timer movementTimer;
    protected Timer gameTimer;
    protected static int frameWidth;
    protected static int frameHeight;
    protected int cnqCount = 0;
    protected int maxNum;
    protected boolean testing = true;
    private int clickCount;
    protected long startTime;
    private long endTime;
    private MouseAdapter mouseListener;

    public QnQPanel(int generationDelay, int maxNum) {
        setBackground(Color.black);
        frameWidth = Andie.getFrame().getWidth();
        frameHeight = Andie.getFrame().getHeight();
        this.maxNum = maxNum;
        this.clickCount = 0;
        crackersAndCheeses = new ArrayList<>();

        // timer for moving the balls
        movementTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (CrackerAndCheese cnq : crackersAndCheeses) {
                    cnq.move();
                }
                repaint();
            }
        });
        movementTimer.start();

        // timer for cnq genreation
        generationTimer = new Timer(generationDelay, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cnqCount < maxNum) {
                    addCrackerAndCheese();
                    QnQPanel.frameWidth = Andie.getFrame().getWidth();
                    QnQPanel.frameHeight = Andie.getFrame().getHeight();
                    cnqCount++;
                    if (testing)
                        System.out.println("Cracker and Cheese Count: " + cnqCount);
                } else {
                    generationTimer.stop(); // Stop the timer when the max number of balls is reached
                    gameOver();
                }
            }
        });

        // add mouse listener to detect clicks on the CrackerAndCheese objects
        mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Iterator<CrackerAndCheese> iterator = crackersAndCheeses.iterator();
                while (iterator.hasNext()) {
                    CrackerAndCheese cnq = iterator.next();
                    if (cnq.contains(e.getX(), e.getY())) {
                        if (testing)
                            System.out.println("Cracker and Cheese clicked at (" + e.getX() + ", " + e.getY() + ")");
                        iterator.remove();
                        cnqCount--;
                        clickCount++;
                        if (generationTimer.isRunning()) {
                            generationTimer.setDelay(generationTimer.getDelay() - 100);
                            if (testing)
                                System.out.println("Current Delay: " + generationTimer.getDelay());
                        }
                        break; // Exit the loop after removing the clicked item to avoid
                               // ConcurrentModificationException
                    }
                }
            }
        };

        this.addMouseListener(mouseListener);
        // start the game time
        startTime = System.nanoTime();
    }

    protected void gameOver() {
        // stop the awt timers
        if (movementTimer != null && movementTimer.isRunning()) {
            movementTimer.stop();
        }
        if (generationTimer != null && generationTimer.isRunning()) {
            generationTimer.stop();
        }

        // calculate game time
        endTime = System.nanoTime();
        double gameTime = (double) (((endTime - startTime) / 1000000) / 1000);

        // show game over message
        String message = "You managed to click " + clickCount + " crackers and cheeses in " + gameTime
                + " seconds!\nWell Done you absolute legend.";
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);

        // remove the CrackerAndCheese objects from list
        crackersAndCheeses.clear();

        // remove listeners
        this.removeMouseListener(mouseListener);

        // Remove the panel from the frame
        Andie.frame.remove(this);

        // repaint andie frame so its properly goneskis
        Andie.frame.repaint();
    }

    public void addCrackerAndCheese() {
        CrackerAndCheese cnq = new CrackerAndCheese();
        crackersAndCheeses.add(cnq);
    }

    public void removeCrackerAndCheese(CrackerAndCheese cnq) {
        crackersAndCheeses.remove(cnq);
        cnqCount--;
        clickCount++;
        int currentDelay = generationTimer.getDelay();
        if (generationTimer.isRunning() && currentDelay > 100) {
            generationTimer.setDelay(currentDelay - 100);
            if (testing)
                System.out.println("Current Delay: " + (currentDelay - 100));
        }
    }

    public void startGeneratingCnQs() {
        generationTimer.start();
    }

    /**
     * This is to change the size of the panel at will
     * 
     * @param width  new width of the panel
     * @param height new height of the panel
     */
    public void updatePanelSize(int width, int height) {
        frameWidth = width;
        frameHeight = height;
        this.setBounds(new Rectangle(frameWidth, frameHeight));
    }

    public static int getPanelWidth() {
        return frameWidth;
    }

    public static int getPanelHeight() {
        return frameHeight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (CrackerAndCheese cnq : crackersAndCheeses) {
            cnq.draw(g); // draw each ball
        }
    }
}
