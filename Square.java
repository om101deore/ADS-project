import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * Square
 */
public class Square {

    int x_cord, y_cord, side;
    Color color = Color.white;

    boolean visited = false;

    Square from ;
    
    void drawSqr(Graphics2D g2d, int x, int y, int side){
        x_cord = x;
        y_cord = y;
        this.side = side;

        float thikness = 2;
        Stroke oldStroke = g2d.getStroke();

        g2d.setStroke(new BasicStroke(thikness));
        g2d.setColor(color);
        g2d.fillRect(x, y, side, side);

        g2d.setStroke(oldStroke);
    }

    int getX() {
        return this.x_cord;
    }

    int getY() {
        return this.y_cord;
    }

    int getSide(){
        return this.side;
    }

    Color getColor() {
        return color;
    }

    void setColor(Color color){
        this.color = color;
    }

    boolean isVisited(){
        return this.visited;
    }

    void setVisited(){
        this.visited = true;
        this.setColor(Color.yellow);
    }

    void setUnvisited(){
        this.visited = false;
        this.setColor(Color.white);
    }

    void setFrom(Square from){
        this.from = from;
    }

}
