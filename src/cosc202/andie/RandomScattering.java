package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.util.Random;

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
        Random r = new Random();

        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));

        }

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

        //loop through each pixel 
        for(int y = 0; y < input.getHeight(); y++){
            for(int x = 0; x < input.getWidth(); x++){

                System.out.println("looping through pixels");

                //find the neighbourhood
                List<Integer> pixelOptions = new ArrayList<>();

                for(int col = -radius; col <= radius; col++){ //get each pixel in the neighbourhood 
                    int neighbourY = y + col; //find the index of the neightbour for checking
                    if(neighbourY >= 0 && neighbourY <= input.getHeight()){ // check that the neighbour is in bounds
                        for(int row = -radius; row <= radius; row++){ 
                            int neighbourX = x + row;
                            if(neighbourX >= 0 && neighbourX <= input.getWidth()){
                                pixelOptions.add(input.getRGB(row, col));
                                System.out.println("Added an item to the arrayList");
                            }
                        }
                    }
                }//put all the possible values in an array list 

                //set the value to one in the surrounding radius 
                int randomIndex = r.nextInt(pixelOptions.size()); //choose a random index in the arrayList 
                    System.out.println(randomIndex);
                int getValue = pixelOptions.get(randomIndex); //get the value in the array 
                    System.out.println(getValue);
                output.setRGB(x, y, getValue); // set the RGB value to the new value 
            }
        } 

        return output;

    }
    
}
