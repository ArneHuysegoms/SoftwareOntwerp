package figures.diagramFigures;

import figures.basicShapes.Rectangle;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActivationBar extends Figure {

    private Point2D tl, br;

    public ActivationBar(Point2D tl, Point2D br){
        this.tl = tl;
        this.br = br;
    }

    @Override
    public void draw(Graphics graphics) {
        Rectangle r = new Rectangle(tl,br);
        r.draw(graphics);
    }
}
