package cosc202.ToolBarTesting;

import javax.swing.*;
import java.awt.*;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Class that loads a bunch of buttons with different LooksAndFeels for the
 * buttons, add any in the comments below you know of!
 * 
 * 
 * 
 */
public class LookAndFeelExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create a frame
            JFrame frame = new JFrame("Look and Feel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 800);

            // Create a panel with GridLayout to hold the components
            JPanel panel = new JPanel(new GridLayout(0, 4, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding

            // Create components with different look and feels
            createButtons(panel);

            // Add the panel to the frame
            frame.getContentPane().add(panel);
            frame.setVisible(true);
        });
    }

    // Method to create buttons with different look and feels
    private static void createButtons(JPanel panel) {
        // Get available look and feels
        LookAndFeelInfo[] lookAndFeels = UIManager.getInstalledLookAndFeels();

        // Create buttons with different look and feels
        for (int i = 0; i < lookAndFeels.length && i < 12; i++) {
            try {
                // Set look and feel
                UIManager.setLookAndFeel(lookAndFeels[i].getClassName());

                // Create button
                JButton button = new JButton("Button " + (i + 1));
                panel.add(button);

                // Reset to default look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
                    | IllegalAccessException e) {
                // Skip if look and feel cannot be set
                continue;
            }
        }
    }
}
