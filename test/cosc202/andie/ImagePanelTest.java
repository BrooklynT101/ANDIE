package cosc202.andie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//// import cosc202.andie.ImagePanel;

public class ImagePanelTest {
   @Test
   void passDummyTest() {
      boolean check = true;
      Assertions.assertEquals(true, check);
   }

   @Test
   void getZoomInitialValue() {
      ImagePanel testPanel = new ImagePanel();
      Assertions.assertEquals(100.0, testPanel.getZoom());
   }
}
