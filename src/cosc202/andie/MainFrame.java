package cosc202.andie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private static Toolbar toolbar = new Toolbar();

    public MainFrame() {
        setTitle("Dialog Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem menuItem = new JMenuItem("Edit Toolbar Dialog");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDialog();
            }
        });
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void openDialog() {
        JDialog dialog = new JDialog(this, "Button Dialog", true);
        dialog.setLayout(new GridLayout(2, 2)); // Two columns for two panels

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        addButtons(panel1, panel2, toolbar.getInactiveActions().size()); // Add 5 random buttons to panel1 (inactive
                                                                         // actions)
        addButtons(panel2, panel1, toolbar.getActiveActions().size()); // Add 5 random buttons to panel2 (active
                                                                       // actions)

        dialog.add(panel1);
        dialog.add(panel2);

        JButton acceptButton = new JButton("Accept");
        JButton cancelButton = new JButton("Cancel");

        // Accept button should take everything from the seoncd panel and add actions to
        // toolbar
        acceptButton.addActionListener(e -> {
            // remove all actions
            toolbar.removeAllActions();

            for (Component comp : panel2.getComponents()) {
                if (comp instanceof JButton) {
                    JButton button = (JButton) comp;
                    // Find the action associated with the button
                    for (Action action : toolbar.getAllActions()) {
                        if (button.getText().equals(action.getValue(Action.NAME))) {
                            // Add the action to the toolbar
                            toolbar.addAction(action);
                            break;
                        }
                    }
                }
            }
            // // Repaint and validate the toolbar
            // toolbar.revalidate();
            // toolbar.repaint();

            // Print the active actions in the toolbar
            for (Action action : toolbar.getActiveActions()) {
                System.out.println("Active action: " + action.getValue(Action.NAME));
            }

            // Dispose the dialog
            dialog.dispose();
        });

        // cancel button function
        cancelButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void addButtons(JPanel panel1, JPanel panel2, int count) {
        panel1.setLayout(new GridLayout(count, 1)); // Set layout for panel
        for (Action action : toolbar.getAllActions()) {
            JButton button = new JButton((String) action.getValue(Action.NAME)); // get the name, convert it to string,
                                                                                 // add as a button
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource(); // Get the button that was clicked
                    JPanel parentPanel = (JPanel) clickedButton.getParent(); // Get the parent panel of the clicked
                                                                             // button
                    JPanel targetPanel = parentPanel == panel1 ? panel2 : panel1; // Determine the target panel
                    parentPanel.remove(clickedButton); // Remove the clicked button from the current panel
                    targetPanel.add(clickedButton); // Add the clicked button to the target panel
                    parentPanel.revalidate(); // Revalidate the current panel to reflect changes
                    parentPanel.repaint(); // Repaint the current panel to update the UI
                    targetPanel.revalidate(); // Revalidate the target panel to reflect changes
                    targetPanel.repaint(); // Repaint the target panel to update the UI
                }
            });
            panel1.add(button);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
                frame.getContentPane().add(toolbar, BorderLayout.NORTH);
            }
        });
    }
}
