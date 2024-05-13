package cosc202.andie;

import java.awt.image.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * <p>
 * A color filter that allows the user to adjust the brightness and contrast.
 * </p>
 * 
 * <p>
 * This operation enables the user to separately adjust the brightness and contrast to higher or 
 * lower values within a range of -100 to 100.
 * </p>
 * 
 * @author Amelia Johnston
 * @version 1.0
 * @see <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 */

public class BrightnessAndContrast implements ImageOperation, java.io.Serializable {

    int brightness;
    int contrast;

    BrightnessAndContrast() {
    }

    BrightnessAndContrast(int brightness, int contrast) {
        this.brightness = brightness;
        this.contrast = contrast;
    }

    public BufferedImage apply(BufferedImage input) {
        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));
        }

        // System.out.println("Method is calling");

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);

        // loop through each pixel
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {

                int argb = input.getRGB(x, y); // find the pixel value from at specified position
                int a = (argb & 0xFF000000) >> 24; // this does not change
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                // System.out.println("original red value = " + r);
                // System.out.println("original green value = " + g);
                // System.out.println("original blue value = " + b);
                // System.out.println(argb);
                int red = calcColour(r);
                int green = calcColour(g);
                int blue = calcColour(b);

                // System.out.println("new red value = " + red);
                // System.out.println("new green value = " + green);
                // System.out.println("new blue value = " + blue);

                argb = (a << 24) | (red << 16) | (green << 8) | blue;
                // System.out.println(argb);
                output.setRGB(x, y, argb);

            }
        }

        return output;

    }

    private int calcColour(int pixel) {
        // v` = (1 + (c/100)) * (v -127.5) + 127.5 * (1 + (b/100))

        // System.out.println(brightness);
        // System.out.println(contrast);

        // System.out.println("original pixel value = " + pixel);

        int pixelValue = (int) Math
                .round((1 + (contrast / 100.0)) * (pixel - 127.5) + 127.5 * (1 + (brightness / 100.0)));

        if (pixelValue > 250) {
            pixelValue = 250;
        } else if (pixelValue < 0) {
            pixelValue = 0;
        }

        // System.out.println("new pixel value = " + pixelValue);

        return pixelValue;
    }

    @Override
    public String getName() {
        return "Brightness and Contrast";
    }

    /**
     * Method to return 'this' as an ImageOperation to support @see MacroActions
     */
    @Override

    public ImageOperation clone() {
        // Create a new instance of BrightnessAndContrast
        BrightnessAndContrast copy = new BrightnessAndContrast();

        // Copy the internal state from the original object to the new instance
        copy.brightness = this.brightness;
        copy.contrast = this.contrast;

        // Return the new instance
        return copy;
    }

}
