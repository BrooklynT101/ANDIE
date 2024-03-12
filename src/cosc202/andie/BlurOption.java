package cosc202.andie;

public class BlurOption implements ImageOperation, java.io.Serializable{
    


    /*
    BlurOption default constructor 
    Creates Option.
    */
    BlurOption(){

    }


    public BufferedImage apply(BufferedImage input){
        return input;
    }

}
