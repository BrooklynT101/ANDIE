package cosc202.andie;

import java.awt.image.*;

public class ColourChannelCycling implements ImageOperation, java.io.Serializable {

    /** Creates an ColourChannelCycling operation */
    ColourChannelCycling(){

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

                argb = (a << 24) | (g << 16) | (b << 8) | r;
                input.setRGB(x, y, argb);
            }
        }
        
        return input;
    }
}
