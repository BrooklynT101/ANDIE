package cosc202.andie;

import java.util.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

@SuppressWarnings("unused")
public class Macro {

    private EditableImage original;
    /** The sequence of operations currently applied to the image. */
    private Stack<ImageOperation> currentOps;
    /** A memory of 'undone' operations to support 'create'. */
    private Stack<ImageOperation> macroOps;
    /** The name of the macro. */
    private String macroName;
    /** The file where the macro sequence is stored. */
    private String macroFilename;
    public static boolean isRunning;

    public Macro(EditableImage current) {
        original = current;
    }

    // public boolean hasImage() {
    //     return current != null;
    // }

    public void startRecording() {
        //original.getImage().getOps();
    }

    public void stopRecording() {

    }
    public void save() throws Exception {
    }

}