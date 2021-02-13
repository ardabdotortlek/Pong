import java.awt.*;

public class Text {
    private Font font;
    private double x, y;
    private String text;

    public Text(Font font, double x, double y, String text) {
        this.font = font;
        this.x = x;
        this.y = y;
        this.text = text;
    }

    public void draw(Graphics2D g){
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(text,(float) x, (float) y);
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
