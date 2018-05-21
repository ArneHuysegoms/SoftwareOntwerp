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

    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        super.draw(graphics, minX, minY, maxX, maxY);
        drawTextBox(graphics, minX, minY, maxX, maxY);
    }

    public void drawTextBox(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new TextBoxFigure(dialogBox.getLabelTextBox(), "??? ResultDBFigure")
                .draw(graphics, minX, minY, maxX, maxY);
    }
}
