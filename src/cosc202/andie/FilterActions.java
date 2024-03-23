package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood.
 * This includes a mean filter (a simple blur) in the sample code, but more
 * operations will need to be added.
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
public class FilterActions {

    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions() {
        actions = new ArrayList<Action>();
        actions.add(new MeanFilterAction("Mean filter", null, "Apply a mean filter", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new BlurOptionAction("Blur Soft", null, "Apply a soft blur", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new SoftBlurAction("Blur filter", null, "Apply a blur filter", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new SharpenAction("Sharpen filter", null, "Apply a sharpen filter", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new GaussianBlurAction("Gaussian Blur", null, "Apply Gaussian blur", Integer.valueOf(KeyEvent.VK_M)));

    }

    /**
     * <p>
     * Create a menu containing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Filter");

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     * 
     * @see MeanFilter
     */
    public class MeanFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized
         * {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter filter radius",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new MeanFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    // From lab book
    public class BlurOptionAction extends ImageAction {
        BlurOptionAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new BlurOption());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    // taken from the lab book example
    /**
     * 
     */
    public class SoftBlurAction extends ImageAction {
        
        /**
         * Create a new sharpen filter action.
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SoftBlurAction(String name, ImageIcon icon,
            String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Determine the number of times to apply the blur - ask the user.
            int repeat = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel repeatModel = new SpinnerNumberModel(1, 1, 100000, 1);
            JSpinner repeatSpinner = new JSpinner(repeatModel);
            int option = JOptionPane.showOptionDialog(null, repeatSpinner, "Enter SoftBlur Intensity",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                repeat = repeatModel.getNumber().intValue();
            }

            //TODO: this need to be considered a single action so when you redo it redos the whole stack
            // Create and apply the filter 'repeat' number of times
            for (int i = 1; i < repeat; i++) {
                target.getImage().apply(new SoftBlur());
                target.repaint();
                target.getParent().revalidate();
            }
        }
        
    }

    /**
     * Action to blur an image with a mean filter.
     * 
     * @see SharpenFilter
     */
    public class SharpenAction extends ImageAction {
        
        SharpenAction(String name, ImageIcon icon,
            String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Determine the number of times to apply the sharpen - ask the user.
            int repeat = 1;

            // Pop-up dialog box to ask for the "intensity" or actually, the amount of times to apply the filter.
            SpinnerNumberModel repeatModel = new SpinnerNumberModel(1, 1, 100000, 1);
            JSpinner repeatSpinner = new JSpinner(repeatModel);
            int option = JOptionPane.showOptionDialog(null, repeatSpinner, "Enter Sharpen Intensity",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                repeat = repeatModel.getNumber().intValue();
            }

            //TODO: this need to be considered a single action so when you redo it redos the whole stack
            // Create and apply the filter 'repeat' number of times
            for (int i = 1; i <= repeat; i++) {
                target.getImage().apply(new SharpenFilter());
                target.repaint();
                target.getParent().revalidate();
            }
        }
        
    }


    public class GaussianBlurAction extends ImageAction {
        
        GaussianBlurAction(String name, ImageIcon icon,
            String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Determine the number of times to apply the blur - ask the user.
            int repeat = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel repeatModel = new SpinnerNumberModel(1, 1, 100000, 1);
            JSpinner repeatSpinner = new JSpinner(repeatModel);
            int option = JOptionPane.showOptionDialog(null, repeatSpinner, "Enter SoftBlur Intensity",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                repeat = repeatModel.getNumber().intValue();
            }

            //TODO: this need to be considered a single action so when you redo it redos the whole stack
            // Create and apply the filter 'repeat' number of times
            for (int i = 1; i < repeat; i++) {
                target.getImage().apply(new SoftBlur());
                target.repaint();
                target.getParent().revalidate();
            }
        }
        
    }

    /**
     * Action to blur an image with a mean filter.
     * 
     * @see SharpenFilter
     */
    public class SharpenAction extends ImageAction {
        
        SharpenAction(String name, ImageIcon icon,
            String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Determine the number of times to apply the sharpen - ask the user.
            int repeat = 1;

            // Pop-up dialog box to ask for the "intensity" or actually, the amount of times to apply the filter.
            SpinnerNumberModel repeatModel = new SpinnerNumberModel(1, 1, 100000, 1);
            JSpinner repeatSpinner = new JSpinner(repeatModel);
            int option = JOptionPane.showOptionDialog(null, repeatSpinner, "Enter Sharpen Intensity",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                repeat = repeatModel.getNumber().intValue();
            }

            //TODO: this need to be considered a single action so when you redo it redos the whole stack
            // Create and apply the filter 'repeat' number of times
            for (int i = 1; i <= repeat; i++) {
                target.getImage().apply(new SharpenFilter());
                target.repaint();
                target.getParent().revalidate();
            }
        }
        
    }


    public class SharpenFilterAction extends ImageAction {
        SharpenFilterAction(String name, ImageIcon icon,
            String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter filter radius",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new Sharpen(radius));
            target.repaint();
            target.getParent().revalidate();
        }
        
    }


    public class GaussianBlurAction extends ImageAction {
        
        GaussianBlurAction(String name, ImageIcon icon,
            String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter filter radius",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new GaussianBlur(radius));
            target.repaint();
            target.getParent().revalidate();
        }
        
    }

}
