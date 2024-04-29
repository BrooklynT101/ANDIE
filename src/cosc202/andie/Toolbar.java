package cosc202.andie;

import java.util.*;
import java.awt.Image;
import java.awt.image.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * A ToolBar class that creates a JToolBar that allows a user
 * to access commonly used tools to manipulate the image
 * 
 * @author Brooklyn Taylor
 */
public class ToolBar extends JToolBar {

    // Create actions
    private FileActions FilA = new FileActions();
    private EditActions EA = new EditActions();
    private ViewActions VA = new ViewActions();
    private FilterActions FiltA = new FilterActions();
    private ColourActions CA = new ColourActions();
    private ImageActions IM = new ImageActions();
    private MacroActions MA = new MacroActions();

    private boolean testing = true;
    private String name;
    private int maxSize;
    private int DefaultMaxSize = 10;

    protected ArrayList<Action> currentActions;

    // TODO: allow user to add and remove actions from tool bar, update GUI

    /**
     * default constructor, should create the action list and create the
     * action components as buttons, a default do-nothing set of buttons
     */
    public ToolBar() {
        // create default values
        this.name = "ToolBar";
        this.maxSize = DefaultMaxSize;
        currentActions = new ArrayList<>();
        if (testing)
            System.out.println("toolbar created");
        loadDefaultActions();
    }

    public ToolBar(String name, int maxSize, ArrayList<Action> actions) {
        this.name = name;
        this.maxSize = maxSize;
        for (Action action : actions) {
            // TODO: create a deepCopy() method
            // action.clone();
        }
    }

    public ToolBar(String name, int maxSize) {
        this.name = name;
        this.maxSize = maxSize;
        currentActions = new ArrayList<>();
        loadDefaultActions();
    }

    /**
     * Mutator method to add an action from the list, returning true or false
     * depending on the outcome.
     * 
     * @param action the action to remove
     * @return true for successful removal or false for a failed operation
     */
    public boolean addAction(Action action) {
        if (currentActions.size() >= maxSize) {
            return false;
        } else {
            currentActions.add(action);
            this.add(new JButton((Action) action));
            return true;
        }
    }

    /**
     * Mutator method to remove an action from the list, returning true or false
     * depending on the outcome.
     * 
     * @param action the action to remove
     * @return true for successful removal or false for a failed operation
     */
    public boolean removeAction(Action action) {
        if (currentActions.isEmpty()) {
            return false;
        } else {
            currentActions.remove(action);
            loadActionsToToolBar(currentActions);
            return true;
        }
    }

    // TODO: need to have two lists, active actions and non-active actions to
    // support adding and removing. Each should be adjusted each time an action is
    // added or removed, itll just be easier to display used actions and what could
    // be added via the prompt
    /**
     * Tries to find all available actions from the menulists
     * 
     * @return arraylist of actions containing all found actions
     */
    public ArrayList<Action> getAllActions() {
        ArrayList<Action> foundActions = new ArrayList<>();

        foundActions.addAll(FilA.getActions());
        foundActions.addAll(EA.getActions());
        foundActions.addAll(VA.getActions());
        foundActions.addAll(FiltA.getActions());
        foundActions.addAll(CA.getActions());
        foundActions.addAll(IM.getActions());
        foundActions.addAll(MA.getActions());

        return foundActions;
    }

    /**
     * Getter method for finding the wanted icon in the builds' resource folder
     * 
     * @param name
     * @return the requested icon if found, null otherwise
     */
    //TODO: try tossing a getter for the icon when creating the action in say fileactions, who knows at this point im tired
    public Icon loadIcon(String name) {
        URL url = null;

        // try to find the url
        try {
            url = Andie.class.getResource("/resources/" + name + ".png");
        } catch (NullPointerException e) {
            System.out.println("Couldn't create the url, couldnt find the resource");
        }

        // if the url is nothing
        if (url == null) {
            System.out.println("Couldn't find the url, url is NULL!");
            return null;
        } else {
            System.out.println("Found url! " + url.toString());
            try {
                // BufferedImage image =
                // ImageIO.read(Andie.class.getClassLoader().getResource(name + ".png"));
                BufferedImage image = ImageIO.read(url);
                Image newImg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // Adjust the size
                Icon icon = new ImageIcon(newImg);
                return icon;
            } catch (Exception e) {
                System.out.println("Error loading " + name + ".png: " + e);
                return null;
            }
        }
    }

