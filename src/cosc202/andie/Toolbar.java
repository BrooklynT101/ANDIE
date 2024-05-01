package cosc202.andie;

import java.util.*;
import java.awt.*;
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

    private String name;
    private int maxActions;
    private static final int DefaultMaxSize = 10;

    protected ArrayList<Action> currentActions;

    // TODO: allow user to add and remove actions from tool bar, update GUI

    /**
     * Default ToolBar constructor, takes no parameters and loads the default values
     * and actions in the ToolBar class.
     */
    public ToolBar() {
        // create default values
        this.name = "ToolBar";
        this.maxActions = DefaultMaxSize;
        currentActions = new ArrayList<>();
        loadDefaultActions();
    }

    /**
     * ToolBar constructor, takes in a name and max size, then loads a Default list
     * of actions to the ToolBar instance.
     * 
     * @param name       the name of the ToolBar
     * @param maxActions the max number of Actions to add to the ToolBar
     */
    public ToolBar(String name, int maxActions, ArrayList<Action> actions) {
        this.name = name;
        this.maxActions = maxActions;
        currentActions = new ArrayList<>();
        for (Action action : actions) {
            this.addAction(action, loadIcon(action.getValue("Name").toString()));
        }
    }

    /**
     * ToolBar constructor, takes in a name and max size, then loads a Default list
     * of actions to the ToolBar instance.
     * 
     * @param name       the name of the ToolBar
     * @param maxActions the max number of Actions to add to the ToolBar
     */
    public ToolBar(String name, int maxActions) {
        this.name = name;
        this.maxActions = maxActions;
        currentActions = new ArrayList<>();
        loadDefaultActions();
    }

    /**
     * Takes in an action, creates a new JButton, adds it as a component to this
     * ToolBar (@see java.awt.Container).
     * 
     * Mutator method to add an action from the list, returning true or false
     * depending on the outcome.
     * 
     * @param action the action to add
     * @return true for successful addition or false for a failed operation
     */
    public boolean addAction(Action action) {
        if (currentActions.size() >= this.maxActions) {
            System.err.println("Too many actions in the ToolBar!");
            return false;
        } else {
            currentActions.add(action);
            System.err.println("Added action " + action.getValue("Name") + "!");
            this.add(new JButton((Action) action));

            String actionName = action.getValue("Name").toString();
            boolean result = hasAction(actionName);
            if (result == false)
                System.err.println(actionName + " wasn't added!");
            return result;
        }
    }

    /**
     * Overloaded addAction method, allows you to add an icon as well as an action.
     * Creates a new JButton, adds it as a component to this ToolBar (@see
     * java.awt.Container).
     * 
     * Keeps continuity with the currentActions list too, keeping to the constraint
     * of maxActions.
     * 
     * @param action the action to add
     * @param icon   the icon to add to the action
     * @return true for successful removal or false for a failed operation
     */
    public boolean addAction(Action action, Icon iconName) {
        // If we're at our limits
        if (currentActions.size() >= this.maxActions) {
            System.err.println("Too many actions in the ToolBar!");
            return false;
        }
        // Nah we good;
        String actionName = action.getValue("Name").toString();
        // Create the button
        JButton button = new JButton(actionName);
        button.setAction(action);
        button.setIcon(loadIcon(actionName));
        // Add the button
        this.add(button);
        // Add the action
        currentActions.add(action);
        // Return if it worked
        return this.hasAction(actionName);
    }

    /**
     * Getter method that retrieves a given action name as a String, throwing an
     * exception if not found or the ToolBar currentActions list is empty.
     * 
     * @param actionName
     * @return the requested Action object.
     * @throws Exception
     */
    public Action getAction(String actionName) throws Exception {
        if (currentActions.isEmpty()) {
            System.err.println("Action list is empty!");
            throw new Exception("No actions loaded to ToolBar (currentActions == null)");
        }

        for (Action action : currentActions) {
            String name = (String) action.getValue("Name");
            System.out.println(name + " found!");
            if (actionName.equals(name)) {
                return action;
            }
        }
        throw new Exception("Action not found!");
    }

    /**
     * Tries to find an action in the list by a string.
     * 
     * @param actionName
     * @return true if found, false if not found or list is empty
     */
    public boolean hasAction(String actionName) {
        if (currentActions.isEmpty()) {
            System.err.println("Nothing in currentActions!");
            return false;
        }

        for (Action action : currentActions) {
            String name = (String) action.getValue("Name");
            if (name.equals(actionName)) {
                return true;
            }
        }
        System.err.println("hasAction() - couldn't find action ");
        return false; // cant find it if we're here
    }

    /**
     * Mutator method to remove an action from the list, returning true or false
     * depending on the outcome.
     * 
     * @see addAction
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
     * Getter method to return the current loaded list of actions
     * 
     * @param name
     * @return the currently loaded ArrayList<Action> of actions
     */
    public ArrayList<Action> getCurrentActions() {
        return currentActions;
    }

    /**
     * Getter method to return the current loaded list of JButtons
     * 
     * @param name
     * @return the currently loaded ArrayList<Action> of actions
     */
    public Component[] getButtons() {
        return this.getComponents();
    }

    /**
     * Getter method for finding the wanted icon in the builds' resource folder,
     * note the icon needs the same name as the action as defined in the parameters
     * when you create a new action.
     * 
     * This takes in the name of the icon, tries to load the class resources, and
     * find the given icon.
     * 
     * @param name
     * @return the requested icon if found, null otherwise
     */
    public Icon loadIcon(String name) {
        try {
            URL url = Andie.class.getClassLoader().getResource(name + ".png");
            Image image = null;
            if (url != null) {
                image = ImageIO.read(url);
            } else {
                System.err.println("Couldn't create url for " + name);
                return null;
            }
            Image newImg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
            Icon icon = new ImageIcon(newImg);
            return icon;

        } catch (Exception e) {
            System.out.println("Couldn't create the icon " + name + ", couldnt find the resource");
            e.printStackTrace();
            return null;
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
        // Clear everything else first
        removeAllActions();

        // iterate over each action in list
        for (Action action : actions) {
            // Get the name
            String actionName = (String) action.getValue("Name");
            // Find the icon
            Icon icon = null;
            icon = loadIcon(actionName);
            if (icon == null) {
                System.err.println("Could not find icon " + actionName + ".png");
            }
            // Create the button
            JButton button = new JButton();
            button.setAction(action);
            // Add the button
            this.add(button);

            //Use the icon to display if there is one
            if (icon != null) {
                button.setIcon(icon);
                button.setText("");
            } else {
                button.setText(actionName);
            }
            
            // Add the action
            currentActions.add(action);
            System.out.println("Created button " + actionName);
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

        // Macro - Start Recording, Macro - Stop Recording 
        ArrayList<Action> macroActions = MA.getActions();
        defaultActions.add(macroActions.get(1));
        defaultActions.add(macroActions.get(2));
        
        // // Selection, Crop TODO: Add Selection and Crop when marcus does the mouse
        // stuff
        // ArrayList<Action> mouseSelectionActions = MouseSelectionActions.getActions();
        // defaultActions.add(mouseSelectionActions.get(0));
        // defaultActions.add(mouseSelectionActions.get(1));

        // // Drawbox, DrawLine TODO: Add Drawbox and DrawLine when marcus does the draw
        // stuff
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
    public void setMaxSize(int maxActions) {
        this.maxActions = maxActions;
    }

    /**
     * Getter method for the toolbar instance max size
     * 
     * @return
     */
    public int getMaxSize() {
        return this.maxActions;
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
