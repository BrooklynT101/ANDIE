package cosc202.andie.CrackerTest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;

import cosc202.andie.Andie;

public class CrackerAndCheese {
    private int x, y;
    private int xSpeed, ySpeed;
    private static final int DIAMETER = 50;
    private static final int SPEED = 3;
    private Rectangle bounds;
    private int widthBounds;
    private int heightBounds;
    private Random r = new Random();
    private BufferedImage image; // Instance variable to store the BufferedImage
    private double angle = 0;

    public CrackerAndCheese() {
        this.widthBounds = Andie.getFrame().getWidth();
        this.heightBounds = Andie.getFrame().getHeight();
        // put the image object thing somewhere randomly inside the frame
        this.x = r.nextInt(35, widthBounds - 35);
        this.y = r.nextInt(35, heightBounds - 35);
        this.xSpeed = SPEED;
        this.ySpeed = SPEED;
        this.image = createCrackerAndCheeseImage(DIAMETER); // creating and loading the image

        // add actionPerformed();
    }

    /**
     * This is to create and return a BufferedImage of a cracker and cheese
     * 
     * @param size size of the cracker & cheese image
     * @return a bufferedimage of crackers and cheese
     */
    public BufferedImage createCrackerAndCheeseImage(int size) {
        int width = size;
        int height = size;
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

        // Making 6 holes
        for (int i = 0; i < 6; i++) {
            int holeX = cheeseX + random.nextInt(cheeseSize - holeSize);
            int holeY = cheeseY + random.nextInt(cheeseSize - holeSize);
            g2d.fillOval(holeX, holeY, holeSize, holeSize);
        }

        g2d.dispose();
        return image;
    }

    /**
     * Move the objects around
     */
    public void move() {
        x += xSpeed;
        y += ySpeed;

        // Rotate the object based on the xSpeed
        angle += 0.1 * xSpeed / SPEED;

        bounds = new Rectangle(0, 0, QnQPanel.getPanelWidth(), QnQPanel.getPanelHeight());

        if (x <= bounds.x || x >= bounds.width - DIAMETER) {
            xSpeed = -xSpeed;
        }
        if (y <= bounds.y || y >= bounds.height - DIAMETER) {
            ySpeed = -ySpeed;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(angle, x + DIAMETER / 2, y + DIAMETER / 2);
        g2d.drawImage(image, x, y, null);
        g2d.dispose();
    }

    /**
     * Check if the given point (px, py) is inside the CrackerAndCheese object
     * 
     * @param px the x-coordinate of the point
     * @param py the y-coordinate of the point
     * @return true if the point is inside the object, false otherwise
     */
    public boolean contains(int px, int py) {
        return px >= x && px <= x + DIAMETER && py >= y && py <= y + DIAMETER;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
