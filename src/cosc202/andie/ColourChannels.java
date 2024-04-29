package cosc202.andie;

import java.awt.image.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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

                if(rotation == 1){ //rgb
                    argb = (a << 24) | (g << 16) | (b << 8) | r;
                }else if(rotation == 2){

                }else if(rotation == 3){

                }else if(rotation == 4){

                }else if(rotation == 5){

                }
 
                input.setRGB(x, y, argb);
            }
        }

        return input;
    }
    
}
