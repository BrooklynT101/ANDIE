package cosc202.andie;

import java.awt.image.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * <p>
 * ImageOperation to rotate the image
 * </p>
 * 
 * <p>
 * this rotates the image in 90 degree increments and changes the height and
 * width
 * of the image to be correctly sized.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Amelia Johnston
 * @version 1.0
 */

public class ImageRotation implements ImageOperation, java.io.Serializable {

    /** an public data field for ddefining the direction of rotation applied */
    public boolean clockwise;
    public boolean degrees; // true mean 180 degrees rotation

    /**
     * <p>
     * Create a new image rotation operation.
     * </p>
     * 
     * @param clockwise to determine which direction the image should rotate in
     */
    public ImageRotation(boolean clockwise) {
        this.clockwise = clockwise;
        this.degrees = false;
    }

    public ImageRotation(boolean clockwise, boolean degrees) { // if degrees is true then it rotates 180 degrees rather than 90
        this.clockwise = clockwise;
        this.degrees = degrees;
    }

    /**
     * <p>
     * Apply the rotation action to the image in specified direction
     * </p>
     * 
     * @param input The image to be rotated
     * @return The resulting rotated image.
     */
    public BufferedImage apply(BufferedImage input) {

        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));

        }

        int width = input.getWidth();
        int height = input.getHeight();
        BufferedImage output;

        if (degrees == true) {    
            output = new BufferedImage(width, height, input.getType());
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                        output.setRGB(width - x - 1, height - y - 1, input.getRGB(x, y));
                }
            }
        } else {
            output = new BufferedImage(height, width, input.getType());
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (clockwise == true) {
                        output.setRGB(height - y - 1, x, input.getRGB(x, y));
                    } else {
                        output.setRGB(y, width - x - 1, input.getRGB(x, y));
                    }
                }
            }
        }
        return output;
    }

    @Override
    public String getName() {
        if (clockwise) {
            return "Rotate Clockwise";
        } else if (degrees) {
            return "Rotate 180";
        } else { 
            return "Rotate AntiClockwise";
        }
    }

}
