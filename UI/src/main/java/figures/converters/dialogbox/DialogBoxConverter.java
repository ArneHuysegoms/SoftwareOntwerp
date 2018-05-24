package figures.converters.dialogbox;

import figures.converters.SubwindowConverter;
import figures.drawable.subwindowFigures.*;
import window.dialogbox.*;

import java.awt.*;

public class DialogBoxConverter extends SubwindowConverter {

    protected SubwindowFigure figure;

    public DialogBoxConverter(DialogBox dialogBox){
        if (dialogBox instanceof PartyDialogBox) {
            figure = new PartyDialogBoxFigure((PartyDialogBox)dialogBox);
        } else if (dialogBox instanceof InvocationMessageDialogBox) {
            figure = new InvocationMessageDialogBoxFigure((InvocationMessageDialogBox)dialogBox);
        } else if (dialogBox instanceof ResultMessageDialogBox) {
            figure = new ResultMessageDialogBoxFigure((ResultMessageDialogBox)dialogBox);
        } else if (dialogBox instanceof DiagramDialogBox) {
            figure = new DiagramDialogBoxFigure((DiagramDialogBox)dialogBox);
        }
    }
    /**
     * method that draws the subwindow's skeleton, an empty subwindow
     *
     * @param graphics object used to draw on the program's window
     */
    @Override
    public void drawSubwindow(Graphics graphics) {
        figure.draw(graphics);
    }
}