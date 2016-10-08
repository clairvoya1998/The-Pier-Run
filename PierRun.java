import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.*; 
import java.io.*;

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
    private int x = 1000;
	protected void update(int deltaTime){
   	   score++;
   	   x -= deltaTime * 0.2;
   	   while(x < 0){
   	   	   	x += 1000;
   	   }
	}
	
	
	protected void render(Graphics2D g){
		g.drawString("Score: " + score, 10, 10);
		g.drawImage(stu, 50, 400, 127, 95, null);
		g.drawImage(square, x, 400, 100, 100, null);
		g.drawLine(0, 495, 1000, 495);
	}
   
   public static void main(String [] args){
	  PierRun ex = new PierRun();
	  new Thread(ex).start();
   }

}