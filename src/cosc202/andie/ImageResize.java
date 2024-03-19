package cosc202.andie;

import java.awt.image.*;
import java.util.*;

public class ImageResize {

    ImageResize() {

    }

    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(input.getColorModel(),
                input.copyData(null),
                input.isAlphaPremultiplied(), null);


        return output;
    }

}
