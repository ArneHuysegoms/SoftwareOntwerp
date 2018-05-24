package figures.converters.dialogbox;

import figures.drawable.subwindowFigures.PartyDialogBoxFigure;
import window.dialogbox.PartyDialogBox;

public class PartyDialogBoxConverter extends DialogBoxConverter {

    private PartyDialogBox dialogBox;

    public PartyDialogBoxConverter(PartyDialogBox dialogBox) {
        this.dialogBox = dialogBox;
        this.figure = new PartyDialogBoxFigure(dialogBox);
    }
}
