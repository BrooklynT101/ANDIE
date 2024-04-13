package cosc202.andie;

import java.util.*;
import java.awt.event.*;
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

    protected ArrayList<Action> actions;

    public ImageActions() {
        // initialising actions and action list
        actions = new ArrayList<Action>();
        RotateClockwiseAction clock = new RotateClockwiseAction(Andie.bundle.getString("RotateClockwise"), null,
                Andie.bundle.getString("RotateClockwise"), Integer.valueOf(KeyEvent.VK_C));
        RotateAnticlockwiseAction anticlock = new RotateAnticlockwiseAction(
                Andie.bundle.getString("RotateAnticlockwise"), null,
                Andie.bundle.getString("RotateAnticlockwise"), Integer.valueOf(KeyEvent.VK_A));
        ImageFlipVerticalAction vertFlip = new ImageFlipVerticalAction(Andie.bundle.getString("VerticalFlip"), null,
                Andie.bundle.getString("Flipsimagevertically"),
                Integer.valueOf(KeyEvent.VK_R));
        ImageFlipHorizontalAction horiFlip = new ImageFlipHorizontalAction(Andie.bundle.getString("HorizontalFlip"),
                null,
                Andie.bundle.getString("Flipsimagehorizontally"),
                Integer.valueOf(KeyEvent.VK_R));
        ImageResizeAction resize = new ImageResizeAction(Andie.bundle.getString("ImageResize"), null,
                Andie.bundle.getString("ResizeImage"), Integer.valueOf(KeyEvent.VK_R));

        // adding actions to list
        actions.add(clock);
        actions.add(anticlock);
        actions.add(vertFlip);
        actions.add(horiFlip);
        actions.add(resize);

        // keyboard shortcuts
        resize.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control R"));
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

}
