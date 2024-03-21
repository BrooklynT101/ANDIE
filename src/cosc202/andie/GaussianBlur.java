package cosc202.andie;

import java.awt.image.*;
//import java.util.*;

/*Gaussian Blur Class, allows image to be blurred using gaussian blur from a radius. */

public class GaussianBlur implements ImageOperation, java.io.Serializable {

    private int radius;

    /* Constructor takes in a radius value. */
    GaussianBlur(int radius) {
        this.radius = radius;
    }

    GaussianBlur() {
        this.radius = 1;
    }


    /*Buffered image method applys  the Gaussian filter to the image */
    public BufferedImage apply(BufferedImage input) {
        int size = (2 * radius + 1) * (2 * radius + 1);

        float[] array = new float[size];


        /*A nested for loop which goes through each x and y corrdinate and makes it 
        puts in the array for the corresponding entry calculting the value with the 
        gaussian formuala. 
        */
        int l = this.radius / 3;
        int x = 0;
        while (x < size) {
            for (int i = 0; i < 2 * radius + 1; i++) {
                for (int j = 0; j < 2 * radius + 1; j++) {
                    array[x] = ((float) (1 / ((float) 2 * (float) Math.PI * (float) (l ^ 2))) *
                            (float) Math
                                    .exp(-1 * (float) (((float) (i ^ 2) + (float) (j ^ 2)) / (2 * (float) (l ^ 2)))));
                    x++;
                }
            }
        }

        float sum = 0;

        for (int i = 0; i < size; i++) {
            sum += array[i];
        }

        x = 0;


        //Normalised all the gaussian values, by dividing each entry by the sum of all of them.
        for (int i = 0; i < size; i++) {

            array[x] = array[x] / sum;
            x++;
        }



        

        Kernel kernel = new Kernel(2 * radius + 1, 2 * radius + 1, array);

        ConvolveOp convOp = new ConvolveOp(kernel);

        BufferedImage output = new BufferedImage(input.getColorModel(),
                input.copyData(null),
                input.isAlphaPremultiplied(), null);

        convOp.filter(input, output);

        return output;
    }

}
