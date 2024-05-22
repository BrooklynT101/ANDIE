package cosc202.andie;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

public class DynamicDrawTest extends MouseInputAdapter {

    ImagePanel ip;
    BufferedImage image;
    BufferedImage imagecopy;

    public static boolean isCropping;
    public static boolean isDrawing;
    public static boolean isErasing;
    public int xStart;
    public int yStart;
    // private boolean startCounter = true;
    public int xEnd;
    public int yEnd;
    public int imageWidth;
    public int imageHeight;
    int regionXStart = 0;
    int regionYStart = 0;
    int regionWidth = 0;
    int regionHeight = 0;
    public DrawingOptionsDisplay drawOptions;

    public void setCrop(Boolean crop) {
        isCropping = crop;

    }

    public void setErasor(Boolean erasor) {
        isErasing = erasor;

    }

    public void setDraw(Boolean draw) {
        isDrawing = draw;

    }

    DynamicDrawTest(ImagePanel ip) {
        this.ip = ip;
        ip.addMouseListener(this);
        ip.addMouseMotionListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Implement mouseClicked event handling
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getX() <= imageWidth && e.getY() <= imageHeight) {

            if (isCropping || isDrawing) {
                xStart = e.getX();
                yStart = e.getY();

            }
        }

        imagecopy = ip.getImage().returnAcopy(ip.getImage().getCurrentImage());

    }

    @Override
    public void mouseReleased(MouseEvent e) {

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

                System.out.println(regionXStart + " " + regionYStart + " " + regionWidth + " " + regionHeight);
                // perform crop
                ip.getImage().apply(new CropFunction(regionXStart, regionYStart, regionWidth, regionHeight));
                ip.repaint();
                ip.getParent().revalidate();

            } else if (choice == 1) {
                image.getGraphics().drawImage(imagecopy, 0, 0, null);

                ip.repaint();
                ip.getParent().revalidate();

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

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Implement mouseEntered event handling
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Implement mouseExited event handling
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (e.getX() <= imageWidth && e.getY() <= imageHeight) {

            if (isCropping || isDrawing) {

                ip.invalidate();

                xEnd = e.getX();
                yEnd = e.getY();
            }
            // draw proposed region and allow for 4 way selection
            Graphics2D g2d = (Graphics2D) image.getGraphics();
            image.getGraphics().drawImage(imagecopy, 0, 0, null);
            // g2d.drawImage(imagecopy, 0, 0, null);
            // ip.repaint();
            // ip.getParent().revalidate();
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

            if (isDrawing) {
                // image.getGraphics().drawImage(imagecopy, 0, 0, null);

                DrawFunction d = new DrawFunction(xStart, yStart, xEnd, yEnd,
                        regionXStart, regionYStart, regionWidth, regionHeight,
                        drawOptions.getColour(), drawOptions.getSelectedShape(),
                        drawOptions.getSelectedStyle(), drawOptions.getLineWidth());

                d.temporarydraw(ip.getImage().getCurrentImage());
                ip.repaint();
                ip.getParent().revalidate();
            }

            // if(isFreeDrawing) {

            // }

            if (isErasing) {
                ip.getImage()
                        .apply(new ErasorFunction(e.getX(), e.getY()));
                System.out.println(e.getX() + "Erasor" + e.getY());

            }

        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Implement mouseMoved event handling
    }
}
