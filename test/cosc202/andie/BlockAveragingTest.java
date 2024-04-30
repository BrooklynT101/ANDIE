// package cosc202.andie;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;

// import java.awt.image.BufferedImage;

// /**
//  * LLM generated JUnit test, sucks needs to rehaul, throws dumb errors, tests
//  * even dumber things
//  */
// public class BlockAveragingTest {

//     /**
//      * Test to verify that BlockAveraging correctly averages the color values of
//      * blocks in the image.
//      */
//     @Test
//     void testBlockAveraging() {
//         // Create a sample input image
//         BufferedImage input = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

//         // Fill the input image with a gradient for testing
//         for (int y = 0; y < input.getHeight(); y++) {
//             for (int x = 0; x < input.getWidth(); x++) {
//                 int red = x;
//                 int green = y;
//                 int blue = 255 - x - y;
//                 int rgb = (red << 16) | (green << 8) | blue;
//                 input.setRGB(x, y, rgb);
//             }
//         }

//         // Apply BlockAveraging with a blockSize of 10
//         BlockAveraging blockAveraging = new BlockAveraging(10);
//         BufferedImage output = blockAveraging.apply(input);

//         // Check that the output image has the correct dimensions
//         Assertions.assertEquals(input.getWidth(), output.getWidth());
//         Assertions.assertEquals(input.getHeight(), output.getHeight());

//         // Check that the color values in each block have been averaged correctly
//         for (int y = 0; y < output.getHeight(); y += 10) {
//             for (int x = 0; x < output.getWidth(); x += 10) {
//                 int blockColor = output.getRGB(x, y);
//                 // Since each block is 10x10, the averaged color should be the average of the
//                 // colors in that block
//                 int expectedColor = (127 << 16) | (127 << 8) | 127; // Expected average color in the block
//                 Assertions.assertEquals(expectedColor, blockColor);
//             }
//         }
//     }

//     /**
//      * Test to verify that BlockAveraging handles null input image correctly by
//      * returning null.
//      */
//     @Test
//     void testNullInputImage() {
//         BlockAveraging blockAveraging = new BlockAveraging();
//         BufferedImage output = blockAveraging.apply(null);
//         Assertions.assertNull(output);
//     }

//     /**
//      * Test to verify that BlockAveraging returns the correct name.
//      */
//     @Test
//     void testGetName() {
//         BlockAveraging blockAveraging = new BlockAveraging();
//         Assertions.assertEquals("Block Averaging", blockAveraging.getName());
//     }
// }
