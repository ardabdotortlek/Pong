import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable {
    //Fields
    Graphics2D graphics2D;
    KeyListener keyListener;
    Rect playerOne, playerTwo, ball;
    PlayerController playerOneController,playerTwoController;
    BallController ballController;
    Text rightText, leftText;

    //Constructor
    public Window(){
        this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.graphics2D = (Graphics2D) this.getGraphics();
        this.keyListener = new KeyListener();
        this.addKeyListener(keyListener);

        Constants.TOOLBAR_UP = this.getInsets().top;
        Constants.TOOLBAR_DOWN = this.getInsets().bottom;

        this.playerOne = new Rect(Constants.HORIZONTAL_PADDING,Constants.VERTICAL_PADDING,Constants.RECT_HEIGHT,Constants.RECT_WIDTH,Color.WHITE,Constants.PLAYER_ONE_ID);
        this.playerOneController = new PlayerController(playerOne,keyListener);

        this.playerTwo = new Rect(Constants.SCREEN_WIDTH -Constants.RECT_WIDTH - Constants.HORIZONTAL_PADDING,Constants.VERTICAL_PADDING,Constants.RECT_HEIGHT,Constants.RECT_WIDTH,Color.WHITE,Constants.PLAYER_TWO_ID);
        this.playerTwoController = new PlayerController(playerTwo,keyListener);

        this.rightText = new Text(new Font("Times New Roman", Font.BOLD,25),Constants.SCREEN_WIDTH - 25, 65, 0 + "");
        this.leftText = new Text(new Font("Times New Roman", Font.BOLD,25),15, 65, 0 + "");

        this.ball = new Rect(Constants.SCREEN_WIDTH/2,Constants.SCREEN_HEIGHT/2,Constants.BALL_SIZE,Constants.BALL_SIZE,Color.WHITE,Constants.BALL_ID);
        this.ballController = new BallController(ball,playerOne,playerTwo,rightText,leftText);
    }

    public void drawFirstBuffer(Graphics dbGraphics){
        Graphics2D graphics2D = (Graphics2D) dbGraphics;

        graphics2D.setColor(Color.GREEN);
        graphics2D.fillRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

        graphics2D.setColor(Color.WHITE);
        graphics2D.drawLine(Constants.SCREEN_WIDTH/2,0,Constants.SCREEN_WIDTH/2,Constants.SCREEN_HEIGHT);
        graphics2D.drawOval(Constants.SCREEN_WIDTH/2 - Constants.SCREEN_HEIGHT/6,Constants.SCREEN_HEIGHT/3,Constants.SCREEN_HEIGHT/3,Constants.SCREEN_HEIGHT/3);

        playerOne.draw(graphics2D);
        playerTwo.draw(graphics2D);
        ball.draw(graphics2D);

        rightText.draw(graphics2D);
        leftText.draw(graphics2D);
    }

    public void update(double dt){
        Image dbImage = createImage(getWidth(),getHeight());
        Graphics dbGraphics = dbImage.getGraphics();
        this.drawFirstBuffer(dbGraphics);
        graphics2D.drawImage(dbImage,0,0,this); //Second buffer

        playerOneController.update(dt);
        playerTwoController.update(dt);
        ballController.update(dt);

    }

    @Override
    public void run() {
        double lastFrameTime = 0.0;
        //Game Loop
        while(true){
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);
        }
    }
}
