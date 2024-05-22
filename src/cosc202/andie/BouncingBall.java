// package cosc202.andie;

// import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;
// import java.awt.Graphics;
// import java.awt.Color;
// import java.awt.Component;
// import java.awt.Rectangle;

// import javax.swing.Timer;
// import javax.swing.JPanel;
// import javax.swing.JMenuItem;
// import javax.swing.AbstractButton;

// public class BouncingBall extends JPanel {

//     private final static boolean testing = true;
//     private int x;
//     private int y;
//     private int speed = 4;
//     private int xSpeed = speed;
//     private int ySpeed = speed;
//     private Rectangle frameBounds;
//     private Timer timer;

//     // creates a transparent color
//     private Color transparentColor = new Color(0, 0, 0, 0); // RGB values and alpha (transparency)

//     private long lastClickTime = System.nanoTime();
//     private int MILLION = 1000000;
//     // How long the ball is "invincible"
//     private long DELAY = 7 * MILLION * 1000;

//     // public BouncingBall() {
//     // frameBounds = new Rectangle(0, 0, Andie.frame.getWidth(),
//     // Andie.frame.getHeight());
//     // // setBackground(transparentColor);
//     // }

//     public BouncingBall(int initialX, int initialY) {
//         this.x = initialX;
//         this.y = initialY;
//         int frameHeight = Andie.frame.getHeight() - Andie.toolbar.getHeight() - Andie.menuBar.getHeight() + 30;
//         frameBounds = new Rectangle(0, Andie.menuBar.getHeight(), Andie.frame.getWidth(), frameHeight);
        
//         setBounds(0, 0, Andie.frame.getWidth(), Andie.frame.getHeight());
//         if (testing)
//             System.out.println("Ball bounds dimensions - Height: " + frameBounds.getHeight() + ", Width: "
//                     + frameBounds.getWidth());
//         setBackground(transparentColor);
//     }

//     public void start() {
//         timer = new Timer(10, new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 // interactiveMove();
//                 move();
//                 repaint();
//             }
//         });
//         timer.start();
//     }

//     private void move() {
//         // Move the ball
//         x += xSpeed;
//         y += ySpeed;

//         // Reverse direction if the ball reaches the frame boundaries
//         if (x <= 0 || x >= getWidth() - 30) {
//             // If the ball reaches the left or right edge of the frame, reverse its
//             // horizontal direction
//             if (testing)
//                 System.out.println("x value when bounced off side: " + x);
//             xSpeed = -xSpeed;
//         }
//         if (y <= 0 || y >= getHeight() - 30) {
//             // If the ball reaches the top or bottom edge of the frame, reverse its vertical
//             // direction
//             if (testing)
//                 System.out.println("y value when bounced off side: " + y);
//             ySpeed = -ySpeed;
//         }

//         // Check for collision with other components here
//         // For example, check for collision with the menu bar
//         Rectangle ballBounds = new Rectangle(x, y, 30, 30);
//         Rectangle toolBarBounds = new Rectangle(Andie.toolbar.getWidth(), Andie.toolbar.getHeight() - 34);
//         Rectangle menuBarBounds = new Rectangle(Andie.menuBar.getWidth(), Andie.menuBar.getHeight());
//         // horizontal = 0
//         int toolbarOrientation = Toolbar.CURRENT_ORIENTATION;

//         if (ballBounds.intersects(menuBarBounds)) {
//             // Adjust ball's velocity or direction for bouncing off the menu bar
//             // For example, you might reverse the direction of the ball's movement
//             ySpeed = -ySpeed;
//             if (testing)
//                 System.out.println("y value when bounced off menuBar: " + y);
//         } else if (ballBounds.intersects(toolBarBounds)) {
//             if (toolbarOrientation == 0) {
//                 if (testing)
//                     System.out.println("y value when bounced off horizontal toolbar: " + y);
//                 ySpeed = -ySpeed;
//             } else {
//                 if (testing)
//                     System.out.println("x value when bounced off vertical toolbar: " + x);
//                 xSpeed = -xSpeed;
//             }
//         }
//     }

//     @SuppressWarnings("unused")
//     private void interactiveMove() {
//         // Move the ball
//         x += xSpeed;
//         y += ySpeed;

//         // Reverse direction if the ball reaches the frame boundaries
//         if (!frameBounds.contains(x, y) || !frameBounds.contains(x + 30, y + 30)) {
//             // If the ball goes beyond the frame boundaries, reverse its direction
//             if (x <= 0 || x >= getWidth()) {
//                 xSpeed = -xSpeed;
//             }
//             if (y <= 0 || y >= getHeight()) {
//                 ySpeed = -ySpeed;
//             }
//         }
//         // Check for collision with other components here
//         // For example, check for collision with the menu bar
//         Rectangle ballBounds = new Rectangle(x, y, 30, 30);
//         Rectangle toolBarBounds = Andie.toolbar.getBounds();
//         Rectangle menuBarBounds = Andie.menuBar.getBounds();

