package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the File menu.
 * </p>
 * 
 * <p>
 * The File menu is very common across applications,
 * and there are several items that the user will expect to find here.
 * Opening and saving files is an obvious one, but also exiting the program.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class FileActions {

    /** A list of actions for the File menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     */
    public FileActions() {
        // initialising actions and action list
        actions = new ArrayList<Action>();
        FileOpenAction open = new FileOpenAction(Andie.bundle.getString("Open"), null,
                Andie.bundle.getString("Openafile"), Integer.valueOf(KeyEvent.VK_O));
        FileDefaultAction defaultImage = new FileDefaultAction(Andie.bundle.getString("Default"), null,
                Andie.bundle.getString("DefaultDesc"), Integer.valueOf(KeyEvent.VK_D));
        FileSaveAction save = new FileSaveAction(Andie.bundle.getString("Save"), null,
                Andie.bundle.getString("Savethefile"), Integer.valueOf(KeyEvent.VK_S));
        FileSaveAsAction saveas = new FileSaveAsAction(Andie.bundle.getString("SaveAs"), null,
                Andie.bundle.getString("Saveacopy"), Integer.valueOf(KeyEvent.VK_A));
        FileExportAction export = new FileExportAction(Andie.bundle.getString("Export"), null,
                Andie.bundle.getString("Exportimage"), Integer.valueOf(KeyEvent.VK_A));
        FileExitAction exit = new FileExitAction(Andie.bundle.getString("Exit"), null,
                Andie.bundle.getString("Exittheprogram"), Integer.valueOf(0));

        // keyboard shortcuts
        open.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control O"));
        defaultImage.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control D"));
        save.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control S"));
        export.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control P"));
        exit.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control E"));

        // adding actions to list
        actions.add(open);
        actions.add(defaultImage);
        actions.add(save);
        actions.add(saveas);
        actions.add(export);
        actions.add(exit);
    }

    /**
     * <p>
     * Create a menu containing the list of File actions.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(Andie.bundle.getString("File"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }
    
    public ArrayList<Action> getActions() {
        return this.actions;
    }

    /**
     * <p>
     * Action to open an image from file.
     * </p>
     * 
     * @see EditableImage#open(String)
     */
    public class FileOpenAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-open action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileOpenAction is triggered.
         * It prompts the user to select a file and opens it as an image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();

            int result = fileChooser.showOpenDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().open(imageFilepath);
                } catch (Exception ex) {
                    // System.exit(1);
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleasechooseanimagefile!"));
                }
            }

            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to load a default image from resources.
     * </p>
     * 
     * @see EditableImage#open(String)
     */
    public class FileDefaultAction extends ImageAction {

        /**
         * <p>
         * Create a new default image action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileDefaultAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * 
         * <p>
         * This method is called whenever the LoadDefaultImageAction is triggered.
         * It grabs an image from the resources folder called
         * Otago_University_Clocktower.jpg, if the file isn't there or the name has been
         * changed it'll prompt and tell you.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                Andie.class.getClassLoader().getResource("icon.png");
                target.getImage().open("src/resources/Otago_University_Clocktower.jpg");
            } catch (Exception ex) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, Andie.bundle.getString("DefaultError"));
            }

            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to save an image to its current file location.
     * </p>
     * 
     * @see EditableImage#save()
     */
    public class FileSaveAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileSaveAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-save action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAction is triggered.
         * It saves the image to its original filepath.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().save();
            } catch (Exception ex) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));
            }
        }

    }

    /**
     * <p>
     * Action to save an image to a new file location.
     * </p>
     * 
     * @see EditableImage#saveAs(String)
     */
    public class FileSaveAsAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save-as action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileSaveAsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-save-as action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAsAction is triggered.
         * It prompts the user to select a file and saves the image to it.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save as"); // changed this to save as to be distinctive between save and export
            int result = fileChooser.showSaveDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().saveAs(imageFilepath);
                } catch (Exception ex) {
                    // System.exit(1);
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));
                }
            }
        }

    }

    /**
     * <p>
     * Action to export an image to a new file location.
     * <p>
     * 
     * <p>
     * This means to save the image file with all its edits, and no operations file
     * to accompany it
     * <p>
     * 
     * @see EditableImage#saveAs(String)
     * @author Brooklyn
     */
    public class FileExportAction extends ImageAction {

        /**
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileExportAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * Prompts the user to select a file, enter a filename, and exports the image
         * file to it.
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Set the default directory for the "Export" dialog box
            // fileChooser.setSelectedFile(new File());

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle(Andie.bundle.getString("export"));
            int result = fileChooser.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().export(imageFilepath);
                } catch (Exception ex) {
                    System.err.println("Couldn't export file" + "\n");
                    ex.printStackTrace();
                    // System.exit(1);
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, Andie.bundle.getString("CouldntExport\n" + ex));
                    // JOptionPane.showMessageDialog(frame,
                    // Andie.bundle.getString("CouldntExport"));
                }
            }
        }

    }

    /**
     * <p>
     * Action to quit the ANDIE application.
     * </p>
     */
    public class FileExitAction extends AbstractAction {

        /**
         * <p>
         * Create a new file-exit action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileExitAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-exit action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExitAction is triggered.
         * It quits the program.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

}
