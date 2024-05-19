package cosc202.andie.game;

import javax.swing.*;
import cosc202.andie.*;

public class GameTest extends JPanel {

    public static void main(String[] args) {
        // Initialize the existing Andie frame
        Andie andie = new Andie();
        JFrame andieFrame = andie.getFrame();  // Assuming you have a method to get the frame

        // Create the game panel
        BrickBreaker gamePlay = new BrickBreaker();

        // Set the bounds of the game panel to match the Andie frame
        gamePlay.setBounds(andieFrame.getContentPane().getBounds());

        // Add the game panel to the Andie frame
        andieFrame.getContentPane().add(gamePlay);

        // Set properties for the Andie frame
        andieFrame.setTitle("Brick Breaker");
        andieFrame.setResizable(false);
        andieFrame.setVisible(true);
    }
}
