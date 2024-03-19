package cosc202.andie;
import java.awt.image.*;
import java.util.*;

public class ImageRotation implements ImageOperation, java.io.Serializable{
    /** Constructor */
    ImageRotation(){
        
    }

    public BufferedImage apply(BufferedImage input) {
        return input;
    }
}
