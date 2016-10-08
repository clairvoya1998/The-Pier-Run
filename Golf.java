public class Golf extends Sprite {

    private final int BOARD_WIDTH = 800;
    private final int Golf_SPEED = 3;

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

}
