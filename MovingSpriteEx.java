import java.awt.EventQueue;
import javax.swing.JFrame;

public class MovingSpriteEx extends JFrame {

    public MovingSpriteEx() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        
        setSize(800, 400);
        setResizable(false);
        
        setTitle("The Pier Run");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                MovingSpriteEx ex = new MovingSpriteEx();
                               
                ex.setVisible(true);
            }
        });
    }
}