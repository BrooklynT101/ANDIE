package cosc202.andie;

import java.awt.image.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * <p>
 * Emboss7Filter class, applies an emboss convoultion kernel.
 * <p>
 * 
 * <p>
 * Creates Emboss7Filter object and applies the emboss convolution kernel to the
 * input image using the FilterswithNegativeResults convoltion method
 * implementation.
 * 
 * 
 * 
 * 
 * @author Ari Josephson
 */
public class Emboss7Filter implements ImageOperation, java.io.Serializable {

    private int radius;

    /**
     * <p>
     * Construct a Emboss filter with the given size.
     * 
     * @param radius The radius of the newly constructed MeanFilter
     */
    Emboss7Filter(int radius) {
        this.radius = 1;
    }

    Emboss7Filter() {
        this(1);
    }

    public BufferedImage apply(BufferedImage input) {
        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));

        }

        float[] array = {
                0, -1, 0,
                0, 0, 0,
                0, +1, 0
        };

        Kernel kernel = new Kernel(2 * radius + 1, 2 * radius + 1, array);

        FilterswithNegativeResults f = new FilterswithNegativeResults(kernel);

        BufferedImage output = f.filter(input, radius);

        return output;

    }

    @Override
    public String getName() {
        return "Emboss7 Filter";
    }

    /**
     * Method to return 'this' as an ImageOperation to support @see MacroActions
     */
    @Override
    public ImageOperation clone() {
        Emboss7Filter copy = new Emboss7Filter(this.radius);
        return copy;
    }

}
