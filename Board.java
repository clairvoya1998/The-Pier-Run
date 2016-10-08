import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Pier pier;
    private final int DELAY = 20;
    private int frameCount = 0;

    public Board() {
        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);
        pier = new Pier(Pier.WIDTH,Pier.HEIGHT);
        timer = new Timer(DELAY, this);
        timer.start();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        pier.drawOn(g2d);
        pier.setVisible(true);
        ArrayList<Golf> gs = pier.getGolfs();

        for (Golf m : gs) {
          if (m.isVisible()) {
            g.drawImage(m.getImage(), m.getX(), m.getY(), this);
          }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        pier.move();
        updateGolfs();
        repaint();
    }

    private void updateGolfs() {


        ArrayList<Golf> gs = pier.getGolfs();

        for (int i = 0; i < gs.size(); i++) {

            Golf m = gs.get(i);

            if (m.isVisible()) {
                m.move();
            } else {
                gs.remove(i);
            }
        }
  }
    private class TAdapter extends KeyAdapter {
    	@Override
        public void keyReleased(KeyEvent e) {
        	pier.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	pier.keyPressed(e);

        }
    }
}
