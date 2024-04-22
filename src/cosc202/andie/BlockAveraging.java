package cosc202.andie;

import java.awt.image.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BlockAveraging implements ImageOperation, java.io.Serializable{

    private int blockSize;

    BlockAveraging(int blockSize){
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

        int neighbourhoodSize = blockSize * blockSize; //do i need this?
        System.out.println(neighbourhoodSize);

        for (int y = 0; y < input.getHeight(); y += blockSize) { //loop through the pixels in block size?
            for (int x = 0; x < input.getWidth(); x += blockSize) {

                

            }
        }

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

        return output;
    }

}
