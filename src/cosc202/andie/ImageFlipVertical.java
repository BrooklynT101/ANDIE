package cosc202.andie;

import java.awt.image.*;

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
