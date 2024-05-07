package cosc202.andie;

import java.awt.image.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Emboss6Filter implements ImageOperation, java.io.Serializable {

    private int radius;

    /**
     * <p>
     * Construct a Emboss filter with the given size.
     * 
     * @param radius The radius of the newly constructed MeanFilter
     */
    Emboss6Filter(int radius) {
        this.radius = 1;
    }

    Emboss6Filter() {
        this(1);
    }

    public BufferedImage apply(BufferedImage input) {
        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));

        }

        float[] array = {
                -1, 0, 0,
                0, 0, 0,
                0, 0, +1
        };

        Kernel kernel = new Kernel(2 * radius + 1, 2 * radius + 1, array);

        FilterswithNegativeResults f = new FilterswithNegativeResults(kernel);

        BufferedImage output = f.filter(input, radius);

        return output;

    }

    @Override
    public String getName() {
        return "Emboss6 Filter";
    }

    /**
     * Method to return 'this' as an ImageOperation to support @see MacroActions
     */
    @Override
    public ImageOperation clone() {
        Emboss6Filter copy = new Emboss6Filter(this.radius);
        return copy;
    }

}
