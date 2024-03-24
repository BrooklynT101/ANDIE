package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class ImageActions {

    protected ArrayList<Action> actions;

    public JMenu createMenu() {
        JMenu editMenu = new JMenu("Image");

        for (Action action: actions) {
            editMenu.add(new JMenuItem(action));
        }

        return editMenu;
    }

    public ImageActions() {
        actions = new ArrayList<Action>();
        actions.add(new RotateClockwiseAction("Rotate Clockwise", null, "Rotate Clockwise", Integer.valueOf(KeyEvent.VK_C)));
        actions.add(new RotateAnticlockwiseAction("Rotate Anticlockwise", null, "Rotate Anticlockwise", Integer.valueOf(KeyEvent.VK_A)));
    }

    public class RotateClockwiseAction extends ImageAction {

        RotateClockwiseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) { 

            //create and apply the filter 
            target.getImage().apply(new ImageRotation(true));
            target.repaint();
            target.getParent().revalidate();
    
        }
    }

    public class RotateAnticlockwiseAction extends ImageAction {

        RotateAnticlockwiseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) { 

            //create and apply the filter 
            target.getImage().apply(new ImageRotation(false));
            target.repaint();
            target.getParent().revalidate();

    
        }
    }
    
}
