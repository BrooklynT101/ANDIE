package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
/**
 * A Toolbar class that creates a JToolbar that allows a user
 * to access commonly used tools to manipulate the image
 * 
 * @author Brooklyn Taylor
 */
public class Toolbar extends JFrame {

    private ArrayList<Action> actions;
    /**
     * default constructor, should create the action list and create the 
     * action components as buttons
     */
    public Toolbar() {
        // create action list
        actions = new ArrayList<Action>();
        // add each action to the list (could store it in the prefs?)
        // for each action, try to load the action.png

        //add options to add and remove actions from tool bar, update GUI each time


        // get icon block code
        // try
        // {
        // Image image =
        // ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
        // frame.setIconImage(image);
        // }catch(
        // Exception e)
        // {
        // System.out.println("Error loading icon.png");
        // e.printStackTrace();
        // }
    }

}
