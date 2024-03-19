import java.awt.image.*;
import java.util.*;

/**
 * ImageOperation to flip a photo vertically or horizontally.
 * 
 * The slip involves flipping, in place, each pixel by its y 
 * coordinates for vertical or x coordinates for the horizontal flip. 
 * 
 * @author Brooklyn Taylor
 */
public class ImageFlipVertical implements ImageOperation, java.io.Serializable{
    
    /** Constructor */
    ImageFlipVertical(){
    }

    public BufferedImage apply(BufferedImage input) {
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                argb = (a << 24) | (g << 16) | (b << 8) | r;
                input.setRGB(x, y, argb);
            }
        }
        return input; //TODO: replace this with the actual image
    }
}
