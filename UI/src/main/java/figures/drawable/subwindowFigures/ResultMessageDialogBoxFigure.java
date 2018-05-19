package figures.drawable.subwindowFigures;

import window.dialogbox.ResultMessageDialogBox;
import window.elements.textbox.TextBox;

import java.awt.*;

public class ResultMessageDialogBoxFigure extends SubwindowFigure {

    private ResultMessageDialogBox dialogBox;

    public ResultMessageDialogBoxFigure(ResultMessageDialogBox dialogBox) {
        super(dialogBox.getFrame());
        this.dialogBox = dialogBox;
    }

    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        super.draw(graphics, minX, minY, maxX, maxY);
        drawTextBox(graphics, minX, minY, maxX, maxY);
    }

    public void drawTextBox(Graphics graphics, int minX, int minY, int maxX, int maxY){
        //TODO description of static title dingetje toevoegen. EN .Wat er in de textBox sta/getypt wordt
        new figures.drawable.basicShapes.Rectangle(dialogBox.getLabelTextBox().getCoordinate(), TextBox.HEIGHT, TextBox.WIDTH).draw(graphics, minX, minY, maxX, maxY);
    }

    //TODO A dialog box for a result message shows a text box for the label.
}
