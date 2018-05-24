package figures.drawable.diagramFigures;

import window.elements.RadioButton;

import java.awt.*;
import java.awt.geom.Point2D;

public class SelectedRadioButtonFigure extends RadioButtonFigure {
    public SelectedRadioButtonFigure(RadioButton rb, Point2D absolutePosition, String title) {
        super(rb, absolutePosition, title);
    }

    /**
     * a draw function that draws a the circle of a radio button on the Graphics parameter object
     *
     * @param graphics       object used to draw on the program's window
     * @param circlePosition coordinate of the top left point of this button
     */
    @Override
    protected void drawCircle(Graphics graphics, Point2D circlePosition) {
        graphics.fillOval((int)absolutePosition.getX(), (int)absolutePosition.getY(),RadioButton.WIDTH, RadioButton.HEIGHT);
    }

}
