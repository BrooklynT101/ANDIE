# ANDIE - A Non-Destructive Image Editor

Welcome to ANDIE, ANDIE is used to perform simple operations on image files and save them. ANDIE also saves the list of image operations that were performed on it, this is so when the image is opened again (and assuming it wasn't moved away from the operations file) the undo commands will still work. This can also be used to view the steps taken to achieve the final result

## Image Operations

The ANDIE program contains many simple image editing features. Here are the operations you can perform in the program:

- `Convert To Grey`: Convert an image to become greyscale, by editing each pixels RBG values.
- `Image Inversion`: Converts an images RGB values to the inverse of their original values.
- `Colour Channel Cycling`: Allows a user to cycle the colour channels to any possible configuration based on how the user specifies the colour channel.
- `Mean Filter`: A simple blurring filter that smooths out the image by finding the mean value of the surrounding kernel around a pixel and applying the mean value to that pixel.
- `Soft Blur`: Blurs an image by a *soft* margin.
- `Median Filter`: Uses a local neighbourhood with a specified radius to blur an image.
- `Gaussian Blur`: Blurs an image as per the mathmatical principles of Gaussian(normal) distribution.
- `Sharpen Filter`: Sharpens an image by using a set kernel and convolution.
- `Image Rotation Clockwise`: Rotates an image 90° in the clockwise direction.
- `Image Rotation Anticlockwise`: Rotates an image 90° in the anti-clockwise direction.
- `Image Flip Vertically`: Flip an image vertically along the y-axis.
- `Image Flip Horizontally`: Flip an image horizontally along the x-axis.
- `Image Resize`: Scales an image by a given percentage.
- `Zoom In`: Zooms in at 10 percent.
- `Zoom Out`: Zooms out at 10 percent.
- `Zoom Full`: Returns image back to it's original size.

## Saving images
- The `Save` action saves the image operations file in the directory the image was opened in, replacing (or creating) image operations file and keeping the original image unchanged. 
- The `Save As` operation allows the user to specify the file name and directory directly. Each option will save along with the image file an *operations* file, this file saves the image operations that were performed.
- The `Export` action allows the user to export the image with all operations preformed as a single image file, this means that it will create a copy of the image and saves a new one to the specified directory, leaving the original file unchanged. If the user specifies a different file type the system will attempt to save the desired file type and if the file doesnt correctly save to the system the method is to throw a new exception depending on the outcome.

### Known supported file types
- JPG - pass
- JPEG - pass
- PNG - pass
- GIF - pass
- BMP - pass
- TIFF - pass
### Known unsupported file types;
- WBMP - fail
- ICO - fail
- PNM - fail
- PCX - fail
>Note: The user can only export to the file directories they have permissions to write to. 

## Undo and Redo Action 
This feature allows you to undo the edits you've made or redo any undone changes. Some filters are applied multiple times but the undo and redo only needs a single operation to reverse the change.

The operations performed on an image is saved with the image, meaning that after you exit the application and reload your image, you should still have access to your previous actions.

## MultiLingual Support
```
The Multilingual support feature allows users to use our ANDIE project in the set language of both English and Maori. The default langauge is set to English but if the user chooses to they can set the language to Maori, this prompts a restart of the system to apply the change. Upon reopening, the language and all the menu options are converted to the new chosen language. 
```

### Contribution
Within our group of 4 we divided the project up to fit our strengths. We each took a 'difficult' operation to begin, later moving on to simpler areas of the project. Once all the image and file actions were added we went back into our code, implementing the Multilingual feature and developing our exception handling within any area of code that seemed necessary. 
We Developed our Andie, Incramentally, implementing our features in seperate branchs, ensuring each individual feature is tested and known to work before merged to main.

The main contributions our team members made are as follows:


`Amelia Johnston`: Completed the Colour actions (**Colour Inversion** and **Colour Channel Cycling**) and **Image Rotation**. 


`Ari Josephson`: Completed the **Gaussian Blur**, **Image Resize** and the **Multilingual Support**.


`Brooklyn Taylor`: Completed the **Sharpen Filter**, **Blur Filter**, **Image Flip** (horizontal/vertical) and **Image Exporting**. 


`Marcus Woodward`: Completed the **Median Filter**, and tackled a large portion of **Exception** and **Error Handling**

## Notable code edits
During development two blurring actions were added to the ANDIE program, the naming of the two were inconsistent with it features and implementations, so Brooklyn worked to replace and rename the two classes to make the actions clearer to those reading the actions code. BlurOption (Now BlurFilter) originally applied a single blur whereas SoftBlur had a user dictate the number of filter operations.

Marcus worked on removing the excess clutter from the main branch. At some point during our merging-to-main race to ensure our own filters and actions were now in main an excess of unesscessary file commits were pushed to main. This causes confusion in the priority of files and added clutter to the workspace so Marcus went through the code and removed the files not pertaining to the project.

## Testing 
Throughout the creation of this project we all preformed individual tests on our code such as adding print line statements and comparing the outcome with the specified task and examples. 

We tryed to use Junit testing, but were able to as the importing of the Junit resource weren't working for our
Gradle build.

Some examples of testing included:
- `Gaussian Blur`: Our test was to print output array and compare to the kernel that was expected for the Gaussian Distribution. Consultation with the lab tutors allowed Ari to determine that his filter was properly implemented, as well comparing the printed array values for the corrosponding radius values, with the expected values at each index plugged into the Gaussian equation and it lines up.
- `File Saving/Exporting`: System print lines were added to ensure that the code was able to run to a certain part of the code block, usually with simple strings ("Test1", "notSaved", etc.) this allowed us to ensure that the files were being saved or just attempting to.
- `Colour Channel Cycling`: Using print commands to print the RGB values to the console each time the action is performed to determine if the values are actually getting rearranged. Print the RGB values before the cycle operation then print it again afterwards, allowing us to determine the values are cycling correctly.
```
Before colour channel cycle
Alpha: -1, Red: 45, Green: 47, Blue: 33
After colour channel cycle
Alpha: -1, Red: 47, Green: 33, Blue: 45
```
- `Image Inversion`: Similar to before with the colour channel cycling, we used print commands to print the RGB values of the pixels before and after. And after calculating the values ourselves as per what the filter required, we determined the filter is correctly working
```
Before colour inversion
Alpha: -1, Red: 45, Green: 47, Blue: 33
After colour inversion
Alpha: -1, Red: 210, Green: 208, Blue: 222
```
## Known errors and/or exceptions
When trying to perform an operation on an image when one isnt currently loaded it throws a lot of exceptions into the output, this doesn't affect the program but is something to note as these are caught and printed to the terminal.



## Authors
Created by Amelia Johnston, Ari Josephson, Brooklyn Taylor, and Marcus Woodward