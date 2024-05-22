package cosc202.andie.BallTest;

import cosc202.andie.Andie;

public class Main {

    public static void main(String[] args) {

        try {
            Andie.main(args); // Launch the Andie application

            // Add a delay to ensure that Andie's frame is fully initialized
            Thread.sleep(1); // Wait for 1 second

            Andie.activateEasterEgg(); // Add the game overlay via easter egg
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
