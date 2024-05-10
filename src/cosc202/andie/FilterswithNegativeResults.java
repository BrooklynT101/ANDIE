package cosc202.andie;

import java.awt.Color;
import java.awt.image.*;
import java.nio.Buffer;

public class FilterswithNegativeResults {

    private Kernel kernel;

    FilterswithNegativeResults(Kernel kernel) {
        this.kernel = kernel;

    }

    public BufferedImage filter(BufferedImage input, int radius) {

        float[] kerneldata = new float[kernel.getHeight() * kernel.getWidth()];
        // System.out.println(kerneldata.length);
        kernel.getKernelData(kerneldata);
        float[][] kernerldata2d = new float[kernel.getHeight()][kernel.getWidth()];
        // System.out.println(kernerldata2d.length);
        // System.out.println(kernerldata2d[0].length);

        int index = 0;
        for (int i = 0; i < kernerldata2d.length; i++) {
            for (int j = 0; j < kernerldata2d[i].length; j++) {
                kernerldata2d[i][j] = kerneldata[index];
                index++;
                // System.out.println(kernerldata2d[i][j]);
            }
        }

        // System.out.println("emd");




        
        // BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());

        // // WITHOUT EXTENDING IMAGE.
        // float[][] Resultaf = new float[input.getWidth()][input.getHeight()];
        // float[][] Resultrf = new float[input.getWidth()][input.getHeight()];
        // float[][] Resultgf = new float[input.getWidth()][input.getHeight()];
        // float[][] Resultbf = new float[input.getWidth()][input.getHeight()];

        // int[][] Resulta = new int[input.getWidth()][input.getHeight()];
        // int[][] Resultr = new int[input.getWidth()][input.getHeight()];
        // int[][] Resultg = new int[input.getWidth()][input.getHeight()];
        // int[][] Resultb = new int[input.getWidth()][input.getHeight()];
        
        // for (int y = 0; y < input.getHeight(); y++) {
        //     for (int x = 0; x < input.getWidth(); x++) {
        //         Resultaf[x][y] = 0;
        //         Resultrf[x][y] = 0;
        //         Resultgf[x][y] = 0;
        //         Resultbf[x][y] = 0;
                
                


        //         for (int dy = -radius; dy <= radius; dy++) {
        //             for (int dx = -radius; dx <= radius; dx++) {
        //                 int ddx = dx;
        //                 int ddy = dy;

        //                 if(x + dx<0){
        //                     ddx = 0;
        //                 }

        //                 if(y + dy < 0){
        //                     ddy = 0;
        //                 }

        //                 if(input.getWidth() <= x + dx ){
        //                     ddx = 0;
        //                 }

        //                 if(input.getHeight()<= y + dy){
        //                     ddy = 0;
        //                 }

        //                 Color c = new Color(input.getRGB(x + ddx, y + ddy));

                       
        //                 Resultaf[x][y] = c.getAlpha();
                   
        //                 Resultrf[x][y] += kernerldata2d[radius + ddx][radius + ddy] * c.getRed();
        //                 Resultgf[x][y] += kernerldata2d[radius + ddx][radius + ddy] * c.getGreen();
        //                 Resultbf[x][y] += kernerldata2d[radius + ddx][radius + ddy] * c.getBlue();
        //             }
        //         }

                
        //         // Cases for Negative results
        //         Resultrf[x][y] = (float)Resultrf[x][y] / 2.f + 127.f;
        //         Resultgf[x][y] = (float)Resultgf[x][y] / 2.f + 127.f;
        //         Resultbf[x][y] = (float)Resultbf[x][y] / 2.f + 127.f;
              
        //         Color c = new Color(input.getRGB(x, y));

        //         Resulta[x][y] = c.getAlpha();
        //         Resultr[x][y] = (int) Math.min(Math.max(Resultrf[x][y], 0), 255);
        //         Resultg[x][y] = (int) Math.min(Math.max(Resultgf[x][y], 0), 255);
        //         Resultb[x][y] = (int) Math.min(Math.max(Resultbf[x][y], 0), 255);



        //         int argb;
        //         argb = (Resulta[x][y] << 24) | (Resultr[x][y] << 16) | (Resultg[x][y] << 8) | (Resultb[x][y]);
                
        //         output.setRGB(x, y, argb);


        //     }
        // }


        // return output;




        // Expand the image.
        ExtendingFilters expand = new ExtendingFilters(input, radius);

        BufferedImage expanded = expand.extending();

        BufferedImage output = new BufferedImage(expanded.getWidth(), expanded.getHeight(), expanded.getType());
        
        // BufferedImage output = new BufferedImage(expanded.getColorModel(),expanded.copyData(null),expanded.isAlphaPremultiplied(),null);


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

                        Color c = new Color(expanded.getRGB(x + dx, y + dy),true);
                        
                        
                        Resultrf[x][y] += kernerldata2d[radius + dx][radius + dy] * c.getRed();
                        Resultgf[x][y] += kernerldata2d[radius + dx][radius + dy] * c.getGreen();
                        Resultbf[x][y] += kernerldata2d[radius + dx][radius + dy] * c.getBlue();
                    }
                }


                // Cases for Negative results
                Resultrf[x][y] = (float)Resultrf[x][y] / 2.f + 127.f;
                Resultgf[x][y] = (float)Resultgf[x][y] / 2.f + 127.f;
                Resultbf[x][y] = (float)Resultbf[x][y] / 2.f + 127.f;
               
                Color c = new Color(expanded.getRGB(x, y),true);
                // expanded.getGraphics().getColor().getAlpha();

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

/*
        //          * MAYBE TRY EXTENDING EDGes within 
        //          * if y>input.getheight{
        //          *     input.getheight -1 
        //          * }
        //          * 
        //          * if y<0 {
        //          * y = 0}
        //          * 
        //          */

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
