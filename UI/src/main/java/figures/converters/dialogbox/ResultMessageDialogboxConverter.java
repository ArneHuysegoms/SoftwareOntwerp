package figures.converters.dialogbox;

import figures.drawable.subwindowFigures.ResultMessageDialogBoxFigure;
import window.dialogbox.ResultMessageDialogBox;

import java.awt.*;

public class ResultMessageDialogboxConverter extends DialogboxConverter {

    private ResultMessageDialogBox dialogBox;

    public ResultMessageDialogboxConverter(ResultMessageDialogBox dialogBox) {
        this.dialogBox = dialogBox;
        setFigure();
    }

    protected void setFigure() {
        this.figure = new ResultMessageDialogBoxFigure(dialogBox);
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
        drawSelectionBox(graphics, dialogBox.getSelected());
    }
}
