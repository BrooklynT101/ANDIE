package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;
import java.awt.event.*;
import javax.swing.*;

public class LanguageActions {

    protected ArrayList<Action> actions;

    public LanguageActions() {
        actions = new ArrayList<Action>();
        actions.add(new EnglishAction("English", null, "Language: English", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new TeReoAction("Te Reo", null, "Language: Te Reo", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new FrenchAction("French", null, "Language: French", Integer.valueOf(KeyEvent.VK_M)));

    }

    /**
     * <p>
     * Create a menu containing the list of Languages.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu languageMenu = new JMenu("languages");

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
            Preferences prefs = Preferences.userNodeForPackage(Andie.class);

            prefs.put("language", "mi");
            prefs.put("country", "NZ");

            Locale englishLocale = Locale.ENGLISH;
            ResourceBundle commands = commands.getBundle("commands", englishLocale);

        }

    }

    public class FrenchAction extends ImageAction {

        FrenchAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            Preferences prefs = Preferences.userNodeForPackage(Andie.class);

            prefs.put("language", "mi");
            prefs.put("country", "NZ");

        }

    }

    public class TeReoAction extends ImageAction {

        TeReoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            Preferences prefs = Preferences.userNodeForPackage(Andie.class);
            prefs.put("language", "mi");
            prefs.put("country", "NZ");

        }

    }

}
