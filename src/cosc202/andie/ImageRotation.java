package cosc202.andie;
import java.awt.image.*;

public class ImageRotation implements ImageOperation, java.io.Serializable{
    /** Constructor */
    ImageRotation(){
        
    }

    public BufferedImage apply(BufferedImage input) {
        return input;
    }
}
