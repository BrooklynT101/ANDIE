package cosc202.andie;

import java.awt.image.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BlockAveraging implements ImageOperation, java.io.Serializable {

    private int blockSize;

    BlockAveraging(int blockSize) {
        this.blockSize = blockSize;
    }

    BlockAveraging() {
        this(1);
    }

    public BufferedImage apply(BufferedImage input) {
        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));

        }
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);

        int numBlocksX = (input.getWidth() / blockSize) + 1; // find how many blocks are width ways
        int numBlocksY = (input.getHeight() / blockSize) + 1; // find how many blocks are height ways

        for (int blockY = 0; blockY < numBlocksY; blockY++) { // loop through each block in the image
            for (int blockX = 0; blockX < numBlocksX; blockX++) {
                // calculate the coordinates of the current clock
                int startX = blockX * blockSize;
                int startY = blockY * blockSize;
                int endX = Math.min(startX + blockSize, input.getWidth());
                int endY = Math.min(startY + blockSize, input.getHeight());

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

                int avgRed = totalRed / pixels;
                int avgGreen = totalGreen / pixels;
                int avgBlue = totalBlue / pixels;

                int avgRGB = (avgRed << 16) | (avgGreen << 8) | avgBlue;

                for (int y = startY; y < endY; y++) {
                    for (int x = startX; x < endX; x++) {
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
        BlockAveraging copy = new BlockAveraging(this.blockSize);
        return copy;
    }

}
