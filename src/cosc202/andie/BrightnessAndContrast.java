package cosc202.andie;

import java.awt.image.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BrightnessAndContrast implements ImageOperation, java.io.Serializable{

    private int brightness;
    private int contrast;

    BrightnessAndContrast(){
    }

    BrightnessAndContrast(int brightness, int contrast){
        this.brightness = brightness;
        this.contrast = contrast;
    }

    public BufferedImage apply(BufferedImage input) {
        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));

        }

        // loop through each pixel
        for(int w = 0; w <= input.getWidth(); w++){ //w = width
            for(int h = 0; h <= input.getHeight(); h++){ // h = height 

                int argb = input.getRGB(w, h); // find the pixel value from at specified position
                int a = (argb & 0xFF000000) >> 24; // this does not change
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                int red = calcColour(r);
                int green = calcColour(g);
                int blue = calcColour(b);

                argb = (a << 24) | (red << 16) | (green << 8) | blue;

                input.setRGB(w, h, argb);

            }
        }

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

        return output;

    }

    private int calcColour(int pixel){ //x = the original pixel value 
        // v` = (1 + (c/100)) * (v -127.5) + 127.5 * (1 + (b/100))
        int pixelValue = (int) Math.round((1 + (contrast/100)) * (pixel -127.5) + 127.5 * (1 + (brightness/100)));

        //if out of bounds return the closest option??

        return pixelValue;
    }

    
}
