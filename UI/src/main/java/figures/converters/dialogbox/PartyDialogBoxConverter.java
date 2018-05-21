package figures.converters.dialogbox;

import figures.drawable.subwindowFigures.PartyDialogBoxFigure;
import window.dialogbox.PartyDialogBox;

import java.awt.*;

public class PartyDialogBoxConverter extends DialogBoxConverter {

    private PartyDialogBox dialogBox;

    public PartyDialogBoxConverter(PartyDialogBox dialogBox) {
        this.dialogBox = dialogBox;
        setFigure();
    }

    protected void setFigure() {
        this.figure = new PartyDialogBoxFigure(dialogBox);
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
        drawSelectionBox(graphics, dialogBox.getSelected());
    }
}
