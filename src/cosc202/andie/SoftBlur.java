package cosc202.andie;

import java.awt.image.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * <p>
 * Soft Blur Class, allows image to be blurred using a reduction array into a
 * kernel
 * <p>
 * 
 * <p>
 * Creates a Soft Blur object creates a float array of fractions, putting into
 * a kernel.
 * 
 * <p>
 * 
 * @implNote Implements ImageOperation, java.io.Serializable
 * @author Ari Josephson
 */
public class SoftBlur implements ImageOperation, java.io.Serializable {

    /*
     * SoftBlur default constructor
     * Creates Option.
     */
    SoftBlur() {

    }

    public BufferedImage apply(BufferedImage input) {
        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));

        }
        // Code From Lab document
        // The values for the kernel as a 9-element array
        float[] array = { 0, 1 / 8.0f, 0,
                1 / 8.0f, 1 / 2.0f, 1 / 8.0f,
                0, 1 / 8.0f, 0 };
        // Make a 3x3 filter from the array
        Kernel kernel = new Kernel(3, 3, array);
        // Apply this as a convolution - same code as in MeanFilter
        ConvolveOp convOp = new ConvolveOp(kernel);

        // Creates Output image?
        BufferedImage output = new BufferedImage(input.getColorModel(),
                input.copyData(null),
                input.isAlphaPremultiplied(), null);

        // Making Output be input with the filter on?
        convOp.filter(input, output);

        return output;
    }

}
