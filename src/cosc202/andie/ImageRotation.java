package cosc202.andie;
import java.awt.image.*;

public class ImageRotation implements ImageOperation, java.io.Serializable{

    public boolean clockwise;

    public ImageRotation(boolean clockwise){
        this.clockwise = clockwise;
    }

    public BufferedImage apply(BufferedImage input) {

        int width = input.getWidth();
        int height = input.getHeight();
        BufferedImage output = new BufferedImage(height, width, input.getType());

        for (int x = 0; x < width; x++) { 
            for (int y = 0; y < height; y++) {
                if(clockwise == true){
                    output.setRGB(height - y - 1, x, input.getRGB(x, y));
                }else{
                    output.setRGB(y, width - x - 1, input.getRGB(x, y));
                }
            }
        }
        return output;
    }

}
