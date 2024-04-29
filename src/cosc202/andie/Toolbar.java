package cosc202.andie;

import java.util.*;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import cosc202.andie.*;
import cosc202.andie.FileActions.FileOpenAction;

/**
 * A ToolBar class that creates a JToolBar that allows a user
 * to access commonly used tools to manipulate the image
 * 
 * @author Brooklyn Taylor
 */
@SuppressWarnings("unused")
public class ToolBar extends JToolBar {

    private boolean testing = true;
    private String name;
    private int maxSize;
    private int DefaultMaxSize = 10;

    protected ArrayList<Action> actions;

        // TODO: allow user to add and remove actions from tool bar, update GUI
        
    /**
     * default constructor, should create the action list and create the
     * action components as buttons
     */
    public ToolBar() {
        // create default values
        this.name = "ToolBar";
        this.maxSize = DefaultMaxSize;
        actions = new ArrayList<>();
        if (testing)System.out.println("toolbar created");
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
        if (actions.size() >= maxSize) {
            return false;
        } else {
            actions.add(action);
            this.add(new JButton(action));
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
        if (actions.isEmpty()) {
            return false;
        } else {
            actions.remove(action);
            loadActions(actions);
            return true;
        }
    } 
     * 

    /**
     * Tries to find all available actions from the menulists
     * @return arraylist of actions containing all found actions
     */
    public ArrayList<Action> findActions() {
        ArrayList<Action> foundActions = new ArrayList<>();
        foundActions.addAll(ColourActions.getActions());
        foundActions.addAll(EditActions.getActions());
        foundActions.addAll(
        foundActions.addAll(FilterActions.getActions());
        foundActions.addAll(ImageActions.getActions());
        foundActions.addAll(ViewActions.getActions());
        return foundActions; 
    }

    /**
     * Getter method for finding the wanted icon in the builds' resource folder
     * 
     * @param name
     * @return the requested icon if found, null otherwise
     */
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
            out.writeObject(actions);
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
        // try dserialize the perferred common tools configuration
        actions.clear();
        try (InputStream inputStream = Andie.class.getClassLoader().getResourceAsStream("actions.ser")) {
            ObjectInputStream in = new ObjectInputStream(inputStream);
            actions = (ArrayList<Action>) in.readObject();
            for (Action action : actions) {
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
    public void loadActions(ArrayList<Action> actions) {
        // Clear everything
        this.removeAll();
        this.actions.clear();
        // Add acitons to list and ToolBar component
        for (Action action : actions) {
            this.actions.add(action);
            this.add(action);
        }
    }

    /**
     * Loads the default set of buttons
     */
    public boolean loadDefaultActions() {
        // Clear everything
        this.removeAll();
        this.actions.clear();
        Icon icon = null;
        try {
            icon = loadIcon("icon");
        } catch (Exception e) {
        }
        Action open = new AbstractAction("Open", icon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform select action
                System.out.println("open action triggered");
            }
        };

        Action save = new AbstractAction("Save", icon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform select action
                System.out.println("save action triggered");
            }
        };
        Action select = new AbstractAction("Select", icon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform select action
                System.out.println("select action triggered");
            }
        };
        Action crop = new AbstractAction("Crop", icon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform select action
                System.out.println("crop action triggered");
            }
        };
        Action flipHori = new AbstractAction("FlipHorizontal", icon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform select action
                System.out.println("fliphori action triggered");
            }
        };
        Action flipVert = new AbstractAction("FlipVertical", icon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform select action
                System.out.println("flipvert action triggered");
            }
        };
        Action undo = new AbstractAction("Undo", icon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform select action
                System.out.println("undo action triggered");
            }
        };
        Action redo = new AbstractAction("Redo", icon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform select action
                System.out.println("redo action triggered");
            }
        };
        Action zin = new AbstractAction("Zoom out", icon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform select action
                System.out.println("zin action triggered");
            }
        };
        Action zout = new AbstractAction("Zoom in", icon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform select action
                System.out.println("zout action triggered");
            }
        };

        actions.add(save);
        actions.add(select);
        actions.add(crop);
        actions.add(flipHori);
        actions.add(flipVert);
        actions.add(undo);
        actions.add(redo);
        actions.add(zin);
        actions.add(zout);

        //
        loadActions(actions);
        return true;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Action action : actions) {
            builder.append(action.toString()).append("\n");
        }
        return builder.toString();
    }
}
