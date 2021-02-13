public class BallController {
    private Rect ball, left, right;
    private double velocityY, velocityX;
    Text rightText, leftText;

    public BallController(Rect ball, Rect left, Rect right, Text rightT, Text leftT){
        this.ball = ball;
        this.left = left;
        this.right = right;
        this.velocityX = -200;
        this.velocityY = 100;
        this.rightText = rightT;
        this.leftText = leftT;
    }

    private void calculateNewVelocities(Rect rect){
        double rectY = (rect.getY() + rect.getHeight()/2) - (ball.getY() + ball.getHeight()/2);
        double normal = rectY / rect.getHeight()/2;
        double angle = Math.toRadians(normal * Constants.MAX_DEGREE);
        double oldSign = Math.signum(velocityX);

        velocityX = Constants.BALL_SPEED  * Math.cos(angle) * -1 * oldSign;
        velocityY = Constants.BALL_SPEED  * Math.sin(angle) * -1;
    }

    public void update(double dt){
        if(velocityX < 0){
            if(ball.getX() <= left.getX() + left.getWidth() && ball.getX() + ball.getWidth() >= left.getX() && ball.getY() <= left.getY()
            + left.getHeight() && ball.getY() + ball.getHeight() >= left.getY()){
                calculateNewVelocities(left);
            }
            else if(ball.getX() + ball.getWidth() < 0){
                updateScore(rightText);
                velocityX = -200;
                System.out.println("Player 2 scored");
            }

        }
        else if(velocityX > 0){
            if(ball.getX() <= right.getX() + right.getWidth() && ball.getX() + ball.getWidth() >= right.getX() && ball.getY() <= right.getY()
                    + right.getHeight() && ball.getY() + ball.getHeight() >= right.getY()){
                calculateNewVelocities(right);
            }
            else if(ball.getX() + ball.getWidth() > Constants.SCREEN_WIDTH){
                updateScore(leftText);
                velocityX = 200;
                System.out.println("Player 1 scored");
            }

        }

        if(velocityY > 0){
            if(ball.getY() + ball.getHeight() >= Constants.SCREEN_HEIGHT - Constants.TOOLBAR_DOWN)
                velocityY = velocityY * -1;
        }

        else if(velocityY < 0){
            if(ball.getY() <= Constants.TOOLBAR_UP)
                velocityY = velocityY * -1;
        }

        ball.setX(ball.getX() + dt * velocityX);
        ball.setY(ball.getY() + dt * velocityY);
    }


    public void updateScore(Text text){
        int score = Integer.parseInt(text.getText());
        score++;
        if(score == 10)
            System.exit(0);
        text.setText(score + "");
        ball.setX(Constants.SCREEN_WIDTH/2);
        ball.setY(Constants.SCREEN_HEIGHT/2);
    }

}
