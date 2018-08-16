package figures.drawable.subwindowFigures;

import command.changeType.PartyCommand.ChangeToActorCommand;
import command.changeType.PartyCommand.ChangeToObjectCommand;
import diagram.DiagramElement;
import diagram.party.Actor;
import diagram.party.Object;
import figures.drawable.diagramFigures.RadioButtonFigure;
import figures.drawable.diagramFigures.SelectedRadioButtonFigure;
import figures.drawable.diagramFigures.SelectedTextBoxFigure;
import figures.drawable.diagramFigures.TextBoxFigure;
import window.dialogbox.PartyDialogBox;
import window.elements.DialogboxElement;
import window.elements.radiobutton.PartyRadioButton;
import window.elements.radiobutton.RadioButton;
import window.elements.textbox.ClassTextBox;
import window.elements.textbox.InstanceTextBox;
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
        super.handleSelectedElement(graphics,dialogBox);

        if(dialogBox.getDesignerMode()) {
            this.drawOrangeTitleBar(graphics);
        }
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
        TextBox temp;
        for(DialogboxElement ele : dialogBox.getElementList()){
            if(ele instanceof InstanceTextBox) {
                temp = (TextBox) ele;
                new TextBoxFigure(temp, dialogBox.getAbsolutePosition(temp.getCoordinate()), temp.getDescription())
                        .draw(graphics, minX, minY, maxX, maxY);
            }
            if(ele instanceof ClassTextBox) {
                temp = (TextBox) ele;
                new TextBoxFigure(temp, dialogBox.getAbsolutePosition(temp.getCoordinate()), temp.getDescription())
                        .draw(graphics, minX, minY, maxX, maxY);
            }
        }
    }

    /**
     * draws the dialog box's text boxes
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawTextBoxes(Graphics graphics) {
        this.drawTextBoxes(graphics, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
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
        PartyRadioButton temp;
        for(DialogboxElement ele : dialogBox.getElementList()){
            if(ele instanceof PartyRadioButton){
                temp = (PartyRadioButton)ele;
                if (dialogBox.getParty() instanceof Actor && temp.getCommand() instanceof ChangeToActorCommand) {
                    new SelectedRadioButtonFigure(temp, dialogBox.getAbsolutePosition(temp.getCoordinate()), temp.getDescription())
                            .draw(graphics);
                } else if (dialogBox.getParty() instanceof Object && temp.getCommand() instanceof ChangeToObjectCommand) {
                    new SelectedRadioButtonFigure(temp, dialogBox.getAbsolutePosition(temp.getCoordinate()), temp.getDescription())
                            .draw(graphics);
                }
                else {
                    new RadioButtonFigure(temp, dialogBox.getAbsolutePosition(temp.getCoordinate()), temp.getDescription())
                            .draw(graphics, minX, minY, maxX, maxY);
                }
            }
        }
    }

    /**
     * draws the dialog box's radio buttons
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawRadioButtons(Graphics graphics) {
        this.drawRadioButtons(graphics, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
}
