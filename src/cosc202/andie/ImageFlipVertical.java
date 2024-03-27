package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageFlipVertical to flip a photo vertically.
 * <p>
 * 
 * <p>
 * The operation involves flipping, in place, each pixel by its y coordinates, moving 
 * across each pixel per row and column and sending it to its relative place on the 
 * opposite side.
 * <p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @version 1.0
 * @see ImageFlipHorizontal
 * @author Brooklyn Taylor
 */
public class ImageFlipVertical implements ImageOperation, java.io.Serializable{
    
    /** Constructor */
    ImageFlipVertical(){
    }

    /**
     * <p>
     * Applies the flip transformation to the image
     * <p>
     * @param input of type BufferedImage
     * @return the resulting image as a BufferedImage
     */
    public BufferedImage apply(BufferedImage input) {
        int imageWidth = input.getWidth();
        int imageHeight = input.getHeight();

        BufferedImage result = new BufferedImage(imageWidth, imageHeight, input.getType());
        //System.out.println("Test1");
        for (int y = 0; y < imageHeight; y++) {
            //System.out.println("test2");
            for (int x = 0; x < imageWidth; x++) {
                result.setRGB(x, imageHeight-y-1, input.getRGB(x, y)); //need to minus one off the y value because 0 indexing
            }
        }
        return result;
    }
}
