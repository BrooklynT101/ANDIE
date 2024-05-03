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
                Color d = new Color(expanded.getRGB(x , y ));
                System.out.println("Pre:" + d.getAlpha());
                // float sumr = 0;
                // float sumg = 0;
                // float sumb = 0;
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {

                        Color c = new Color(expanded.getRGB(x + dx, y + dy));
                        // Resultaf[x][y] += kernerldata2d[radius + dx][radius + dy] * c.getAlpha();
                        // sumr += kernerldata2d[radius + dx][radius + dy] * c.getRed();
                        // sumg += kernerldata2d[radius + dx][radius + dy] * c.getGreen();
                        // sumb += kernerldata2d[radius + dx][radius + dy] * c.getBlue();
                        // Resultaf[x][y] = c.getAlpha();
                        Resultrf[x][y] += kernerldata2d[radius + dx][radius + dy] * c.getRed();
                        Resultgf[x][y] += kernerldata2d[radius + dx][radius + dy] * c.getGreen();
                        Resultbf[x][y] += kernerldata2d[radius + dx][radius + dy] * c.getBlue();
                        
                        // System.out.println(kernerldata2d[radius + dx][radius + dy]);
                    }
                }

                // Cases for Negative results
                    Resultrf[x][y] = Resultrf[x][y]/2 +127;
                    Resultgf[x][y] = Resultgf[x][y]/2 +127;
                    Resultbf[x][y] = Resultbf[x][y]/2 +127;
                    // System.out.println(Resultrf[x][y] + ":" + Resultgf[x][y] + ":" +Resultbf[x][y]);

                    // sumr = sumr/2 +127;
                    // sumg = sumg/2 +127;
                    // sumb = sumb/2 +127;
                                Color c = new Color(expanded.getRGB(x , y ));
                            
                // Resulta[x][y] = (int)Resultaf[x][y];
                Resulta[x][y] = c.getAlpha();
                System.out.println(Resulta[x][y]);
                Resultr[x][y] = (int) Math.min(Math.max(Resultrf[x][y],0),255);
                Resultg[x][y] = (int) Math.min(Math.max(Resultgf[x][y],0),255);
                Resultb[x][y] = (int) Math.min(Math.max(Resultbf[x][y],0),255);

                // int r = (int) Math.min(Math.max(sumr,0),255);
                // int g = (int) Math.min(Math.max(sumg,0),255);
                // int b = (int) Math.min(Math.max(sumb,0),255);

                int argb;
                argb = (Resulta[x][y] << 24) | (Resultr[x][y] << 16) | (Resultg[x][y] << 8) | (Resultb[x][y]);
                // Color c = new Color(expanded.getRGB(x , y ));
                // argb = (c.getAlpha() << 24) |(r << 16) | (g << 8) | (b);

                // Color c = new Color(Resultrf[x][y], Resultgf[x][y], Resultbf[x][y],
                // Resultaf[x][y]);
                expanded.setRGB(x, y, argb);
                // float[] components = { Resultrf[x][y], Resultgf[x][y], Resultbf[x][y] };
                // Color k = new Color(expanded.getGraphics().getColor().getColorSpace(),
                // components, Resultaf[x][y]);

                // expanded.getGraphics().setColor(k);

            }
        }

        BufferedImage output = expand.shrinking(expanded);

        return expanded;
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
