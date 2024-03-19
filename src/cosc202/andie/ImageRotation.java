package cosc202.andie;
import java.awt.image.*;
import java.util.*;

public class ImageRotation implements ImageOperation, java.io.Serializable{
    /** Constructor that tae in the degrees of rotation*/

    //default constructor
    ImageRotation(){
    }

    //BufferedImage b = new BufferedImage();

    public BufferedImage apply(BufferedImage input) {
        return input;

    }

    // public void paintComponent(Graphics g) {
    //     super.paintComponent(g);
    //     if (image.hasImage()) {
    //         Graphics2D g2  = (Graphics2D) g.create();
    //         g2.scale(scale, scale);
    //         g2.drawImage(image.getCurrentImage(), null, 0, 0);
    //         g2.dispose();
    //     }
    // }
}
