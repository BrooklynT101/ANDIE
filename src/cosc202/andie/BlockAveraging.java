package cosc202.andie;

import java.awt.image.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * <p>
 * ImageOperation to get the block average of a user inputted block size.
 * </p>
 * 
 * <p>
 * this produces and image that still looks similar but is differently coloured
 * and can rotate between the other values. The individual pixels RGB values are
 * changed and stored for dther changes.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Amelia Johnston
 * @version 1.0
 */

public class BlockAveraging implements ImageOperation, java.io.Serializable {

    private int blockWidth;
    private int blockHeight;
    private int avgRed;
    private int avgGreen;
    private int avgBlue;

    BlockAveraging(int blockWidth, int blockHeight) {
        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
    }

    BlockAveraging() {
        this(1, 1);
    }

    public BufferedImage apply(BufferedImage input) {
        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));

        }

        // System.out.println("width " + blockWidth + " height " + blockHeight);

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);

        int numBlocksX = (input.getWidth() / blockWidth) + 1; // find how many blocks are width ways
        int numBlocksY = (input.getHeight() / blockHeight) + 1; // find how many blocks are height ways

        // System.out.println("number of blocks width " + numBlocksX + " number of blocks height " + numBlocksY);

        for (int blockY = 0; blockY < numBlocksY; blockY++) { // loop through each block in the image
            for (int blockX = 0; blockX < numBlocksX; blockX++) {
                // calculate the coordinates of the current block
                int startX = blockX * blockWidth;
                int startY = blockY * blockHeight;
                int endX = Math.min(startX + blockWidth, input.getWidth()); //check that the start is not the same as the end
                int endY = Math.min(startY + blockHeight, input.getHeight());

                // System.out.println("start x " + startX + " start y " + startY);
                // System.out.println("end x " + endX + " start y " + endY);

                // variables to store the total values
                int totalRed = 0;
                int totalGreen = 0;
                int totalBlue = 0;
                int pixels = 0;

                // loop through each pixel in the current block
                for (int y = startY; y < endY; y++) {
                    for (int x = startX; x < endX; x++) {

                        int argb = input.getRGB(x, y);
                        // int a = (argb & 0xFF000000) >> 24;
                        int red = (argb & 0x00FF0000) >> 16;
                        int green = (argb & 0x0000FF00) >> 8;
                        int blue = (argb & 0x000000FF);

                        totalRed += red;
                        totalGreen += green;
                        totalBlue += blue;
                        pixels++;
                    }
                }

                if(pixels == 0){
                    avgRed = totalRed / 1;
                    avgGreen = totalGreen / 1;
                    avgBlue = totalBlue / 1;  
                }else{
                    avgRed = totalRed / pixels;
                    avgGreen = totalGreen / pixels;
                    avgBlue = totalBlue / pixels;
                }

                int avgRGB = (avgRed << 16) | (avgGreen << 8) | avgBlue;
                // System.out.println(avgRGB);

                // System.out.println(input.getRGB(blockX, blockY));
                // System.out.println(avgRGB);

                for(int y = startY; y < endY; y++){
                    for(int x = startX; x < endX; x++){
                        output.setRGB(x, y, avgRGB);
                    }
                }

            }
        }

        return output;
    }

    @Override
    public String getName() {
        return "Block Averaging";
    }

    /**
     * Method to return 'this' as an ImageOperation to support @see MacroActions
     */
    @Override
    public ImageOperation clone() {
        BlockAveraging copy = new BlockAveraging(this.blockWidth, this.blockHeight);
        return copy;
    }

}
