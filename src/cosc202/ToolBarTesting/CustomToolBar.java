package cosc202.ToolBarTesting;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

/**
 * Need to check this out
 */

public class CustomToolBar extends JToolBar {

    private ArrayList<Action> selectedActions;
    private ArrayList<AbstractButton> toolbarButtons;
    private ArrayList<Action> allActions;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Custom ToolBar");
            JMenuBar menuBar = new JMenuBar();
            CustomToolBar toolBar = new CustomToolBar();
            JButton editButton = new JButton(
                    new EditToolBarAction("EditToolBar", null, "EditToolBar", Integer.valueOf(KeyEvent.VK_Z), toolBar));
            menuBar.add(editButton);
            frame.setJMenuBar(menuBar);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(toolBar, BorderLayout.NORTH);
            frame.setSize(400, 200);
            frame.setVisible(true);
        });
    }

    public CustomToolBar() {
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
