package cosc202.andie;
import java.awt.image.*;

/**
 * Default Constructor for the soft blue filter, with a default
 * radius of 1
 */
public class Sharpen implements ImageOperation, java.io.Serializable {

   private int radius;

   /**
    * Default Constructor for the soft blue filter, with a default
    * radius of 1
    */
    Sharpen() {
      this.radius = 1;
   }

   /**
    * Constructor for the soft blur filter, radius set by input
    * @param radius of type int
    */
    Sharpen(int radius) {
      this.radius = radius;
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
           0,    1/2.0f,   0,
         1/2.0f,  3.0f,  1/2.0f,
           0,    1/2.0f,   0
      };
      
      //Creating kernel, mimicking same dimensions as SoftBlue, using array as input
      Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array);
      ConvolveOp convOp = new ConvolveOp(kernel);
      
      //output image? taken from MeanFilter
      BufferedImage output = new BufferedImage(input.getColorModel(),
      input.copyData(null),
      input.isAlphaPremultiplied(), null);
      convOp.filter(input, output);
      
      // And we're done
      return output;

   }
}
