package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to cycle the colour channeld between RGB, GBR, and BRG.
 * </p>
 * 
 * <p>
 * this produces and image that still looks similar but is differently coloured
 * and can rotate between the other values. The individual pixels RGB values are 
 * changed and stored for dther changes.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Amelia Johnston
 * @version 1.0
 */

public class ColourChannelCycling implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Create a new ColourChannelCycling operation.
     * </p>
     */
    ColourChannelCycling(){
    }

    /**
     * <p>
     * Apply colour channel cycling conversion to an image.
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
                //System.out.println("Before colour channel cycle\nAlpha: "+a+", Red: "+r+", Green: "+g+", Blue: "+b);
                argb = (a << 24) | (g << 16) | (b << 8) | r; //rotates the RGB values 
                //System.out.println("After colour channel cycle\nAlpha: "+a+", Red: "+r+", Green: "+g+", Blue: "+b);
                input.setRGB(x, y, argb);
            }
        }
        
        return input;
    }
}
