package cosc202.andie;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ExtendingFilters class, extends an image, so a filter can apply to its edges.
 * <p>
 * 
 * <p>
 * Creates a ExtendingFilters object, has extending method, which makes new
 * image, larger then input by radius
 * and draws input in the centre. Has Shrinking Method, which gets subimage at
 * the centre location,
 * to be used post filter applied to extend image.
 * 
 * 
 * <p>
 * 
 * @author Ari Josephson
 */
public class ExtendingFilters {
    BufferedImage input;
    int radius;

    ExtendingFilters(BufferedImage input, int radius) {
        this.input = input;
        this.radius = radius;
    }

    public BufferedImage extending() {
        Image result = input.getScaledInstance(input.getWidth(), input.getHeight(), Image.SCALE_DEFAULT);

        Image background = input.getScaledInstance(2 * radius + input.getWidth(), 2 * radius + input.getHeight(),
                Image.SCALE_DEFAULT);

        BufferedImage extended = new BufferedImage(2 * radius + input.getWidth(), 2 * radius + input.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        extended.getGraphics().drawImage(background, 0, 0, null);
        extended.getGraphics().drawImage(result, radius, radius, null);

        return extended;
    }

    public BufferedImage shrinking(BufferedImage extended) {

        BufferedImage output = extended.getSubimage(radius, radius, input.getWidth(), input.getHeight());

        return output;
    }

}
