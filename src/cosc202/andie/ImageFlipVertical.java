package cosc202.andie;

import java.awt.image.*;
import java.util.*;

/**
 * ImageFlipVertical to flip a photo vertically.
 * 
 * The flip involves flipping in place, each pixel by its y 
 * coordinates. 
 * 
 * @author Brooklyn Taylor
 */
public class ImageFlipVertical implements ImageOperation, java.io.Serializable{
    
    /** Constructor */
    ImageFlipVertical(){
    }

    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage result = new BufferedImage(width, height, input.getType());
        System.out.println("Test1");
        for (int y = 0; y < height; y++) {
            System.out.println("test2");
            for (int x = 0; x < width; x++) {
                result.setRGB(x, height - 1 - y, input.getRGB(x, y));
            }
        }
        return input; //TODO: replace this with the actual image
    }
}
