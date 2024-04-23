package cosc202.andie;

import java.awt.image.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * <p>
 * ImageOperation to apply a Median (simple blur) filter.
 * </p>
 * 
 * <p>
 * A Median filter blurs an image by replacing each pixel by the average of the
 * pixels in a surrounding neighbourhood, its size determined by a user provided
 * radius.
 * This is not implemented using convolution.
 * </p>
 * 
 * @author Marcus
 */
public class MedianFilter implements ImageOperation, java.io.Serializable {

    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a
     * 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a Median filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed MedianFilter
     */
    MedianFilter(int radius) {
        this.radius = radius;

    }

    /**
     * <p>
     * Construct a Median filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Median filter has radius 1.
     * </p>
     * 
     * @see MedianFilter(int)
     */
    MedianFilter() {
        this(1);
    }

    /**
     * <p>
     * Apply a Median filter to an image.
     * </p>
     * 
     * <p>
     * Unlike the mean filter, the Median filter is not implemented via convolution.
     * Instead we use a local neibourhood with a specified radius.
     * {@link radius}.
     * Larger radii results in stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Median filter to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply(BufferedImage input) {
        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));

        }

        int neighbourhoodSize = ((radius * 2 + 1) * (radius * 2 + 1));
        System.out.println(neighbourhoodSize);

        for (int y = radius; y < input.getHeight() - radius; y++) {

            for (int x = radius; x < input.getWidth() - radius; x++) {
                int argb = input.getRGB(x, y);
                //int a = (argb & 0xFF000000) >> 24;
                int[] red = new int[neighbourhoodSize];
                int[] green = new int[neighbourhoodSize];
                int[] blue = new int[neighbourhoodSize];
                int[] alpha = new int[neighbourhoodSize];
                int index = 0;

                for (int ny = y - radius; ny <= y + radius; ny++) {
                    for (int nx = x - radius; nx <= x + radius; nx++) {

                        int argbNeighbourhood = input.getRGB(nx, ny);

                        red[index] = (argbNeighbourhood & 0x00FF0000) >> 16;
                        green[index] = (argbNeighbourhood & 0x0000FF00) >> 8;
                        blue[index] = (argbNeighbourhood & 0x000000FF);
                        alpha[index] = (argbNeighbourhood & 0xFF000000) >> 24;

                        index++;
                    }
                }

                Arrays.sort(red);
                Arrays.sort(green);
                Arrays.sort(blue);
                Arrays.sort(alpha);

                int redMedian = (red[(red.length) / 2]);
                int greenMedian = (green[(green.length / 2)]);
                int blueMedian = (blue[(blue.length) / 2]);
                int alphaMedian = (alpha[(alpha.length) / 2]);

                argb = (alphaMedian << 24) | (redMedian << 16) | (greenMedian << 8) | (blueMedian);

                input.setRGB(x, y, argb);
            }
        }

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);

        return output;

    }

}
