package cosc202.andie;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
    protected static ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions() {
        actions = new ArrayList<Action>();
        actions.add(new MeanFilterAction(Andie.bundle.getString("Meanfilter"), null,
                Andie.bundle.getString("Applyameanfilter"), Integer.valueOf(KeyEvent.VK_M)));

        // adding keyboard shortcut for blur
        BlurFilterAction blur = new BlurFilterAction(Andie.bundle.getString("Blurfilter"), null,
                Andie.bundle.getString("Applyasoftblur"), Integer.valueOf(KeyEvent.VK_B));
        blur.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control B"));
        actions.add(blur);

        actions.add(new SoftBlurAction(Andie.bundle.getString("BlurSoft"), null,
                Andie.bundle.getString("Applyablurfilter"), Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new MedianFilterAction(Andie.bundle.getString("Medianfilter"), null,
                Andie.bundle.getString("Applyamedianfilter"), Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new GaussianBlurAction(Andie.bundle.getString("GaussianBlur"), null,
                Andie.bundle.getString("ApplyGaussianblur"), Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new SharpenAction(Andie.bundle.getString("Sharpenfilter"), null,
                Andie.bundle.getString("Applyasharpenfilter"), Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new BlockAveragingAction(Andie.bundle.getString("BlockAveraging"), null,
                Andie.bundle.getString("ApplyBlockAveraging"), Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new RandomScatteringAction(Andie.bundle.getString("RandomScattering"), null,
                Andie.bundle.getString("Applyrandomscattering"), Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new Emboss1FilterAction("Emboss Filters", null, null, Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new SobelFilterAction("Sobel Filters", null, null, Integer.valueOf(KeyEvent.VK_S)));
    }

    /**
     * <p>
     * Create a menu containing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(Andie.bundle.getString("Filter"));

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
         * Callback for when the mean filter action is triggered.
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
            // Prevents adding no 1 - 10 integers, by making the text field un editable by
            // default.
            ((DefaultEditor) radiusSpinner.getEditor()).getTextField().setEditable(false);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, Andie.bundle.getString("Enterfilterradius"),
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

    /**
     * <p>
     * Action to blur an image a given number of times.
     * <p>
     * 
     * <p>
     * Note that despite the number of filter operations, its still considered a
     * single action.
     * <p>
     * 
     * @see BlurFilter
     * @author Brooklyn
     */
    public class BlurFilterAction extends ImageAction {
        /**
         * Create a new blur filter action.
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        BlurFilterAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the BlurFilterAction is triggered.
         * It prompts the user for a value, then blurs the image that number of times
         * {@link BlurFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Runs at least once
            int repeat = 1;

            // Pop-up dialog box to ask for the desired repeated operations.
            SpinnerNumberModel repeatModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner repeatSpinner = new JSpinner(repeatModel);
            // Prevents adding no 1 - 10 integers, by making the text field un editable by
            // default.
            ((DefaultEditor) repeatSpinner.getEditor()).getTextField().setEditable(false);
            int option = JOptionPane.showOptionDialog(null, repeatSpinner,
                    Andie.bundle.getString("RepeatFilter"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                repeat = repeatModel.getNumber().intValue();
            }

            // Create and apply the filter 'repeat' number of time
            target.getImage().apply(new BlurFilter(repeat));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to apply a soft blur to an image once, taken from the lab book example
     * <p>
     * 
     * @see SoftBlur
     * @author Ari
     */
    public class SoftBlurAction extends ImageAction {

        /**
         * Create a new blur filter action.
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

        /**
         * <p>
         * This method is called whenever the SoftBlurAction is triggered.
         * {@link SoftBlur}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new SoftBlur());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to "sharpen" an image a number of times.
     * <p>
     * 
     * <p>
     * Note that despite the number of filter operations, its still considered a
     * single action.
     * <p>
     * 
     * @see SharpenFilter
     * @author Brooklyn
     */
    public class SharpenAction extends ImageAction {

        /**
         * Create a new Sharpen filter action.
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SharpenAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the SharpenAction is triggered.
         * It prompts the user for a value, then sharpens the image that number of times
         * {@link SharpenFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Determine the number of times to apply the sharpen - ask the user.
            int repeat = 1;

            // Prompt the user for the number of times to apply the filter
            SpinnerNumberModel repeatModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner repeatSpinner = new JSpinner(repeatModel);
            // Prevents adding no 1 - 10 integers, by making the text field un editable by
            // default.
            ((DefaultEditor) repeatSpinner.getEditor()).getTextField().setEditable(false);
            int option = JOptionPane.showOptionDialog(null, repeatSpinner,
                    Andie.bundle.getString("RepeatFilter"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                repeat = repeatModel.getNumber().intValue();
            }

            // Create and apply the filter 'repeat' number of times
            target.getImage().apply(new SharpenFilter(repeat));
            target.repaint();
            target.getParent().revalidate();

        }

    }

    /**
     * <p>
     * Action to apply a gaussian blur to an image determined by a radius value.
     * <p>
     * 
     * @see GaussianBlur
     * @author Ari
     */
    public class GaussianBlurAction extends ImageAction {

        /**
         * Create a new gaussian blur filter action.
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        GaussianBlurAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the GaussianBlurAction is triggered.
         * It prompts the user for a radius, then blurs the image based on that radius.
         * {@link GaussianBlur}.
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
            // Prevents adding no 1 - 10 integers, by making the text field un editable by
            // default.
            ((DefaultEditor) radiusSpinner.getEditor()).getTextField().setEditable(false);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, Andie.bundle.getString("Enterfilterradius"),
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

    /**
     * <p>
     * Action to blur an image with a median filter.
     * </p>
     * 
     * @see MedianFilter
     * @author Marcus
     */
    public class MedianFilterAction extends ImageAction {

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
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the median filter action is triggered.
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
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            // Prevents adding no 1 - 10 integers, by making the text field un editable by
            // default.
            ((DefaultEditor) radiusSpinner.getEditor()).getTextField().setEditable(false);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, Andie.bundle.getString("Enterfilterradius"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();

            }

            // Create and apply the filter
            if (5 <= radius) {
                JFrame frame = new JFrame();
                int cont = JOptionPane.showConfirmDialog(frame, Andie.bundle.getString("JustLoading,PleasebePatient"));
                if (cont == JOptionPane.OK_OPTION) {
                    target.getImage().apply(new MedianFilter(radius));
                    target.repaint();
                    target.getParent().revalidate();
                }
            } else {
                target.getImage().apply(new MedianFilter(radius));
                target.repaint();
                target.getParent().revalidate();
            }
        }

    }

    /**
     * <p>
     * Action to do the block averaging filter
     * <p>
     * 
     * @see BlockAveraging
     * @author Amelia
     */
    public class BlockAveragingAction extends ImageAction {
        /**
         * Create a new blur filter action.
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        BlockAveragingAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the block averaging action is triggered.
         * It prompts the user for a width value and a height value for the block size
         * and the block averaging is applied to a block of the given size
         * {@link BlockAveraging}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Runs at least once
            int blockWidth = 1;
            int blockHeight = 1;

            JPanel spinnerPanel = new JPanel();

            // Pop-up dialog box to ask for the desired repeated operations.
            SpinnerNumberModel xModel = new SpinnerNumberModel(1, 1, 50, 1);
            SpinnerNumberModel yModel = new SpinnerNumberModel(1, 1, 50, 1);
            JSpinner xSpinner = new JSpinner(xModel);
            JSpinner ySpinner = new JSpinner(yModel);
            // Prevents adding no 1 - 10 integers, by making the text field un editable by
            // default.
            ((DefaultEditor) xSpinner.getEditor()).getTextField().setEditable(false);
            ((DefaultEditor) ySpinner.getEditor()).getTextField().setEditable(false);

            spinnerPanel.add(xSpinner);
            spinnerPanel.add(ySpinner);

            int option1 = JOptionPane.showOptionDialog(null, xSpinner,
                    Andie.bundle.getString("EnterRadiusWidth"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            int option2 = JOptionPane.showOptionDialog(null, ySpinner,
                    Andie.bundle.getString("EnterRadiusHeight"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            // if (option1 == JOptionPane.CANCEL_OPTION || option1 == JOptionPane.CANCEL_OPTION) {
            //     return;
            // } else if (option1 == JOptionPane.OK_OPTION && option2 == JOptionPane.OK_OPTION) {
            //     blockWidth = xModel.getNumber().intValue();
            //     blockHeight = yModel.getNumber().intValue();
            // }

            if (option1 == JOptionPane.OK_OPTION && option2 == JOptionPane.OK_OPTION) {
                blockWidth = xModel.getNumber().intValue();
                blockHeight = yModel.getNumber().intValue();
                // Create and apply the filter wth the blockSIze
                target.getImage().apply(new BlockAveraging(blockWidth, blockHeight));
                target.repaint();
                target.getParent().revalidate();
            }

            
        }
    }

    /**
     * <p>
     * Action to do the random scattering filter
     * <p>
     * 
     * @see RandomScattering
     * @author Amelia
     */
    public class RandomScatteringAction extends ImageAction {
        /**
         * Create a new blur filter action.
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RandomScatteringAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the random scattering action is triggered.
         * It prompts the user for a value, then creates a neighbourhood of the given
         * size
         * to apply the random scattering.
         * {@link RandomScattering}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Runs at least once
            int blockSize = 1;

            // Pop-up dialog box to ask for the desired repeated operations.
            SpinnerNumberModel repeatModel = new SpinnerNumberModel(1, 1, 100, 1);
            JSpinner repeatSpinner = new JSpinner(repeatModel);
            // Prevents adding no 1 - 10 integers, by making the text field un editable by
            // default.
            ((DefaultEditor) repeatSpinner.getEditor()).getTextField().setEditable(false);
            int option = JOptionPane.showOptionDialog(null, repeatSpinner,
                    Andie.bundle.getString("Enterfilterradius2"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.OK_OPTION) {
                blockSize = repeatModel.getNumber().intValue();
                // Create and apply the filter wth the blockSIze
                target.getImage().apply(new RandomScattering(blockSize));
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    /**
     * <p>
     * Action to apply a Emboss Filter to an image determined by a radius value.
     * <p>
     * 
     * @see Emboss1Filter
     * @author Ari
     */
    public class Emboss1FilterAction extends ImageAction {
        int optionindex = 0;
        private final String[] options = { "Left to Right", "Top Left to Bottem Right", "Top to Bottom",
                "Top Right to Bottem Left", "Right to Left", "Bottem Right to Top Left", "Bottom to Top",
                "Top Right to Bottom Left" };

        /**
         * Create a new Emboss filter action.
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        Emboss1FilterAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the Emboss1FilterAction is triggered.
         * It prompts the user for a radius, then blurs the image based on that radius.
         * {@link Emboss1Filter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JPanel panel = new JPanel();

            JComboBox<String> dropdown = new JComboBox<String>(options);
            int option = JOptionPane.showOptionDialog(null, dropdown, Andie.bundle.getString("EmbossOption"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            dropdown.setVisible(true);
            panel.add(dropdown);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                int opt = dropdown.getSelectedIndex();
                // Create and apply the filter
                if (opt == 0) {
                    target.getImage().apply(new Emboss1Filter(1));
                }
                if (opt == 1) {
                    target.getImage().apply(new Emboss2Filter(1));
                }
                if (opt == 2) {
                    target.getImage().apply(new Emboss3Filter(1));
                }
                if (opt == 3) {
                    target.getImage().apply(new Emboss4Filter(1));
                }
                if (opt == 4) {
                    target.getImage().apply(new Emboss5Filter(1));
                }
                if (opt == 5) {
                    target.getImage().apply(new Emboss6Filter(1));
                }
                if (opt == 6) {
                    target.getImage().apply(new Emboss7Filter(1));
                }
                if (opt == 7) {
                    target.getImage().apply(new Emboss8Filter(1));
                }

                target.repaint();
                target.getParent().revalidate();
            }
        }

    }

    /**
     * <p>
     * Action to apply a Emboss Filter to an image determined by a radius value.
     * <p>
     * 
     * @see HorizontalSobelFilter and @see VerticalSobelFilter
     * @author Ari
     */
    public class SobelFilterAction extends ImageAction {
        int optionindex = 0;
        private final String[] options = { "Horizontal Sobel", "Vertical Sobel" };

        /**
         * Create a new Emboss filter action.
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SobelFilterAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the Emboss1FilterAction is triggered.
         * It prompts the user for a radius, then blurs the image based on that radius.
         * {@link Emboss1Filter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JPanel panel = new JPanel();

            JComboBox<String> dropdown = new JComboBox<String>(options);
            int option = JOptionPane.showOptionDialog(null, dropdown, Andie.bundle.getString("EmbossOption"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            dropdown.setVisible(true);
            panel.add(dropdown);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                int opt = dropdown.getSelectedIndex();
                // Create and apply the filter
                if (opt == 0) {
                    target.getImage().apply(new HorizontalSobelFilter(1));
                }
                if (opt == 1) {
                    target.getImage().apply(new VerticalSobelFilter(1));
                }

                target.repaint();
                target.getParent().revalidate();
            }
        }

    }

    /**
     * Getter method to get the actions for the colour actions
     * 
     * @return all available actions
     */
    public ArrayList<Action> getActions() {
        ArrayList<Action> allActions = actions;
        return allActions;
    }
}
