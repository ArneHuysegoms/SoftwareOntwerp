package figures.diagramFigures;

import figures.PointXY;
import figures.basicShapes.Line;

import java.awt.*;

public class Arrow extends Figure {
    private Line line;
    private Line arrowTop;
    private Line arrowBottom;

    public Arrow(int x, int y, int length){
        line = new Line(x,y,x+length,y);
        arrowTop = new Line(x+length,y, x+length-15,y-10);
        arrowBottom = new Line(x+length,y, x+length-15,y+10);
    }
    @Override
    public void draw(Graphics graphics) {
        line.draw(graphics);
        arrowTop.draw(graphics);
        arrowBottom.draw(graphics);
    }
}
