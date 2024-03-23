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
        int imageHeight = input.getHeight();

        BufferedImage result = new BufferedImage(width, imageHeight, input.getType());
        //System.out.println("Test1");
        for (int y = 0; y < imageHeight; y++) {
            //System.out.println("test2");
            for (int x = 0; x < width; x++) {
                result.setRGB(x, imageHeight - 1- y, input.getRGB(x, y));
            }
        }
        return result; //TODO: replace this with the actual image
    }
}
