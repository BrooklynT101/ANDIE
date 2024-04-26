package cosc202.andie;

import java.awt.image.*;
import java.awt.Image;

/**
 * <p>
 * Image Resize to resize image, scaling image down and up pixels.
 * <p>
 * 
 * <p>
 * Makes an ImageResize object which takes an inputed height (height) scale
 * (which will be the same as the width scale) which if less then 100 scales
 * down,
 * if 100 stays the same, and over 100 scales up.
 * <p>
 * 
 * @see ImageResize
 * @author Ari Josephson
 */

public class ImageResize implements ImageOperation, java.io.Serializable {

    private int width;
    private int height;
    private int ogheight;
    private int ogWidth;
    //private Image ogImage;

    ImageResize(int width, int height, int ogWidth,int ogheight, Image ogImage) {
        this.width = width;
        this.height = height;
        this.ogheight = ogheight;
        this.ogWidth = ogWidth;
        //this.ogImage = ogImage;
    }

    public BufferedImage apply(BufferedImage input) {

        int w = ogWidth;
        int h = ogheight;

        float scaleh = (h / 100);
        float scalew = (w / 100);

        if (height < 100) {
            int newH = (int) (height * scaleh);
            int newW = (int) (width * scalew);

            Image result = input.getScaledInstance(newW, newH, Image.SCALE_AREA_AVERAGING);

            BufferedImage output = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

            output.getGraphics().drawImage(result, 0, 0, null);

            return output;
        } else if (height > 100) {
            int newH = (int) (height * scaleh);
            int newW = (int) (width * scalew);

            Image result = input.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);

            BufferedImage output = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

            output.getGraphics().drawImage(result, 0, 0, null);

            return output;
        } else {
            //can you simplify this to just return ogImage?
            int newH = (int) (height * scaleh);
            int newW = (int) (width * scalew);

            BufferedImage output = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
            output = input;

            return output;
        }
    }

    @Override
    public String getName() {
        return "Resize";
    }

}
