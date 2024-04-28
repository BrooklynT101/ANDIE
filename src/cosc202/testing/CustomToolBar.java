package cosc202.testing; 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class CustomToolBar extends JToolBar {

    private ArrayList<Action> selectedActions;
    private ArrayList<AbstractButton> toolbarButtons;

    public CustomToolBar() {
        selectedActions = new ArrayList<>();
        toolbarButtons = new ArrayList<>();
        setFloatable(true);

        // Sample actions
        ArrayList<Action> allActions = new ArrayList<>();
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

        // Initialize selected actions (only load the first 5 actions initially)
        for (int i = 0; i < 5 && i < allActions.size(); i++) {
            selectedActions.add(allActions.get(i));
        }

        // Create toolbar buttons
        for (Action action : selectedActions) {
            AbstractButton button = new JButton(action);
            toolbarButtons.add(button);
            add(button);
        }

        JButton editButton = new JButton("Edit ToolBar");
        editButton.addActionListener(e -> showEditDialog(allActions));

        addSeparator();
        add(editButton);
    }

    private void showEditDialog(ArrayList<Action> allActions) {
        ToolBarEditDialog dialog = new ToolBarEditDialog(null, allActions, selectedActions);
        dialog.setVisible(true);

        // Update selectedActions after dialog is closed
        selectedActions = dialog.getSelectedActions();

        // Remove existing toolbar buttons
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Custom ToolBar");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new CustomToolBar(), BorderLayout.NORTH);
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
