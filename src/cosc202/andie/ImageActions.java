package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class ImageActions {

    protected ArrayList<Action> actions;

    public ImageActions() {
        actions = new ArrayList<Action>();
        actions.add(new ImageRotationAction("Image Rotation", null, "Image Rotation", Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new ImageFlipVerticalAction("Vertical Flip", null, "Flips image vertically",
                Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new ImageFlipHorizontalAction("Horizontal Flip", null, "Flips image horizontally",
                Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new ImageResizeAction("Image Resize", null, "Resize Image", Integer.valueOf(KeyEvent.VK_R)));
    }

    public JMenu createMenu() {
        JMenu editMenu = new JMenu("Image");

        for (Action action : actions) {
            editMenu.add(new JMenuItem(action));
        }

        return editMenu;
    }

    public class ImageRotationAction extends ImageAction {

        ImageRotationAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ImageRotation());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * 
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
     * 
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
            int option1 = JOptionPane.showOptionDialog(null, scaleSpinner, "Enter Percent Scaled",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

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
