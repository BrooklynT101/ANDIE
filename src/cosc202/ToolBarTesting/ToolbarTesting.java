package cosc202.ToolBarTesting;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.util.InputMismatchException;
import java.util.*;

import cosc202.andie.ImageActions;
import cosc202.andie.ImageRotation;
import cosc202.andie.ToolBar;

/**
 * example class to mess around with the toolbar, let me know if this
 * exists in the final build as it shouldn't be
 */

@SuppressWarnings("unused")

public class ToolbarTesting extends JFrame {

    public static ToolBar toolbar = new ToolBar();
    public static ArrayList<Action> allActions = new ArrayList<>(toolbar.getAllActions());
    private Action selectAction;
    private Action cropAction;
    private Action brushAction;
    private Action eraserAction;
    private Action zoomAction;

    public static void main(String[] args) {
        andieToolBarDebug();
        SwingUtilities.invokeLater(() -> {
            ToolbarTesting example = new ToolbarTesting();
            // ToolBarUI example2 = new ToolBarUI(example);
            example.setVisible(true);
        });
    }

    public ToolbarTesting() {
        setTitle("Image Manipulation Software");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create Actions
        selectAction = new AbstractAction("Select") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform select action
                System.out.println("Select action triggered");
            }
        };

        cropAction = new AbstractAction("Crop") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform crop action
                System.out.println("Crop action triggered");
            }
        };

        brushAction = new AbstractAction("Brush") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform brush action
                System.out.println("Brush action triggered");
            }
        };

        eraserAction = new AbstractAction("Eraser") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform eraser action
                System.out.println("Eraser action triggered");
            }
        };

        zoomAction = new AbstractAction("Zoom") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform zoom action
                System.out.println("Zoom action triggered");
            }
        };

        // Create toolbar
        JToolBar toolBar = new JToolBar("Common Tools");
        // toolBar.addSeparator();
        add(toolBar);

        // Add actions to toolbar buttons
        toolBar.add(new JButton(selectAction));
        toolBar.add(new JButton(cropAction));
        toolBar.add(new JButton(brushAction));
        toolBar.add(new JButton(eraserAction));
        toolBar.add(new JButton(zoomAction));
    }

    public static void andieToolBarDebug() {
        // Create toolbar
        ToolBar toolbar = new ToolBar();
        System.out.println(
                "Create a toolbar named " + toolbar.getName() + "Testing with a max size of " + toolbar.getMaxSize());

        // Print menu
        boolean correct = false;
        while (!correct) {
            System.out.println(toolbar.toString());
            int input = readInt(
                    "Select Option\n1. Add Actions\n2. Delete Actions\n3. Create new toolbar\n4. Display in a new JFrame\n0. exit");
            switch (input) {
                case 1:
                addActionMenu();
                    break;
                case 2:
                    toolbar.addAction(null);
                    break;
                case 3:
                    String name = readStr("Enter toolbar name");
                    int size = readInt("Enter the max size");
                    toolbar = new ToolBar();
                    toolbar.setName(name);
                    toolbar.setMaxSize(size);
                    toolbar.loadDefaultActions();
                    System.out.println("Create a toolbar named " + name + " with a max size of " + size
                            + " and default do-nothing action buttons");
                    break;
                case 4:
                    JFrame frame = new JFrame("ToolBar Test");
                    JPanel panel = new JPanel();
                    frame.setTitle("Image Manipulation Software");
                    frame.setSize(800, 600);
                    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    frame.add(toolbar);
                    panel.add(toolbar);
                    frame.add(panel);

                    frame.setVisible(true);
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

    private static void addActionMenu() {
        boolean correct = false;
        while (!correct) {
            int input = readInt("Select Option\n1. option\n2. option\n0. exit");
            switch (input) {
                case 1:
                    break;
                case 2:
                    break;
                case 0:
                    System.exit(0);
            }
        }
        
        ArrayList<Action> foundActions = toolbar.getAllActions();
        return;
    }

    public static String readStr(String message) {
        Scanner scanner = new Scanner(System.in);
        boolean correct = false;
        String userInput = "";
        while (!correct) {
            System.out.println(message);
            try {
                userInput = scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a String.");
                scanner.next();
            }
            if (userInput.length() >= 1) {
                correct = true;
            } else {
                correct = false;
            }
        }
        scanner.close();
        return userInput;
    }

    public static int readInt(String message) {
        Scanner scanner = new Scanner(System.in);
        boolean correct = false;
        int userInput = 0;
        while (!correct) {
            System.out.println(message);
            try {
                userInput = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();
            }
            if (userInput >= 0) {
                correct = true;
            } else {
                correct = false;
            }
        }
        scanner.close();
        return userInput;
    }
}