package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.*;

public class ErasorFunction implements ImageOperation, java.io.Serializable {

    int x;
    int y;

    ErasorFunction(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public BufferedImage apply(BufferedImage input) {

        float[] array = { 0, 0, 0,
                0, 1, 0,
                0, 0, 0 };
        Kernel k = new Kernel(3, 3, array);
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());

        ConvolveOp convOp = new ConvolveOp(k, ConvolveOp.EDGE_NO_OP, null);

        ExtendingFilters expand = new ExtendingFilters(input, 3);

        BufferedImage expanded = expand.extending();

        BufferedImage image = new BufferedImage(expanded.getWidth(), expanded.getHeight(),
                expanded.getType());

        convOp.filter(expanded, image);

        output = expand.shrinking(image);

        int argb;
        argb = (0 << 16) | (0 << 8) | (0);
        output.setRGB(x, y, argb);
        Color c = new Color(input.getRGB(x, y), true);
        //LOOP AROUND A LARGE RADIUS TO ERASE IMPLEMENT THIS.
        System.out.println(c.getAlpha() + "alpha");
        return output;

    }

    @Override
    public String getName() {
        return "Erasor Function";
    }

    /**
     * Method to return 'this' as an ImageOperation to support @see MacroActions
     */
    @Override
    public ImageOperation clone() {
        ErasorFunction copy = new ErasorFunction(x, y);
        return copy;
    }
}
