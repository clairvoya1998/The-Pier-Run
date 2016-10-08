import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.Graphics2D;

public class Pier {
    private final float GRAVITY = 9f;

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

    public void drawOn(Graphics2D g2d) {
        y += GRAVITY;
        y += dy;

        if(y <= 140) {
          dy = 0;
        }

        y = Math.min(y,260);

        g2d.drawImage(this.image, this.x, this.y, null);
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
        	if (y == 260) dy = -20;
        }

    }
    public void keyReleased(KeyEvent e) {
        dy = 0;
    }
}
