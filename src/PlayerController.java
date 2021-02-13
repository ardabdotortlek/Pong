import java.awt.event.KeyEvent;

public class PlayerController {
    private Rect rect;
    private KeyListener keyListener;

    public PlayerController(Rect rect, KeyListener keyListener){
        this.rect = rect;
        this.keyListener = keyListener;
    }

    public void update(double dt){
        if(rect.getId() == Constants.PLAYER_TWO_ID) {
            if (keyListener.isKeyPressed(KeyEvent.VK_UP))
                moveUp(dt);
            else if (keyListener.isKeyPressed(KeyEvent.VK_DOWN))
                moveDown(dt);

        }
        else if(rect.getId() == Constants.PLAYER_ONE_ID){
            if(keyListener.isKeyPressed(KeyEvent.VK_W))
                moveUp(dt);
            else if(keyListener.isKeyPressed(KeyEvent.VK_S))
                moveDown(dt);
        }
    }

    public void moveUp(double dt){
        if(rect.getY() - dt * Constants.SPEED >= Constants.TOOLBAR_UP)
            rect.setY(rect.getY() - dt * Constants.SPEED);
    }

    public void moveDown(double dt){
        if(rect.getY() + Constants.RECT_HEIGHT +  dt * Constants.SPEED  <= Constants.SCREEN_HEIGHT - Constants.TOOLBAR_DOWN)
            rect.setY(rect.getY() + dt * Constants.SPEED);
    }
}
