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

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * A Toolbar class that creates a JToolbar that allows a user
 * to access commonly used tools to manipulate the image
 * 
 * @author Brooklyn Taylor
 */
@SuppressWarnings("unused")
public class ToolBar extends JToolBar {

    private String name;
    private int maxSize;
    protected ArrayList<Action> actions;
    protected Icon icon;

    /**
     * default constructor, should create the action list and create the
     * action components as buttons
     */
    public ToolBar(String name, int maxSize) {
        // create action list
        this.name = name;
        this.maxSize = maxSize;
        // // Add actions to toolbar buttons
        // TODO: allow user to add and remove actions from tool bar, update GUI
    }

    public boolean addAction(Action action) {
        if (actions.size() >= maxSize) {
            return false;
        } else {
            actions.add(action);
            this.add(action);
            return true;
        }
    }

    public boolean removeAction(Action action) {
        if (actions.isEmpty()) {
            return false;
        } else {
            actions.remove(action);
            loadActions(actions);
            return true;
        }
    }

    public Icon loadIcon(String name) {
        try {
            BufferedImage image = ImageIO.read(Andie.class.getClassLoader().getResource(name + ".png"));
            Image newImg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // Adjust the size as needed
            Icon icon = new ImageIcon(newImg);
        } catch (Exception e) {
            System.out.println("Error loading icon.png");
            e.printStackTrace();
        }
        return icon;
    }

    /**
     * tries to save the saved actions list from file
     */
    public void saveActions() {
        // Serialize the actions list
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("actions.ser"))) {
            out.writeObject(actions);
        } catch (IOException e) {
            System.err.println("Couldn't save toolbar preferences");
            e.printStackTrace();
        }
    }

    /**
     * tries to load the saved actions list from file
     */
    public void loadActions() {
        // try dserialize the perferred common tools configuration
        actions.clear();
        try (InputStream inputStream = Andie.class.getClassLoader().getResourceAsStream("actions.ser")) {
            ObjectInputStream in = new ObjectInputStream(inputStream);
            actions = (ArrayList<Action>) in.readObject();
            for (Action action : actions) {
                this.add(action);
            }
        } catch (IOException | ClassNotFoundException e) {
            loadDefaultActions();
        }
    }

    /**
     * tries to load the given actions to the toolbar
     */
    public void loadActions(ArrayList<Action> actions) {
        for (Action action : actions) {
            this.add(action);
        }
    }

    /**
     * Load a set of default nothing buttons
     */
    public void loadDefaultActions() {
        loadIcon("icon");
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
                System.out.println("open action triggered");
            }
        };
        Action select = new AbstractAction("Select", icon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform select action
                System.out.println("open action triggered");
            }
        };
        Action crop = new AbstractAction("Crop", icon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform select action
                System.out.println("open action triggered");
            }
        };
        Action flipHori = new AbstractAction("FlipHorizontal", icon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform select action
                System.out.println("open action triggered");
            }
        };
        Action flipVert = new AbstractAction("FlipVertical", icon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform select action
                System.out.println("open action triggered");
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

        // adding actions
        actions.add(open);
        actions.add(save);
        actions.add(select);
        actions.add(crop);
        actions.add(flipHori);
        actions.add(flipVert);
        actions.add(undo);
        actions.add(redo);
        actions.add(zin);
        actions.add(zout);
    }
}
