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
            // action.clone();
        }
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
    public void loadActionsToToolBar(ArrayList<Action> actions, Icon icon) {
        removeAllActions();
        // Add actions to list and ToolBar component
        for (Action action : actions) {
            this.currentActions.add(action);
            //TODO: add implementation to load icons
            this.add(new JButton(action));
        }
    }

    /**
     * Method that removes all actions from the Toolbar, including all the
     * components on the toolbar itself.
     * 
     */
    public void removeAllActions() {
        // Clear everything
        this.removeAll();
        this.currentActions.clear();
    }

    /**
     * Loads the default set of buttons
     */
    public boolean loadDefaultActions() {
        //Clear everything
        this.removeAllActions();

        //Open, Save
        ArrayList<Action> fileActions = FilA.getActions();
        currentActions.add(fileActions.get(0));
        currentActions.add(fileActions.get(0));

        //Undo, Redo
        ArrayList<Action> editActions = EA.getActions();
        currentActions.add(editActions.get(0));
        currentActions.add(editActions.get(1));

        //Zoom in, Zoom out
        ArrayList<Action> viewActions = VA.getActions();
        currentActions.add(viewActions.get(0));
        currentActions.add(viewActions.get(1));

        //Flip Horizontally, Flip Vertically TODO:create icon for resize, rotate clock/antiClock
        ArrayList<Action> imageActions = IM.getActions();
        currentActions.add(imageActions.get(4));
        currentActions.add(imageActions.get(3));
        
        //TODO: create icon for this and add
        ArrayList<Action> macroActions = MA.getActions();
        
        
        Icon icon = null;
        icon = loadIcon("icon");
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

        // Add all abstract actions to an actions list
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(save);
        actions.add(select);
        actions.add(crop);
        actions.add(flipHori);
        actions.add(flipVert);
        actions.add(undo);
        actions.add(redo);
        actions.add(zin);
        actions.add(zout);

        loadActionsToToolBar(actions);
        return true;
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
