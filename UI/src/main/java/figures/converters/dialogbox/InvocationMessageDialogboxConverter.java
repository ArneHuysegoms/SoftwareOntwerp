package figures.converters.dialogbox;

import figures.drawable.subwindowFigures.InvocationMessageDialogBoxFigure;
import window.dialogbox.InvocationMessageDialogBox;

import java.awt.*;

public class InvocationMessageDialogboxConverter extends DialogboxConverter {

    private InvocationMessageDialogBox dialogBox;

    public InvocationMessageDialogboxConverter(InvocationMessageDialogBox dialogBox) {
        this.dialogBox = dialogBox;
        setFigure();
    }

    protected void setFigure() {
        this.figure = new InvocationMessageDialogBoxFigure(dialogBox);
    }

    @Override
    public void draw(Graphics graphics){
        super.draw(graphics);
        drawSelectionBox(graphics, dialogBox.getSelected());
    }
}
