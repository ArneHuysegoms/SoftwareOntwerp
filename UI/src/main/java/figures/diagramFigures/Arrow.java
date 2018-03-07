package figures.diagramFigures;

import figures.basicShapes.Line;

import java.awt.*;
import java.awt.geom.Point2D;

public class Arrow extends Figure {
    private Line line;
    private Line arrowTop;
    private Line arrowBottom;

    public Arrow(Point2D start, Point2D end) {
        line = new Line(start, end);
        calculateArrowHead((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
    }

    public Arrow(int x, int y, int x2, int y2) {
        line = new Line(x, y, x2, y2);
        calculateArrowHead(x, y, x2, y2);
    }

    private void calculateArrowHead(int x, int y, int x2, int y2) {
        //TODO tweak arrow directions, better boundries for switching head
        if (x < x2) {
            if (y == y2) {
                arrowTop = new Line(x2, y, x2 - 10, y - 10);
                arrowBottom = new Line(x2, y, x2 - 10, y + 10);
            } else if (y < y2) {
                //ZuidOost
                arrowTop = new Line(x2, y2, x2, y2 - 10);
                arrowBottom = new Line(x2, y2, x2 - 10, y2);
            } else if (y > y2) {
                //NoordOost
                arrowTop = new Line(x2, y2, x2 - 10, y2);
                arrowBottom = new Line(x2, y2, x2, y2 + 10);
            }
        } else if (x > x2) {
            if (y == y2) {
                arrowTop = new Line(x2, y2, x2 + 10, y2 - 10);
                arrowBottom = new Line(x2, y2, x2 + 10, y2 + 10);
            } else if (y < y2) {
                //ZuiWest
                arrowTop = new Line(x2, y2, x2, y2 - 10);
                arrowBottom = new Line(x2, y2, x2 + 10, y2);
            } else if (y > y2) {
                //NoordWest
                arrowTop = new Line(x2, y2, x2 + 10, y2);
                arrowBottom = new Line(x2, y2, x2, y2 + 10);
            }
        } else if (x == x2) {
            if (y < y2) {
                //South
                arrowTop = new Line(x2, y2, x2 + 10, y2 - 10);
                arrowBottom = new Line(x2, y2, x2 - 10, y2 - 10);
            } else if(y>y2){
                //North
                arrowTop = new Line(x2, y2, x2 - 10, y2 +10);
                arrowBottom = new Line(x2, y2, x2 +10, y2 + 10);
            }
        }

    }

    @Override
    public void draw(Graphics graphics) {
        line.draw(graphics);
        arrowTop.draw(graphics);
        arrowBottom.draw(graphics);
    }
}
