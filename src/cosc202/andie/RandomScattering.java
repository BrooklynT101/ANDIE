package cosc202.andie;

import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RandomScattering implements ImageOperation, java.io.Serializable {

    private int radius;

    RandomScattering() {
        this(1);
    }

    RandomScattering(int radius) {
        this.radius = radius;
    }

    public BufferedImage apply(BufferedImage input) {

        Random r = new Random();

        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));

        }

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);

        // loop through each pixel
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int randomIndexX = r.nextInt(x-radius, x +radius);
                int randomIndexY = r.nextInt(y-radius, y +radius);

            while(randomIndexX < 0 || randomIndexX >= input.getWidth()){
                 randomIndexX = r.nextInt(x - radius, x + radius);
            }

            while(randomIndexY < 0 || randomIndexY >= input.getHeight()){
                randomIndexY = r.nextInt(y - radius, y + radius);
            }

            output.setRGB(x, y, input.getRGB(randomIndexX, randomIndexY)); // set the RGB value to the new value
            
            }
        }

        return output;

    }

    @Override
    public String getName() {
        return "Random Scattering";
    }

    /**
     * Method to return 'this' as an ImageOperation to support @see MacroActions
     */
    @Override
    public ImageOperation clone() {
        RandomScattering copy = new RandomScattering(this.radius);
        return copy;
    }

}
