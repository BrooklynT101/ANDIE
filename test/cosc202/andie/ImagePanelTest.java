package cosc202.andie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImagePanelTest {

   @Test
   void getZoomInitialValue() {
      ImagePanel testPanel = new ImagePanel();
      Assertions.assertEquals(100.0, testPanel.getZoom());
   }
}
