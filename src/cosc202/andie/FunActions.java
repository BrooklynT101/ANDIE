package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FunActions {

    // Static fields
    public static Stack<ImageOperation> originalOperations;
    public static boolean isRunning;

    // Data Field
    protected ArrayList<Action> funMenuActions;

    /**
     * <p>
     * Create a set of Macro menu actions.
     * </p>
     */
    public FunActions() {
        // initializing actions and action list
        funMenuActions = new ArrayList<>();
        ConfettiAction confetti = new ConfettiAction(Andie.bundle.getString("Confetti!"), null,
                Andie.bundle.getString("Celebrategoodtimes!"), null);
        QnQAction qnq = new QnQAction(Andie.bundle.getString("QnQEasteregg"), null,
                Andie.bundle.getString("QuackingQwazy!"), null);

        // Andie.bundle.getString("Quacking Qwazy!")

        // adding actions to list
        funMenuActions.add(confetti);
        funMenuActions.add(qnq);
    }

    /**
     * <p>
     * Create a menu containing the list of Macro actions.
     * </p>
     * 
     * @return The Macro menu UI element.
     */
    public JMenu createMenu() {
        JMenu funMenu = new JMenu(Andie.bundle.getString("FunMenu"));
        for (Action action : funMenuActions) {
            funMenu.add(new JMenuItem(action));
        }
        return funMenu;
    }

    /**
     * Getter method for the macro menu actions
     * 
     * @return
     */
    public ArrayList<Action> getActions() {
        return this.funMenuActions;
    }

    public class ConfettiAction extends ImageAction {

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
        ConfettiAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            Andie.activateConfetti();
        }
    }

    public class QnQAction extends ImageAction {

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
        QnQAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            Andie.activateQNQEgg();
        }
    }

}