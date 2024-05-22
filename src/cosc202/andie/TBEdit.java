package cosc202.andie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Custom dialog for editing the actions on the toolbar and saving them to save
 * via the toolbar methods
 * 
 * @author Brooklyn Taylor
 */
public class TBEdit extends JFrame {

    // static panels so methods can freely update these
    private static JPanel inactiveActionsPanel = new JPanel();
    private static JPanel activeActionsPanel = new JPanel();
    private boolean testing = false; // set to true to print statements for whats happening
    protected static JDialog dialog;

    // default constructor
    public TBEdit() {
    }

    // the meat of the class, shows the dialog and handles editing
    public void showDialog() {
        // creating the dialog box
        dialog = new JDialog(this, "Button Dialog", true);
        dialog.setLayout(new BorderLayout());

        // adding active actions to the right panel
        addActiveActionsToPanel(Andie.toolbar.getActiveActions());

        // adding inactive actions to the left panel
        addInactiveActionsToPanel(Andie.toolbar.getInactiveActions());

        // adjusting and adding inactive actions panel
        JScrollPane scrollPane = new JScrollPane(inactiveActionsPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Inactive Actions"));
        dialog.add(scrollPane, BorderLayout.CENTER);

        // adjusting and adding active actions panel
        activeActionsPanel.setBorder(BorderFactory.createTitledBorder("Active Actions"));
        dialog.add(activeActionsPanel, BorderLayout.EAST);

        // creating buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Align buttons to the right
        JButton acceptButton = new JButton("Accept");
        JButton cancelButton = new JButton("Cancel");

        // add buttons and panel
        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom

        // Accept button should take all the actions from the right panel and trying to
        // load the actions
        acceptButton.addActionListener(e -> {
            int confirm = 0;
            if (activeActionsPanel.getComponents().length > 10) {
                // JOptionPane.showConfirmDialog(TBEdit.dialog,
                // Andie.bundle.getString("TooManyActions"));
                confirm = JOptionPane.showConfirmDialog(TBEdit.dialog, Andie.bundle.getString("TooManyActions"),
                        Andie.bundle.getString("continue"), JOptionPane.OK_CANCEL_OPTION);
            }
            if (confirm == 0) {
                // remove all actions from the toolbar
                Andie.toolbar.removeAllActions();

                // add active actions from the right panel to the toolbar
                for (Component comp : activeActionsPanel.getComponents()) {
                    if (comp instanceof JButton) {
                        JButton button = (JButton) comp;
                        for (Action action : Andie.toolbar.getAllActions()) {
                            // if action name equals buttons name
                            if (button.getText().equals(action.getValue(Action.NAME))) {
                                Andie.toolbar.addAction(action);
                                break;
                            }
                        }
                    }
                }

                // update the actions because it'll freak out
                Andie.toolbar.updateActions();

                // repaint and validate the toolbar because see previous comment
                Andie.toolbar.revalidate();
                Andie.toolbar.repaint();

                // Finally save to file the user preferences
                if (testing)
                    System.out.println("Saving user toolbar prefs to file...");
                Andie.toolbar.saveActions();

                // close
                dialog.dispose();
            }
        });

        // Cancel button function
        cancelButton.addActionListener(e -> dialog.dispose());

        // setting dialog size n stuff
        dialog.setSize(400, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        // adjusting the button panel so it fits the buttons correctly
        buttonPanel.setPreferredSize(
                new Dimension(acceptButton.getPreferredSize().width * 2, acceptButton.getPreferredSize().height));
    }

    // add inactive actions to the left panel
    private void addInactiveActionsToPanel(ArrayList<Action> actions) {
        if (testing)
            System.out.println("Adding actions to left panel");
        // remove everything so its not adding actions to the panel each time
        inactiveActionsPanel.removeAll();
        inactiveActionsPanel.setLayout(new BoxLayout(inactiveActionsPanel, BoxLayout.Y_AXIS));

        // iterate over all the given actions and slam them into the left panel
        for (Action action : actions) {
            if (Andie.toolbar.getActiveActions().contains(action)) {
            } else {
                if (testing)
                    System.out.println("Inactive action name: " + action.getValue(Action.NAME));
                JButton button = new JButton((String) action.getValue(Action.NAME)); // get the name, add as a button
                button.addActionListener(new ActionListener() {
                    @Override
                    // add the functionality to flip between the panels
                    public void actionPerformed(ActionEvent e) {
                        JButton clickedButton = (JButton) e.getSource(); // get the button that was clicked
                        JPanel parentPanel = (JPanel) clickedButton.getParent(); // Get the parent panel of the button
                        JPanel targetPanel = parentPanel == inactiveActionsPanel ? activeActionsPanel
                                : inactiveActionsPanel; // determine the target panel based on where the button isn't
                        parentPanel.remove(clickedButton); // Remove the button from its panel
                        targetPanel.add(clickedButton); // add the button to the other panel

                        // revalidate & repaint the panels to reflect changes
                        parentPanel.revalidate();
                        parentPanel.repaint();
                        targetPanel.revalidate();
                        targetPanel.repaint();
                    }
                });
                // put the button in the active actions panel
                inactiveActionsPanel.add(button);
            }
        }
    }

    /*
     * Method to add active actions to the right panel
     * 
     * @see addInactiveActionsToPanel
     */
    private void addActiveActionsToPanel(ArrayList<Action> actions) {
        if (testing)
            System.out.println("Adding active actions to right panel");
        // remove everything so its not adding actions to the panel each time
        activeActionsPanel.removeAll();
        activeActionsPanel.setLayout(new BoxLayout(activeActionsPanel, BoxLayout.Y_AXIS));

        // iterate over all the given actions and slam them into the panel
        for (Action action : actions) {
            if (testing)
                System.out.println("Active action name: " + action.getValue(Action.NAME));
            JButton button = new JButton((String) action.getValue(Action.NAME)); // get the name, add as a button
            button.setName((String) action.getValue(Action.NAME));
            button.addActionListener(new ActionListener() {
                // add the functionality to flip between the panels
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();
                    JPanel parentPanel = (JPanel) clickedButton.getParent();
                    JPanel targetPanel = parentPanel == inactiveActionsPanel ? activeActionsPanel
                            : inactiveActionsPanel;
                    parentPanel.remove(clickedButton);
                    targetPanel.add(clickedButton);
                    parentPanel.revalidate();
                    parentPanel.repaint();
                    targetPanel.revalidate();
                    targetPanel.repaint();
                }
            });
            activeActionsPanel.add(button);
        }
    }
}
