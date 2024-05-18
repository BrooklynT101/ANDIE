package cosc202.andie;

import java.awt.Color;
import java.awt.image.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class DifferenceofImages implements ImageOperation, java.io.Serializable{

    private BufferedImage different;

    DifferenceofImages(BufferedImage different){
        this.different = different;
    }
    
    public BufferedImage apply(BufferedImage input) {
        if (input == null) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, Andie.bundle.getString("Pleaseopenanimagefilefirst!"));
        }

        if(different.getWidth()<input.getWidth()){
            ExtendingFilters e = new ExtendingFilters(different,input.getWidth()-different.getWidth() );
            different = e.extending();
        } else if(different.getHeight()<input.getHeight()){
            ExtendingFilters e = new ExtendingFilters(different,input.getHeight()-different.getHeight() );
            different = e.extending();
        }

        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(),input.getType());

        for(int y=0;y<input.getHeight();y++){
            for(int x=0; x<input.getWidth();x++){
                Color c = new Color(input.getRGB(x , y),true);
                Color d = new Color(different.getRGB(x , y),true);
                int argb;
                argb = (c.getAlpha()-d.getAlpha()<< 24) | (c.getRed()-d.getRed() << 16) | (c.getGreen()-d.getGreen() << 8) | (c.getBlue()-d.getBlue()); 
                output.setRGB(x,y,argb);
            }
        }
        

        return output;

    }

    @Override
    public String getName() {
        return "DifferenceofImages";
    }

    /**
     * Method to return 'this' as an ImageOperation to support @see MacroActions
     */
    @Override
    public ImageOperation clone() {
        DifferenceofImages copy = new DifferenceofImages(different);
        return copy;
    }

}

