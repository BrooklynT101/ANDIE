package cosc202.andie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.AbstractAction;
import javax.swing.Action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Tester for the ToolBar class, making sure the toolbar methods function
 * correctly
 * 
 * @author BrooklynT
 */
public class ToolBarTest {
    /**
     * Test to verify that open is correctly being added to the ToolBar and its
     * values are set correctly
     */
    @Test
    void checkOpen() {
        ToolBar toolBar = new ToolBar();
        Assertions.assertTrue(toolBar.hasAction("Open"));
    }

    /**
     * Test to verify that addAction() method adds an action to the toolbar
     */
    @Test
    void addAction() {
        ToolBar toolBar = new ToolBar();

        // Create a dummy do nothing action
        Action dummyAction = new DummyAction("dummyAction");
        toolBar.addAction(dummyAction);
        // Assertions.assertTrue();
        Assertions.assertTrue(toolBar.hasAction(dummyAction.getValue("Name").toString()));
    }

    /**
     * Adds an action and tries to get it back, verifies the addAction() and
     * getAction() methods are working
     */
    @Test
    void getAction() {
        ToolBar toolBar = new ToolBar();

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
        ToolBar toolBar = new ToolBar();

        // Create a dummy do nothing action
        Action dummyAction = new DummyAction("dummyAction");

        toolBar.addAction(dummyAction);
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
        ToolBar toolBar = new ToolBar();
        toolBar.loadDefaultActions();
        int expectedSize = 8; // Number of default actions
        Assertions.assertEquals(expectedSize, toolBar.currentActions.size());
    }

    /**
     * 
     */
    @Test
    void addOpenWithIcon() {
        ToolBar toolBar = new ToolBar();

        // Create a dummy do nothing action
        Action dummyAction = new DummyAction("dummyAction");
        toolBar.addAction(dummyAction);
        // Assertions.assertTrue();
        Assertions.assertTrue(toolBar.hasAction(dummyAction.getValue("Name").toString()));
    }
    /**
     * Tests the constraints of the ToolBar, ensuring the addAction method doesn't
     * go above the limit
     */
    @Test
    void maxSize() {
        int maxSize = 11;
        ArrayList<Action> actions = new ArrayList<>();
        ToolBar toolBar = new ToolBar("MaxSizeTest", maxSize, actions);
        for (int i = 2; i <= maxSize; i++) {
            toolBar.addAction(new DummyAction("dummyAction"));
        }
        System.out.println("Current number of components " + toolBar.getComponentCount());

        // should be at 10
        Assertions.assertTrue(toolBar.getComponentCount() < maxSize);

        // add another, ensure its at maxSize
        toolBar.addAction(new DummyAction("dummyAction"));
        System.out.println("Current number of components " + toolBar.getComponentCount());
        Assertions.assertTrue(toolBar.getComponentCount() == maxSize);

        // cant add anymore
        Assertions.assertFalse(toolBar.addAction(new DummyAction("dummyAction")));

        // stress the system
        for (int i = 2; i <= maxSize; i++) {
            toolBar.addAction(new DummyAction("dummyAction"));
        }
        // should still be at maxSize
        Assertions.assertTrue(toolBar.getComponentCount() == maxSize);
        // is not OVER maxSize
        Assertions.assertFalse(toolBar.getComponentCount() > toolBar.getMaxSize());
    }

    // Dummy Action class that implements the Action interface, just added so i can
    // create do-nothing actions to add to ToolBar and tests the instance methods.
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
