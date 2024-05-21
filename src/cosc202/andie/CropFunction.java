package cosc202.andie;

import java.awt.image.BufferedImage;

public class CropFunction implements ImageOperation, java.io.Serializable {

    int regionHeight;
    int regionWidth;
    int xStart;
    int yStart;
    // int xEnd;
    // int yEnd;

    CropFunction(int xStart, int yStart, int regionWidth, int regionHeight) {
    this.xStart = xStart;
    this.yStart = yStart;
    this.regionWidth = regionWidth;
    this.regionHeight = regionHeight;

    }


    public BufferedImage apply(BufferedImage input) {

        
        try{
            BufferedImage cropped = input.getSubimage(xStart + 1, yStart + 1, regionWidth - 1, regionHeight - 1);
            return cropped;
        }catch(Exception e){
            System.out.println(e.toString());
            return input;
        }
    
    }

}
