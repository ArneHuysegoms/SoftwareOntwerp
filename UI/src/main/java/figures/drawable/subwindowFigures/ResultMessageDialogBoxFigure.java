package figures.drawable.subwindowFigures;

import figures.drawable.diagramFigures.TextBoxFigure;
import window.dialogbox.ResultMessageDialogBox;
import window.elements.DialogboxElement;
import window.elements.textbox.ClassTextBox;
import window.elements.textbox.InstanceTextBox;
import window.elements.textbox.TextBox;

import java.awt.*;

public class ResultMessageDialogBoxFigure extends DialogBoxSubwindowFigure {

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
        drawTextBoxes(graphics, minX, minY, maxX, maxY);
        super.handleSelectedElement(graphics,dialogBox);

        if(dialogBox.getDesignerMode()) {
            this.drawOrangeTitleBar(graphics);
        }
    }

    /**
     * draws a text box
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    public void drawTextBoxes(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        TextBox temp;
        for(DialogboxElement ele : dialogBox.getElementList()){
            temp = (TextBox) ele;
            new TextBoxFigure(temp, dialogBox.getAbsolutePosition(temp.getCoordinate()), temp.getDescription())
                    .draw(graphics, minX, minY, maxX, maxY);
        }
    }

    /**
     * draws a text box
     * @param graphics object used to draw on the program's window
     */
    public void drawTextBoxes(Graphics graphics) {
        this.drawTextBoxes(graphics, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
}
