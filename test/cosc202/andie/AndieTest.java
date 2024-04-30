package cosc202.andie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Locale;

public class AndieTest {

    @Test
    void checkDefaultLanguage() {
        Locale defaultLocale = new Locale.Builder().setLanguage("en").setRegion("NZ").build();
        Assertions.assertEquals(defaultLocale, Andie.locale);
    }

    @Test
    void checkCustomIconLoading() {
        ImageIcon customIcon = null;
        try {
            customIcon = new ImageIcon(Andie.class.getClassLoader().getResource("icon.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(customIcon, "Custom icon not loaded successfully");
    }

    // Ensure the toolbar default actions is loading 10 actions
    @Test
    void checkToolbarDefaultActions() {
        ToolBar testToolBar = new ToolBar();
        testToolBar.loadDefaultActions();
        int defaultActionsCount = 8;
        Assertions.assertEquals(defaultActionsCount, testToolBar.getComponentCount(),
                "Number of default actions loaded in the toolbar is incorrect");
    }
}
