import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.*; 
import java.io.*;
import java.util.Random;
import java.lang.Math;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class PierRun implements Runnable{
   
   final int WIDTH = 1000;
   final int HEIGHT = 700;
   
   JFrame frame;
   Canvas canvas;
   BufferStrategy bufferStrategy;
   
   public PierRun(){
	  frame = new JFrame("The Pier Run");
	  
	  JPanel panel = (JPanel) frame.getContentPane();
	  panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	  panel.setLayout(null);
	  
	  canvas = new Canvas();
	  canvas.setBounds(0, 0, WIDTH, HEIGHT);
	  canvas.setIgnoreRepaint(true);
	  
	  panel.add(canvas);
	  
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.pack();
	  frame.setResizable(false);
	  frame.setVisible(true);
	  
	  canvas.createBufferStrategy(2);
	  bufferStrategy = canvas.getBufferStrategy();
	  
	  canvas.requestFocus();
   }
   
   
   long desiredFPS = 30;
   long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;
	
   boolean running = true;
   
   public void run(){
	  
	  long beginLoopTime;
	  long endLoopTime;
	  long currentUpdateTime = System.nanoTime();
	  long lastUpdateTime;
	  long deltaLoop;
	  
	  loadImage();
	  while(running){
		 beginLoopTime = System.nanoTime();
		 
		 render();
		 
		 lastUpdateTime = currentUpdateTime;
		 currentUpdateTime = System.nanoTime();
		 update((int) ((currentUpdateTime - lastUpdateTime)/(1000*1000)));
		 
		 endLoopTime = System.nanoTime();
		 deltaLoop = endLoopTime - beginLoopTime;
		   
		   if(deltaLoop > desiredDeltaLoop){
			   //Do nothing. We are already late.
		   }
		   else{
			   try{
				   Thread.sleep((desiredDeltaLoop - deltaLoop)/(1000*1000));
			   }catch(InterruptedException e){
				   //Do nothing
			   }
		   }
	  }
   }
   
	private void render() {
   	   	Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
   	   	g.clearRect(0, 0, WIDTH, HEIGHT);
   	   	render(g);
   	   	g.dispose();
   	   	bufferStrategy.show();
	}
   	
	
	private long score = 0;
	
	BufferedImage stu = null;
	BufferedImage square = null;
	private void loadImage() {
		try {
			stu = ImageIO.read(new File("main.png"));
		} catch (IOException e) {
		}
		try {
			square = ImageIO.read(new File("square.gif"));
		} catch (IOException e) {
		}
	}
    private int[] x = {1200, 1600, 2000, 2400, 2800};
	protected void update(int deltaTime){
   	   score++;
   	   for (int i=0;i<5;i++) {
   	   	   x[i] -= deltaTime * 0.2;
   	   	   while(x[i] < 0){
   	   	   	   x[i] += 1000 + randInt(0, 3000);
   	   	   	   for (int j=0;j<5;j++) {
   	   	   	   	   while (Math.abs(x[i]-x[j])<200 && i!=j) x[i] += 200;
   	   	   	   }
   	   	   }
   	   }
   	   
	}
	
	private static int randInt(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}
	
	private static int log(long x, int base) {
		return (int) (Math.log(x) / Math.log(base));
	}
	
	protected void render(Graphics2D g){
		g.drawString("Score: " + score, 10, 10);
		g.drawImage(stu, 50, 400, 127, 95, null);
		for (int i=0;i<5;i++) {
			g.drawImage(square, x[i], 450, 50, 50, null);
		}
		g.drawLine(0, 495, 1000, 495);
	}
   
   public static void main(String [] args){
	  PierRun ex = new PierRun();
	  new Thread(ex).start();
   }

}