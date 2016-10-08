import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Pier pier;
    private final int DELAY = 20;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);

        pier = new Pier();

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
        g2d.drawImage(pier.getImage(), pier.getX(), pier.getY(), null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        pier.move();
        repaint();
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
