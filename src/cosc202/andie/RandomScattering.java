package cosc202.andie;

import java.awt.image.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RandomScattering implements ImageOperation, java.io.Serializable{

    private int radius;

    RandomScattering(){
        this(1); //what should the default size be??
    }

    RandomScattering(int radius){
        this.radius = radius;
    }

    public BufferedImage apply(BufferedImage input) {

        System.out.println("Method is calling");

        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));

        }

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

        //loop through each pixel 
        for(int y = 0; y < input.getHeight(); y++){
            for(int x = 0; x < input.getWidth(); x++){

                System.out.println("looping through pixels");

                //find the value of the pixel 
                int argb = input.getRGB(x, y); //store this somewhere

                //find the neighbourhood
                List<Integer> arrList = new ArrayList<Integer>();

                for(int col = y - radius; col < radius * 2; col++){ //get each pixel in the neighbourhood 
                    for(int row = x - radius; row < radius * 2; row++){
                        arrList.add(input.getRGB(row, col));
                    }
                }

                // System.out.println(arrList);

                //set the value to one in the surrounding radius 

            
                //should you set straught away or wait??

            }
        } 

        return output;

    }
    
}
