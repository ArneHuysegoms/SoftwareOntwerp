package figures.diagramFigures;

import figures.basicShapes.CloseButtonFigure;
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
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x1, y1, getWidth(), getHeight());
        graphics.setColor(Color.BLACK);
        new Rectangle(x1, y1, getWidth(), getHeight()).draw(graphics,minX,minY,maxX,maxY);
        CloseButtonFigure closeButtonFigure = new CloseButtonFigure(x1 + getWidth(), y1);
        closeButtonFigure.draw(graphics,minX,minY,maxX,maxY);
        new Line(x1, y1 + closeButtonFigure.getHeight(), x1 + getWidth(), y1 + closeButtonFigure.getHeight()).draw(graphics,minX,minY,maxX,maxY);
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
