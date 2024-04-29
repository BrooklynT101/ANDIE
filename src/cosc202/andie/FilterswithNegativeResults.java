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

        // ASSUMPTION ALWAYS AN EXTENDED IMAGE.
        int[][] Resulta = new int[expanded.getWidth()][expanded.getHeight()];
        int[][] Resultr = new int[expanded.getWidth()][expanded.getHeight()];
        int[][] Resultg = new int[expanded.getWidth()][expanded.getHeight()];
        int[][] Resultb = new int[expanded.getWidth()][expanded.getHeight()];
        for (int y = radius; y < input.getHeight(); y++) {
            for (int x = radius; x < input.getWidth(); x++) {
                Resulta[x][y] = 0;
                Resultr[x][y] = 0;
                Resultg[x][y] = 0;
                Resultb[x][y] = 0;
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {

                        Color c = new Color(expanded.getRGB(x + dx, y + dy));
                        Resulta[x][y] += kernerldata2d[radius + dx][radius + dy] * c.getAlpha();
                        Resultr[x][y] += kernerldata2d[radius + dx][radius + dy] * c.getRed();
                        Resultg[x][y] += kernerldata2d[radius + dx][radius + dy] * c.getGreen();
                        Resultb[x][y] += kernerldata2d[radius + dx][radius + dy] * c.getBlue();

                    }
                }
                if(Resulta[x][y]<0||Resultr[x][y]<0||Resultg[x][y]<0||Resultb[x][y]<0){
                Resulta[x][y]+=127;
                Resultr[x][y]+=127;
                Resultg[x][y]+=127;
                Resultb[x][y]+=127;

                int argb;
                argb = (Resulta[x][y] << 24) | (Resultr[x][y] << 16) | (Resultg[x][y] << 8) |
                (Resultb[x][y]);
                expanded.setRGB(x, y,Math.min(argb,255));

                }else if(Resulta[x][y]>255||Resultr[x][y]>255||Resultg[x][y]>255||Resultb[x][y]>255){
                Resulta[x][y]-=127;
                Resultr[x][y]-=127;
                Resultg[x][y]-=127;
                Resultb[x][y]-=127;

                int argb;
                argb = (Resulta[x][y] << 24) | (Resultr[x][y] << 16) | (Resultg[x][y] << 8) |
                (Resultb[x][y]);
                expanded.setRGB(x, y,Math.min(argb,255));
                }else{
                int argb;
                argb = (Resulta[x][y] << 24) | (Resultr[x][y] << 16) | (Resultg[x][y] << 8) | (Resultb[x][y]);
                expanded.setRGB(x, y, argb);
             }
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
