package cosc202.andie;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.swing.*;
import javax.imageio.*;

/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 * 
 * <p>
 * This class is the entry point for the program.
 * It creates a Graphical User Interface (GUI) that provides access to various
 * image editing and processing operations.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class Andie {

   // Setting prefs
   protected static Preferences prefs = Preferences.userNodeForPackage(Andie.class);
   protected static Locale locale = new Locale.Builder().setLanguage(prefs.get("language", "en"))
         .setRegion(prefs.get("country", "NZ")).build();
   protected static ResourceBundle bundle = ResourceBundle.getBundle("CommandsBundle", locale);

   // creating static toolbar
   protected static Toolbar toolbar = new Toolbar();
   // Set up the main GUI frame
   protected static JFrame frame = new JFrame("ANDIE");
   
   private static JLayeredPane layeredPane;

   /**
    * <p>
    * Launches the main GUI for the ANDIE program.
    * </p>
    * 
    * <p>
    * This method sets up an interface consisting of an active image (an
    * {@code ImagePanel})
    * and various menus which can be used to trigger operations to load, save,
    * edit, etc.
    * These operations are implemented {@link ImageOperation}s and triggered via
    * {@code ImageAction}s grouped by their general purpose into menus.
    * </p>
    * 
    * @see ImagePanel
    * @see ImageAction
    * @see ImageOperation
    * @see FileActions
    * @see EditActions
    * @see ViewActions
    * @see FilterActions
    * @see ColourActions
    * 
    * @throws Exception if something goes wrong.
    */

   private static void createAndShowGUI() throws Exception {

      // getting icon from resource folder
      try {
         Image icon = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
         frame.setIconImage(icon);

      } catch (Exception e) {
         System.out.println("Error loading icon.png");
         e.printStackTrace();
      }

      // Set this to nothing so the exitListener works.
      frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

      /**
       * <p>
       * This code is taken from StackOverflow:
       * <a href=
       * "https://stackoverflow.com/a/6084220">https://stackoverflow.com/a/6084220</a>
       * in response to
       * <a href=
       * "https://stackoverflow.com/questions/6084039/create-custom-operation-for-setdefaultcloseoperation">https://stackoverflow.com/questions/6084039/create-custom-operation-for-setdefaultcloseoperation</a>.
       * Code by mKorbel used under the CC BY-SA 2.5 license.
       * </p>
       * 
       * <p>
       * This code block is released under
       * <a href="https://creativecommons.org/licenses/by-sa/2.5/">CC BY-SA 2.5</a>
       * </p>
       */

      WindowListener exitListener = new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            // Message: Are You Sure to Close Application?
            String message = Andie.bundle.getString("WantToClose?");
            if (!EditableImage.isSaved) {
               // Message: You haven't saved your changes yet.
               message += "\n" + Andie.bundle.getString("HaveNotSaved");
            }
            int confirm = JOptionPane.showOptionDialog(null, message,
                  Andie.bundle.getString("ExitConfirmation"), JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE, null,
                  null, null);
            if (confirm == 0) {
               System.exit(0);
            }
         }
      };

      frame.addWindowListener(exitListener);

      // The main content area is an ImagePanel
      ImagePanel imagePanel = new ImagePanel();
      ImageAction.setTarget(imagePanel);
      JScrollPane scrollPane = new JScrollPane(imagePanel);

      // Configure the custom ToolBar
      toolbar.setFloatable(true); // Allow toolbar to be dragged
      toolbar.setOrientation(JToolBar.HORIZONTAL); // Set the orientation of the toolbar
      toolbar.setVisible(true);

      // Add components to the frame using BorderLayout
      frame.add(scrollPane, BorderLayout.CENTER);
      frame.add(toolbar, BorderLayout.NORTH); // Add the ToolBar to the top

      // Add in menus for various types of action the user may perform.
      JMenuBar menuBar = new JMenuBar();

      // File menus are pretty standard, so things that usually go in File menus go
      // here.
      FileActions fileActions = new FileActions();
      menuBar.add(fileActions.createMenu());

      // Likewise Edit menus are very common, so should be clear what might go here.
      EditActions editActions = new EditActions();
      menuBar.add(editActions.createMenu());

      // View actions control how the image is displayed, but do not alter its actual
      // content
      ViewActions viewActions = new ViewActions();
      menuBar.add(viewActions.createMenu());

      // Filters apply a per-pixel operation to the image, generally based on a local
      // window
      FilterActions filterActions = new FilterActions();
      menuBar.add(filterActions.createMenu());

      // Actions that affect the representation of colour in the image
      ColourActions colourActions = new ColourActions();
      menuBar.add(colourActions.createMenu());

      // Actions that affect the rotation and size in the image
      ImageActions imageActions = new ImageActions();
      menuBar.add(imageActions.createMenu());

      // Language options, change language of each menu
      LanguageActions languageActions = new LanguageActions();
      menuBar.add(languageActions.createMenu());

      // Macro options, create macros for operations
      MacroActions macroActions = new MacroActions();
      menuBar.add(macroActions.createMenu());

      frame.setJMenuBar(menuBar);
      frame.getContentPane().add(toolbar, BorderLayout.NORTH);
      frame.pack();
      frame.setVisible(true);
   }

   /**
    * <p>
    * Main entry point to the ANDIE program.
    * </p>
    * 
    * <p>
    * Creates and launches the main GUI in a separate thread.
    * As a result, this is essentially a wrapper around {@code createAndShowGUI()}.
    * </p>
    * 
    * @param args Command line arguments, not currently used
    * @throws Exception If something goes awry
    * @see #createAndShowGUI()
    */
   public static void main(String[] args) throws Exception {
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            try {
               createAndShowGUI();
            } catch (Exception ex) {
               ex.printStackTrace();
               System.exit(1);
            }
         }
      });
   }

}
