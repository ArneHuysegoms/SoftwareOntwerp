package figures.drawable.basicShapes;

import window.elements.RadioButton;

import java.awt.*;
import java.awt.geom.Point2D;

public class SelectedRadioButtonFigure extends RadioButtonFigure {
    public SelectedRadioButtonFigure(RadioButton rb, String title) {
        super(rb, title);
    }

    @Override
    protected void drawCirle(Graphics graphics, Point2D circlePosition, int minX, int minY, int maxX, int maxY) {
        new FilledCircle(circlePosition,RadioButton.HEIGHT).draw(graphics, minX, minY, maxX, maxY);
    }

}
