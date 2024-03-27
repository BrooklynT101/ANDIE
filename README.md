## ANDIE - A Non-Destructive Image Editor

Welcome to ANDIE, ANDIE is used to perform simple operations on image files and save them. ANDIE also saves the list of image operations that were performed on it, this is so when the image is opened again (and assuming it wasn't moved away from the operations file) the undo commands will still work. This can also be used to view the steps taken to achieve the final result

## Image Operations

The ANDIE program contains many simple image operations implemented, including:

- `Convert To Grey`: Convert an image to become greyscale, by editing each pixels RBG values.
- `Image Inversion`: Converts an iamges RGB values to the inverse of their original values.
- `Colour Channel Cycling`: Allows a user to cycle the colour channels to any possible configuration based on how the user specifies the colour channel.
- `Undo`: Allows the users to undo their previous operations one by one as many times as needed.
- `Redo`: Allows the users to red their undo operations back to the edited version of the image.
- `Mean Filter`: A simple blurrng filter that smoths out the image by finding the mean value of the surrounding kernel around a pixel and applyig the mean value to that pixel.
- `Soft Blur`: Blurs an image by a *soft* margin.
- `Median Filter`: Uses a local neighbourhood with a specified radius to blur an image.
- `Gaussian Blur`: Blurs an image as per the mathmatical principles of Gaussian(normal) distribution.
- `Sharpen Filter`: Sharpens an image by using a set kernal and convolution.
- `Image Rotation Clockwise`: Rotates an image 90° in the clockwise direction.
- `Image Rotation Anticlockwise`: Rotates an image 90° in the anticlockwise direction.
- `Image Flip Vertically`: Flip an image either vertically across the y-axis.
- `Image Flip Horizontally`: Flip an image either horizontally across the x-axis.
- `Image Resize`: Scales an image by a given percentage.
- `Zoom In`: Allows the user to zoom in at 10 percent each time applied.
- `Zoom Out`: Allows the user to zoom out at a 10 percent each time applied.
- `Zoom In`: Zooms the image back to it's original size.

## Saving images
With `Save` the program by default saves the image in the file directory it was opened in, replacing the original image. With the option `Save As` you can specify the file name and directory directly. Each option will save along with the image file an *operations* file, this file saves the image operations that were performed.

> If you want to just export the image, to save it without an operations file, you can use the `Export` function to do so, specifying file type as well as directory.

## Undo and Redo Action 
The Undo and Redo action allows you to go back or forward at one step at a time. Some of the actions applied on the image are applied multiple times so the undo and redo feature will need to be used multiple times to make it back to the original image. When asked to apply a filter multiple times the user should understand that it may take multiple actions to undo the operations fully.

## MultiLingual Support
The Multilingual support features allows users to use our ANDIE project in the set language of both English and Maori. The default langauge is set to English but if the user choses to they can set the language to Maori, this restarts the system to change the language and all the menu options convert to the new chosen language. DOES THIS LOOSE OPERATIONS ON IMAGE? 


## Contribution
Within our group of 4 we divided the project up to fit our strengths. We each took a 'difficult' operation to begin then later moved on to the simpler areas of this project. Once all the actions were added we went back into our code adding the MultiLingual feature and developing our exception handling within any area of code that seemed necessary. 
The specific areas that each of out team members completed includes:
> Ari Josephson: Completed the Gaussian blur, ...


> Amelia Johnston: Completed the Colour actions (Colour Inversion and Colour Channel Cycling), ...


> Brooklyn Taylor: Completed the 


> Marcus Woodward: Completed the Median Filter,...


## Testing 
Throughout the creation of this project we all preformed individual tests on our code such as adding print line statements and ____. We also preforms JUnit tests _____. 
Some examples of testing included:
- `Gaussian Blur`: Ou test was to print out the outputted array and compared to the kernel that was expected for Gaussian Distribution.

## Authors
Created by Amelia Johnston, Ari Josephson, Brooklyn Taylor, and Marcus Woodard