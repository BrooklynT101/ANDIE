## ANDIE - A Non-Destructive Image Editor

Welcome to ANDIE, ANDIE is used to perform simple operations on image files and save them. ANDIE also saves the list of image operations that were performed on it, this is so when the image is opened again (and assuming it wasn't moved away from the operations file) the undo commands will still work. This can also be used to view the steps taken to achieve the final result

## Image Operations

The ANDIE program contains many simple image operations implemented, including:

- `SoftBlur`: blurs an image by a *soft* margin.
- `ColourChannelCycling`: allows a user to cycle the colour channels to any possible configuration based on how the user specifies the colour channel.
- `ConvertToGrey`: convert an image to become grey, by edit each pixels RBG values.
- `Gaussian Blur`: blurs an image as per the mathmatical principles of Gaussian.
- `Image Flip`: flip an image either vertically or horizontally.
- `Image Inversion`: inverts the colour spectrum of the image.
- `Image Resize`: scales an image by a given percentage.
- `Mean Filter`: blurs an image by replacing each pixel by the average of the pixels in a surrounding neighbourhood.
- `Sharpen Filter`: sharpens an image by using a set kernal and convolution.
- `Image Rotation`: rotates an image 90° to either the left or right.
- `Median Filter`: uses a local neighbourhood with a specified radius to blur an image.

## Image Operations to be added



## Saving images
With `Save` the program by default saves the image in the file directory it was opened in, replacing the original image. With the option `Save As` you can specify the file name and directory directly. Each option will save along with the image file an *operations* file, this file saves the image operations that were performed.

> If you want to just export the image, to save it without an operations file, you can use the `Export` function to do so, specifying file type as well as directory.