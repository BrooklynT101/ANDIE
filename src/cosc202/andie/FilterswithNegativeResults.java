package cosc202.andie;

import java.awt.Color;
import java.awt.image.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FilterswithNegativeResults {

    private Kernel kernel;

    FilterswithNegativeResults(Kernel kernel) {
        this.kernel = kernel;

    }

    public BufferedImage filter(BufferedImage input, int radius) {

        float[] kerneldata = new float[kernel.getHeight() * kernel.getWidth()];
        System.out.println(kerneldata.length);
        kernel.getKernelData(kerneldata);
        float[][] kernerldata2d = new float[kernel.getHeight()][kernel.getWidth()];
        System.out.println(kernerldata2d.length);
        System.out.println(kernerldata2d[0].length);

        int index = 0;
        for (int i = 0; i < kernerldata2d.length; i++) {
            for (int j = 0; j < kernerldata2d[i].length; j++) {
                kernerldata2d[i][j] = kerneldata[index];
                index++;
            }
        }

        // Expand the image.
        ExtendingFilters expand = new ExtendingFilters(input, radius);

        BufferedImage expanded = expand.extending();

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

                        Color c = new Color(expanded.getRGB(x + dx, y + dy));
                        Resultaf[x][y] += kernerldata2d[radius + dx][radius + dy] * c.getAlpha();
                        // Resulta[x][y] = c.getAlpha();
                        Resultrf[x][y] += kernerldata2d[radius + dx][radius + dy] * c.getRed();
                        Resultgf[x][y] += kernerldata2d[radius + dx][radius + dy] * c.getGreen();
                        Resultbf[x][y] += kernerldata2d[radius + dx][radius + dy] * c.getBlue();

                    }
                }

                // Cases for Negative results
                if (Resultaf[x][y] < 0) {
                    Resultaf[x][y] += 127.f;
                    Resultaf[x][y] = Math.max(Resultaf[x][y], 0.f);

                }
                if (Resultrf[x][y] < 0) {
                    Resultrf[x][y] += 127.f;
                    Resultrf[x][y] = Math.max(Resultrf[x][y], 0.f);

                }
                if (Resultgf[x][y] < 0) {
                    Resultgf[x][y] += 127.f;
                    Resultgf[x][y] = Math.max(Resultgf[x][y], 0.f);

                }

                if (Resultbf[x][y] < 0) {
                    Resultbf[x][y] += 127.f;
                    Resultbf[x][y] = Math.max(Resultbf[x][y], 0.f);
                }

                // 2^32

                // Cases for Over 255 results
                if (Resultaf[x][y] > 255) {
                    Resultaf[x][y] -= 127.f;
                    Resultaf[x][y] = Math.min(Resultaf[x][y], 255.f);

                }
                if (Resultrf[x][y] > 255) {
                    Resultrf[x][y] -= 127.f;
                    Resultrf[x][y] = Math.min(Resultrf[x][y], 255.f);

                }
                if (Resultgf[x][y] > 255) {
                    Resultgf[x][y] -= 127.f;
                    Resultgf[x][y] = Math.min(Resultgf[x][y], 255.f);

                }
                if (Resultbf[x][y] > 255) {
                    Resultbf[x][y] -= 127.f;
                    Resultbf[x][y] = Math.min(Resultbf[x][y], 255.f);

                }

                Resulta[x][y] = (int) Resultaf[x][y];
                Resultr[x][y] = (int) Resultrf[x][y];
                Resultg[x][y] = (int) Resultgf[x][y];
                Resultb[x][y] = (int) Resultbf[x][y];

                int argb;
                argb = (Resulta[x][y] << 24) | (Resultr[x][y] << 16) | (Resultg[x][y] << 8) | (Resultb[x][y]);

                expanded.setRGB(x, y, argb);

            }
        }

        BufferedImage output = expand.shrinking(expanded);

        return output;
    }

}

/*
 * // -255
 * -127
 * 
 * // -27
 * 100
 * 
 * 
 * - 50
 * - 70
 * 
 * 0
 * 0
 * 
 * 
 * 
 * 
 * if val < 0
 * val += 127
 * max (val, 0)
 * 
 * if val >255
 * val -= 127
 * min(val, 255)
 * 
 * 255
 * -2
 * 
 * input.getrgbs
 * now we a,r,g,b
 * kenerl
 * 
 * a * Kernel
 * 
 * 127 * {1/2, 1, }
 * 
 * {127, 127, 255, ....} * {1/2, 1/3, -1/2}
 * 
 * //matrix multiplaction
 */
