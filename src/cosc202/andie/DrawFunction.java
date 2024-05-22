package cosc202.andie;

import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;

public class DrawFunction implements ImageOperation, java.io.Serializable {

  int regionXStart;
  int regionYStart;
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

  /**
   * <p>
   * Construct a draw Function to be performed.
   * </p
   * >
   * <p>
   * Passes information to data fields about the operation.
   * </p>
   * 
   * @param xStart       base x coordinate of line draw
   * @param yStart       base y coordinate of line draw
   * @param xEnd         end x coordinate of line draw
   * @param yEnd         end y coordinate of line draw
   * @param regionXStart base x coordinate of rectangle/oval draw
   * @param regionYStart base y coordinate of rectangle/oval draw
   * @param regionWidth  width of rectangle/oval draw
   * @param regionHeight height of rectangle/oval draw
   * @param colour       colour of draw operation
   * @param shape        desired shape to be drawn
   * @param style        fill/outline
   * @param lineWidth    stroke width
   */
  DrawFunction(int xStart, int yStart, int xEnd, int yEnd,
      int regionXStart, int regionYStart, int regionWidth, int regionHeight,
      Color colour, String shape, String style, int lineWidth) {

    this.xStart = xStart;
    this.yStart = yStart;
    this.xEnd = xEnd;
    this.yEnd = yEnd;
    this.regionWidth = regionWidth;
    this.regionHeight = regionHeight;
    this.regionXStart = regionXStart;
    this.regionYStart = regionYStart;
    this.colour = colour;
    this.shape = shape;
    this.style = style;
    this.lineWidth = lineWidth;

  }

  /**
   * <p>
   * Apply a draw Function to an image.
   * </p>
   * 
   * @param input The image to apply the Draw Function to.
   * @return The resulting image with added 2d graphics.
   */
  public BufferedImage apply(BufferedImage input) {

    Graphics2D g2d = (Graphics2D) input.getGraphics();
    g2d.setColor(colour);

    g2d.setStroke(new BasicStroke(lineWidth));

    if (shape.equals("Rectangle")) {
      if (style.equals("Filled")) {
        g2d.fillRect(regionXStart, regionYStart, regionWidth, regionHeight);
      } else {
        g2d.drawRect(regionXStart, regionYStart, regionWidth, regionHeight);
      }

    } else if (shape.equals("Oval")) {
      if (style.equals("Filled")) {
        g2d.fillOval(regionXStart, regionYStart, regionWidth, regionHeight);
      } else {
        g2d.drawOval(regionXStart, regionYStart, regionWidth, regionHeight);
      }

    } else if (shape.equals("Line")) {
      g2d.drawLine(xStart, yStart, xEnd, yEnd);

    }
    return input;
  }

  @Override
  public String getName() {
    return "Draw Function";
  }

  /**
   * Method to return 'this' as an ImageOperation to support @see MacroActions
   */
  @Override
  public ImageOperation clone() {
    DrawFunction copy = new DrawFunction(xStart, yStart, xEnd, yEnd,
        regionXStart, regionYStart, regionWidth, regionHeight,
        colour, shape, style, lineWidth);
    return copy;
  }
}
