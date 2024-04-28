package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.*;

public class MacroActions {

    // Static fields
    public static Stack<ImageOperation> originalOperations;
    public static boolean isRunning;

    // Data Field
    protected ArrayList<Action> macroMenuActions;

    /**
     * <p>
     * Create a set of Macro menu actions.
     * </p>
     */
    public MacroActions() {
        // defs to bundle
        // initializing actions and action list
        macroMenuActions = new ArrayList<Action>();
        MacroLoadAction mLoad = new MacroLoadAction(Andie.bundle.getString("MacroLoad"), null,
                Andie.bundle.getString("MacroLoadDef"), Integer.valueOf(KeyEvent.VK_L));
        MacroStartRecording mStart = new MacroStartRecording(Andie.bundle.getString("MacroStart"), null,
                Andie.bundle.getString("MacroStartDef"), Integer.valueOf(KeyEvent.VK_S));
        MacroStopRecording mStop = new MacroStopRecording(Andie.bundle.getString("MacroStop"), null,
                Andie.bundle.getString("MacroStopDef"), Integer.valueOf(KeyEvent.VK_S));

        // keyboard shortcuts
        mLoad.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control O"));
        mStart.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control D"));
        mStop.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control S"));

        // adding actions to list
        macroMenuActions.add(mLoad);
        macroMenuActions.add(mStart);
        macroMenuActions.add(mStop);
    }

    /**
     * <p>
     * Create a menu containing the list of Macro actions.
     * </p>
     * 
     * @return The Macro menu UI element.
     */
    public JMenu createMenu() {
        JMenu macroMenu = new JMenu(Andie.bundle.getString("MacroMenu"));
        for (Action action : macroMenuActions) {
            macroMenu.add(new JMenuItem(action));
        }
        return macroMenu;
    }

    //TODO: implement this, add macro quick action to menu
    // public JMenu addMacroToMenu(Macro macro) {
    //     JMenu newMenu = new JMenu();
    //     JButton macroButton = new JButton(macro);
    //     macroMenuActions.add(macro);
    //     return createMenu();
    // }
    /**
     * Getter method for the macro menu actions
     * 
     * @return
     */
    public ArrayList<Action> getActions() {
        return this.macroMenuActions;
    }

    /**
     * <p>
     * Action to load an existing macro from file to the image.
     * </p>
     * 
     * @see EditableImage#open(String)
     */
    public class MacroLoadAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action. Extends ImageAction.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MacroLoadAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();

            int result = fileChooser.showOpenDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String macroFilePath = fileChooser.getSelectedFile().getCanonicalPath();
                    FileInputStream fileIn = new FileInputStream(macroFilePath);
                    ObjectInputStream objIn = new ObjectInputStream(fileIn);

                    // Silence the Java compiler warning about type casting.
                    // Understanding the cause of the warning has something
                    // to do with "type erasure" @see Open in EditableImage.
                    @SuppressWarnings("unchecked")
                    // Operations file is taken from a saved file (if any), and saved to a stack
                    // that holds ImageOperations
                    Stack<ImageOperation> opsFromFile = (Stack<ImageOperation>) objIn.readObject();

                    // apply the macro//TODO: look at this
                    for (ImageOperation operation : opsFromFile) {
                        target.getImage().apply(operation);
                    }

                    objIn.close();
                    fileIn.close();

                } catch (Exception ex) {
                    // Could be no file or something else. Carry on for now.
                    System.err.println("Could not open macro: " + ex);
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, Andie.bundle.getString("CantLoadMacro"));
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
    public class MacroStartRecording extends ImageAction {

        /**
         * <p>
         * Create a new MacroStartRecording action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MacroStartRecording(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
        // public void actionPerformed(ActionEvent e) {
        // Macro macro = new Macro(target.getImage());
        // }

        public void actionPerformed(ActionEvent e) {
            if (isRunning) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, Andie.bundle.getString("MacroAlreadyRecording"));
            } else {
                // its running
                isRunning = true;

                // get the current operations
                originalOperations = target.getImage().getCurrentOperations();
                System.out.println("The grabbed operations:" + originalOperations);
                // tell user
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, Andie.bundle.getString("MacroRecording"));

            }
        }

    }

    /**
     * <p>
     * Action to stop the macro recording and save it to the resources folder.
     * </p>
     * 
     * @see EditableImage#save()
     */
    public class MacroStopRecording extends ImageAction {

        /**
         * <p>
         * Create a new MacroStopRecording action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MacroStopRecording(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            isRunning = false;

            Stack<ImageOperation> newOperations = target.getImage().getCurrentOperations();

            System.out.println("Original Operations: " + originalOperations.toString());
            System.out.println("New Operations: " + newOperations.toString());

            for (int i = 0; i < originalOperations.size(); i++) {
                newOperations.remove(i);
            }
            // Prompt to save the macro
            int option = JOptionPane.showConfirmDialog(null, buildActionListMessage(newOperations),
                    "Save Macro", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                saveMacro(newOperations);
            }

        }

        /**
         * Save the macro to the file
         */
        public void saveMacro(Stack<ImageOperation> newOperations) {
            System.out.println("Wow didnt actually anything but if it did that'd be cool");
        }

        public String buildActionListMessage(Stack<ImageOperation> recordedActions) {
            if (recordedActions.isEmpty()) {
                return "No actions recorded.";
            } else {
                StringBuilder message = new StringBuilder("Recorded actions:\n");
                for (ImageOperation operation : recordedActions) {
                    message.append(operation.getName()).append("\n");
                }
                return message.toString();
            }
        }

    }
}