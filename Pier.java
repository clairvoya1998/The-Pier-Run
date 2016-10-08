import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.util.Random;
import java.lang.Math;
import java.awt.Font;

public class Pier {
    private final float GRAVITY = 9f;
    
    private int dy;
    private int x;
    private int y;
    private boolean dead = false;
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
    private boolean cDetect() {
    	for (int i = 0; i < 5; i++) {
    		if (obx[i] - this.x < 60 && this.y > 240) return true;
    	}
    	return false;
    }
    private int score = 0;
    public void drawOn(Graphics2D g2d) {
    	if (!dead) {
    		score++;
    	
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
    					while (Math.abs(obx[i] - obx[j]) < 200 && i != j) obx[i] += 350;
    				}
    			}
    		}
        	for (int i = 0; i < 5; i++) {
        		if (obx[i] < 1000) g2d.drawRect(obx[i], 270, 50, 50);
        	}
        	g2d.drawLine(0, 320, 1000, 320);
        	y = Math.min(y,260);
        
        	g2d.drawString("Score: " + score, 10, 10);
        	g2d.drawImage(this.image, this.x, this.y, null);
        	dead = cDetect();
        } else
			gameOver(g2d, score);
    }
    
    private void gameOver(Graphics2D g, long score) {
		Font gameOverFont = new Font("Sans-Serif",1,70);
		g.setFont(gameOverFont);
		g.drawString("GAME OVER", 150, 100);
		Font scoreFont = new Font("Sans-Serif",1,40);
		g.setFont(scoreFont);
		score-=1;
		g.drawString("Your score was: "+score, 160, 200);
		Font optionsFont = new Font("Sans-Serif",1,20);
		g.setFont(optionsFont);
		g.drawString("Do you want to play again? Y/N", 210, 270);
		
	}
    
	
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
        	if (y == 260) dy = -20;
        }
        char character = e.getKeyChar();
        if (character=='y'||character=='Y') {
        	score = 0;
        	dead = false;
        	y = 100;
        }
        if (character=='n'||character=='N') {
        		System.exit(0);
        }

    }
    public void keyReleased(KeyEvent e) {
        dy = 0;
    }
}
