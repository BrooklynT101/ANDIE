package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * <p>
 * A class to display a drawing menu. Allows user to specify what they want to
 * draw efficiently
 * <p>
 * 
 * @author Marcus Woodward
 */
public class DrawingOptionsDisplay extends JPanel {

    JComboBox<String> colourDropBox;

    ButtonGroup shapeChoiceButtons;
    JRadioButton rectangleButton;
    JRadioButton ovalButton;
    JRadioButton lineButton;
    JColorChooser colors;

    ButtonGroup styleChoiceButtons;
    JRadioButton fillButton;
    JRadioButton outlineButton;

    JSpinner lineWidthSpinner;

    /**
     * <p>
     * Constructor for DrawingOptionsDisplay instance
     * <p>
     */
    public DrawingOptionsDisplay() {

        // JColorChooser object to be added to the display
        colors = new JColorChooser();

        // set the layout of the display
        setLayout(new BorderLayout());

        // add shape buttons
        shapeChoiceButtons = new ButtonGroup();
        rectangleButton = new JRadioButton("Rectangle");
        shapeChoiceButtons.add(rectangleButton);
        ovalButton = new JRadioButton("Oval");
        shapeChoiceButtons.add(ovalButton);
        lineButton = new JRadioButton("Line");
        shapeChoiceButtons.add(lineButton);
        JPanel choices = new JPanel();
        choices.setLayout(new FlowLayout());
        add(choices, BorderLayout.LINE_START);
        choices.add(new JLabel("Shape:"));
        choices.add(rectangleButton);
        choices.add(ovalButton);
        choices.add(lineButton);
        choices.add(new JLabel("       "));

        // add style buttons
        styleChoiceButtons = new ButtonGroup();
        fillButton = new JRadioButton("Fill");
        styleChoiceButtons.add(fillButton);
        outlineButton = new JRadioButton("Outline");
        styleChoiceButtons.add(outlineButton);
        choices.add(new JLabel("Style:"));
        choices.add(fillButton);
        choices.add(outlineButton);

        // add line width spinner
        choices.add(new JLabel("Line Width:"));
        SpinnerNumberModel lineWidthModel = new SpinnerNumberModel(1, 1, 10, 1);
        lineWidthSpinner = new JSpinner(lineWidthModel);
        choices.add(lineWidthSpinner);

        // add colour panel
        JPanel colorpanel = new JPanel();
        add(colorpanel, BorderLayout.PAGE_END);
        colorpanel.add(colors);

    }

    /**
     * <p>
     * Accessor for selected colour on the DrawingOptionsDisplay instance
     * <p>
     */
    public Color getColour() {
        return colors.getColor();

    }

    /**
     * <p>
     * Accessor for selected shape on the DrawingOptionsDisplay instance
     * <p>
     */
    public String getSelectedShape() {
        if (rectangleButton.isSelected()) {
            return "Rectangle";
        } else if (ovalButton.isSelected()) {
            return "Oval";
        } else {
            return "Line";
        }
    }

    /**
     * <p>
     * Accessor for selected style on the DrawingOptionsDisplay instance
     * <p>
     */
    public String getSelectedStyle() {
        if (fillButton.isSelected()) {
            return "Filled";
        } else {
            return "Outlined";
        }
    }

    /**
     * <p>
     * Accessor for line width on the DrawingOptionsDisplay instance
     * <p>
     */
    public int getLineWidth() {
        return (int) lineWidthSpinner.getValue();
    }
}
