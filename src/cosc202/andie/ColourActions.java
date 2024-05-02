package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    protected static ArrayList<Action> actions;

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
        actions.add(new BrightnessAndContrastAction(Andie.bundle.getString("BrightnessAndContrast"), null,
                Andie.bundle.getString("Applyabrightnessandcontrastfilter"), Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new ColourChannelsAction(Andie.bundle.getString("ColourChannels"), null,
                Andie.bundle.getString("ApplyColourChannels"), Integer.valueOf(KeyEvent.VK_S)));
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
     * 
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
     * Works by copying the current red values to the green, green to the blue, and
     * blue to the red.
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

    /**
     * <p>
     * Action to adjust the brightness and contrast of an image
     * </p>
     * 
     * @see BrightnessAndContrast
     * @author Amelia
     */
    public class BrightnessAndContrastAction extends ImageAction {

        /**
         * <p>
         * Create a new median-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        BrightnessAndContrastAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MedianFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized
         * {@link MedianFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int brightness = 0;
            int contrast = 0;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel brightnessModel = new SpinnerNumberModel(0, -100, 100, 1);
            SpinnerNumberModel contrastModel = new SpinnerNumberModel(0, -100, 100, 1);
            JSpinner brightnessSpinner = new JSpinner(brightnessModel);
            JSpinner contrastSpinner = new JSpinner(contrastModel);

            JSlider brightnessSlider = new JSlider(-100, 100, 0);
            JSlider contrastSlider = new JSlider(-100, 100, 0);

            brightnessSlider.setMinorTickSpacing(10);
            brightnessSlider.setMajorTickSpacing(50);
            brightnessSlider.setPaintTicks(true);
            brightnessSlider.setPaintLabels(true);
            brightnessSlider.setLabelTable(brightnessSlider.createStandardLabels(100));

            contrastSlider.setMinorTickSpacing(10);
            contrastSlider.setMajorTickSpacing(50);
            contrastSlider.setPaintTicks(true);
            contrastSlider.setPaintLabels(true);
            contrastSlider.setLabelTable(contrastSlider.createStandardLabels(100));

            class JSpinnerListener implements ChangeListener { // go through and comment on this and understand

                public void stateChanged(ChangeEvent e) {
                    // set brightness and contrast to spinner values
                    brightnessSlider.setValue((int) brightnessSpinner.getValue());
                    contrastSlider.setValue((int) contrastSpinner.getValue());

                    // target.repaint();
                    // target.getParent().revalidate();
                }

            }

            class JSliderListener implements ChangeListener { // go through and comment on this and understand

                public void stateChanged(ChangeEvent e) {
                    // set brightness and contrast to spinner values
                    brightnessSpinner.setValue((int) brightnessSlider.getValue());
                    contrastSpinner.setValue((int) contrastSlider.getValue());

                    // target.repaint();
                    // target.getParent().revalidate();
                }

            }

            // add listener to brightness and contrast spinners
            brightnessSpinner.addChangeListener(new JSpinnerListener());
            contrastSpinner.addChangeListener(new JSpinnerListener());

            // add listener to brightness and contrast spinners
            brightnessSlider.addChangeListener(new JSliderListener());
            contrastSlider.addChangeListener(new JSliderListener());

            JPanel spinnerPanel = new JPanel();
            spinnerPanel.add(new JLabel(Andie.bundle.getString("Choosepercentbrightness")));
            spinnerPanel.add(brightnessSpinner);
            spinnerPanel.add(brightnessSlider);

            spinnerPanel.add(new JLabel(Andie.bundle.getString("Choosepercentcontrast")));
            spinnerPanel.add(contrastSpinner);
            spinnerPanel.add(contrastSlider);

            // Prevents adding no 1 - 100 integers, by making the text field un editable by
            // default.
            int option = JOptionPane.showOptionDialog(null,
                    spinnerPanel,
                    Andie.bundle.getString("Choosepercentbrightness"),
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) { // do we need to check that one works? do both need to work?
                return;
            } else {
                System.out.println("its working");
                brightness = brightnessModel.getNumber().intValue();
                contrast = contrastModel.getNumber().intValue();

            }

            // System.out.println(brightness); //checking that the values are correctly
            // being updated
            // System.out.println(contrast);
            // System.out.println(brightness + " " + contrast);

            // Create and apply the filter
            target.getImage().apply(new BrightnessAndContrast(brightness, contrast));
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
     * Works by copying the current red values to the green, green to the blue, and
     * blue to the red.
     * Cycling with each action call.
     * <p>
     * 
     * @see ColourChannelCycling
     * @author Amelia
     */
    public class ColourChannelsAction extends ImageAction {

        int rotationNum = 0;
        private final String[] choices = { "RGB", "RBG", "GBR", "GRB", "BGR", "BRG" }; //language?
        


        /**
         * Creates a new colour channel cycling action
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ColourChannelsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            
        }


        /**
         * callback for when the colour channel cycling is triggered
         * <p>
         * This method is called whenever the ColourChannelCyclingAction is triggered.
         * Cycling the RGB values by 1 step each time
         * {@link ColourChannels}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            JPanel panel = new JPanel();

            JComboBox<String> dropdown = new JComboBox<String>(choices);
            int option = JOptionPane.showOptionDialog(null, dropdown, Andie.bundle.getString("Selectcolourchannel"),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            dropdown.setVisible(true);
            panel.add(dropdown);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                rotationNum = dropdown.getSelectedIndex();
            }

            //int rotationNum = dropdown.getSelectedIndex();
            System.out.println(rotationNum);

            target.getImage().apply(new ColourChannels(rotationNum));
            target.repaint();
            target.getParent().revalidate();
        }

    }


    /**
     * Getter method to get the actions for the colour actions
     * @return all available actions
     */
    public ArrayList<Action> getActions() {
        ArrayList<Action> allActions = actions;
        return allActions;
    }
    
}
//option pane 
//get selcteditem 