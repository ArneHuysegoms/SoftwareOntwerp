package figures.diagramFigures;

import figures.basicShapes.CloseButton;
import figures.basicShapes.Line;
import figures.basicShapes.Rectangle;

import java.awt.*;
import java.awt.geom.Point2D;

public class SubwindowFigure extends Figure{

    private int x,y,heigth,width;

    public SubwindowFigure(Point2D point, int height, int width){
        x = (int)point.getX();
        y = (int)point.getY();
        this.heigth = height;
        this.width = width;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x,y,width,heigth);
        graphics.setColor(Color.BLACK);
        new Rectangle(x,y, width, heigth);
        CloseButton closeButton = new CloseButton(x+width, y);
        new Line(x, y+closeButton.getHeight(),x+width, y+heigth);
    }
}
