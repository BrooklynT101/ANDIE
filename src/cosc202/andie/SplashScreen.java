package cosc202.andie;

import javax.swing.Timer;
import javax.swing.JWindow;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

import java.net.URL;

/**
 * This is a splash screen creator class that displays a splash screen for a
 * given number of milliseconds
 * 
 * @author Brooklyn Taylor
 */
public class SplashScreen extends JWindow {

    private int DURATION;
    private boolean testing = false;
    // creates a transparent color
    private Color transparentColor = new Color(0, 0, 0, 0); // RGB values and alpha (transparency)

    /**
     * Constructor for the splash screen, defines variables and the number of
     * milliseconds to display the screen for
     * 
     * @param duration the screen is up for (in milliseconds)
     */
    public SplashScreen(int duration) {
        this.DURATION = duration;

        JPanel contentPane = (JPanel) getContentPane();

        // Setting the backgrounds to be transparent else it create a box
        contentPane.setBackground(transparentColor);
        setBackground(transparentColor);

        // Load Splash Screen from the resources folder
        URL imageUrl = null;
        try {
            imageUrl = Andie.class.getClassLoader().getResource("splashScreen.png");
        } catch (Exception e) {
            System.err.println("Resource not found: splashScreen.png");
            e.printStackTrace();
        }

        if (imageUrl != null) {
            // tries to load the splash screen
            ImageIcon imageIcon = new ImageIcon(imageUrl);
            JLabel label = new JLabel(new ImageIcon());

            // label.setBackground(Color.green);
            label.setIcon(imageIcon);
            // add to the window
            contentPane.add(label, BorderLayout.CENTER);

            // centering the window
            int width = imageIcon.getIconWidth();
            int height = imageIcon.getIconHeight();
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (screen.width - width) / 2;
            int y = (screen.height - height) / 2;
            setBounds(x, y, width, height);
        } else {
        }

        setAlwaysOnTop(true); // Ensure the splash screen is always on top
    }

    /**
     * method to display the splash screen
     */
    public void showSplash() {
        // make it appear
        setVisible(true);

        // wait to make it image disappear
        Timer timer = new Timer(DURATION, e -> {
            if (testing)
                System.out.println("Finished waiting");
            setVisible(false);
        });
        timer.setRepeats(false);
        timer.start();

        if (testing)
            System.out.println("Started waiting");
    }
}
