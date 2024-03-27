package cosc202.andie;

import java.awt.image.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * <p>
 * ImageOperation to inversly change the images RGB values.
 * </p>
 * 
 * <p>
 * creates an image that has inverse colour scheme to the original
 * swapping to the opposite RGB pixel value.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Amelia Johnston
 * @version 1.0
 */

public class ImageInversion implements ImageOperation, java.io.Serializable{
    
    /**
     * <p>
     * Create a new ImageInversion operation.
     * </p>
     */
    ImageInversion(){

    }

    /**
     * <p>
     * Apply colour inversion to an image.
     * </p>
     * 
     * @param input The image to be converted to greyscale
     * @return The resulting greyscale image.
     */
    public BufferedImage apply(BufferedImage input) {

        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));
   
        }
  
        //testing variables
        int ba = 0;
        int br = 0;
        int bg = 0;
        int bb = 0;
        int aa = 0;
        int ar = 0;
        int ag = 0;
        int ab = 0;

        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                //testing before pixel values
                //System.out.println("Before colour inversion\nAlpha: "+a+", Red: "+r+", Green: "+g+", Blue: "+b);
                ba = a;
                br = r;
                bg = g;
                bb = b;
                
                int rNew = 255 - r;
                int gNew = 255 - g;
                int bNew = 255 - b;
                
                argb = (a << 24) | (rNew << 16) | (gNew << 8) | bNew;
                
                //testing after pixel values
                //System.out.println("After colour inversion\nAlpha: "+a+", Red: "+r+", Green: "+g+", Blue: "+b);
                aa = (argb & 0xFF000000) >> 24;
                ar = (argb & 0x00FF0000) >> 16;
                ag = (argb & 0x0000FF00) >> 8;
                ab = (argb & 0x000000FF);

                input.setRGB(x, y, argb);
            }
        }
        //outputting test - if 0 theres a problem
        System.out.println("Before colour inversion\nAlpha: "+ba+", Red: "+br+", Green: "+bg+", Blue: "+bb);
        System.out.println("After colour inversion\nAlpha: "+aa+", Red: "+ar+", Green: "+ag+", Blue: "+ab);
        
        return input;
    }

}
