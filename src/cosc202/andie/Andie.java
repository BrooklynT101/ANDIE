package cosc202.andie;

import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;

import java.util.prefs.Preferences;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

import cosc202.andie.Confetti.Ball;
import cosc202.andie.Confetti.BallPanel;

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
   // boolean for printing out test related values
   protected static boolean testing = false;

   // Setting prefs
   protected static Preferences prefs = Preferences.userNodeForPackage(Andie.class);
   protected static Locale locale = new Locale.Builder().setLanguage(prefs.get("language", "en"))
         .setRegion(prefs.get("country", "NZ")).build();
   protected static ResourceBundle bundle = ResourceBundle.getBundle("CommandsBundle", locale);

   // creating static toolbar
   protected static Toolbar toolbar = new Toolbar();
   // Set up the main GUI frame
   public static JFrame frame = new JFrame("ANDIE");
   // How long the splash stays on screen (in milliseconds)
   protected static final int DURATION = 1500;
   // boolean to display the splash screen or not (can get annoying)
   protected static final boolean displaySplash = false;

   // this is the key sequence to activate the easter egg
   // protected static final int[] KEY_SEQUENCE_GROUP_NAME = { KeyEvent.VK_Q, KeyEvent.VK_N, KeyEvent.VK_Q };
   // protected static boolean easterEggActivated = false;
   protected static Random r = new Random();

   private static JLayeredPane layeredPane;

   protected static QnQPanel qnqPanel;
   protected static BallPanel ballPanel;

   // private static BallPanel gameOverlay;

   private static final int MAXIMUM_BALLS = 50; // max number of balls
   private static final int BALLS_DELAY = 100; // in milliseconds

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
      frame.setAlwaysOnTop(true); // so it loads on top of everything

      // getting icon from resource folder
      try {
         Image icon = ImageIO.read(Andie.class.getClassLoader().getResource("QnQ Logo.png"));
         frame.setIconImage(icon);

      } catch (Exception e) {
         System.err.println("Error loading QnQ Logo.png");
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

      // // key listener for the Easter egg
      // KeyAdapter keyAdapter = new KeyAdapter() {
      //    private int index = 0;

      //    @Override
      //    public void keyPressed(KeyEvent e) {
      //       if (testing)
      //          System.out.println("Key pressed: " + e.getKeyChar());
      //       if (!easterEggActivated && e.getKeyCode() == KEY_SEQUENCE_GROUP_NAME[index]) {
      //          index++;
      //          if (index == KEY_SEQUENCE_GROUP_NAME.length) {
      //             // Easter egg activated
      //             if (testing)
      //                System.out.println("Easter egg activated!");
      //             easterEggActivated = true;
      //             activateEasterEgg();
      //          }
      //       } else {
      //          index = 0;
      //       }
      //    }
      // };

      // frame.addKeyListener(keyAdapter);

      // Add a component listener to the frame to handle resizing events
      frame.addComponentListener(new ComponentAdapter() {
         @Override
         public void componentResized(ComponentEvent e) {
            if (testing)
               System.out.println("Frame has been resized");
            super.componentResized(e);
            // Update the panel size when the frame is resized if the panel
            if (qnqPanel != null) {
               Andie.qnqPanel.updatePanelSize(Andie.frame.getWidth(), Andie.frame.getHeight());
            }
            if (ballPanel != null && BallPanel.isRunning) {
               Andie.ballPanel.updatePanelSize(Andie.frame.getWidth(), Andie.frame.getHeight());
            }
         }
      });

      // Configure the Toolbar
      toolbar.setFloatable(true); // Allow toolbar to be dragged
      toolbar.setOrientation(0); // Set the orientation of the toolbar
      toolbar.setVisible(true);

      // Add components to the frame using BorderLayout
      frame.add(scrollPane, BorderLayout.CENTER);
      frame.add(toolbar, BorderLayout.NORTH); // Add the ToolBar to the top

      frame.setJMenuBar(createMenuBar());
      // frame.getContentPane().add(toolbar, BorderLayout.NORTH);
      frame.pack();
      frame.setVisible(true);
      frame.setAlwaysOnTop(false); // so it doesn't stay loaded on top of everything
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
      JMenuBar menuBar = new JMenuBar();

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
      
      FunActions funActions = new FunActions();
      menuBar.add(funActions.createMenu());
      
      return menuBar;
   }

   public static void activateConfetti() {
      JFrame frame = getFrame();// get andie frame
      layeredPane = frame.getLayeredPane();

      // check if BallPanel is already running
      BallPanel existingBallPanel = null;
      for (int i = 0; i < layeredPane.getComponentCount(); i++) {
         if (layeredPane.getComponent(i) instanceof BallPanel) {
            existingBallPanel = (BallPanel) layeredPane.getComponent(i);
            break;
         }
      }

      // end it
      if (existingBallPanel != null && BallPanel.isRunning) {
         existingBallPanel.endConfetti();
         layeredPane.remove(existingBallPanel);
      } else {
         ballPanel = new BallPanel(frame.getWidth(), frame.getHeight(), BALLS_DELAY, MAXIMUM_BALLS);
         layeredPane.add(ballPanel, JLayeredPane.POPUP_LAYER);

         // define the panel
         ballPanel.setOpaque(false);
         ballPanel.setBackground(Color.red);
         ballPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());

         ballPanel.requestFocusInWindow();
         ballPanel.startGeneratingBalls();
      }

      layeredPane.revalidate();
      layeredPane.repaint();
   }

   public static void activateQNQEgg() {
      qnqPanel = new QnQPanel();
      JFrame frame = getFrame(); // Get the frame instance
      layeredPane = frame.getLayeredPane(); // Initialize layeredPane using the frame
      layeredPane.add(qnqPanel, JLayeredPane.POPUP_LAYER);

      // define the panel
      qnqPanel.setOpaque(false);
      qnqPanel.setBounds(0, 25, frame.getWidth() - 25, frame.getHeight() - 35);

      qnqPanel.requestFocusInWindow();
      qnqPanel.startGeneratingCnQs();
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
               if (testing) {
                  System.out
                        .println("Frame dimensions - Height: " + frame.getHeight() + ", Width: " + frame.getWidth());
                  System.out.println(
                        "Toolbar dimensions - Height: " + toolbar.getHeight() + ", Width: " + toolbar.getWidth());
                  // System.out.println(
                  // "MenuBar dimensions - Height: " + menuBar.getHeight() + ", Width: " +
                  // menuBar.getWidth());
               }
            } catch (Exception e) {
               e.printStackTrace();
            }
         });
      }

   }

}
