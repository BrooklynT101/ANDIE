package cosc202.andie;

import java.awt.*;
import javax.swing.*;

public class DrawingOptionsDisplay extends JPanel {

    JComboBox<String> colourDropBox;

    ButtonGroup shapeChoiceButtons;
    JRadioButton rectangleButton;
    JRadioButton ovalButton;
    JRadioButton lineButton;

    ButtonGroup styleChoiceButtons;
    JRadioButton fillButton;
    JRadioButton outlineButton;

    JSpinner lineWidthSpinner;

    public DrawingOptionsDisplay() {

        setLayout(new GridLayout(3, 1));

        String[] colourChoices = { "Black", "Red", "Blue", "Green", "Yellow" };
        colourDropBox = new JComboBox<>(colourChoices);
        add(new JLabel("Colour:"));
        add(colourDropBox);
        add(new JLabel("       "));
        add(new JLabel("       "));
        add(new JLabel("       "));

        shapeChoiceButtons = new ButtonGroup();
        rectangleButton = new JRadioButton("Rectangle");
        shapeChoiceButtons.add(rectangleButton);
        ovalButton = new JRadioButton("Oval");
        shapeChoiceButtons.add(ovalButton);
        lineButton = new JRadioButton("Line");
        shapeChoiceButtons.add(lineButton);
        add(new JLabel("Shape:"));
        add(rectangleButton);
        add(ovalButton);
        add(lineButton);
        add(new JLabel("       "));

        styleChoiceButtons = new ButtonGroup();
        fillButton = new JRadioButton("Fill");
        styleChoiceButtons.add(fillButton);
        outlineButton = new JRadioButton("Outline");
        styleChoiceButtons.add(outlineButton);
        add(new JLabel("Style:"));
        add(fillButton);
        add(outlineButton);

        add(new JLabel("Line Width:"));
        SpinnerNumberModel lineWidthModel = new SpinnerNumberModel(1, 1, 10, 1);
        lineWidthSpinner = new JSpinner(lineWidthModel);
        add(lineWidthSpinner);

    }

    public Color getColour() {
        String chosenColour = (String) colourDropBox.getSelectedItem();
        switch (chosenColour) {
            case "Black":
                return Color.BLACK;
            case "Blue":
                return Color.BLUE;
            case "Green":
                return Color.GREEN;
            case "Yellow":
                return Color.YELLOW;
            default:
                return Color.RED;
        }
    }

    public String getSelectedShape() {
        if (rectangleButton.isSelected()) {
            return "Rectangle";
        } else if (ovalButton.isSelected()) {
            return "Oval";
        } else {
            return "Line";
        }
    }

    public String getSelectedStyle() {
        if (fillButton.isSelected()) {
            return "Filled";
        } else {
            return "Outlined";
        }
    }

    public int getLineWidth() {
        return (int) lineWidthSpinner.getValue();
    }
}
