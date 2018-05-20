package figures.drawable.diagramFigures;

import figures.drawable.IDrawable;
import figures.drawable.basicShapes.Circle;
import window.elements.RadioButton;

import java.awt.*;
import java.awt.geom.Point2D;

public class RadioButtonFigure implements IDrawable {

    private RadioButton rb;
    private String title;

    public RadioButtonFigure(RadioButton rb, String title){
        this.rb = rb;
        this.title = title;
    }

    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        //TODO might have to tweek coordinates
        Point2D circlePosition=rb.getCoordinate();
        Point2D textPosition = new Point2D.Double(circlePosition.getX()+RadioButton.WIDTH+3,circlePosition.getY());

        drawTitle(graphics, textPosition);
        drawCirle(graphics, circlePosition, minX, minY, maxX, maxY);
    }

    protected void drawCirle(Graphics graphics, Point2D circlePosition, int minX, int minY, int maxX, int maxY) {
        new Circle(circlePosition,RadioButton.HEIGHT).draw(graphics, minX, minY, maxX, maxY);
    }

    private void drawTitle(Graphics graphics, Point2D titlePosition) {
        graphics.drawString(title, (int)titlePosition.getX(), (int)titlePosition.getY());
    }
}
