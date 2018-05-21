package figures.converters.dialogbox;

import figures.drawable.subwindowFigures.PartyDialogBoxFigure;
import window.dialogbox.PartyDialogBox;

import java.awt.*;

public class PartyDialogboxConverter extends DialogboxConverter {

    private PartyDialogBox dialogBox;

    public PartyDialogboxConverter(PartyDialogBox dialogBox) {
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
