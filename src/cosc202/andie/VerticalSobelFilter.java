package cosc202.andie;

import java.awt.image.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VerticalSobelFilter implements ImageOperation, java.io.Serializable {

    private int radius;

    /**
     * <p>
     * Construct a Emboss filter with the given size.
     * 
     * @param radius The radius of the newly constructed MeanFilter
     */
    VerticalSobelFilter(int radius) {
        this.radius = 1;
    }

    VerticalSobelFilter() {
        this(1);
    }

    public BufferedImage apply(BufferedImage input) {
        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));
        }

        float[] array = {
                -1/2, 0, +1/2,
                -1, 0, +1,
                -1/2, 0, 1/2
        };

        Kernel kernel = new Kernel(2 * radius + 1, 2 * radius + 1, array);
        FilterswithNegativeResults f = new FilterswithNegativeResults(kernel);
        BufferedImage output = f.filter(input, radius);

        return output;

    }

    @Override
    public String getName() {
        return "Vertical Sobel Filter";
    }

    /**
     * Method to return 'this' as an ImageOperation to support @see MacroActions
     */
    @Override
    public ImageOperation clone() {
        VerticalSobelFilter copy = new VerticalSobelFilter(this.radius);
        return copy;
    }

    
}
