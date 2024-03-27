package cosc202.andie;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * <p>
 * Actions provided by the Languages menu.
 * </p>
 * 
 * <p>
 * The Language menu contains actions that affect how the text is displayed in the
 * application, currently showing English (native) and a Māori translation, when 
 * the other is selected the user is prompted to restart the application.
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
public class LanguageActions {

    protected ArrayList<Action> actions;

    public LanguageActions() {
        actions = new ArrayList<Action>();
        actions.add(new EnglishAction("English", null, Andie.bundle.getString("language") + ": English",
                Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new TeReoAction("TeReo", null, Andie.bundle.getString("language") + ": Te Reo",
                Integer.valueOf(KeyEvent.VK_T)));
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
                    // Andie.bundle.getString("YouMustExitApplication") + " \n\t\t\t\t\t\t "
                    //         + Andie.bundle.getString("and") + "\n" + Andie.bundle.getString("ReopenToApplyChanges"),
                    // "Continue?",
                    // JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
                    Andie.bundle.getString("ReopenToApplyChanges"),
                    Andie.bundle.getString("continue"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                try {
                    Andie.prefs.put("language", "en");
                    Andie.prefs.put("country", "NZ");
                } catch (Exception ex) {
                    ex.printStackTrace();
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
                    // Andie.bundle.getString("YouMustExitApplication") + " \n\t\t\t\t\t\t "
                    //         + Andie.bundle.getString("and") + "\n" + Andie.bundle.getString("ReopenToApplyChanges"),
                    // "Continue?",
                    // JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
                    Andie.bundle.getString("ReopenToApplyChanges"),
                    Andie.bundle.getString("continue"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                try {
                    Andie.prefs.put("language", "mi");
                    Andie.prefs.put("country", "NZ");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }

    }

}
