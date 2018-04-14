package figures.diagramFigures;

import figures.basicShapes.CloseButton;
import figures.basicShapes.Line;
import figures.basicShapes.Rectangle;

import java.awt.*;
import java.awt.geom.Point2D;

public class SubwindowFigure extends Figure {

    private int x1, y1, x2, y2;

    public SubwindowFigure(Point2D start, Point2D end) {
        x1 = (int) start.getX();
        y1 = (int) start.getY();
        x2 = (int) end.getX();
        y2 = (int) end.getY();
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x1, y1, getWidth(), getHeight());
        graphics.setColor(Color.BLACK);
        new Rectangle(x1, y1, getWidth(), getHeight());
        CloseButton closeButton = new CloseButton(x1 + getWidth(), y1);
        new Line(x1, y1 + closeButton.getHeight(), x1 + getWidth(), y1 + getHeight());
    }

    public int getWidth(){
        return x2-x1;
    }

    public int getHeight(){
        return y2-y1;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }
}
