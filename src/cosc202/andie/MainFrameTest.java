package cosc202.andie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrameTest extends JFrame {

    private static Toolbar toolbar = new Toolbar();
    private static JPanel inactiveActionsPanel = new JPanel();
    private static JPanel activeActionsPanel = new JPanel();

    public MainFrameTest() {
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
        dialog.setLayout(new GridLayout(1, 2)); // One row for two panels

        // Add active actions to the right panel
        addActiveActionsToPanel(toolbar.getActiveActions());

        // Add inactive actions to the left panel
        addInactiveActionsToPanel(toolbar.getInactiveActions());

        JScrollPane scrollPane = new JScrollPane(inactiveActionsPanel);
        dialog.add(scrollPane);
        dialog.add(activeActionsPanel);

        JButton acceptButton = new JButton("Accept");
        JButton cancelButton = new JButton("Cancel");

        // Accept button should take everything from the second panel and add actions to
        // the toolbar
        acceptButton.addActionListener(e -> {
            // Remove all existing actions from the toolbar
            toolbar.removeAllActions();

            // Add active actions from the right panel to the toolbar
            for (Component comp : activeActionsPanel.getComponents()) {
                if (comp instanceof JButton) {
                    JButton button = (JButton) comp;
                    for (Action action : toolbar.getAllActions()) {
                        if (button.getText().equals(action.getValue(Action.NAME))) {
                            toolbar.addAction(action);
                            break;
                        }
                    }
                }
            }

            toolbar.updateActions();

            // Repaint and validate the toolbar to reflect changes
            toolbar.revalidate();
            toolbar.repaint();
            dialog.dispose();
        });

        // Cancel button function
        cancelButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // Method to add actions to a panel
    private void addInactiveActionsToPanel(ArrayList<Action> actions) {
        inactiveActionsPanel.removeAll();
        inactiveActionsPanel.setLayout(new BoxLayout(inactiveActionsPanel, BoxLayout.Y_AXIS));

        for (Action action : actions) {
            if (toolbar.getActiveActions().contains(action)) {
            } else {
                System.out.println("Action name: " + action.getValue(Action.NAME));
                JButton button = new JButton((String) action.getValue(Action.NAME)); // Get the name and add as a button
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton clickedButton = (JButton) e.getSource(); // Get the button that was clicked
                        JPanel parentPanel = (JPanel) clickedButton.getParent(); // Get the parent panel of the clicked
                                                                                 // button
                        JPanel targetPanel = parentPanel == inactiveActionsPanel ? activeActionsPanel
                                : inactiveActionsPanel; // Determine the target panel
                        parentPanel.remove(clickedButton); // Remove the clicked button from the current panel
                        targetPanel.add(clickedButton); // Add the clicked button to the target panel
                        parentPanel.revalidate(); // Revalidate the current panel to reflect changes
                        parentPanel.repaint(); // Repaint the current panel to update the UI
                        targetPanel.revalidate(); // Revalidate the target panel to reflect changes
                        targetPanel.repaint(); // Repaint the target panel to update the UI
                    }
                });
                inactiveActionsPanel.add(button);
                System.out.println("Added button to inactive panel: " + button.getName());
            }
        }
    }

    // Method to add actions to a panel
    private void addActiveActionsToPanel(ArrayList<Action> actions) {
        activeActionsPanel.removeAll();
        activeActionsPanel.setLayout(new BoxLayout(activeActionsPanel, BoxLayout.Y_AXIS));
        for (Action action : actions) {
            JButton button = new JButton((String) action.getValue(Action.NAME)); // Get the name and add as a button
            button.setName((String) action.getValue(Action.NAME));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource(); // Get the button that was clicked
                    JPanel parentPanel = (JPanel) clickedButton.getParent(); // Get the parent panel of the clicked
                                                                             // button
                    JPanel targetPanel = parentPanel == inactiveActionsPanel ? activeActionsPanel
                            : inactiveActionsPanel; // Determine the target panel
                    parentPanel.remove(clickedButton); // Remove the clicked button from the current panel
                    targetPanel.add(clickedButton); // Add the clicked button to the target panel
                    parentPanel.revalidate(); // Revalidate the current panel to reflect changes
                    parentPanel.repaint(); // Repaint the current panel to update the UI
                    targetPanel.revalidate(); // Revalidate the target panel to reflect changes
                    targetPanel.repaint(); // Repaint the target panel to update the UI
                }
            });
            activeActionsPanel.add(button);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrameTest frame = new MainFrameTest();
                frame.setVisible(true);
                frame.getContentPane().add(toolbar, BorderLayout.NORTH);
            }
        });
    }
}
