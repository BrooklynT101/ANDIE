package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;
import java.awt.Window;
import java.awt.event.*;
import javax.swing.*;

public class LanguageActions {

    protected ArrayList<Action> actions;

    public LanguageActions() {
        actions = new ArrayList<Action>();
        actions.add(new EnglishAction("English", null, Andie.bundle.getString("language") + ": English",
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new TeReoAction("TeReo", null, Andie.bundle.getString("language") + ": Te Reo",
                Integer.valueOf(KeyEvent.VK_M)));
    }

    /**
     * <p>
     * Create a menu containing the list of Languages.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu languageMenu = new JMenu(Andie.bundle.getString("languages"));

        for (Action action : actions) {
            languageMenu.add(new JMenuItem(action));
        }

        return languageMenu;
    }

    public class EnglishAction extends ImageAction {

        EnglishAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            int option = JOptionPane.showConfirmDialog(null,
                    "Restart Application To Apply Changes \n\s\s\s\s\s\s\s\s\s\s\s\s\s\s\s\s\s\s\s\s\s\s\s\s\sApply?",
                    "Continue?",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                try {
                    Andie.prefs.put("language", "en");
                    Andie.prefs.put("country", "NZ");
                } catch (Exception q) {

                }

            }

        }

    }

    /*
     * public class FrenchAction extends ImageAction {
     * 
     * FrenchAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
     * super(name, icon, desc, mnemonic);
     * }
     * 
     * public void actionPerformed(ActionEvent e) {
     * 
     * Andie.prefs.put("language", "mi");
     * Andie.prefs.put("country", "NZ");
     * 
     * }
     * 
     * }
     */

    public class TeReoAction extends ImageAction {

        TeReoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.showConfirmDialog(null,
                    "You Must Exit Application \n\s\s\s\s\s\s\s\s\s\s\s\s\s\s\s\s\s and \nReopen To Apply Changes",
                    "Continue?",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                try {
                    Andie.prefs.put("language", "mi");
                    Andie.prefs.put("country", "NZ");
                } catch (Exception q) {

                }

            }
        }

    }

}
