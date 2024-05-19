package cosc202.andie;

import java.awt.image.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
// import javafx.scene.effect.Effect;
// import javafx.scene.effect.ColourAdjust;

/**
 * <p>
 * A color filter that allows the user to adjust the saturation.
 * </p>
 * 
 * <p>
 * This operation enables the user to adjust the saturation of the image
 * </p>
 * 
 * @author Amelia Johnston
 * @version 1.0
 * @see <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * 
 * //formula = S(HSV) = (max(R,G,B)-min(R,G,B)) ⁄ max(R,G,B).
 */

public class Saturation implements ImageOperation, java.io.Serializable {

    int saturation;
    // ColourAdjust cadj = new ColourAdjust();

    Saturation() {
    }

    Saturation(int saturation) {
        this.saturation = saturation;
    }

    public BufferedImage apply(BufferedImage input) {
        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));
        }
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);
        
        System.out.println(saturation);

        float saturationFactor = 1 + (float) saturation / 100;

        // loop through all the pixels 
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {

                int argb = input.getRGB(x, y); // find the pixel value from at specified position
                int a = (argb & 0xFF000000) >> 24; // this does not change
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                float[] hsv = rgbToHsv(r, g, b);
                // Color.RGBtoHSB(r, g, b, hsv);

                hsv[1] *= saturationFactor;
                if(hsv[1] >1){
                    hsv[1] = 1;
                }

                int rgb = Color.HSBtoRGB(hsv[0], hsv[1], hsv[2]);
                // System.out.println(argb);
                output.setRGB(x, y, (a<<25) | rgb);

            }
        }
        return output;
    }

    private float[] rgbToHsv(int r, int g, int b){
        float[] hsv = new float[3];
        Color.RGBtoHSB(r, g, b, hsv);
        return hsv;
    }

    @Override
    public String getName() {
        return "Saturation";
    }

    /**
     * Method to return 'this' as an ImageOperation to support @see MacroActions
     */
    @Override

    public ImageOperation clone() {

        Saturation copy = new Saturation();
        copy.saturation = this.saturation;
        return copy;

    }

}
