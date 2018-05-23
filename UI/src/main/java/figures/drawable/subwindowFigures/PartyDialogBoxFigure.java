package figures.drawable.subwindowFigures;

import diagram.party.Actor;
import figures.drawable.diagramFigures.*;
import window.dialogbox.PartyDialogBox;
import window.elements.DialogboxElement;
import window.elements.ListBox;
import window.elements.RadioButton;
import window.elements.button.FakeButton;
import window.elements.textbox.TextBox;

import java.awt.*;
import java.awt.geom.Point2D;

public class PartyDialogBoxFigure extends DialogBoxSubwindowFigure {

    private PartyDialogBox dialogBox;

    public PartyDialogBoxFigure(PartyDialogBox dialogBox) {
        super(dialogBox.getFrame());
        this.dialogBox = dialogBox;
    }

    /**
     * draws a party dialog box
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        drawWindowFrame(graphics);
        drawRadioButtons(graphics, minX, minY, maxX, maxY);
        drawTextBoxes(graphics, minX, minY, maxX, maxY);
        super.handleSelectedElement(graphics,dialogBox.getSelected(),dialogBox.getAbsolutePosition(dialogBox.getSelected().getCoordinate()));
    }

    /**
     * draws the dialog box's text boxes
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    private void drawTextBoxes(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new TextBoxFigure(dialogBox.getInstanceTextBox(), dialogBox.getAbsolutePosition(dialogBox.getInstanceTextBox().getCoordinate()), PartyDialogBox.INSTANCE_DESCRIPTION)
                .draw(graphics, minX, minY, maxX, maxY);
        new TextBoxFigure(dialogBox.getClassTextBox(), dialogBox.getAbsolutePosition(dialogBox.getClassTextBox().getCoordinate()), PartyDialogBox.CLASS_DESCRIPTION)
                .draw(graphics, minX, minY, maxX, maxY);
    }

    /**
     * draws the dialog box's text boxes
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawTextBoxes(Graphics graphics) {
        new TextBoxFigure(dialogBox.getInstanceTextBox(), dialogBox.getAbsolutePosition(dialogBox.getInstanceTextBox().getCoordinate()), PartyDialogBox.INSTANCE_DESCRIPTION)
                .draw(graphics);
        new TextBoxFigure(dialogBox.getClassTextBox(), dialogBox.getAbsolutePosition(dialogBox.getClassTextBox().getCoordinate()), PartyDialogBox.CLASS_DESCRIPTION)
                .draw(graphics);
    }

    /**
     * draws the dialog box's radio buttons
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    private void drawRadioButtons(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        new RadioButtonFigure(dialogBox.getToActor(), dialogBox.getAbsolutePosition(dialogBox.getToActor().getCoordinate()), PartyDialogBox.TOACTOR_DESCRIPTION)
                .draw(graphics, minX, minY, maxX, maxY);
        new RadioButtonFigure(dialogBox.getToObject(), dialogBox.getAbsolutePosition(dialogBox.getToObject().getCoordinate()), PartyDialogBox.TOOBJECT_DESPCRIPTION)
                .draw(graphics, minX, minY, maxX, maxY);
        drawSelectedRadioButton(graphics);
    }

    /**
     * draws the dialog box's radio buttons
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawRadioButtons(Graphics graphics) {
        new RadioButtonFigure(dialogBox.getToActor(), dialogBox.getAbsolutePosition(dialogBox.getToActor().getCoordinate()), PartyDialogBox.TOACTOR_DESCRIPTION)
                .draw(graphics);
        new RadioButtonFigure(dialogBox.getToObject(), dialogBox.getAbsolutePosition(dialogBox.getToObject().getCoordinate()), PartyDialogBox.TOOBJECT_DESPCRIPTION)
                .draw(graphics);
        drawSelectedRadioButton(graphics);
    }

    /**
     * draws the dialog box's selected radio button
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawSelectedRadioButton(Graphics graphics) {
        if (dialogBox.getParty() instanceof Actor) {
            new SelectedRadioButtonFigure(dialogBox.getToActor(), dialogBox.getAbsolutePosition(dialogBox.getToActor().getCoordinate()),"")
                    .draw(graphics);
        } else if (dialogBox.getParty() instanceof Object) {
            new SelectedRadioButtonFigure(dialogBox.getToObject(),  dialogBox.getAbsolutePosition(dialogBox.getToObject().getCoordinate()),"")
                    .draw(graphics);
        }
    }
}
