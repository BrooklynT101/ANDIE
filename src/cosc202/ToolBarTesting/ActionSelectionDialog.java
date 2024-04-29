package cosc202.ToolBarTesting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ActionSelectionDialog extends JFrame {

    private ArrayList<Action> allActions;
    private ArrayList<Action> selectedActions;

    public ActionSelectionDialog() {
        setTitle("Action Selection Dialog");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));

        // Sample actions
        allActions = new ArrayList<>();
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

        // Initialize selected actions (can be empty initially)
        selectedActions = new ArrayList<>(allActions);

        JButton editButton = new JButton("Edit ToolBar");
        editButton.addActionListener(e -> showEditDialog());

        JPanel panel = new JPanel();
        panel.add(editButton);

        add(panel);
        pack();
        setLocationRelativeTo(null); // Center the frame
    }

    private void showEditDialog() {
        ToolBarEditDialog dialog = new ToolBarEditDialog(this, allActions, selectedActions);
        dialog.setVisible(true);

        // Update selectedActions after dialog is closed
        selectedActions = dialog.getSelectedActions();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ActionSelectionDialog dialog = new ActionSelectionDialog();
            dialog.setVisible(true);
        });
    }
}
