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

}
