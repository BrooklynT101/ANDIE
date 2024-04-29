package cosc202.ToolBarTesting;

import javax.swing.*;
import java.awt.event.ActionEvent;
import cosc202.andie.ImageAction;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MagicToolBarApp {
    public static void main(String[] args) {
        createAndShowGUI();

    }

    public static void createAndShowGUI() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Custom ToolBar");
            JMenuBar menuBar = new JMenuBar();
            MagicToolBar toolBar = new MagicToolBar();
            JButton editButton = new JButton(
                    new EditMagicToolBarAction("EditToolBar", null, "EditToolBar", Integer.valueOf(KeyEvent.VK_Z), toolBar));

            menuBar.add(editButton);
            frame.setJMenuBar(menuBar);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(toolBar, BorderLayout.NORTH);
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

class EditMagicToolBarAction extends ImageAction {

    //private MagicToolBar toolBar;

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
    EditMagicToolBarAction(String name, ImageIcon icon, String desc, Integer mnemonic, MagicToolBar toolBar) {
        super(name, icon, desc, mnemonic);
    }

    /**
     * Callback for when the edit toolbar action is triggered.
     * 
     * @param e The event triggering this callback.
     */
    public void actionPerformed(ActionEvent e) {
        MagicToolBar toolBar = new MagicToolBar(); // Create a new MagicToolBar instance
        toolBar.showEditDialog();
        toolBar.repaint();
        if (toolBar.getParent() != null) {
            toolBar.getParent().revalidate();
        }
    }
}
