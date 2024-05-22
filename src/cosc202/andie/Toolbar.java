package cosc202.andie;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Custom toolbar class that supports adding and removing action and saving user
 * prefs to a file
 * 
 * @author Brooklyn Taylor
 */
public class Toolbar extends JToolBar {

    public static int CURRENT_ORIENTATION;
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
    private static final int DefaultMaxSize = 1000;

    protected ArrayList<Action> activeActions;
    protected ArrayList<Action> inactiveActions;
    private boolean testing = false; // set to true to print statements for whats happening

    /**
     * Default constructor
     */
    public Toolbar() {
        this.name = "ToolBar";
        this.maxActions = DefaultMaxSize;
        activeActions = new ArrayList<>();
        loadActionsFromFile();
    }

    // public Toolbar(String name, int maxActions, ArrayList<Action> actions) {
    // this.name = name;
    // this.maxActions = maxActions;
    // this.inactiveActions = getAllActions();
    // activeActions = new ArrayList<>();
    // for (Action action : actions) {
    // this.addAction(action, loadIcon(action.getValue(Action.NAME).toString()));
    // }
    // this.inactiveActions.removeAll(actions);
    // }

    public Toolbar(String name, int maxActions) {
        this.name = name;
        this.maxActions = maxActions;
        activeActions = new ArrayList<>();
        loadActionsFromFile();
    }

    public boolean addAction(Action action) {
        if (activeActions.size() >= this.maxActions) {
            System.err.println("Too many actions in the ToolBar!");
            return false;
        }
        Icon icon = loadIcon(action.getValue(Action.NAME).toString());

        if (icon != null) { // if there's an icon asscioated load it
            return addAction(action, icon);
        } else { // otherwise just add it
            activeActions.add(action);
            inactiveActions.remove(action);
            System.err.println("Added action " + action.getValue(Action.NAME) + "!");
            this.add(new JButton((Action) action));

            String actionName = action.getValue(Action.NAME).toString();
            boolean result = hasAction(actionName);
            if (result == false)
                System.err.println(actionName + " wasn't added!");
            return result;
        }
    }

    public boolean addAction(Action action, Icon iconName) {
        if (activeActions.size() >= this.maxActions) {
            System.err.println("Too many actions in the ToolBar!");
            return false;
        }

        ImageAction imageAction = (ImageAction) action;
        String actionName = imageAction.getName();
        JButton button = new JButton(actionName);
        button.setAction(action);
        button.setIcon(loadIcon(actionName));
        this.add(button);
        activeActions.add(action);
        inactiveActions.remove(action);
        return this.hasAction(actionName);
    }

    public boolean addAction(String actionName) {
        for (Action action : getAllActions()) {
            if (action.getValue(Action.NAME).equals(actionName)) {
                if (testing)
                    System.out.println("Found action (with value key name) " + action.getValue(name));
                return addAction(action, loadIcon(action.getValue(Action.NAME).toString()));
            }
        }
        return false;
    }

    public Action getAction(String actionName) throws Exception {
        if (activeActions.isEmpty()) {
            System.err.println("Action list is empty!");
            throw new Exception("No actions loaded to ToolBar (activeActions == null)");
        }

        for (Action action : activeActions) {
            String name = (String) action.getValue(Action.NAME);
            if (testing)
                System.out.println(name + " found!");
            if (actionName.equals(name)) {
                return action;
            }
        }
        throw new Exception("Action not found!");
    }

    public boolean hasAction(String actionName) {
        if (activeActions.isEmpty()) {
            System.err.println("Nothing in activeActions!");
            return false;
        }

        for (Action action : activeActions) {
            String name = (String) action.getValue(Action.NAME);
            if (name.equals(actionName)) {
                return true;
            }
        }
        System.err.println("hasAction() - couldn't find action ");
        return false;
    }

    public boolean removeAction(Action action) {
        if (activeActions.isEmpty()) {
            return false;
        } else {
            activeActions.remove(action);
            loadActionsToToolBar(activeActions);
            return true;
        }
    }

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

    public ArrayList<Action> getActiveActions() {
        return activeActions;
    }

