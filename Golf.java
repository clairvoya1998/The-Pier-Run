public class Golf extends Sprite {

    private final int BOARD_WIDTH = Pier.WIDTH;
    private final int Golf_SPEED = 20;

    public Golf(int x, int y) {
        super(x, y);
        initGolf();
    }

    private void initGolf() {

        loadImage("golf-ball.png");
        getImageDimensions();
    }


    public void move() {

        x += Golf_SPEED;

        if (x > BOARD_WIDTH) {
            vis = false;
        }
    }
    public int getX() {
    	return x;
    }
    public void setX(int newx) {
    	x = newx;
    }

}
