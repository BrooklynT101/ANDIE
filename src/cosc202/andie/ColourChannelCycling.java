package cosc202.andie;

import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * <p>
 * ImageOperation to cycle the colour channels.
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

public class ColourChannelCycling implements ImageOperation, java.io.Serializable {

    public int rotation;

    /**
     * <p>
     * Create a new ColourChannelCycling operation.
     * </p>
     */
    ColourChannelCycling(int rotation){
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

        // testing variables
        // int ba = 0;
        // int br = 0;
        // int bg = 0;
        // int bb = 0;
        // int aa = 0;
        // int ar = 0;
        // int ag = 0;
        // int ab = 0;

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {


                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                // testing before pixel values
                // System.out.println("Before colour channel cycle\nAlpha: "+a+", Red: "+r+",
                // Green: "+g+", Blue: "+b);
                // ba = a;
                // br = r;
                // bg = g;
                // bb = b;

                //random order?
                switch(rotation){ // TODO: cycles through the colours correctly but doesnt go back to start?
                    case 1:
                        //argb = EditableImage.getOriginalImage().getRGB(x, y);
                        argb = (a << 24) | (r << 16) | (g << 8) | b; //rgb = normal colours
                        //System.out.println("rgb");
                        break;
                    case 2:
                        argb = (a << 24) | (r << 16) | (b << 8) | g; //rbg = dark green sky, pink tower 
                        //System.out.println("rbg");
                        break;
                    case 3:
                        argb = (a << 24) | (b << 16) | (g << 8) | r; //gbr = green sky, purple tower
                        //System.out.println("gbr");
                        break;
                    case 4:
                        argb = (a << 24) | (r << 16) | (b << 8) | g; //grb = purple sky, dark green tower
                        //System.out.println("grb");
                        break;
                    case 5:
                        argb = (a << 24) | (b << 16) | (g << 8) | r; //brg = pink sky, dark green tower
                        //System.out.println("brg");
                        break;
                    case 6: 
                        argb = (a << 24) | (r << 16) | (b << 8) | g; //bgr = orange sky, blue tower
                        //System.out.println("bgr");
                        break;
                }


                // argb = (a << 24) | (g << 16) | (b << 8) | r; //rotates the RGB values 
                
                //testing after pixel values
                //System.out.println("After colour channel cycle\nAlpha: "+a+", Red: "+r+", Green: "+g+", Blue: "+b);
                // aa = (argb & 0xFF000000) >> 24;
                // ar = (argb & 0x00FF0000) >> 16;
                // ag = (argb & 0x0000FF00) >> 8;
                // ab = (argb & 0x000000FF);
                output.setRGB(x, y, argb);
            }
        }
        //outputting test - if 0 theres a problem
        // System.out.println("Before colour channel cycle\nAlpha: "+ba+", Red: "+br+", Green: "+bg+", Blue: "+bb);
        // System.out.println("After colour channel cycle\nAlpha: "+aa+", Red: "+ar+", Green: "+ag+", Blue: "+ab);
        
        return output;
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
        ColourChannelCycling copy = new ColourChannelCycling(rotation);
        return copy;
    }

}
