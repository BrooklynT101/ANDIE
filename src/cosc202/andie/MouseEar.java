package cosc202.andie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class MouseEar implements MouseListener {

   ImagePanel ip;
   BufferedImage image;

   public static boolean isCropping;
   public static boolean isDrawing;
   public int xStart;
   public int yStart;
   public int xEnd;
   public int yEnd;
   public int imageWidth;
   public int imageHeight;
   public DrawingOptionsDisplay drawOptions;

   public void setCrop(Boolean crop) {
      isCropping = crop;

   }

   public void setDraw(Boolean draw) {
      isDrawing = draw;

   }

   MouseEar(ImagePanel ip) {
      this.ip = ip;
      ip.addMouseListener(this);

   }

   public void mouseClicked(MouseEvent e) {

   }

   public void mouseEntered(MouseEvent e) {

   }

   public void mouseExited(MouseEvent e) {

   }

   public void mousePressed(MouseEvent e) {

      if (e.getX() <= imageWidth && e.getY() <= imageHeight) {

         if (isCropping || isDrawing) {
            xStart = e.getX();
            yStart = e.getY();

         }
      }

   }

   public void mouseReleased(MouseEvent e) {

      if (e.getX() <= imageWidth && e.getY() <= imageHeight) {

         if (isCropping || isDrawing) {
            xEnd = e.getX();
            yEnd = e.getY();

            // region location variables

            int regionXStart = 0;
            int regionYStart = 0;
            int regionWidth = 0;
            int regionHeight = 0;

            // draw proposed region and allow for 4 way selection
            Graphics2D g2d = (Graphics2D) image.getGraphics();
            g2d.setColor(Color.RED);
            if (xStart <= xEnd && yStart <= yEnd) { // top left -> bottom right

               if (isCropping) {
                  g2d.draw(new Rectangle(xStart, yStart, (xEnd - xStart), (yEnd - yStart)));
               } // (x, y, width, height)
               regionWidth = xEnd - xStart;
               regionHeight = yEnd - yStart;
               regionXStart = xStart;
               regionYStart = yStart;

            } else if (xStart >= xEnd && yStart <= yEnd) { // top right -> bottom left

               if (isCropping) {
                  g2d.draw(new Rectangle(xEnd, yStart, (xStart - xEnd), (yEnd - yStart)));
               } // only draw if cropping
               regionWidth = xStart - xEnd;
               regionHeight = yEnd - yStart;
               regionXStart = xEnd;
               regionYStart = yStart;

            } else if (xStart <= xEnd && yStart >= yEnd) { // bottom left -> top right

               if (isCropping) {
                  g2d.draw(new Rectangle(xStart, yEnd, (xEnd - xStart), (yStart - yEnd)));
               }
               regionWidth = xEnd - xStart;
               regionHeight = yStart - yEnd;
               regionXStart = xStart;
               regionYStart = yEnd;

            } else if (xStart >= xEnd && yStart >= yEnd) { // bottom right -> top left

               if (isCropping) {
                  g2d.draw(new Rectangle(xEnd, yEnd, (xStart - xEnd), (yStart - yEnd)));
               }
               regionWidth = xStart - xEnd;
               regionHeight = yStart - yEnd;
               regionXStart = xEnd;
               regionYStart = yEnd;

            }
            ip.repaint();
            ip.getParent().revalidate();

            System.out.println(xStart + " " + yStart + " " + xEnd + " " + yEnd);

            if (isCropping) {

               // display crop options dialogue
               Object[] options = { "Crop Image", "Cancel" };
               int choice = JOptionPane.showOptionDialog(null, null,
                     Andie.bundle.getString("Wouldyouliketocropthisimage?"),
                     JOptionPane.YES_NO_OPTION,
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     options, // the titles of buttons
                     options[0]); // default button title

               if (choice == 0) {
                  System.out.println("Pressed Crop");
                  if (isCropping) {
                     System.out.println(regionXStart + " " + regionYStart + " " + regionWidth + " " + regionHeight);
                     // perform crop
                     ip.getImage().apply(new CropFunction(regionXStart, regionYStart, regionWidth, regionHeight));
                     ip.repaint();
                     ip.getParent().revalidate();
                  }

               } else if (choice == 1) {
                  System.out.println("Pressed CANCEL");

               }

               setCrop(false);

            } else if (isDrawing) {

               ip.getImage()
                     .apply(new DrawFunction(xStart, yStart, xEnd, yEnd,
                           regionXStart, regionYStart, regionWidth, regionHeight,
                           drawOptions.getColour(), drawOptions.getSelectedShape(),
                           drawOptions.getSelectedStyle(), drawOptions.getLineWidth()));
               ip.repaint();
               ip.getParent().revalidate();

               // setDraw(false);
            }
         }
      }

   }
}