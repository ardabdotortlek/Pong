import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rect {
    //Fields
    private double x;
    private double y;
    private double height;
    private double width;
    private Color color;
    private int id;

    //Constructor
    public Rect(double x, double y, double height, double width, Color color, int id) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
        this.id = id;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.fill(new Rectangle2D.Double(x,y,width,height));
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getId(){
        return this.id;
    }

    public void setX(double x){
        this.x = x;
    }

    public double getX(){
        return x;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
}
