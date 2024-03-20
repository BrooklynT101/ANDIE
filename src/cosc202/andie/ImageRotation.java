package cosc202.andie;
import java.awt.image.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class ImageRotation implements ImageOperation, java.io.Serializable{
    /** Constructor that tae in the degrees of rotation*/

    //default constructor
    public ImageRotation() {
        image = new EditableImage();
    }

    private EditableImage image;
    private double scale;


    //BufferedImage b = new BufferedImage();

    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        if (image.hasImage()) {
            Graphics2D g2  = (Graphics2D) g.create();
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 90, 90);
            g2.dispose();
        }
    }

    public BufferedImage apply(BufferedImage input) {
  
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                argb = (a << 24) | (r << 16) | (g << 8) | b;
                input.setRGB(x, y, argb);

            }
        } 
        
        return input;
    }

}
