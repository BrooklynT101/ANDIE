package cosc202.andie;
import java.awt.image.*;

public class SoftBlur implements ImageOperation, java.io.Serializable {

   private int radius;

   SoftBlur(int radius) {
      this.radius = radius;
   }

public BufferedImage apply (BufferedImage input) {
   return input; //PLACEHOLDER PLEASE CHANGE
   }
}
