package cosc202;

import javax.swing.*;
import javax.tools.Tool;

import java.awt.event.ActionEvent;
import java.util.InputMismatchException;
import java.util.Scanner;

import cosc202.andie.ToolBar;

// TODO: ensure this isn't in the final main build
/**
 * example class to mess around with the toolbar, let me know if this
 * exists in the final build as it shouldn't be
 */
public class ToolbarExample extends JFrame {

    private Action selectAction;
    private Action cropAction;
    private Action brushAction;
    private Action eraserAction;
    private Action zoomAction;

    public ToolbarExample() {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ToolbarExample example = new ToolbarExample();
            // ToolBarUI example2 = new ToolBarUI(example);
            example.setVisible(true);
        });

        SwingUtilities.invokeLater(() -> {
            ToolbarExample example = new ToolbarExample();
            // ToolBarUI example2 = new ToolBarUI(example);
            example.setVisible(true);
        });
    }

    public static void andieToolBarDebug() {
        // Create toolbar
        ToolBar toolBar = new ToolBar("Testing", 10);
        System.out.println("Create a toolbar named Testing with a max size of 10");

        // Print menu
        boolean correct = false;
        while (!correct) {
            System.out.println(toolBar);
            int input = readInt("Select Option\n1. Add Actions\n2. Delete Actions\n3. Create new toolbar\n0. exit");
            switch (input) {
                case 1:
                    break;
                case 2:
                    toolBar.addAction(null);
                    break;
                case 3:
                    String name = readStr("Enter toolbar name");
                    int size = readInt("Enter the max size");
                    toolBar = new ToolBar(name, size);
                    System.out.println("Create a toolbar named "+name+" with a max size of "+size);
                    break;
                case 0:
                    System.exit(0);
            }
        }
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
