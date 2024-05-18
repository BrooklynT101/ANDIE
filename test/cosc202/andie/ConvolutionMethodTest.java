// package cosc202.andie;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;

// import java.awt.Color;
// import java.awt.image.BufferedImage;
// import java.awt.image.*;

// import javax.swing.*;;

// public class ConvolutionMethodTest {

//    @Test
//    void checkIfDifferent() {
//       ImagePanel testPanel = new ImagePanel();
//       ImagePanel testPanel2 = new ImagePanel();

//       try {
//          Andie.class.getClassLoader().getResource("icon.png");
//          testPanel.getImage().open("src/resources/Otago_University_Clocktower.jpg");
//          testPanel2.getImage().open("src/resources/Otago_University_Clocktower.jpg");

//      } catch (Exception ex) {
//          JFrame frame = new JFrame();
//          JOptionPane.showMessageDialog(frame, "Can't open default test image");
//      }

//       testPanel.getImage().apply(new TestGaussianBlur(1));
//       testPanel2.getImage().apply(new GaussianBlur(1));

//       BufferedImage output = new BufferedImage(testPanel.getImage().getCurrentImage().getWidth(), testPanel.getImage().getCurrentImage().getHeight(), testPanel.getImage().getCurrentImage().getType());
//       for(int y=0;y<testPanel.getImage().getCurrentImage().getHeight();y++ ){
//          for(int x=0;x<testPanel.getImage().getCurrentImage().getWidth();x++){
//             Color c = new Color(testPanel.getImage().getCurrentImage().getRGB(x, y),true);
//             Color d = new Color(testPanel2.getImage().getCurrentImage().getRGB(x, y),true);
//             Assertions.assertEquals(0,c.getAlpha()-d.getAlpha());
//             Assertions.assertEquals(0,c.getRed()-d.getRed());
//             Assertions.assertEquals(0,c.getBlue()-d.getBlue());
//             Assertions.assertEquals(0,c.getGreen()-d.getGreen());
      
//          }
//       }


    

//    }
// }
