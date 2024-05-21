package cosc202.andie;

import java.awt.*;
import javax.swing.*;

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

    public DrawingOptionsDisplay() {

        colors = new JColorChooser();

        setLayout(new BorderLayout());

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

        styleChoiceButtons = new ButtonGroup();
        fillButton = new JRadioButton("Fill");
        styleChoiceButtons.add(fillButton);
        outlineButton = new JRadioButton("Outline");
        styleChoiceButtons.add(outlineButton);
        choices.add(new JLabel("Style:"));
        choices.add(fillButton);
        choices.add(outlineButton);

        choices.add(new JLabel("Line Width:"));
        SpinnerNumberModel lineWidthModel = new SpinnerNumberModel(1, 1, 10, 1);
        lineWidthSpinner = new JSpinner(lineWidthModel);
        choices.add(lineWidthSpinner);

        JPanel colorpanel = new JPanel();
        add(colorpanel, BorderLayout.PAGE_END);
        colorpanel.add(colors);

    }

    public Color getColour() {
        return colors.getColor();

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
