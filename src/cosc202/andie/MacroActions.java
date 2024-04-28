package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class MacroActions {

    // Static fields
    public static Stack<ImageOperation> originalOperations;
    public static boolean isRunning;

    // Data Field
    protected ArrayList<Action> macroMenuActions;
    protected boolean testing = false;

    /**
     * <p>
     * Create a set of Macro menu actions.
     * </p>
     */
    public MacroActions() {
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

    // TODO: implement this, add macro quick action to menu

    /**
     * Getter method for the macro menu actions
     * 
     * @return
     */
    public ArrayList<Action> getActions() {
        return this.macroMenuActions;
    }

    public static Stack<ImageOperation> deepCopy(Stack<ImageOperation> operations) {
        Stack<ImageOperation> copy = new Stack<>();

        for (ImageOperation operation : operations) {

            // Clone each ImageOperation and push it onto the copy stack
            copy.push(operation.clone());
        }

        // Reverse the order of the copy stack to match the og order
        Collections.reverse(copy);
        return copy;
    }

    /**
     * <p>
     * Action to load an existing macro from file and apply to image.
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

                    // apply the macro
                    // TODO: change this to be a singular action similar to how the blur function
                    // works
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
     * Action to start recording the users actions
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

        // TODO: make it so if the macro is recording and start is called again it
        // simply stops it
        public void actionPerformed(ActionEvent e) {
            if (isRunning) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, Andie.bundle.getString("MacroAlreadyRecording"));
            } else { // its not running
                isRunning = true;

                // get the current operations
                MacroActions.originalOperations = MacroActions.deepCopy(target.getImage().getCurrentOperations());
                if (testing)
                    System.out.println("The grabbed operations:" + originalOperations);
                // tell user
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, Andie.bundle.getString("MacroRecording"));

            }
        }

    }

    /**
     * <p>
     * Action to stop the macro recording and save it.
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
            if (isRunning == false) {
                System.err.println("Macro not running"); // TODO: add an actual prompt here?
                return;
            } else {
                isRunning = false;
            }
            // create a deepcopy of the operations, else it'll just mess with the images
            // operations
            Stack<ImageOperation> newOperations = MacroActions.deepCopy(target.getImage().getCurrentOperations());
//TODO: if newOps is empty then the message just says ayo
            if (testing)
                System.out.println("Original Operations: " + MacroActions.originalOperations.toString());

            if (newOperations.size() == MacroActions.originalOperations.size()) {
                if (testing)
                    System.out.println("Lists are same size");
            } else {
                if (testing)
                    System.out.println("New Operations (before loop): " + newOperations.toString());
                for (int i = 0; i < MacroActions.originalOperations.size(); i++) {
                    newOperations.pop();
                }
            }
            if (testing)System.out.println("New Operations (after loop): " + newOperations.toString());
            
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
        public void saveMacro(Stack<ImageOperation> macro) {
            //System.out.println("Wow didnt actually anything but if it did that'd be cool");
            JFileChooser fileChooser = new JFileChooser();

            // Ask user where to save
            fileChooser.setDialogTitle("Save Macro");
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                try {
                    String macroFilepath = fileChooser.getSelectedFile().getCanonicalPath() + ".macro"; // adding macro
                                                                                                        // extension

                    // Write macro file
                    FileOutputStream fileOut = new FileOutputStream(macroFilepath);
                    ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
                    objOut.writeObject(macro);
                    objOut.close();
                    fileOut.close();

                    // Saved
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, Andie.bundle.getString("SavedMacro"));

                } catch (Exception ex) {
                    System.err.println("Could not save the macro actions:\n" + macro.toString());
                    ex.printStackTrace();
                }
            } else {
                // User canceled or closed the dialog
                System.out.println("Save operation cancelled.");
            }
        }

    }

    public String buildActionListMessage(Stack<ImageOperation> recordedActions) {
        if (recordedActions.isEmpty()) {
            return "No actions recorded.";
        } else {
            StringBuilder message = new StringBuilder("Recorded actions:\n");
            Collections.reverse(recordedActions);
            int count = 1;
            for (ImageOperation operation : recordedActions) {
                message.append(count + ". ").append(operation.getName()).append("\n");
                count++;
            }
            return message.toString();
        }
    }

}