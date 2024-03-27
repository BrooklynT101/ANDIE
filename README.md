## ANDIE - A Non-Destructive Image Editor

Welcome to ANDIE, ANDIE is used to perform simple operations on image files and save them. ANDIE also saves the list of image operations that were performed on it, this is so when the image is opened again (and assuming it wasn't moved away from the operations file) the undo commands will still work. This can also be used to view the steps taken to achieve the final result

## Image Operations

The ANDIE program contains many simple image operations implemented, including:

- `Convert To Grey`: Convert an image to become greyscale, by editing each pixels RBG values.
- `Image Inversion`: Converts an iamges RGB values to the inverse of their original values.
- `Colour Channel Cycling`: Allows a user to cycle the colour channels to any possible configuration based on how the user specifies the colour channel.
- `Undo`: Allows the users to **undo** their previous operations until they reach the original action.
- `Redo`: Allows the users to **redo** their **undo** operations back to the edited version of the image.
- `Mean Filter`: A simple blurring filter that smooths out the image by finding the mean value of the surrounding kernel around a pixel and applying the mean value to that pixel.
- `Soft Blur`: Blurs an image by a *soft* margin.
- `Median Filter`: Uses a local neighbourhood with a specified radius to blur an image.
- `Gaussian Blur`: Blurs an image as per the mathmatical principles of Gaussian(normal) distribution.
- `Sharpen Filter`: Sharpens an image by using a set kernal and convolution.
- `Image Rotation Clockwise`: Rotates an image 90° in the clockwise direction.
- `Image Rotation Anticlockwise`: Rotates an image 90° in the anticlockwise direction.
- `Image Flip Vertically`: Flip an image vertically along the y-axis.
- `Image Flip Horizontally`: Flip an image horizontally along the x-axis.
- `Image Resize`: Scales an image by a given percentage.
- `Zoom In`: Allows the user to zoom in at 10 percent each time applied.
- `Zoom Out`: Allows the user to zoom out at a 10 percent each time applied.
- `Zoom In`: Zooms the image back to it's original size.

## Saving images
- The `Save` action saves the image operations file in the directory the image was opened in, replacing (or creating) image operations file and keeping the original image unchanged. 
- The `Save As` operation allows the user to specify the file name and directory directly. Each option will save along with the image file an *operations* file, this file saves the image operations that were performed.
- The `Export` action allows the user to export the image with all operations preformed as a single image file, this means that it will create a copy of the image and saves a new one to the specified directory, leaving the original file unchanged. If the user specifies a different file type the system will attempt to save the 

# Known supported file types
Supported file types;
- JPG - pass
- JPEG - pass
- PNG - pass
- GIF - pass
- BMP - pass
- TIFF - pass
# Known unsupported file types;
- WBMP - fail
- ICO - fail
- PNM - fail
- PCX - fail
>Note User can only export to the file directories they have permissions for. 

## Undo and Redo Action 
The Undo and Redo action allows you to go back or forward at one step at a time. Some of the actions applied on the image are applied multiple times so the undo and redo feature will need to be used multiple times to make it back to the original image. When asked to apply a filter multiple times the user should understand that it may take multiple actions to undo the operations fully.

## MultiLingual Support
The Multilingual support features allows users to use our ANDIE project in the set language of both English and Maori. The default langauge is set to English but if the user choses to they can set the language to Maori, this restarts the system to change the language and all the menu options convert to the new chosen language. DOES THIS LOOSE OPERATIONS ON IMAGE? 

## Contribution
Within our group of 4 we divided the project up to fit our strengths. We each took a 'difficult' operation to begin, later moving on to simpler areas of the project. Once all the image and file actions were added we went back into our code, implementing the MultiLingual feature and developing our exception handling within any area of code that seemed necessary. 
The contributions our team members made are as follows:

> Ari Josephson: Completed the Gaussian blur, image resize and the multilingual support.


> Amelia Johnston: Completed the Colour actions (colour inversion and the colour channel cycling) and the image rotation. 


> Brooklyn Taylor: Completed the sharpen filter, image flip and image export. 


> Marcus Woodward: Completed the Median Filter, and tackled a large portion of exception and error handling

## Notable code edits
During development two blurring actions were added to the ANDIE program, the naming of the two were inconsistent with it features and implementations, so Brooklyn worked to replace and rename the two classes to make the actions clearer to those reading the actions code. BlurOption (Now BlurFilter) originally applied a single blur whereas SoftBlur had a user dictate the number of filter operations.

Marcus worked on removing the excess clutter from the main branch. At some point during our merging-to-main race to ensure our own filters and actions were now in main an excess of unesscessary file commits were pushed to main. This causes confusion in the priority of files and added clutter to the workspace so Marcus went through the code and removed the files not pertaining to the project.

## Testing 
Throughout the creation of this project we all preformed individual tests on our code such as adding print line statements and comparing the outcome with the specified task and examples. 
We also preformed JUnit tests _____. ????
Some examples of testing included:
- `Gaussian Blur`: Our test was to print out the outputted array and compared to the kernel that was expected for Gaussian Distribution. Consultation with the lab tutors allowed Ari to determine that his filter was properly implemented
- `File Saving/Exporting`: System print lines were added to ensure that the code was able to run to a certain part of the code block, usually with simple strings ("Test1", "notSaved", etc.) this allowed us to ensure that the files were being saved or just attempting to.
- `Colour Channel Cycling`: We could use print commands to print the RGB values to the console each time the action is performed to determine if the values are actually getting rearranged. Print the RGB values before the cycle operation then print it again afterwards, then we can visualling determine if the values are cycling  

## Authors
Created by Amelia Johnston, Ari Josephson, Brooklyn Taylor, and Marcus Woodard