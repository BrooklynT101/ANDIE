package cosc202.andie;
import java.awt.image.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class ImageRotation implements ImageOperation, java.io.Serializable{

    //default constructor
    public ImageRotation() { 
        //image = new EditableImage(); 
    }

    public BufferedImage apply(BufferedImage input) {

        // Image image = input.getScaledInstance(600, 800, Image.SCALE_AREA_AVERAGING);

        // BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        
        // Graphics2D graphics = output.createGraphics();
        // graphics.rotate(Math.PI);
        // graphics.drawImage(image, 0, 0, null);

        // return output;

        for (int y = 0; y < input.getHeight(); ++y) { //iterate through the piels and move around 
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
