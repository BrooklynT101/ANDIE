package cosc202.andie;

import java.awt.image.*;
import java.util.*;

/*Gaussian Blur Class, allows image to be blurred using gaussian blur from a radius. */

public class GaussianBlur implements ImageOperation, java.io.Serializable {

    private int radius;

    /* Constructor */
    GaussianBlur(int radius) {
        this.radius = radius;
    }

    GaussianBlur() {
        this.radius = 1;
    }

    public BufferedImage apply(BufferedImage input) {
        int size = (2 * radius + 1) * (2 * radius + 1);

        /*
         * NEeed to do this but for mine float [] array = new float[size];
         * Arrays. (array, 1.0f/size); but do it as a 2d array?
         */

        float[] array = new float[size];

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

        for (int i = 0; i < size; i++) {

            array[x] = array[x] / size;
        }

        while (x < size) {
            for (int i = 0; i < 2 * radius + 1; i++) {
                for (int j = 0; j < 2 * radius + 1; j++) {
                    System.out.print(array[x] + " ");
                    x++;
                }
                System.out.println();
            }
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
