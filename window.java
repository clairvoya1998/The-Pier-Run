import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class window{
    public static void main(String args[]){
        
    }
    public static void init() {
    	JFrame myFrame = new JFrame("window");
        myFrame.setSize(1000,600);
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
    }
}
