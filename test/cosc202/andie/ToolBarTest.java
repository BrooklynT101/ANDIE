package cosc202.andie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Tester for the Toolbar class, making sure the toolbar methods function
 * correctly
 * 
 * @author BrooklynT
 */
public class ToolBarTest {
    /**
     * Test to verify that open is correctly being added to the Toolbar and its
     * values are set correctly
     */
    @Test
    void checkOpen() {
        Toolbar toolBar = new Toolbar();
        Assertions.assertTrue(toolBar.hasAction("Open"));
    }

    /**
     * Test to verify that addAction() method adds an action to the toolbar
     */
    @Test
    void addAction() {
        Toolbar toolBar = new Toolbar("Test", 15);

        // Create a dummy do nothing action
        Action dummyAction = new DummyAction("dummyAction");
        toolBar.addAction(dummyAction, null);
        Assertions.assertTrue(toolBar.hasAction(dummyAction.getValue("Name").toString()));
    }

    /**
     * Adds an action and tries to get it back, verifies the addAction() and
     * getAction() methods are working
     */
    @Test
    void getAction() {
        Toolbar toolBar = new Toolbar("Test", 15);

        String foundActionName = "";
        Action dummyAction = new DummyAction("dummyAction");
        Action foundAction = null;
        toolBar.addAction(dummyAction);
        try {
            foundAction = toolBar.getAction("dummyAction");
            foundActionName = (String) foundAction.getValue("Name");
            System.out.println(foundActionName);
        } catch (Exception e) {
            System.err.println("Tripped getAction exception " + e);
        }

        Assertions.assertEquals((Action) dummyAction, foundAction);
    }

    /**
     * Test to ensure that removeAction() method removes actions from the toolbar
     */
    @Test
    void removeAction() {
        Toolbar toolBar = new Toolbar();

        // Create a dummy do nothing action
        Action dummyAction = new DummyAction("dummyAction");

        toolBar.addAction(dummyAction, null);
        boolean result = toolBar.removeAction(dummyAction);
        Assertions.assertTrue(result);
        Assertions.assertFalse(toolBar.hasAction("dummyAction"));
    }

    /**
     * Test to ensure that loadDefaultActions() method loads correct number of
     * actions
     */
    @Test
    void loadDefaultActions() {
        Toolbar toolBar = new Toolbar();
        toolBar.loadDefaultActions();
        int expectedSize = 10; // Number of default actions
        Assertions.assertEquals(expectedSize, toolBar.activeActions.size());
    }

    /**
     * Test that verifies that the Open button is being loaded with an icon
     */
    @Test
    void addOpenWithIcon() {
        Toolbar toolBar = new Toolbar();
        Icon icon = toolBar.loadIcon("Open");
        assertNotNull(icon, "Icon should not be null");
        JButton button = new JButton("Open", icon);
        toolBar.add(button);
        assertTrue(button.getIcon() != null, "Icon should be set on the button");
    }

    /**
     * Test to check if each icon is actually loading to each default action on the
     * toolbar
     */
    @Test
    void testDefaultActionsLoadWithIcons() {
        Toolbar toolBar = new Toolbar();
        Component[] components = toolBar.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                assertNotNull(button.getIcon(), "Icon should be set on all buttons");
            }
        }
    }

    /**
     * Test to ensure that all the icons in the resources folder are there.
     */

    /**
     * Tests the constraints of the Toolbar, ensuring the addAction method doesn't
     * go above the limit
     */
    // @Test
    // void maxSize() {
    //     int maxSize = 15;
    //     ArrayList<Action> actions = new ArrayList<>();
    //     Toolbar toolBar = new Toolbar("MaxSizeTest", maxSize);
    //     for (int i = 2; i <= maxSize; i++) {
    //         toolBar.addAction(new DummyAction("dummyAction"));
    //     }
    //     System.out.println("Current number of components " + toolBar.getComponentCount());

    //     // should be at 10
    //     Assertions.assertTrue(toolBar.getComponentCount() < maxSize);

    //     // add another, ensure its at maxSize
    //     toolBar.addAction(new DummyAction("dummyAction"));
    //     System.out.println("Current number of components " + toolBar.getComponentCount());
    //     Assertions.assertTrue(toolBar.getComponentCount() == maxSize);

    //     // cant add anymore
    //     Assertions.assertFalse(toolBar.addAction(new DummyAction("dummyAction")));

    //     // stress the system
    //     for (int i = 2; i <= maxSize; i++) {
    //         toolBar.addAction(new DummyAction("dummyAction"));
    //     }
    //     // should still be at maxSize
    //     Assertions.assertTrue(toolBar.getComponentCount() == maxSize);
    //     // is not OVER maxSize
    //     Assertions.assertFalse(toolBar.getComponentCount() > toolBar.getMaxSize());
    // }

    // Dummy Action class that implements the Action interface, just added so i can
    // create do-nothing actions to add to Toolbar and tests the instance methods.
    class DummyAction extends AbstractAction {

        public DummyAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // This space intentionally left blank
        }
    }

}
