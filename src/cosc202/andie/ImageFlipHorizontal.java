package cosc202.andie;

import java.awt.image.*;

/**
 * ImageOperation to flip a photo horizontally.
 * 
 * The slip involves flipping, in place, each pixel by its x coordinates. 
 * 
 * 
 * @author Brooklyn Taylor
 */
public class ImageFlipHorizontal implements ImageOperation, java.io.Serializable{
    
    /** Constructor */
    ImageFlipHorizontal(){
    }
    /** Applies fliter to bufferedimage */
    public BufferedImage apply(BufferedImage input) {
        int imageWidth = input.getWidth();
        int imageHeight = input.getHeight();

        BufferedImage result = new BufferedImage(imageWidth, imageHeight, input.getType());
        //System.out.println("Test1");
        for (int y = 0; y < imageHeight; y++) {
            //System.out.println("test2");
            for (int x = 0; x < imageWidth; x++) {
                result.setRGB(imageWidth-x-1, y, input.getRGB(x, y)); //need to minus one off the x value because 0 indexing
            }
        }
        return result;

    }
}
