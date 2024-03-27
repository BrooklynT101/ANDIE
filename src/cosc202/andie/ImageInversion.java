package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to inversly change the images RGB values.
 * </p>
 * 
 * <p>
 * creates an image that has inverse colour scheme to the original
 * swapping to the opposite RGB pixel value.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Amelia Johnston
 * @version 1.0
 */

public class ImageInversion implements ImageOperation, java.io.Serializable{
    
    /**
     * <p>
     * Create a new ImageInversion operation.
     * </p>
     */
    ImageInversion(){

    }

    /**
     * <p>
     * Apply colour inversion to an image.
     * </p>
     * 
     * @param input The image to be converted to greyscale
     * @return The resulting greyscale image.
     */
    public BufferedImage apply(BufferedImage input) {
  
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                int rNew = 255 - r;
                int gNew = 255 - g;
                int bNew = 255 - b;
                

                argb = (a << 24) | (rNew << 16) | (gNew << 8) | bNew;
                input.setRGB(x, y, argb);
            }
        }
        
        return input;
    }

}
