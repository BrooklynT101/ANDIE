package cosc202.andie;

import java.awt.image.BufferedImage;

public class CropFunction implements ImageOperation, java.io.Serializable {

    int regionHeight;
    int regionWidth;
    int xStart;
    int yStart;

    /**
     * <p>
     * Construct a crop function to be performed.
     * </p
     * >
     * <p>
     * Passes information to data fields about the operation.
     * </p>
     * 
     * @param xStart       start x coordinate of crop
     * @param yStart       start y coordinate of crop
     * @param regionWidth  width of line crop
     * @param regionHeight height of crop
     */
    CropFunction(int xStart, int yStart, int regionWidth, int regionHeight) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.regionWidth = regionWidth;
        this.regionHeight = regionHeight;

    }

    /**
     * <p>
     * Apply a draw Function to an image.
     * </p>
     * 
     * @param input The image to apply the crop to.
     * @return The resulting image after crop is applied.
     */
    public BufferedImage apply(BufferedImage input) {

        try {
            BufferedImage cropped = input.getSubimage(xStart + 1, yStart + 1, regionWidth - 1, regionHeight - 1);
            return cropped;
        } catch (Exception e) {
            System.out.println(e.toString());
            return input;
        }

    }

    @Override
    public String getName() {
        return "Crop Function";
    }

    /**
     * Method to return 'this' as an ImageOperation to support @see MacroActions
     */
    @Override
    public ImageOperation clone() {
        CropFunction copy = new CropFunction(xStart, yStart, regionWidth, regionHeight);
        return copy;
    }

}
