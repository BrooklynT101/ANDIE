package cosc202.andie;

import java.awt.image.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FiltersWithNegativeResults {

    private Kernel kernel;

    FiltersWithNegativeResults(Kernel kernel) {
        this.kernel = kernel;

    }

    public BufferedImage filter(BufferedImage input) {
        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {

            }
        }
    }

    /*
     * // -255
     * -127
     * 
     * // -27
     * 100
     * 
     * 
     * - 50
     * - 70
     * 
     * 0
     * 0
     * 
     * 
     * 
     * 
     * if val < 0
     * val += 127
     * max (val, 0)
     * 
     * if val >255
     * val -= 127
     * min(val, 255)
     * 
     * 255
     * -2
     * 
     * input.getrgbs
     * now we a,r,g,b
     * kenerl
     * 
     * a * Kernel
     * 
     * 127 * {1/2, 1, }
     * 
     * {127, 127, 255, ....} * {1/2, 1/3, -1/2}
     * 
     * //matrix multiplaction
     */

}
