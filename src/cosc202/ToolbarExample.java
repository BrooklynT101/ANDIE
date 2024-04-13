package cosc202;

import javax.swing.*;

import java.awt.event.ActionEvent;

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
        JToolBar toolBar = new JToolBar();
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
            //ToolBarUI example2 = new ToolBarUI(example);
            example.setVisible(true);
        });
    }
}
