package cosc202.andie;

import java.awt.image.*;

/**
 * A filter class that applies a variable blur to the whole image
 * @author Brooklyn Taylor
 */
public class BlurFilter implements ImageOperation, java.io.Serializable {

   private int radius;
   private int t;

   /**
    * Default Constructor for the soft blur filter, with a default
    * radius of 1
    * @param t of type int, the number of times to apply the filter
    */
   BlurFilter(int t) {
      this.t = t;
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
      // Idea for a variably changable blur effect from StackOverflow
      // // Calculate the weight for each element based on the radius
      // float weight = 1.0f / ((2 * radius + 1) * (2 * radius + 1));

      // // Define the array with the calculated weight for each element
      // float[] array = new float[(2 * radius + 1) * (2 * radius + 1)];
      // for (int i = 0; i < array.length; i++) {
      //    array[i] = weight;
      // }
      BufferedImage output = input;
      
      //creating the kernal array
      float[] array = {
           0,    1/8.0f,   0,
         1/8.0f, 1/2.0f, 1/8.0f,
           0,    1/8.0f,   0
      };
      
      //Creating kernel, mimicking same dimensions as MeanFilter, using array as input
      Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array);
      ConvolveOp convOp = new ConvolveOp(kernel);
      
      //apply filter 'repeat' number of times 
      for (int i = 1; i <= t; i++) {
         output = convOp.filter(output, null);
      }
      
      // return the output image
      return output;

   }
}
