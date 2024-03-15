package cosc202.andie;

import java.awt.image.*;

public class ImageInversion implements ImageOperation, java.io.Serializable{
    
    /** Creates an ImageInversion operation */
    ImageInversion(){

    }

    /** Applies the colour inversion filter */
    public BufferedImage apply(BufferedImage input) {
  
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                int invert = (int) Math.round((255-r) + (255-g) + (255-b));
                

                argb = (a << 24) | (invert << 16) | (invert << 8) | invert;
                input.setRGB(x, y, argb);
            }
        }
        
        return input;
    }

}
