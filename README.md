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

## Image Operations to be added
- `Image Rotation`: rotates an image 90° to either the left or right.
- `Median Filter`: uses a local neighbourhood with a specified radius to blur an image.


## Saving images
With `Save` the program by default saves the image in the file directory it was opened in, replacing the original image. With the option `Save As` you can specify the file name and directory directly. Each option will save along with the image file an *operations* file, this file saves the image operations that were performed.

> If you want to just export the image, to save it without an operations file, you can use the `Export` function to do so, specifying file type as well as directory.

# Things still to do [DUE 28th March]
This is a part of the README that will get removed at the end of this, this is just to note things that we still need to do. Errors, features, notes, whatever.

## Features to implement
- Image rotation(90° Left & Right) - Amelia
- Median Filter - Marcus
- Multilingual Support - ALL
- Image Export - Brooklyn 
- JUnit Tests - Brooklyn (Ill commit it to main so we all have it)
- More exception handling (just put try catches everywhere the system might try to do something where there's null) - ALL
- Error avoidance (so it doesn't just die and crash) - ALL
- Colour Cycling User Input

>need to add clarity to the two existing blurring options, SoftBlur will be changed to BlurOption and vice versa, I'll need to edit some prompts too. 

with the colour cycling, the labbook has this:

>Task 2.5 (Team): Implement colour channel cycling in ANDIE. Your implementation should allow a user to cycle the colour channels to **any possible configuration** in a single operation. You should consider how the user specifies the colour channel ordering carefully to make sure this operation is easy to use. User experience considerations apply to all operations, but this is the first one that **may need more than just a single number as input**.

so we need to add some functionality to the ColourChannelCycling class to allow the user to change the order of the RGB beyond the two cycle we currently have.

I was thinking that we could either generate a list of the possible combinations of "RGB" and have it run through that or I could look into a ui element that would have a user press [R] [G] [B] buttons in a JPanel, setting the order of it if Amelia could edit the class to have the constructor take a string, store it in a data field, then with the apply() just change the order of the input RGB values to the string.

## Known Errors
We need to throw an error message up when we try to edit an image when theres not an image loaded

bunch of errors pop up when I build and debug run, need to check if thats just my computer being annoying again

need to check with a tutor how they actually want the export feature to work, and how to credit someone on stack overflow for instance about *borrowing* code  