    /**
     * 
     */
    public void saveActions() {
        // Serialize the actions list
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("actions.ser"))) {
            out.writeObject(currentActions);
        } catch (IOException e) {
            System.err.println("Couldn't save ToolBar preferences");
            e.printStackTrace();
        }
    }

    /**
     * tries to load the saved actions list from file
     */
    @SuppressWarnings("unchecked") // TODO: remove this at some stage
    public void loadActionsFromFile() {
        // clear everything that may be there
        this.removeAllActions();

        // try dserialize the perferred common tools configuration
        try (InputStream inputStream = Andie.class.getClassLoader().getResourceAsStream("actions.ser")) {
            ObjectInputStream in = new ObjectInputStream(inputStream);
            currentActions = (ArrayList<Action>) in.readObject();
            for (Action action : currentActions) {
                this.add(action);
            }
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            loadDefaultActions();
        }
    }

    /**
     * Removes all stored and active actions and
     * loads the given actions to the toolbar
     * 
     * @param actions the list of actions to load
     */
    public void loadActionsToToolBar(ArrayList<Action> actions) {
        removeAllActions();
        // Add actions to list and ToolBar component
        for (Action action : actions) {
            currentActions.add(action);
            // TODO: add implementation to load icons
            this.add(new JButton(action));
        }
    }

    /**
     * Method that removes all actions from the Toolbar, including all the
     * components on the toolbar itself.
     * 
     */
    public boolean removeAllActions() {
        // Try to clear everything
        try {
            this.removeAll();
            this.currentActions.clear();
            return true;
        } catch (Exception e) {
            System.err.println("Toolbar was already empty");
            return false;
        }

    }

    /**
     * Loads the default set of buttons
     */
    public void loadDefaultActions() { // TODO: does this need to return a boolean for successfull shit?
        // Create arraylist for default actions
        ArrayList<Action> defaultActions = new ArrayList<>();

        // Clear everything
        this.removeAllActions();

        // Open, Save
        ArrayList<Action> fileActions = FilA.getActions();
        defaultActions.add(fileActions.get(0));
        defaultActions.add(fileActions.get(2));

        // Undo, Redo
        ArrayList<Action> editActions = EA.getActions();
        defaultActions.add(editActions.get(0));
        defaultActions.add(editActions.get(1));

        // TODO: create icon for resize, rotate clock/antiClock
        // Flip Horizontally, Flip Vertically 
        ArrayList<Action> imageActions = IM.getActions();
        defaultActions.add(imageActions.get(4));
        defaultActions.add(imageActions.get(3));

        // Zoom in, Zoom out
        ArrayList<Action> viewActions = VA.getActions();
        defaultActions.add(viewActions.get(0));
        defaultActions.add(viewActions.get(1));

        // TODO: create icon for this and add
        ArrayList<Action> macroActions = MA.getActions();

        // // Selection, Crop TODO: Add Selection and Crop when marcus does the mouse stuff
        // ArrayList<Action> mouseSelectionActions = MouseSelectionActions.getActions();
        // defaultActions.add(mouseSelectionActions.get(0));
        // defaultActions.add(mouseSelectionActions.get(1));
        
        // // Drawbox, DrawLine TODO: Add Drawbox and DrawLine when marcus does the draw stuff
        // ArrayList<Action> drawActions = DrawingActions.getActions();
        // defaultActions.add(drawActions.get(0));
        // defaultActions.add(drawActions.get(1));

        loadActionsToToolBar(defaultActions);
        return;
    }

    /**
     * Setter method for the toolbar instance max size
     * 
     * @return
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Getter method for the toolbar instance max size
     * 
     * @return
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Setter method for the toolbar instance max size
     * 
     * @return
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for the toolbar instance max size
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * toString Method that concatenates the action names to a string and returns a
     * vertical list of the current actions on the toolbar
     * 
     * @return a list of all the current action names
     * 
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Action action : currentActions) {
            builder.append(action.toString()).append("\n");
        }
        return builder.toString();
    }

}
