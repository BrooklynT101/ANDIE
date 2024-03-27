package cosc202.andie;
import java.awt.image.*;

/**
 * <p>
 * ImageOperation to rotate the image 
 * </p>
 * 
 * <p>
 * this rotates the image in 90 degree increments and changes the height and width
 * of the image to be correctly sized.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Amelia Johnston
 * @version 1.0
 */

public class ImageRotation implements ImageOperation, java.io.Serializable{

    /** an public data field for ddefining the direction of rotation applied */
    public boolean clockwise;

    /**
     * <p>
     * Create a new image rotation operation.
     * </p>
     * @param clockwise to determine which direction the image should rotate in
     */
    public ImageRotation(boolean clockwise){
        this.clockwise = clockwise;
    }

    /**
     * <p>
     * Apply the rotation action to the image in specified direction
     * </p>
     * 
     * @param input The image to be converted to greyscale
     * @return The resulting greyscale image.
     */
    public BufferedImage apply(BufferedImage input) {

        int width = input.getWidth();
        int height = input.getHeight();
        BufferedImage output = new BufferedImage(height, width, input.getType());

        for (int x = 0; x < width; x++) { 
            for (int y = 0; y < height; y++) {
                if(clockwise == true){
                    output.setRGB(height - y - 1, x, input.getRGB(x, y));
                }else{
                    output.setRGB(y, width - x - 1, input.getRGB(x, y));
                }
            }
        }
        return output;
    }

}
