package cosc202.andie;

import java.awt.image.*;

public class SoftBlur implements ImageOperation, java.io.Serializable {

    /*
     * SoftBlur default constructor
     * Creates Option.
     */
    SoftBlur() {

    }

    public BufferedImage apply(BufferedImage input) {
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
