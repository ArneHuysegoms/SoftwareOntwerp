package figures.converters;

import figures.drawable.subwindowFigures.*;
import window.dialogbox.*;

import java.awt.*;

public class DialogBoxConverter extends SubwindowConverter {

    private SubwindowFigure figure;
    private DialogBox dialogBox;

    public DialogBoxConverter(DialogBox dialogBox) {
        this.dialogBox = dialogBox;
        setFigure(dialogBox);
    }

    @Override
    public void draw(Graphics graphics){
        drawSubwindow(graphics);
    }

    @Override
    public void drawSubwindow(Graphics graphics) {
        figure.draw(graphics, 0, 0, 2000, 2000);
    }

    private void setFigure(DialogBox dialogBox) {
        if (dialogBox instanceof DiagramDialogBox) {
            figure = new DiagramDialogBoxFigure((DiagramDialogBox)dialogBox);
        } else if (dialogBox instanceof InvocationMessageDialogBox) {
            figure = new InvocationMessageDialogBoxFigure((InvocationMessageDialogBox)dialogBox);
        } else if (dialogBox instanceof PartyDialogBox) {
            figure = new PartyDialogBoxFigure((PartyDialogBox) dialogBox);
        } else if (dialogBox instanceof ResultMessageDialogBox) {
            figure = new ResultMessageDialogBoxFigure((ResultMessageDialogBox) dialogBox);
        }
    }
}
