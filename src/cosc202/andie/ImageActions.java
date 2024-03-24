package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class ImageActions {

    protected ArrayList<Action> actions;

    public ImageActions() {
        actions = new ArrayList<Action>();
        actions.add(new ImageResizeAction("Image Resize", null, "Resize Image", Integer.valueOf(KeyEvent.VK_R)));






    }

    public JMenu createMenu() {
        JMenu editMenu = new JMenu("Image");
        for (Action action : actions) {
            editMenu.add(new JMenuItem(action));
        }
        return editMenu;
    }
    
    /**
     * Resizing an image by a value given by the user
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
