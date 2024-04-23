// package cosc202.andie;

// import java.util.Locale;
// import java.util.ResourceBundle;
// import java.util.prefs.Preferences;
// import java.awt.*;

// import javax.imageio.*;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;

// public class ResourceTesting {
//     @Test
//     void getLanguageBundle() {
//         Preferences prefs = Preferences.userNodeForPackage(Andie.class);
//         Locale locale = new Locale.Builder().setLanguage(prefs.get("language", "en"))
//                 .setRegion(prefs.get("country", "NZ")).build();
//         ResourceBundle bundle = ResourceBundle.getBundle("CommandsBundle", locale);
//         Assertions.assertEquals(!null, bundle);
//     }

//     @Test
//     void getAndieIcon() {
//         Image image = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
//         Assertions.assertEquals(!null, image);
//     }
// }
