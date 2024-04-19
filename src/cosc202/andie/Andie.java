package cosc202.andie;

import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import java.util.ArrayList;

import cosc202.andie.FileActions.*;

import javax.swing.*;
import javax.imageio.*;
import java.io.*;

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

    // protected static ArrayList<Action> actions = {new
    // FileOpenAction(Andie.bundle.getString("Open"), null,
    // Andie.bundle.getString("Openafile"), Integer.valueOf(KeyEvent.VK_O)) };
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

        // Set up the main GUI frame
        JFrame frame = new JFrame("ANDIE");

        // getting icon
        try {
            Image icon = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
            frame.setIconImage(icon);

        } catch (Exception e) {
            System.out.println("Error loading icon.png");
            e.printStackTrace();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The main content area is an ImagePanel
        ImagePanel imagePanel = new ImagePanel();
        ImageAction.setTarget(imagePanel);
        JScrollPane scrollPane = new JScrollPane(imagePanel);

        // // adding toolbar
        // ToolBar toolBar = new ToolBar("Common Tools", 10);
        // toolBar.loadDefaultActions();
        // toolBar.setVisible(true);

        // // if enabled, the whole program turns into a small screen with no scrolling,
        // // the images wont load, and the toolBar doesnt even show anyway
        // // frame.add(toolBar);

        // Create and configure the custom ToolBar
        ToolBar toolBar = new ToolBar("Common Tools", 10);
        toolBar.loadDefaultActions();
        toolBar.setFloatable(true); // Allow toolbar to be dragged
        toolBar.setOrientation(JToolBar.HORIZONTAL); // Set the orientation of the toolbar

        // Add components to the frame using BorderLayout
        
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(toolBar, BorderLayout.NORTH); // Add the ToolBar to the top

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

        frame.setJMenuBar(menuBar);
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
