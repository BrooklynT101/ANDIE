package cosc202.andie;

import java.awt.image.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RandomScattering implements ImageOperation, java.io.Serializable{

    @SuppressWarnings("unused")
    private int blockSize;

    RandomScattering(){
        this(10); //what should the default size be??
    }

    RandomScattering(int blockSize){
        this.blockSize = blockSize;
    }

    public BufferedImage apply(BufferedImage input) {
        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));

        }

        //loop through each pixel and scatter them 
        //does the other pixel switch? 
        for(int w = 0; w < input.getWidth(); w++){ //loop through all the pixels 
            for(int h = 0; h < input.getHeight(); h++){

            }
        }


        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

        return output;

    }
    
}
