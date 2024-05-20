package cosc202.andie;

import java.awt.Color;
import java.awt.image.*;

/**
 * <p>
 * FilterswithNegativeResults class, implements an image convolution method.
 * <p>
 * 
 * <p>
 * Creates a FilterswithNegativeResults object, which has a convolution method,
 * taking in a kernel
 * which is loops through, applying to each pixels RGB channel.
 * 
 * 
 * <p>
 * 
 * @author Ari Josephson
 */
public class FilterswithNegativeResults {

    private Kernel kernel;

    FilterswithNegativeResults(Kernel kernel) {
        this.kernel = kernel;

    }

    public BufferedImage filter(BufferedImage input, int radius) {

        float[] kerneldata = new float[kernel.getHeight() * kernel.getWidth()];
        kernel.getKernelData(kerneldata);
        float[][] kernerldata2d = new float[kernel.getHeight()][kernel.getWidth()];

        int index = 0;
        for (int i = 0; i < kernerldata2d.length; i++) {
            for (int j = 0; j < kernerldata2d[i].length; j++) {
                kernerldata2d[i][j] = kerneldata[index];
                index++;
            }
        }

        // Expand the image.
        ExtendingFilters expand = new ExtendingFilters(input, radius);
        System.out.println(input.getWidth() + "and" + input.getHeight());

        BufferedImage expanded = expand.extending();

        BufferedImage output = new BufferedImage(expanded.getWidth(), expanded.getHeight(), expanded.getType());

        // ALWAYS AN EXTENDED IMAGE.
        float[][] Resultaf = new float[expanded.getWidth()][expanded.getHeight()];
        float[][] Resultrf = new float[expanded.getWidth()][expanded.getHeight()];
        float[][] Resultgf = new float[expanded.getWidth()][expanded.getHeight()];
        float[][] Resultbf = new float[expanded.getWidth()][expanded.getHeight()];

        int[][] Resulta = new int[expanded.getWidth()][expanded.getHeight()];
        int[][] Resultr = new int[expanded.getWidth()][expanded.getHeight()];
        int[][] Resultg = new int[expanded.getWidth()][expanded.getHeight()];
        int[][] Resultb = new int[expanded.getWidth()][expanded.getHeight()];

        for (int y = radius; y < input.getHeight() + radius; y++) {
            for (int x = radius; x < input.getWidth() + radius; x++) {
                Resultaf[x][y] = 0;
                Resultrf[x][y] = 0;
                Resultgf[x][y] = 0;
                Resultbf[x][y] = 0;

                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {

                        Color c = new Color(expanded.getRGB(x + dx, y + dy), true);

                        Resultrf[x][y] += kernerldata2d[radius + dy][radius + dx] * c.getRed();
                        Resultgf[x][y] += kernerldata2d[radius + dy][radius + dx] * c.getGreen();
                        Resultbf[x][y] += kernerldata2d[radius + dy][radius + dx] * c.getBlue();
                    }
                }

                // Cases for Negative results
                Resultrf[x][y] = (float) Resultrf[x][y] / 2.f + 127.f;
                Resultgf[x][y] = (float) Resultgf[x][y] / 2.f + 127.f;
                Resultbf[x][y] = (float) Resultbf[x][y] / 2.f + 127.f;

                Color c = new Color(expanded.getRGB(x, y), true);
                // System.out.println(c.getAlpha() + "" + c.getRed() + "" + c.getGreen() + "" +
                // c.getBlue());
                Resulta[x][y] = c.getAlpha();

                Resultr[x][y] = (int) Math.min(Math.max(Resultrf[x][y], 0), 255);
                Resultg[x][y] = (int) Math.min(Math.max(Resultgf[x][y], 0), 255);
                Resultb[x][y] = (int) Math.min(Math.max(Resultbf[x][y], 0), 255);

                int argb;
                argb = (Resulta[x][y] << 24) | (Resultr[x][y] << 16) | (Resultg[x][y] << 8) | (Resultb[x][y]);

                output.setRGB(x, y, argb);

            }
        }

        output = expand.shrinking(output);

        return output;
    }

}
