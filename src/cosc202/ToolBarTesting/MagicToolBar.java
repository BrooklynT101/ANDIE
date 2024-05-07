package cosc202.ToolBarTesting;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.*;

/**
 * this is jsut a class to demonstrate a way to do the dialog to support users
 * to add/remove actions, sucks ass and doesn't work
 */
public class MagicToolBar extends JToolBar {

    private ArrayList<Action> selectedActions;
    private ArrayList<AbstractButton> toolbarButtons;
    private ArrayList<Action> allActions;

    public MagicToolBar() {
        selectedActions = new ArrayList<>();
        toolbarButtons = new ArrayList<>();
        allActions = new ArrayList<>();
        setFloatable(true);

        // Adding sample actions
        allActions.add(new AbstractAction("Open") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open action triggered");
            }
        });
        allActions.add(new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save action triggered");
            }
        });
        allActions.add(new AbstractAction("Crop") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Crop action triggered");
            }
        });

        // Initialize selected actions
        for (Action action : allActions) {
            selectedActions.add(action);
        }

        // Create toolbar buttons
        for (Action action : selectedActions) {
            AbstractButton button = new JButton(action);
            toolbarButtons.add(button);
            add(button);
        }
    }

    public ArrayList<Action> getAllActions() {
        return allActions;
    }

    public void setSelectedActions(ArrayList<Action> selectedActions) {
        // Clear the toolbar buttons
        for (AbstractButton button : toolbarButtons) {
            remove(button);
        }
        toolbarButtons.clear();

        // Add updated toolbar buttons
        for (Action action : selectedActions) {
            AbstractButton button = new JButton(action);
            toolbarButtons.add(button);
            add(button);
        }

        revalidate(); // Refresh the toolbar layout
    }

    public void showEditDialog() {
        ToolBarEditDialog dialog = new ToolBarEditDialog(null, allActions, selectedActions);
        dialog.setVisible(true);
        
        // Update selectedActions after dialog is closed
        setSelectedActions(dialog.getSelectedActions());
    }
}

class MagicToolBarEditDialog extends JDialog {

    private ArrayList<Action> allActions; // List of all available actions
    private ArrayList<Action> selectedActions; // List of currently selected actions
    private JPanel checkBoxPanel;

    public MagicToolBarEditDialog(JFrame parent, ArrayList<Action> allActions, ArrayList<Action> selectedActions) {
        super(parent, "Edit ToolBar Actions", true);
        this.allActions = allActions;
        this.selectedActions = selectedActions;
        initializeUI();
    }

    public ArrayList<Action> getSelectedActions() {
        return selectedActions;
    }

    private void initializeUI() {
        checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(0, 1)); // One column, variable rows

        // Create and add checkboxes for each action
        for (Action action : allActions) {
            JCheckBox checkBox = new JCheckBox(action.getValue(Action.NAME).toString());
            checkBox.setSelected(selectedActions.contains(action)); // Initialize checkbox based on selected actions
            checkBoxPanel.add(checkBox);
        }

        // Add the checkbox panel to the dialog content pane
        getContentPane().add(checkBoxPanel, BorderLayout.CENTER);

        // Add Save and Cancel buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveChanges);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose()); // Close the dialog when Cancel is clicked
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the dialog on the screen
    }

    private void saveChanges(ActionEvent e) {
        // Update selectedActions based on the checkboxes
        selectedActions.clear();
        Component[] components = checkBoxPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                if (checkBox.isSelected()) {
                    // Find the action corresponding to the checkbox label
                    String actionName = checkBox.getText();
                    for (Action action : allActions) {
                        if (action.getValue(Action.NAME).toString().equals(actionName)) {
                            selectedActions.add(action);
                            break;
                        }
                    }
                }
            }
        }
        dispose(); // Close the dialog after saving changes
    }
}
