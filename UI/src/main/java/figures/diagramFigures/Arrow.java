package figures.diagramFigures;

import figures.basicShapes.Line;

import java.awt.*;

public class Arrow extends Figure {
    private Line line;
    private Line arrowTop;
    private Line arrowBottom;

    public Arrow(int x, int y, int x2, int y2) {
        line = new Line(x, y, x2, y2);

        if (x < x2) {
            if (y == y2) {
                arrowTop = new Line(x2, y, x2 - 15, y - 10);
                arrowBottom = new Line(x2, y, x2 - 15, y + 10);
            } else if (y < y2) {
                //TODO
            } else if (y > y2) {
                //TODO
            }
        } else if (x > x2) {
            if (y == y2) {
                arrowTop = new Line(x2, y2, x2 + 15, y2 - 10);
                arrowBottom = new Line(x2, y2, x2 + 15, y2 + 10);
            } else if (y < y2) {
                //TODO
            } else if (y > y2) {
                //TODO
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
