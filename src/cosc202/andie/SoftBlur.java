package cosc202.andie;

import java.awt.image.*;

/**
 * A filter class that applies a variable blur to the whole image
 * @author Brooklyn Taylor
 */
public class SoftBlur implements ImageOperation, java.io.Serializable {

   private int radius;

   /**
    * Default Constructor for the soft blur filter, with a default
    * radius of 1
    */
   SoftBlur() {
      this.radius = 1;
   }

   
   /**
    * Applys the filter to a given buffered image object, returning the output 
    * image with the blur filter added. Implemented via convolution, where the 
    * convolution kernel is determined by the radius.
    * @param input The image to apply the Soft blur filter to.
    * @return The output image.
    */
   public BufferedImage apply (BufferedImage input) {
      
      //creating the kernal array
      float[] array = {
           0,    1/8.0f,   0,
         1/8.0f, 1/2.0f, 1/8.0f,
           0,    1/8.0f,   0
      };
      
      //Creating kernel, mimicking same dimensions as MeanFilter, using array as input
      Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array);
      ConvolveOp convOp = new ConvolveOp(kernel);
      
      //Applying filter to the input image
      BufferedImage output = new BufferedImage(input.getColorModel(),
         input.copyData(null),
         input.isAlphaPremultiplied(), null);
      convOp.filter(input, output);
      
      // return the output image
      return output;

   }
}
