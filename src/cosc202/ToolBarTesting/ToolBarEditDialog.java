package cosc202.ToolBarTesting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import cosc202.andie.*;

/**
 * Custom dialog 
 */
public class ToolbarEditDialog extends JFrame {

    // static panels so methods can freely update these
    private static JPanel inactiveActionsPanel = new JPanel();
    private static JPanel activeActionsPanel = new JPanel();

    // default constructor
    public ToolbarEditDialog() {
    }

    // the meat of the class, shows the dialog and handles editing
    public void showDialog() {
        // creating the dialog box
        JDialog dialog = new JDialog(this, "Button Dialog", true);
        dialog.setLayout(new GridLayout(2, 2)); // 2x2 grid

        // adding active actions to the right panel
        addActiveActionsToPanel(Andie.toolbar.getActiveActions());

        // adding inactive actions to the left panel
        addInactiveActionsToPanel(Andie.toolbar.getInactiveActions());
        JScrollPane scrollPane = new JScrollPane(inactiveActionsPanel);

        // adjusting panels
        scrollPane.setSize(scrollPane.getWidth(), 600);
        activeActionsPanel.setSize(scrollPane.getWidth(), 600);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Inactive Actions"));
        activeActionsPanel.setBorder(BorderFactory.createTitledBorder("Active Actions"));

        // adding to dialog box
        dialog.add(scrollPane);
        dialog.add(activeActionsPanel);

        // creating buttons
        JButton acceptButton = new JButton("Accept");
        JButton cancelButton = new JButton("Cancel");

        // Accept button should take all the actions from the right panel and trying to
        // load the actions
        acceptButton.addActionListener(e -> {
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

            // close
            dialog.dispose();
        });

        // Cancel button function
        cancelButton.addActionListener(e -> dialog.dispose());
        
        //create button panel and add buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // align buttons to the right
        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);

        // setting dialog size n stuff
        dialog.setSize(600, 400);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // add inactive actions to the left panel
    private void addInactiveActionsToPanel(ArrayList<Action> actions) {
        // remove everything so its not adding actions to the panel each time
        inactiveActionsPanel.removeAll();
        inactiveActionsPanel.setLayout(new BoxLayout(inactiveActionsPanel, BoxLayout.Y_AXIS));

        // iterate over all the given actions and slam them into the panel
        for (Action action : actions) {
            if (Andie.toolbar.getActiveActions().contains(action)) {
            } else {
                System.out.println("Action name: " + action.getValue(Action.NAME));
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
        // remove everything so its not adding actions to the panel each time
        activeActionsPanel.removeAll();
        activeActionsPanel.setLayout(new BoxLayout(activeActionsPanel, BoxLayout.Y_AXIS));

        // iterate over all the given actions and slam them into the panel
        for (Action action : actions) {
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
