package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;

@SuppressWarnings("unused")
public class ToolBarTester {
    public static void main(String[] args) {
        

        // Set up the frame
        JFrame frame = new JFrame("Icon Display");
        try {
            Image imageIcon = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
            frame.setIconImage(imageIcon);
        } catch (Exception e) {
            System.out.println("Error loading icon.png");
            e.printStackTrace();
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adjust frame size based on icon size
        frame.pack();


        // Make the frame visible
        frame.setVisible(true);
    }
}
