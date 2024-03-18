package cosc202.andie;

import java.awt.image.*;
import java.util.*;

/*Gaussian Blur Class, allows image to be blurred using gaussian blur from a radius. */

public class GaussianBlur implements ImageOperation, java.io.Serializable {

    private int radius;

    /* Constructor */
    GaussianBlur() {
        this.radius = radius;
    }

    public BufferedImage apply(BufferedImage input) {
        int l = radius / 3;
        return input; // TODO: replace this with the actual image
    }

}
