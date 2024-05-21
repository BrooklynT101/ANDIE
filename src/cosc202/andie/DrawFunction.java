package cosc202.andie;

import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawFunction implements ImageOperation, java.io.Serializable {

  int regionHeight;
  int regionWidth;
  int xStart;
  int yStart;
  int xEnd;
  int yEnd;
  Color colour;
  String shape;
  String style;
  int lineWidth;

  DrawFunction(int xStart, int yStart, int regionWidth, int regionHeight,
      Color colour, String shape, String style, int lineWidth) {

    this.xStart = xStart;
    this.yStart = yStart;
    this.regionWidth = regionWidth;
    this.regionHeight = regionHeight;
    this.colour = colour;
    this.shape = shape;
    this.style = style;
    this.lineWidth = lineWidth;

  }

  public BufferedImage apply(BufferedImage input) {

    Graphics2D g2d = (Graphics2D) input.getGraphics();
    g2d.setColor(colour);

    g2d.setStroke(new BasicStroke(lineWidth));

    if (shape.equals("Rectangle")) {
      if (style.equals("Filled")) {
        g2d.fillRect(xStart, yStart, regionWidth, regionHeight);
      } else {
        g2d.drawRect(xStart, yStart, regionWidth, regionHeight);
      }

    } else if (shape.equals("Oval")) {
      if (style.equals("Filled")) {
        g2d.fillOval(xStart, yStart, regionWidth, regionHeight);
      } else {
        g2d.drawOval(xStart, yStart, regionWidth, regionHeight);
      }

    } else if (shape.equals("Line")) {
      //g2d.setStroke(new BasicStroke(lineWidth));
      g2d.drawLine(xStart, yStart, (xStart + regionWidth), (yStart + regionHeight));

    }
    return input;
  }

}
