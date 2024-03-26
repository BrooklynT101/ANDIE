## ANDIE - A Non-Destructive Image Editor

Welcome to ANDIE, ANDIE is used to perform simple operations on image files and save them. ANDIE also saves the list of image operations that were performed on it, this is so when the image is opened again (and assuming it wasn't moved away from the operations file) the undo commands will still work. This can also be used to view the steps taken to achieve the final result

## Image Operations

The ANDIE program contains many simple image operations implemented, including:

- `Convert To Grey`: convert an image to become greyscale, by editing each pixels RBG values.
- `Image Inversion`: converts an iamges RGB values to the inverse of their original values.
- `Colour Channel Cycling`: allows a user to cycle the colour channels to any possible configuration based on how the user specifies the colour channel.
- `Undo`: allows the users to undo their previous operations one by one as many times as needed.
- `Redo`: allows the users to red their undo operations back to the edited version of the image.
- `Mean Filter`: A simple blurrng filter that smoths out the image by finding the mean value of the surrounding kernel around a pixel and applyig the mean value to that pixel.
- `Soft Blur`: blurs an image by a *soft* margin.
- `Blur Filter`: ?
- `Median Filter`: Uses a local neighbourhood with a specified radius to blur an image.
- `Gaussian Blur`: blurs an image as per the mathmatical principles of Gaussian(normal) distribution.
- `Sharpen Filter`: sharpens an image by using a set kernal and convolution.
- `Image Rotation Clockwise`: rotates an image 90° in the clockwise direction.
- `Image Rotation Anticlockwise`: rotates an image 90° in the anticlockwise direction.
- `Image Flip Vertically`: flip an image either vertically across the y-axis.
- `Image Flip Horizontally`: flip an image either horizontally across the x-axis.
- `Image Resize`: scales an image by a given percentage.
- `Zoom In`: allows the user to zoom in at a ___ percent? each time applied.
- `Zoom Out`: allows the user to zoom out at a ___ percent? each time applied.
- `Zoom In`: zooms the image back to its original size.?

## Image Operations to be added



## Saving images
With `Save` the program by default saves the image in the file directory it was opened in, replacing the original image. With the option `Save As` you can specify the file name and directory directly. Each option will save along with the image file an *operations* file, this file saves the image operations that were performed.

> If you want to just export the image, to save it without an operations file, you can use the `Export` function to do so, specifying file type as well as directory.


## Contribution
Within our group of 4 we divided the project up to fit our strengths. We each took a 'difficult' operation to begin then later moved on to the simpler areas of this project. Once all the actions were added we went back into our code adding the MultiLingual feature and developing our exception handling within any area of code that seemed necessary. 