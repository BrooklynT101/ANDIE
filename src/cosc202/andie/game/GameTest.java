package cosc202.andie.game;

import cosc202.andie.*;

public class GameTest {

    public static void main(String[] args) {
        try {
            Andie.main(args); // Launch the Andie application
            
            // Add a delay to ensure that Andie's frame is fully initialized
            Thread.sleep(1000); // Wait for 1 second
            
            Andie.addGameOverlay(); // Add the game overlay
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
