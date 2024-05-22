package cosc202.andie.CrackerTest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CrackerAndCheeseImage {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);

        BufferedImage image = createCrackerAndCheeseImage(100, 100);

        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);
    }

    public static BufferedImage createCrackerAndCheeseImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // Enable antialiasing for better quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the cracker (a pale orange round cracker)
        g2d.setColor(new Color(255, 178, 102)); // cracker color
        g2d.fillOval(0, 0, width, height);

        // Draw the cheese (a square piece of yellow swiss cheese)
        int cheeseSize = (int) (width * 0.6);
        int cheeseX = (width - cheeseSize) / 2;
        int cheeseY = (height - cheeseSize) / 2;
        g2d.setColor(Color.YELLOW); // Yellow color
        g2d.fillRect(cheeseX, cheeseY, cheeseSize, cheeseSize);

        // Draw the holes in the swiss cheese
        g2d.setColor(new Color(225, 225, 0)); // hole color
        int holeSize = cheeseSize / 6;
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int holeX = cheeseX + random.nextInt(cheeseSize - holeSize);
            int holeY = cheeseY + random.nextInt(cheeseSize - holeSize);
            g2d.fillOval(holeX, holeY, holeSize, holeSize);
        }

        g2d.dispose();
        return image;
    }
}
