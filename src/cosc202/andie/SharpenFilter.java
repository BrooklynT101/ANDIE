package cosc202.andie;

import java.awt.image.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * <p>
 * Filter class that sharpens an image a number of times.
 * <p>
 * 
 * <p>
 * The operation involves creating a kernal from an int array and using
 * convolution
 * to apply the filter, the repeat number determined by the user
 * <p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @version 1.0
 * @author Brooklyn Taylor
 */
public class SharpenFilter implements ImageOperation, java.io.Serializable {

   private int radius;
   private int t;

   /**
    * Default Constructor for the sharpen filter, with a default
    * radius of 1
    * /TODO: test if the radius being changed influences the filter
    */
   public SharpenFilter(int t) {
      this.t = t; // the amount of times to apply the filter
      this.radius = 1;
   }

   /**
    * Applys filter to a given buffered image object, returning the output
    * image with the sharpen filter applied. Implemented via convolution, where the
    * convolution kernel is determined by the radius.
    * 
    * @param input The image to apply the Soft blur filter to.
    * @return The output image.
    */
   public BufferedImage apply(BufferedImage input) {
      if (input == null) {
         JFrame frame = new JFrame();
         JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));

      }
      // BufferedImage output = input;

      // creating the kernal array
      float[] array = {
            0, -1 / 2.0f, 0,
            -1 / 2.0f, 3.0f, -1 / 2.0f,
            0, -1 / 2.0f, 0
      };

      // Creating kernel, mimicking same dimensions as SoftBlur, using array as input
      Kernel kernel = new Kernel(2 * radius + 1, 2 * radius + 1, array);
      ConvolveOp convOp = new ConvolveOp(kernel);

      // Extending Filter Addition:
      ExtendingFilters expand = new ExtendingFilters(input, radius);

      // Extending Filter Addition:
      BufferedImage expanded = expand.extending();

      // Applying filter to the input image
      for (int i = 1; i <= t; i++) {
         expanded = convOp.filter(expanded, null);
      }

      // Extending Filter Addition:
      BufferedImage output = expand.shrinking(expanded);

      // return the output image
      return output;
   }

   @Override
   public String getName() {
      return "Sharpen Filter";
   }
}
