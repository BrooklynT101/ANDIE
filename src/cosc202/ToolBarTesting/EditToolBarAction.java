package cosc202.ToolBarTesting;

import javax.swing.*;
import java.awt.event.ActionEvent;
import cosc202.andie.*;

public class EditToolBarAction extends ImageAction {

    private CustomToolBar toolBar;

    /**
         * <p>
         * Create a new edit toolbar action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        EditToolBarAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
    
    /**
     * Callback for when the edit toolbar action is triggered.
     * 
     * @param e The event triggering this callback.
     */
    public void actionPerformed(ActionEvent e) {
        toolBar.showEditDialog();
        toolBar.repaint();
        toolBar.getParent().revalidate();
    }
}
