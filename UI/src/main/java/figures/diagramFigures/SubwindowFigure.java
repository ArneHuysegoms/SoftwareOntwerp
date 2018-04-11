package figures.diagramFigures;

import figures.basicShapes.CloseButton;
import figures.basicShapes.Line;
import figures.basicShapes.Rectangle;

import java.awt.*;
import java.awt.geom.Point2D;

public class SubwindowFigure extends Figure{

    private int x,y;

    public SubwindowFigure(Point2D point){
        x = (int)point.getX();
        y = (int)point.getY();
    }

    @Override
    public void draw(Graphics graphics) {
        //graphics.setColor(Color.WHITE);
        //graphics.fillRect(x,y,Subwindow.WIDTH,Subwindow.HEIGHT);
        //graphics.setColor(Color.BLACK);
        //new Rectangle(x,y, Subwindow.WIDTH, Subwindow.HEIGHT);
        //closeButton = new CloseButton(x+Subwindow.WIDTH, y);
        //new Line(x, y+closeButton.getHeight(),x+Subwindow.WIDTH, y+closeButton.getHeight());

        graphics.setColor(Color.WHITE);
        graphics.fillRect(x,y,300,300);
        graphics.setColor(Color.BLACK);
        new Rectangle(x,y, 300, 300).draw(graphics);
        CloseButton closeButton = new CloseButton(x+300, y);
        closeButton.draw(graphics);
        new Line(x, y+closeButton.getHeight(),x+300, y+closeButton.getHeight()).draw(graphics);
    }
}
