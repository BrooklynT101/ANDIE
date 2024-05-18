// package cosc202.ToolBarTesting;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;

// public class ToolBarActionsDialog extends JDialog {
//     private JPanel availableActionsPanel;
//     private JPanel activeActionsPanel;
//     private JButton acceptButton;
//     private JButton cancelButton;
//     private ArrayList<Action> availableActions;
//     private ArrayList<Action> activeActions;
//     private Map<String, ArrayList<Action>> categorizedActions;

//     public ToolBarActionsDialog(JFrame parent, ArrayList<Action> availableActions, ArrayList<Action> activeActions) {
//         super(parent, "ToolBar Actions", true);
//         this.availableActions = availableActions;
//         this.activeActions = activeActions;
//         this.categorizedActions = categorizeActions(availableActions);

//         initializeUI();
//     }

//     private void initializeUI() {
//         setLayout(new BorderLayout());

//         availableActionsPanel = new JPanel();
//         availableActionsPanel.setLayout(new BoxLayout(availableActionsPanel, BoxLayout.Y_AXIS));
//         availableActionsPanel.setBorder(BorderFactory.createTitledBorder("Actions to add"));

//         JTabbedPane actionCategoriesTab = new JTabbedPane();
//         for (Map.Entry<String, ArrayList<Action>> entry : categorizedActions.entrySet()) {
//             JPanel categoryPanel = new JPanel();
//             categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
//             for (Action action : entry.getValue()) {
//                 JButton actionButton = new JButton(action);
//                 categoryPanel.add(actionButton);
//             }
//             actionCategoriesTab.addTab(entry.getKey(), categoryPanel);
//         }
//         availableActionsPanel.add(actionCategoriesTab);

//         activeActionsPanel = new JPanel();
//         activeActionsPanel.setLayout(new BoxLayout(activeActionsPanel, BoxLayout.Y_AXIS));
//         activeActionsPanel.setBorder(BorderFactory.createTitledBorder("Active Actions"));
//         for (Action action : activeActions) {
//             JButton actionButton = new JButton(action);
//             activeActionsPanel.add(actionButton);
//         }

//         JPanel buttonPanel = new JPanel();
//         acceptButton = new JButton("Accept");
//         cancelButton = new JButton("Cancel");
//         buttonPanel.add(acceptButton);
//         buttonPanel.add(cancelButton);

//         add(availableActionsPanel, BorderLayout.WEST);
//         add(activeActionsPanel, BorderLayout.CENTER);
//         add(buttonPanel, BorderLayout.SOUTH);

//         pack();
//         setLocationRelativeTo(null);

//         acceptButton.addActionListener(e -> onAccept());
//         cancelButton.addActionListener(e -> dispose());
//     }

//     private void onAccept() {
//         // Add logic to handle acceptance of selected actions
//         // For example, you can update activeActions based on user selection
//         dispose();
//     }

//     private Map<String, ArrayList<Action>> categorizeActions(ArrayList<Action> actions) {
//         Map<String, ArrayList<Action>> categorizedActions = new HashMap<>();
//         for (Action action : actions) {
//             String category = getCategory(action);
//             categorizedActions.computeIfAbsent(category, k -> new ArrayList<>()).add(action);
//         }
//         return categorizedActions;
//     }

//     private String getCategory(Action action) {
//         // Add logic to determine the category of the action
//         // This is a placeholder implementation
//         String actionName = (String) action.getValue(Action.NAME);
//         if (actionName.contains("File")) {
//             return "FileActions";
//         } else if (actionName.contains("Edit")) {
//             return "EditActions";
//         } else if (actionName.contains("View")) {
//             return "ViewActions";
//         } else if (actionName.contains("Filter")) {
//             return "FilterActions";
//         } else if (actionName.contains("Colour")) {
//             return "ColourActions";
//         } else if (actionName.contains("Image")) {
//             return "ImageActions";
//         } else if (actionName.contains("Macro")) {
//             return "MacroActions";
//         } else {
//             return "OtherActions";
//         }
//     }

//     public static void main(String[] args) {
//         // Sample actions
//         ArrayList<Action> availableActions = new ArrayList<>();
//         ArrayList<Action> activeActions = new ArrayList<>();

//         availableActions.add(new AbstractAction("File Open") {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 System.out.println("File Open action triggered");
//             }
//         });
//         availableActions.add(new AbstractAction("File Save") {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 System.out.println("File Save action triggered");
//             }
//         });
//         availableActions.add(new AbstractAction("Edit Cut") {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 System.out.println("Edit Cut action triggered");
//             }
//         });
//         availableActions.add(new AbstractAction("Edit Copy") {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 System.out.println("Edit Copy action triggered");
//             }
//         });

//         activeActions.add(new AbstractAction("View Zoom In") {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 System.out.println("View Zoom In action triggered");
//             }
//         });
//         activeActions.add(new AbstractAction("View Zoom Out") {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 System.out.println("View Zoom Out action triggered");
//             }
//         });

//         SwingUtilities.invokeLater(() -> {
//             ToolBarActionsDialog dialog = new ToolBarActionsDialog(null, availableActions, activeActions);
//             dialog.setVisible(true);
//         });
//     }
// }
