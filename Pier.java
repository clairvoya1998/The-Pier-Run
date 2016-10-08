import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Pier {
    private int dy;
    private int x;
    private int y;
    private Image image;

    public Pier() {

        initPier();
    }

    private void initPier() {

        ImageIcon ii = new ImageIcon("main_small.png");
        image = ii.getImage().getScaledInstance(58, 63, Image.SCALE_DEFAULT);
        x = 40;
        y = 260;
    }


    public void move() {
        if (y == 260) y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            if (y == 260)dy = -80;
        }
    }
    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();


        if (key == KeyEvent.VK_UP) {
            while (y != 260){
                y = y + 1;
            }    
            dy = 0;
        }
    }
}

