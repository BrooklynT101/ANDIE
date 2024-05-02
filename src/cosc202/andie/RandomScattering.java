package cosc202.andie;

import java.awt.image.*;
import java.util.*;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RandomScattering implements ImageOperation, java.io.Serializable {

    private int radius;
    private ArrayList<Integer> pixelOptions;

    RandomScattering() {
        this(1);
    }

    RandomScattering(int radius) {
        this.radius = radius;
    }

    public BufferedImage apply(BufferedImage input) {

        Random r = new Random();
        System.out.println("Method is calling");
        System.out.println(radius);

        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));

        }

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);

        // loop through each pixel
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {

                System.out.println(x + " " + y);

                // find the neighbourhood
                pixelOptions = new ArrayList<Integer>();

                for (int col = -radius; col <= radius; col++) { // get each pixel in the neighbourhood
                    if (col < 0 || col > input.getWidth()) {
                        continue;
                    }
                    for (int row = -radius; row <= radius; row++) {
                        if (row < 0 || row > input.getHeight()) {
                            continue;
                        }
                        pixelOptions.add(input.getRGB(row, col));
                    }
                }

                // put all the possible values in an array list
                // System.out.println(Arrays.toString(pixelOptions.toArray()));

                // set the value to one in the surrounding radius
                int randomIndex = r.nextInt(pixelOptions.size()); // choose a random index in the arrayList
                // System.out.println(randomIndex);
                int getValue = (int) pixelOptions.get(randomIndex); // get the value in the array
                int argb = input.getRGB(x, y);

                // TODO: Why is the a value negative?????
                int a = (argb & 0xFF000000) >> 24; int red = (argb & 0x00FF0000) >> 16; int green = (argb & 0x0000FF00) >> 8; int blue = (argb & 0x000000FF);
                System.out.println(a + " " + red + " " + green + " " + blue);

                int a1 = (getValue & 0xFF000000) >> 24; int red1 = (getValue & 0x00FF0000) >> 16; int green1 = (getValue & 0x0000FF00) >> 8; int blue1 = (getValue & 0x000000FF);
                System.out.println(a1 + " " + red1 + " " + green1 + " " + blue1);

                System.out.println("original pixel value = " + input.getRGB(x, y));
                System.out.println("index in array = " + randomIndex + " value at that index = " + getValue);
                output.setRGB(x, y, getValue); // set the RGB value to the new value

                pixelOptions.clear();
            }
        }

        return output;

    }

}

