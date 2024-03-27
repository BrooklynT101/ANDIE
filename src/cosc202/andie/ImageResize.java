package cosc202.andie;

import java.awt.image.*;
import java.awt.Image;
//import java.awt.Graphics2D;
//import java.util.*;

public class ImageResize implements ImageOperation, java.io.Serializable {

    private int width;
    private int height;
    private int ogheight;
    private int ogWidth;
    //private Image ogImage;

    ImageResize(int width, int height, int ogheight, int ogWidth, Image ogImage) {
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

            BufferedImage output = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_RGB);

            output.getGraphics().drawImage(result, 0, 0, null);

            return output;
        } else if (height > 100) {
            int newH = (int) (height * scaleh);
            int newW = (int) (width * scalew);

            Image result = input.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);

            BufferedImage output = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_RGB);

            output.getGraphics().drawImage(result, 0, 0, null);

            return output;
        } else {
            //can you simplify this to just return ogImage?
            int newH = (int) (height * scaleh);
            int newW = (int) (width * scalew);

            Image result = input.getScaledInstance(newW, newH, Image.SCALE_DEFAULT);

            BufferedImage output = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_RGB);

            output.getGraphics().drawImage(result, 0, 0, null);
            return output;
        }
    }

}
