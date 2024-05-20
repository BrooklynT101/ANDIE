package cosc202.andie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.*;

import javax.swing.*;;

public class ConvolutionMethodTest {

    @Test
    void checkIfDifferent() {
        ImagePanel testPanel = new ImagePanel();

        try {
            Andie.class.getClassLoader().getResource("QnQ Logo.png");
            testPanel.getImage().open("src/resources/Convolution_tester.jpg");

        } catch (Exception ex) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Can't open test image");
        }

        testPanel.getImage().apply(new Emboss1Filter(1));

        BufferedImage output = new BufferedImage(testPanel.getImage().getCurrentImage().getWidth(),
                testPanel.getImage().getCurrentImage().getHeight(), testPanel.getImage().getCurrentImage().getType());
        for (int y = 1; y < testPanel.getImage().getCurrentImage().getHeight(); y++) {
            for (int x = 1; x < testPanel.getImage().getCurrentImage().getWidth(); x++) {
                Color c = new Color(testPanel.getImage().getCurrentImage().getRGB(x, y), true);
                Assertions.assertEquals(127, c.getBlue());

            }
        }

    }
}
