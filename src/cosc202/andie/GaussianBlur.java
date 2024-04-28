package cosc202.andie;

import java.awt.image.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * <p>
 * Gaussian Blur Class, allows image to be blurred using gaussian function
 * bluring from a radius inputed by user.
 * <p>
 * 
 * <p>
 * Creates a Gaussian Blur object with default radius 1, and
 * then creates a float array for a kernel, using the
 * gaussian function to apply blur to the image.
 * 
 * <p>
 * 
 * @author Ari Josephson
 */

public class GaussianBlur implements ImageOperation, java.io.Serializable {

    private int radius;
    public int x;
    public float[] array;
    public float[][] twoDarray;

    /* Constructor takes in a radius value. */
    GaussianBlur(int radius) {
        this.radius = radius;
    }

    GaussianBlur() {
        this.radius = 1;
    }

    /*
     * Calculates the Gaussian Blur value at the pixel at i,j
     * with the Gaussian formula.
     */
    public float calculateGaussian(int i, int j) {

        float l = (float) this.radius / 3.0f;

        float w = (float) ((float) (1 / ((float) 2 * (float) Math.PI * (float) (Math.pow(l, 2)))) *
                (float) Math
                        .exp(-1 * (float) (((float) (Math.pow(i, 2)) + (float) (Math.pow(j, 2)))
                                / (2 * (float) (Math.pow(l, 2))))));

        return w;
    }

    /*
     * Calculates the Gaussian Blur value at the first half
     * of the x values for the y value j.
     */
    public void firstHalfX(int size, int j) {
        for (int i = radius; i > 0; i--) {
            array[x] = calculateGaussian(i, j);
            if (x + 1 < size) {
                x++;
            } else {
                return;
            }
        }
    }

    /*
     * Calculates the Gaussian Blur value at the middle
     * x value for the y value j.
     */
    public void middleX(int size, int j) {
        array[x] = calculateGaussian(0, j);
        if (x + 1 < size) {
            x++;
        } else {
            return;
        }
    }

    /*
     * Calculates the Gaussian Blur value at the second half
     * of the x values for the y value j.
     */
    public void otherHalfX(int size, int j) {
        for (int i = 1; i <= radius; i++) {
            array[x] = calculateGaussian(i, j);
            if (x + 1 < size) {
                x++;
            } else {
                return;
            }
        }
    }

    /*
     * Calculates the Gaussian Blur value at the centre pixel.
     */
    public void centrePointcalc(int size) {
        array[x] = calculateGaussian(0, 0);
        if (x + 1 < size) {
            x++;
        } else {
            return;
        }

    }

    /*
     * Calculates the Gaussian Blur values at the first half of
     * y values of the image.
     */
    public void firstHalfY(int size) {
        for (int j = radius; j > 0; j--) {
            firstHalfX(size, j);
            middleX(size, j);
            otherHalfX(size, j);
        }

    }

    /*
     * Calculates the Gaussian Blur values at the centre
     * y value of the image.
     */
    public void centreY(int size) {
        firstHalfX(size, 0);
        centrePointcalc(size);
        otherHalfX(size, 0);
    }

    /*
     * Calculates the Gaussian Blur values at the second half of
     * y values of the image.
     */
    public void otherHalfY(int size) {
        for (int j = 1; j <= radius; j++) {
            firstHalfX(size, j);
            middleX(size, j);
            otherHalfX(size, j);
        }

    }

    /* Buffered image method applys the Gaussian filter to the image */
    public BufferedImage apply(BufferedImage input) {
        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));

        }
        int size = (2 * radius + 1) * (2 * radius + 1);

        this.array = new float[size];

        /*
         * A nested for loop which goes through each x and y corrdinate and makes it
         * puts in the array for the corresponding entry calculting the value with the
         * gaussian formuala.
         */

        this.x = 0;

        firstHalfY(size);
        centreY(size);
        otherHalfY(size);

        float sum = 0;

        for (int i = 0; i < size; i++) {
            sum += array[i];
        }

        // Normalised all the gaussian values, by dividing each entry by the sum of all
        // of them.
        for (int i = 0; i < size; i++) {

            array[i] = (float) array[i] / (float) sum;
        }

        x = 0;
        // /*
        // * Test
        // * Gaussian Blur by printing array values and see if it lines up with expected
        // * values.
        // */
        // for (int i = 0; i < 2 * radius + 1; i++) {
        // for (int j = 0; j < 2 * radius + 1; j++) {
        // System.out.print(array[x] + " ");
        // if (x + 1 < size) {
        // x++;
        // } else {
        // break;
        // }
        // }
        // System.out.println();
        // }

        Kernel kernel = new Kernel(2 * radius + 1, 2 * radius + 1, array);

        ConvolveOp convOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);

        ExtendingFilters expand = new ExtendingFilters(input, radius);

        BufferedImage expanded = expand.extending();

        BufferedImage image = new BufferedImage(expanded.getWidth(), expanded.getHeight(),
                expanded.getType());

        convOp.filter(expanded, image);

        BufferedImage output = expand.shrinking(image);

        return output;
    }

}
