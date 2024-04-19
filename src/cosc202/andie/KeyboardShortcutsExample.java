package cosc202.andie;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * ChatGPT generated keyboard shortcuts example
 */
public class KeyboardShortcutsExample extends JFrame {
    private Action copyAction;
    private Action pasteAction;

    public KeyboardShortcutsExample() {
        setTitle("Keyboard Shortcuts Example");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create Actions
        copyAction = new AbstractAction("Copy") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform copy action
                System.out.println("Copy action triggered");
            }
        };
        copyAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control C")); // Define shortcut

        pasteAction = new AbstractAction("Paste") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform paste action
                System.out.println("Paste action triggered");
            }
        };
        pasteAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V")); // Define shortcut

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Create menus
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        // Add actions to menu items
        editMenu.add(new JMenuItem(copyAction));
        editMenu.add(new JMenuItem(pasteAction));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KeyboardShortcutsExample example = new KeyboardShortcutsExample();
            example.setVisible(true);
        });
    }
}