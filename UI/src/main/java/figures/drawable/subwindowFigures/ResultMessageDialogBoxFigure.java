package figures.drawable.subwindowFigures;

import figures.drawable.diagramFigures.TextBoxFigure;
import window.dialogbox.ResultMessageDialogBox;

import java.awt.*;

public class ResultMessageDialogBoxFigure extends SubwindowFigure {

    private ResultMessageDialogBox dialogBox;

    public ResultMessageDialogBoxFigure(ResultMessageDialogBox dialogBox) {
        super(dialogBox.getFrame());
        this.dialogBox = dialogBox;
    }

    /**
     * draws a result message dialog box
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        drawWindowFrame(graphics);
        drawTextBox(graphics, minX, minY, maxX, maxY);
        super.handleSelectedElement(graphics,dialogBox.getSelected(),dialogBox.getAbsolutePosition(dialogBox.getSelected().getCoordinate()));
    }

    /**
     * draws a text box
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    private void drawTextBox(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new TextBoxFigure(dialogBox.getLabelTextBox(), dialogBox.getAbsolutePosition(dialogBox.getLabelTextBox().getCoordinate()), "??? ResultDBFigure")
                .draw(graphics, minX, minY, maxX, maxY);
    }

    /**
     * draws a text box
     * @param graphics object used to draw on the program's window
     */
    private void drawTextBox(Graphics graphics) {
        new TextBoxFigure(dialogBox.getLabelTextBox(), dialogBox.getAbsolutePosition(dialogBox.getLabelTextBox().getCoordinate()), "??? ResultDBFigure")
                .draw(graphics);
    }
}
