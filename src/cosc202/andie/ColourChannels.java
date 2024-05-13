package cosc202.andie;

import java.awt.image.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * <p>
 * ImageOperation to allow the user to chose their intended RGB colour channel
 * </p>
 * 
 * <p>
 * this produces and image that still looks similar but is differently coloured
 * and can rotate between the other values. The individual pixels RGB values are
 * changed and stored for other changes.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Amelia Johnston
 * @version 1.0
 */

public class ColourChannels implements ImageOperation, java.io.Serializable {

    public int rotation;

    /**
     * <p>
     * Create a new ColourChannelCycling operation.
     * </p>
     */
    ColourChannels() {
    }

    ColourChannels(int rotation){
        this.rotation = rotation;
    }

    /**
     * <p>
     * Apply colour channel cycling conversion to an image.
     * </p>
     * 
     * @param input The image to be colour channel cycled
     * @return The resulting image with rotated RGB values
     */
    public BufferedImage apply(BufferedImage input) {

        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));

        }

        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                if(rotation == 0){ //rgb
                    argb = (a << 24) | (r << 16) | (g << 8) | b;
                    // System.out.println("rgb");
                }else if(rotation == 1){ //rbg
                    argb = (a << 24) | (r << 16) | (b << 8) | g;
                    // System.out.println("rbg");
                }else if(rotation == 2){ //gbr
                    argb = (a << 24) | (g << 16) | (b << 8) | r;
                    // System.out.println("gbr");
                }else if(rotation == 3){ //grb
                    argb = (a << 24) | (g << 16) | (r << 8) | b;
                    // System.out.println("grb");
                }else if(rotation == 4){ //bgr
                    argb = (a << 24) | (b << 16) | (g << 8) | r;
                    // System.out.println("bgr");
                }else if(rotation == 5){ //brg
                    argb = (a << 24) | (b << 16) | (r << 8) | g;
                    // System.out.println("brg");
                }
 
                input.setRGB(x, y, argb);
            }
        }

        return input;
    }

    @Override
    public String getName() {
        return "Colour Cycle";
    }

    /**
     * Method to return 'this' as an ImageOperation to support @see MacroActions
     */
    @Override
    public ImageOperation clone() {
        ColourChannels copy = new ColourChannels();
        return copy;
    }
    
}