    public ArrayList<Action> getInactiveActions() {
        return inactiveActions;
    }

    public void updateActions() {
        this.activeActions = new ArrayList<>();
        for (Component comp : getComponents()) { // gets all active buttons
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                Action action = button.getAction(); // get the action from the button
                if (action != null) {
                    activeActions.add(action); // if its not nothing add the action to the list
                }
            }
        }
        inactiveActions = getAllActions();

        inactiveActions.removeAll(getActiveActions());
    }

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
            System.err.println("Couldn't create the icon " + name + ", couldnt find the resource");
            e.printStackTrace();
            return null;
        }
    }

    public void saveActions() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/resources/actions.ser"))) {
            ArrayList<String> actionNames = new ArrayList<>();
            for (Action action : activeActions) {
                actionNames.add(action.getValue(Action.NAME).toString());
            }
            out.writeObject(actionNames);
            if (testing)
                System.out.println("Saved actions.ser toolbar preferences to src / resources");
        } catch (IOException e) {
            System.err.println("Couldn't save ToolBar preferences");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadActionsFromFile() {
        if (testing) System.out.println("Loading from file, firstly removing all actions");
        this.removeAllActions();

        if (testing)
            System.out.println("Trying to load actions from file..");
        try (InputStream inputStream = Andie.class.getClassLoader().getResourceAsStream("actions.ser")) {
            ObjectInputStream in = new ObjectInputStream(inputStream);
            ArrayList<String> actionNames = (ArrayList<String>) in.readObject();
            if (actionNames.isEmpty() || actionNames == null) System.err.println("action name arraylist is null");
            for (String actionName : actionNames) {
                if (testing)
                    System.out.println("Found action in file: " + actionName);
                addAction(actionName);
            }
            if (activeActions.isEmpty()) {
                loadDefaultActions();
            }
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            System.err.println("Couldn't find saved user preferences, loading default list of actions...");
            loadDefaultActions();
        }
    }

    public void loadActionsToToolBar(ArrayList<Action> actions) {
        removeAllActions();

        for (Action action : actions) {
            String actionName = (String) action.getValue(Action.NAME);
            Icon icon = null;
            icon = loadIcon(actionName);
            if (icon == null) {
                System.err.println("Could not find icon " + actionName + ".png");
            }
            JButton button = new JButton();
            button.setAction(action);
            this.add(button);

            if (icon != null) {
                button.setIcon(icon);
                button.setText("");
            } else {
                button.setText(actionName);
            }

            activeActions.add(action);
            if (testing)
                System.out.println("Created button " + actionName);
        }
    }

    public boolean removeAllActions() {
        try {
            this.removeAll();
            this.activeActions.clear();
            this.inactiveActions = getAllActions();
            if (testing)
                System.out.println("Removed all actions from toolbar");
            return true;
        } catch (Exception e) {
            System.err.println("Toolbar was already empty");
            return false;
        }
    }

    public void loadDefaultActions() {

        ArrayList<Action> defaultActions = new ArrayList<>();
        this.removeAllActions();

        ArrayList<Action> fileActions = FilA.getActions();
        defaultActions.add(fileActions.get(0));
        defaultActions.add(fileActions.get(3));

        ArrayList<Action> editActions = EA.getActions();
        defaultActions.add(editActions.get(0));
        defaultActions.add(editActions.get(1));

        ArrayList<Action> imageActions = IM.getActions();
        defaultActions.add(imageActions.get(4));
        defaultActions.add(imageActions.get(3));

        ArrayList<Action> viewActions = VA.getActions();
        defaultActions.add(viewActions.get(0));
        defaultActions.add(viewActions.get(1));

        ArrayList<Action> macroActions = MA.getActions();
        defaultActions.add(macroActions.get(1));
        defaultActions.add(macroActions.get(2));

        loadActionsToToolBar(defaultActions);
        return;
    }

    public void setMaxSize(int maxActions) {
        this.maxActions = maxActions;
    }

    public int getMaxSize() {
        return this.maxActions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Action action : activeActions) {
            builder.append(action.toString()).append("\n");
        }
        return builder.toString();
    }
}
