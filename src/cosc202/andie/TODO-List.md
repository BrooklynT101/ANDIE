# Things still to do [DUE 28th March]
This just a markdown file will get removed at the end of this project, feel free to note things that we still need to do. Errors, features, notes, whatever.

## Features to implement
- Image rotation(90° Left & Right) - Amelia DONE
- Median Filter - Marcus DONE? 
- Multilingual Support - ALL
- Image Export - Brooklyn DONE? 
- JUnit Tests - Brooklyn (Ill commit it to main so we all have it)
- More exception handling (just put try catches everywhere the system might try to do something where there's null) - ALL
- Error avoidance (so it doesn't just die and crash) - ALL
- Colour Cycling User Input (see **below**)

>I needed to add clarity to the two existing blurring options, SoftBlur changed to BlurOption and vice versa, this is just so we have a soft blur and a blur option that applies itself a user defined number of times.

``with the colour cycling, the labbook has this:``

>Task 2.5 (Team): Implement colour channel cycling in ANDIE. Your implementation should allow a user to cycle the colour channels to **any possible configuration** in a single operation. You should consider how the user specifies the colour channel ordering carefully to make sure this operation is easy to use. User experience considerations apply to all operations, but this is the first one that **may need more than just a single number as input**.

so we need to add some functionality to the ColourChannelCycling class to allow the user to change the order of the RGB beyond the two cycle we currently have.

I was thinking that we could either generate a list of the possible combinations of "RGB" and have it run through that or I could look into a ui element that would have a user press [R] [G] [B] buttons in a JPanel, setting the order of it if Amelia could edit the class to have the constructor take a string, store it in a data field, then with the apply() just change the order of the input RGB values to the string, but thats just an idea

## Known Errors
We need to throw an error message up when we try to edit an image when theres not an image loaded

bunch of errors pop up when I build and debug run, need to check if thats just my computer being annoying again

need to check with a tutor how they actually want the export feature to work, and how to credit someone on stack overflow for instance about *borrowing* code  