package cosc202.andie;

import java.awt.image.*;
import java.util.*;

public class MedianFilter implements ImageOperation, java.io.Serializable{
    
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;
    private int neighbourhoodSize;

    /**
     * <p>
     * Construct a Median filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed MedianFilter
     */
    MedianFilter(int radius){
        this.radius = radius;
        this.neighbourhoodSize = (radius*2+1)^2;
    }

    /**
     * <p>
     * Construct a Median filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Median filter has radius 1.
     * </p>
     * 
     * @see MedianFilter(int)
     */
    MedianFilter(){
        this.radius = 1;
        this.neighbourhoodSize = (radius*2+1)^2;
    }
   
    /**
     * <p>
     * Apply a Median filter to an image.
     * </p>
     * 
     * <p>
     * Unlike the mean filter, the Median filter is not implemented via convolution.
     * Instead we use a local neibourhood with a specified radius --> {@link radius}.  
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Median filter to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply(BufferedImage input) {


        for (int y = radius; y < input.getHeight() - radius; ++y) {
            for (int x = radius; x < input.getWidth() - radius; ++x) {
            int argb = input.getRGB(x, y);
            int a = (argb & 0xFF000000) >> 24; 
            int[] red = new int[neighbourhoodSize];
            int[] green = new int[neighbourhoodSize];
            int[] blue = new int[neighbourhoodSize];
            int index = 0;

                for(int i = y - radius; i < (radius * 2) + 1; i++){
                    for(int j = x - radius; j < (radius * 2) + 1; j++){

                    int argbNeighbourhood = input.getRGB(i, j);
                    red[index] = (argbNeighbourhood & 0x00FF0000) >> 16;
                    green[index] = (argbNeighbourhood & 0x0000FF00) >> 8;
                    blue[index] = (argbNeighbourhood & 0x000000FF);
                    index++;
                    }
                }
        
                Arrays.sort(red);
                Arrays.sort(green);
                Arrays.sort(blue);

                argb = (a << 24) | ((red[(red.length)/2]) << 16) | ((green[(green.length/2)]) << 8) | (blue[(blue.length)/2]);

                input.setRGB(x, y, argb);
            }
        }
        
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        //TODO: How to add this to filter actions
        //TODO: replace this with the actual image
        return output;

    }
}
