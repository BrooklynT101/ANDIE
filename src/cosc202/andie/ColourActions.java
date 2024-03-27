package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel
 * directly
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, but more operations
 * will need to be added.
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
public class ColourActions {

    /** A list of actions for the Colour menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     */
    public ColourActions() {
        actions = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction(Andie.bundle.getString("Greyscale"), null,
                Andie.bundle.getString("Converttogreyscale"), Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new ImageInversionAction(Andie.bundle.getString("ImageInversion"), null,
                Andie.bundle.getString("ColourInversion"), Integer.valueOf(KeyEvent.VK_I)));
        actions.add(new ColourChannelCyclingAction(Andie.bundle.getString("ColourChannelCycling"), null,
                Andie.bundle.getString("ColourChannelCycling"), Integer.valueOf(KeyEvent.VK_C)));
    }

    /**
     * <p>
     * Create a menu containing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(Andie.bundle.getString("Colour"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ConvertToGrey());
            target.repaint();
            target.getParent().revalidate();
        }

    }
    /**
     * <p>
     * A filter class that applies a blur effect to the whole image
     * <p>
     * 
     * <p>
     * Creates a blur filter object, with a default radius of 1 and
     * a desired number of filter operations to perform 
     * 
     * <p>
     * @author Amelia
     */
    public class ImageInversionAction extends ImageAction {

        /**
         * create a new image inversion action
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ImageInversionAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * callback for when the image inversion is triggered
         * 
         * @param e The even triggering this callback
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ImageInversion());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Cycles the RGB values of an images, 
     * <p>
     * 
     * <p>
     * Works by copying the current red values to the green, green to the blue, and blue to the red.
     * Cycling with each action call. 
     * <p>
     * 
     * @see ColourChannelCycling 
     * @author Amelia
     */
    public class ColourChannelCyclingAction extends ImageAction {

        /**
         * Creates a new colour channel cycling action
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ColourChannelCyclingAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * callback for when the colour channel cycling is triggered
         * <p>
         * This method is called whenever the ColourChannelCyclingAction is triggered.
         * Cycling the RGB values by 1 step each time
         * {@link ColourChannelCycling}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ColourChannelCycling());
            target.repaint();
            target.getParent().revalidate();
        }


    }
    
}
