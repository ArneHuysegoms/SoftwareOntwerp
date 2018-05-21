package figures.drawable.diagramFigures;

import figures.drawable.basicShapes.FilledCircle;
import figures.drawable.diagramFigures.RadioButtonFigure;
import window.elements.RadioButton;

import java.awt.*;
import java.awt.geom.Point2D;

public class SelectedRadioButtonFigure extends RadioButtonFigure {
    public SelectedRadioButtonFigure(RadioButton rb, String title) {
        super(rb, title);
    }

    /**
     * a draw function that draws a the circle of a radio button on the Graphics parameter object
     *
     * @param graphics       object used to draw on the program's window
     * @param circlePosition coordinate of the top left point of this button
     * @param minX           minimum possible x coördinate value
     * @param minY           minimum possible y coördinate value
     * @param maxX           maximum possible x coördinate value
     * @param maxY           maximum possible y coördinate value
     */
    @Override
    protected void drawCircle(Graphics graphics, Point2D circlePosition, int minX, int minY, int maxX, int maxY) {
        new FilledCircle(circlePosition,RadioButton.HEIGHT).draw(graphics, minX, minY, maxX, maxY);
    }

    /**
     * a draw function that draws a the circle of a radio button on the Graphics parameter object
     *
     * @param graphics       object used to draw on the program's window
     * @param circlePosition coordinate of the top left point of this button
     */
    @Override
    protected void drawCircle(Graphics graphics, Point2D circlePosition) {
        new FilledCircle(circlePosition,RadioButton.HEIGHT).draw(graphics);
    }

}
