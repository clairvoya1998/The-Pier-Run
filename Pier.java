import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.util.Random;
import java.lang.Math;

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
    private static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
    private int[] obx = {
        1200, 1600, 2000, 2400, 2800
    };
    public void drawOn(Graphics2D g2d) {
        y += GRAVITY;
        y += dy;

        if(y <= 140) {
          dy = 0;
        }
        for (int i = 0; i < 5; i++) {
            obx[i] -= 8;
            while (obx[i] < 0) {
                obx[i] += 1000 + randInt(0, 3000);
                for (int j = 0; j < 5; j++) {
                    while (Math.abs(obx[i] - obx[j]) < 200 && i != j) obx[i] += 250;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            if (obx[i] < 1000) g2d.drawRect(obx[i], 270, 50, 50);
        }
        g2d.drawLine(0, 320, 1000, 320);
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
