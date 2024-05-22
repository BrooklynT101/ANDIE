package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;

/**
 * <p>
 * Actions provided by the Image menu.
 * </p>
 * 
 * <p>
 * This contain the list of action or operations to be performed on a given
 * image.
 * Allows a user to edit an image using a variety of different actions.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Queeze and Quackers
 * @version 1.0
 */
public class ImageActions {

    protected static ArrayList<Action> actions;

    public ImageActions() {
        // initialising actions and action list
        actions = new ArrayList<Action>();
        RotateClockwiseAction clock = new RotateClockwiseAction(Andie.bundle.getString("RotateClockwise"), null,
                Andie.bundle.getString("RotateClockwise"), Integer.valueOf(KeyEvent.VK_C));
        RotateAnticlockwiseAction anticlock = new RotateAnticlockwiseAction(
                Andie.bundle.getString("RotateAnticlockwise"), null,
                Andie.bundle.getString("RotateAnticlockwise"), Integer.valueOf(KeyEvent.VK_A));
        Rotate180Action clock180 = new Rotate180Action(Andie.bundle.getString("Rotate180"), null,
                Andie.bundle.getString("Rotate180"), Integer.valueOf(KeyEvent.VK_C));
        ImageFlipVerticalAction vertFlip = new ImageFlipVerticalAction(Andie.bundle.getString("VerticalFlip"), null,
                Andie.bundle.getString("Flipsimagevertically"),
                Integer.valueOf(KeyEvent.VK_R));
        ImageFlipHorizontalAction horiFlip = new ImageFlipHorizontalAction(Andie.bundle.getString("HorizontalFlip"),
                null,
                Andie.bundle.getString("Flipsimagehorizontally"),
                Integer.valueOf(KeyEvent.VK_R));
        ImageResizeAction resize = new ImageResizeAction(Andie.bundle.getString("ImageResize"), null,
                Andie.bundle.getString("ResizeImage"), Integer.valueOf(KeyEvent.VK_R));
        CropFunctionAction crop = new CropFunctionAction(Andie.bundle.getString("CropImage"), null,
                Andie.bundle.getString("CropImage"), Integer.valueOf(KeyEvent.VK_C));
        DrawFunctionsAction draw = new DrawFunctionsAction(Andie.bundle.getString("DrawFunction"), null,
                Andie.bundle.getString("DrawFunction"), Integer.valueOf(KeyEvent.VK_C));
        DifferenceofImagesAction dif = new DifferenceofImagesAction(Andie.bundle.getString("DifferencImage"), null,
                Andie.bundle.getString("DifferencImage"), Integer.valueOf(KeyEvent.VK_R));
        ErasorFunctionAction er = new ErasorFunctionAction(Andie.bundle.getString("Erasor"), null, Andie.bundle.getString("Applyerasor"), 
                Integer.valueOf(KeyEvent.VK_R));

                // Andie.bundle.getString("Applyerasor")

        // keyboard shortcuts
        resize.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control R"));

        // adding actions to list
        actions.add(clock);
        actions.add(anticlock);
        actions.add(clock180);
        actions.add(vertFlip);
        actions.add(horiFlip);
        actions.add(resize);
        actions.add(dif);
        actions.add(crop);
        actions.add(draw);
        actions.add(er);

    }

    public JMenu createMenu() {
        JMenu editMenu = new JMenu(Andie.bundle.getString("Image"));
        for (Action action : actions) {
            editMenu.add(new JMenuItem(action));
        }
        return editMenu;
    }

    /**
     * Rotates an images 90° Clockwise
     * 
     * @author Amelia
     */
    public class RotateClockwiseAction extends ImageAction {

        RotateClockwiseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            // create and apply the filter
            target.getImage().apply(new ImageRotation(true));
            target.repaint();
            target.getParent().revalidate();

        }
    }

    /**
     * Rotates an images 90° AntiClockwise
     * 
     * @author Amelia
     */
    public class RotateAnticlockwiseAction extends ImageAction {

        RotateAnticlockwiseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            // create and apply the filter
            target.getImage().apply(new ImageRotation(false));
            target.repaint();
            target.getParent().revalidate();

        }
    }

    /**
     * Rotates an images 180°
     * 
     * @author Amelia
     */
    public class Rotate180Action extends ImageAction {

        Rotate180Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            // create and apply the filter
            target.getImage().apply(new ImageRotation(true, true));
            target.repaint();
            target.getParent().revalidate();

        }
    }

    /**
     * Flips an image vertically
     * 
     * @author BrooklynT
     */
    public class ImageFlipVerticalAction extends ImageAction {

        ImageFlipVerticalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ImageFlipVertical());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * Flips an image horizontally
     * 
     * @author BrooklynT
     */
    public class ImageFlipHorizontalAction extends ImageAction {

        ImageFlipHorizontalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ImageFlipHorizontal());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * Resizes an image
     * 
     * @author Ari
     */
    public class ImageResizeAction extends ImageAction {

        ImageResizeAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Determine the height and width - ask the user.
            int scale = 100;

            // Pop-up dialog box to ask for the scale value.
            SpinnerNumberModel scaleModel = new SpinnerNumberModel(100, 1, 1000, 4);
            JSpinner scaleSpinner = new JSpinner(scaleModel);
            // Prevents adding no 1 - 10 integers, by making the text field un editable by
            // default.
            ((DefaultEditor) scaleSpinner.getEditor()).getTextField().setEditable(false);
            int option1 = JOptionPane.showOptionDialog(null, scaleSpinner, Andie.bundle.getString("EnterPercentScaled"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (target.getImage().getOriginalImage() == null) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));

            }

            // Check the return value from the dialog box.
            if (option1 == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option1 == JOptionPane.OK_OPTION) {
                scale = scaleModel.getNumber().intValue();
            }

            // Create and apply the filter
            int ogWidth = target.getImage().getOriginalImage().getWidth();
            int ogheight = target.getImage().getOriginalImage().getHeight();
            target.getImage()
                    .apply(new ImageResize(scale, scale, ogWidth, ogheight, target.getImage().getOriginalImage()));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * Subtracts an Image called different from active image.
     * 
     * @author Ari
     */
    public class DifferenceofImagesAction extends ImageAction {

        DifferenceofImagesAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            JFileChooser fileChooser = new JFileChooser();

            int result = fileChooser.showOpenDialog(target);

            BufferedImage different = null;

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    File imageFile = new File(imageFilepath);
                    different = ImageIO.read(imageFile);

                } catch (Exception ex) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleasechooseanimagefile!"));
                }
            }

            if (different == null && result != JFileChooser.CANCEL_OPTION) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleasechooseanimagefile!"));

            }
            target.getImage()
                    .apply(new DifferenceofImages(different));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * Getter method to get the actions for the colour actions
     * 
     * @return all available actions
     */
    public ArrayList<Action> getActions() {
        ArrayList<Action> allActions = actions;
        return allActions;
    }

    /**
     * Crops an image
     * 
     * @author Marcus
     */
    public class CropFunctionAction extends ImageAction {

        CropFunctionAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            if (target.getImage().getOriginalImage() == null) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));
            } else {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, Andie.bundle.getString("CropInstructions"));
            }
            DynamicDrawTest mouseEar = target.getMouseEar();
            // MouseEar mouseEar = target.getMouseEar();

            mouseEar.setCrop(true);
            mouseEar.setDraw(false);

            mouseEar.imagecopy = target.getImage().returnAcopy(target.getImage().getCurrentImage());
            mouseEar.image = target.getImage().getCurrentImage();

            mouseEar.imageWidth = target.getImage().getOriginalImage().getWidth();
            mouseEar.imageHeight = target.getImage().getOriginalImage().getWidth();

        }

    }

    /**
     * Draws on an image
     * 
     * @author Marcus
     */
    public class DrawFunctionsAction extends ImageAction {

        DrawFunctionsAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            if (target.getImage().getOriginalImage() == null) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));
            } else {
                JFrame drawFrame = new JFrame("DrawingOptions");
                DrawingOptionsDisplay display = new DrawingOptionsDisplay();

                drawFrame.add(display);
                drawFrame.pack();
                drawFrame.setVisible(true);

                DynamicDrawTest mouseEar = target.getMouseEar();
                    
                // disallow drawing operations when closed
                drawFrame.addWindowListener(new WindowAdapter() {

                    public void windowClosing(WindowEvent e) {
                        mouseEar.setDraw(false);
                    }
                });

                // send information of current buffered image to mouse listener
                mouseEar.setDraw(true);
                // mouseEar.setCrop(false);

                mouseEar.imagecopy = target.getImage().returnAcopy(target.getImage().getCurrentImage());
                mouseEar.image = target.getImage().getCurrentImage();
                mouseEar.imageWidth = target.getImage().getOriginalImage().getWidth();
                mouseEar.imageHeight = target.getImage().getOriginalImage().getWidth();

                mouseEar.drawOptions = display;
            }
        }
    }

    /**
     * Erasor for the image
     * 
     * @author Ari
     */
    public class ErasorFunctionAction extends ImageAction {

        ErasorFunctionAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            if (target.getImage().getOriginalImage() == null) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));
            }
            DynamicDrawTest mouseEar = target.getMouseEar();

            mouseEar.setErasor(true);

            mouseEar.imagecopy = target.getImage().returnAcopy(target.getImage().getCurrentImage());
            mouseEar.image = target.getImage().getCurrentImage();
            mouseEar.imageWidth = target.getImage().getOriginalImage().getWidth();
            mouseEar.imageHeight = target.getImage().getOriginalImage().getHeight();

        }

    }

}
