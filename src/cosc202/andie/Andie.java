package cosc202.andie;

import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import java.util.prefs.Preferences;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.JMenuBar;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import cosc202.andie.game.BrickBreaker;

import javax.imageio.ImageIO;

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
   //boolean for printing out test related values
   protected static boolean testing = true;

   // Setting prefs
   protected static Preferences prefs = Preferences.userNodeForPackage(Andie.class);
   protected static Locale locale = new Locale.Builder().setLanguage(prefs.get("language", "en"))
         .setRegion(prefs.get("country", "NZ")).build();
   protected static ResourceBundle bundle = ResourceBundle.getBundle("CommandsBundle", locale);

   // creating static toolbar
   protected static Toolbar toolbar = new Toolbar();
   protected static JMenuBar menuBar = null;
   // Set up the main GUI frame
   protected static JFrame frame = new JFrame("ANDIE");
   // How long the splash stays on screen (in milliseconds)
   protected static final int DURATION = 1500;
   // boolean to display the splash screen or not (can get annoying)
   protected static final boolean displaySplash = false;

   protected static JLayeredPane layeredPane;
   private static List<BouncingBall> balls = new ArrayList<>();

   // this is the key sequence to activate the easter egg, first one is Konami code
   // second is QnQ for our group
   protected static final int[] KEY_SEQUENCE_KONAMI = { KeyEvent.VK_UP, KeyEvent.VK_UP, KeyEvent.VK_DOWN,
         KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_B,
         KeyEvent.VK_A };
   protected static final int[] KEY_SEQUENCE_GROUP_NAME = { KeyEvent.VK_Q, KeyEvent.VK_N, KeyEvent.VK_Q };
   protected static boolean easterEggActivated = false;
   protected static Random r = new Random();
   
   
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
      frame.setAlwaysOnTop(true);

      // getting icon from resource folder
      try {
         Image icon = ImageIO.read(Andie.class.getClassLoader().getResource("QnQ Logo.png"));
         frame.setIconImage(icon);

      } catch (Exception e) {
         System.out.println("Error loading QnQ Logo.png");
         e.printStackTrace();
      }

      // Set this to nothing so the exitListener works.
      frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      // The main content area is an ImagePanel
      ImagePanel imagePanel = new ImagePanel();
      ImageAction.setTarget(imagePanel);
      JScrollPane scrollPane = new JScrollPane(imagePanel);

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
            // if theres no image it'll just exit
            if (!imagePanel.getImage().hasImage())
               System.exit(0);
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

      // key listener for the Easter egg
      KeyAdapter keyAdapter = new KeyAdapter() {
         private int index = 0;

         @Override
         public void keyPressed(KeyEvent e) {
            if (testing) System.out.println("Key pressed: "+e.getKeyChar());
            if (!easterEggActivated && e.getKeyCode() == KEY_SEQUENCE_GROUP_NAME[index]) {
               index++;
               if (index == KEY_SEQUENCE_GROUP_NAME.length) {
                  // Easter egg activated
                  if (testing) System.out.println("Easter egg activated!");
                  easterEggActivated = true;
                  activateEasterEgg();
               }
            } else {
               index = 0;
            }
         }
      };

      frame.addKeyListener(keyAdapter);

      // Configure the Toolbar
      toolbar.setFloatable(true); // Allow toolbar to be dragged
      toolbar.setOrientation(0); // Set the orientation of the toolbar
      toolbar.setVisible(true);

      // Add components to the frame using BorderLayout
      frame.add(scrollPane, BorderLayout.CENTER);
      frame.add(toolbar, BorderLayout.NORTH); // Add the ToolBar to the top
      
      createMenuBar();
      frame.setJMenuBar(menuBar);
      frame.getContentPane().add(toolbar, BorderLayout.NORTH);
      frame.pack();
      frame.setVisible(true);
      frame.setAlwaysOnTop(false);

      
      layeredPane = new JLayeredPane();
      layeredPane.setBounds(0, 0, frame.getWidth(), frame.getHeight());
      frame.add(layeredPane, BorderLayout.CENTER);
   }

   protected static void activateEasterEgg() {
      int buffer = 35;
      int x = r.nextInt(buffer, frame.getWidth()-buffer);
      int y = r.nextInt(buffer, frame.getHeight()-buffer);
      BouncingBall ball = new BouncingBall(x, y);
      if (testing) System.out.println("Created ball at "+x+", "+y);
      layeredPane.add(ball, JLayeredPane.DEFAULT_LAYER);
      frame.add(ball);  
      ball.start();
   }

   private static void showSplashScreen() {
      SplashScreen splash = new SplashScreen(DURATION);
      splash.showSplash();

      // Use a Timer to wait for the duration
      Timer timer = new Timer(DURATION, e -> {
         try {
            createAndShowGUI();
         } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
         }
      });
      timer.setRepeats(false);
      timer.start();
      return;
   }

   /**
    * Add in menus for various types of action the user may perform.
    * 
    */
   private static JMenuBar createMenuBar() {
      menuBar = new JMenuBar();

      FileActions fileActions = new FileActions();
      menuBar.add(fileActions.createMenu());

      EditActions editActions = new EditActions();
      menuBar.add(editActions.createMenu());

      ViewActions viewActions = new ViewActions();
      menuBar.add(viewActions.createMenu());

      FilterActions filterActions = new FilterActions();
      menuBar.add(filterActions.createMenu());

      ColourActions colourActions = new ColourActions();
      menuBar.add(colourActions.createMenu());

      ImageActions imageActions = new ImageActions();
      menuBar.add(imageActions.createMenu());

      LanguageActions languageActions = new LanguageActions();
      menuBar.add(languageActions.createMenu());

      MacroActions macroActions = new MacroActions();
      menuBar.add(macroActions.createMenu());
      return menuBar;
   }

   public static void addGameOverlay() {
      JFrame frame = getFrame(); // Get the frame instance
      layeredPane = frame.getLayeredPane(); // Initialize layeredPane using the frame

      BrickBreaker gamePlay = new BrickBreaker(frame.getWidth(), frame.getHeight());
      gamePlay.setOpaque(false);

      gamePlay.setBounds(0, 0, frame.getWidth(), frame.getHeight());
      layeredPane.add(gamePlay, JLayeredPane.POPUP_LAYER);
      gamePlay.requestFocusInWindow();
   }

   public static JFrame getFrame() {
      return frame;
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
      if (displaySplash) {
         // display the splash screen and then the GUI
         SwingUtilities.invokeLater(() -> {
            showSplashScreen();
         });
      } else {
         SwingUtilities.invokeLater(() -> {
            try {
               createAndShowGUI();
               // activateEasterEgg();
               if (testing) {
                  System.out.println("Frame dimensions - Height: "+frame.getHeight()+", Width: "+frame.getWidth());
                  System.out.println("Toolbar dimensions - Height: "+toolbar.getHeight()+", Width: "+toolbar.getWidth());
                  System.out.println("MenuBar dimensions - Height: "+menuBar.getHeight()+", Width: "+menuBar.getWidth());
               }
            } catch (Exception e) {
               e.printStackTrace();
            }
         });
      }

   }

}