//         // if the ball touches the menubar
//         if (ballBounds.intersects(menuBarBounds) && !isDisabled()) {
//             if (testing)
//                 System.out.println("Ball hit menu bar");
//             // check all the components in the menu bar
//             for (Component component : Andie.menuBar.getComponents()) {
//                 // if the ball hits one of the menu bar options
//                 if (component.getBounds().intersects(ballBounds)) {
//                     if (testing)
//                         System.out.println("Ball hit " + component.getName());
//                     // do the component, for instance if it hits the File component in the menu bar
//                     // it opens the drop down options
//                     if (component instanceof JMenuItem) {
//                         pauseTimer();
//                         JMenuItem menuItem = (JMenuItem) component;
//                         menuItem.doClick();
//                         if (testing)
//                             System.out.println("Simulated click on " + menuItem.getName());
//                         // xSpeed = -xSpeed;
//                         ySpeed = -ySpeed;
//                         resumeTimer();
//                     }
//                 }
//             }
//             // otherwise check if the ball hits the toolbar
//         } else if (toolBarBounds.contains(x, y) || toolBarBounds.contains(x + 30, y + 30)) {
//             // Iterate over the toolbar buttons
//             for (Component component : Andie.toolbar.getComponents()) {
//                 if (component instanceof AbstractButton) {
//                     AbstractButton button = (AbstractButton) component;
//                     Rectangle buttonBounds = button.getBounds();
//                     // Check if the ball intersects with the button
//                     if (buttonBounds.contains(x, y)) {
//                         // Simulate a click on the button
//                         pauseTimer();
//                         button.doClick();
//                         resumeTimer();
//                         // Reverse the ball's direction
//                         // xSpeed = -xSpeed;
//                         ySpeed = -ySpeed;
//                         break; // Exit the loop after clicking one button
//                     }
//                 }
//             }
//         }
//     }

//     // Method to pause the timer
//     private void pauseTimer() {
//         if (timer != null && timer.isRunning()) {
//             timer.stop();

//             lastClickTime = System.nanoTime();
//         }
//     }

//     // Method to resume the timer
//     private void resumeTimer() {
//         if (timer != null && !timer.isRunning()) {
//             timer.start();
//         }
//     }

//     private boolean isDisabled() {
//         long currentTime = System.nanoTime();
//         return (currentTime - this.lastClickTime < DELAY);
//     }

//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         g.setColor(Color.RED);
//         g.fillOval(x, y, 30, 30);
//     }
// }

// // private void move() {
// // // Move the ball
// // x += xSpeed;
// // y += ySpeed;

// // // Reverse direction if the ball reaches the frame boundaries
// // if (!frameBounds.contains(x, y) || !frameBounds.contains(x + 30, y + 30)) {
// // // If the ball goes beyond the frame boundaries, reverse its direction
// // if (x <= 0 || x >= getWidth()) {
// // xSpeed = -xSpeed;
// // }
// // if (y <= 0 || y >= getHeight()) {
// // ySpeed = -ySpeed;
// // }
// // }
// // // Check for collision with other components here
// // // For example, check for collision with the menu bar
// // Rectangle ballBounds = new Rectangle(x, y, 30, 30);
// // Rectangle toolBarBounds = Andie.toolbar.getBounds();
// // Rectangle menuBarBounds = Andie.menuBar.getBounds();

// // // if the ball touches the menubar
// // if (ballBounds.intersects(menuBarBounds) && !isDisabled()) {
// // if (testing)
// // System.out.println("Ball hit menu bar");
// // // check all the components in the menu bar
// // for (Component component : Andie.menuBar.getComponents()) {
// // // if the ball hits one of the menu bar options
// // if (component.getBounds().intersects(ballBounds)) {
// // if (testing)
// // System.out.println("Ball hit " + component.getName());
// // // do the component, for instance if it hits the File component in the menu
// // bar
// // // it opens the drop down options
// // if (component instanceof JMenuItem) {
// // pauseTimer();
// // JMenuItem menuItem = (JMenuItem) component;
// // menuItem.doClick();
// // if (testing)
// // System.out.println("Simulated click on " + menuItem.getName());
// // xSpeed = -xSpeed;
// // ySpeed = -ySpeed;
// // resumeTimer();
// // }
// // }
// // }
// // // otherwise check if the ball hits the toolbar
// // } else if (ballBounds.intersects(toolBarBounds) && !isDisabled()) {

// // if (testing)
// // System.out.println("Ball hit ToolBar");

// // // check all the buttons in the toolbar
// // for (Component component : Andie.toolbar.getComponents()) {
// // // if the ball hits one of the toolbar buttons
// // if (component.getBounds().intersects(ballBounds))
// // if (testing)
// // System.out.println("Ball hit " + component.getName());
// // {
// // /*
// // * do the component, for instance if it hits the Open button in the tool bar
// // it
// // * activates the Opens action in the toolbar
// // *
// // * Simulates a mouse click event on the toolbar button
// // */
// // if (component instanceof AbstractButton) {
// // pauseTimer();
// // AbstractButton button = (AbstractButton) component;
// // button.doClick();
// // if (testing)
// // System.out.println("Simulated click on " + button.getName());
// // xSpeed = -xSpeed;
// // ySpeed = -ySpeed;
// // resumeTimer();
// // }
// // }
// // }
// // }
// // }