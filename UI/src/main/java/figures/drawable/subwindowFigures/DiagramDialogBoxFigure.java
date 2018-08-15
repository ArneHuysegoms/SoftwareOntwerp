package figures.drawable.subwindowFigures;

import command.changeType.DiagramCommand.ChangeToCommunicationCommand;
import command.changeType.DiagramCommand.ChangeToSequenceCommand;
import figures.drawable.diagramFigures.RadioButtonFigure;
import figures.drawable.diagramFigures.SelectedRadioButtonFigure;
import window.dialogbox.DiagramDialogBox;
import window.elements.DialogboxElement;
import window.elements.radiobutton.DiagramRadioButton;
import window.elements.radiobutton.PartyRadioButton;

import java.awt.*;

public class DiagramDialogBoxFigure extends DialogBoxSubwindowFigure {

    private DiagramDialogBox dialogBox;

    public DiagramDialogBoxFigure(DiagramDialogBox dialogBox) {
        super(dialogBox.getFrame());
        this.dialogBox = dialogBox;
    }

    /**
     * draws a diagram dialog box
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
        super.handleSelectedElement(graphics, dialogBox);

        if (dialogBox.getDesignerMode()) {
            this.drawOrangeTitleBar(graphics);
        }
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
        DiagramRadioButton temp;
        //for(DialogboxElement ele : PartyDialogBox.PARTYBOXLIST){
        for(DialogboxElement ele : dialogBox.getElementList()){
            if(ele instanceof DiagramRadioButton){
                temp = (DiagramRadioButton)ele;
                if (dialogBox.getDiagramSubwindow().isCommunicationDiagram() && temp.getCommand() instanceof ChangeToCommunicationCommand) {
                    new SelectedRadioButtonFigure(temp, dialogBox.getAbsolutePosition(temp.getCoordinate()), temp.getDescription())
                            .draw(graphics, minX, minY, maxX, maxY);
                }
                else if (dialogBox.getDiagramSubwindow().isSequenceDiagram() && temp.getCommand() instanceof ChangeToSequenceCommand) {
                    new SelectedRadioButtonFigure(temp, dialogBox.getAbsolutePosition(temp.getCoordinate()), temp.getDescription())
                            .draw(graphics, minX, minY, maxX, maxY);
                }
                else {
                    new RadioButtonFigure(temp, dialogBox.getAbsolutePosition(temp.getCoordinate()), temp.getDescription())
                            .draw(graphics, minX, minY, maxX, maxY);
                }
            }
        }
    }

    /**
     * draws the selected radio button
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    /*private void drawSelectedRadioButton(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        if (dialogBox.getDiagramSubwindow().isCommunicationDiagram()) {
            new SelectedRadioButtonFigure(dialogBox.getToCommunicationDiagram(), dialogBox.getAbsolutePosition(dialogBox.getToCommunicationDiagram().getCoordinate()), "")
                    .draw(graphics, minX, minY, maxX, maxY);
        } else if (dialogBox.getDiagramSubwindow().isSequenceDiagram()) {
            new SelectedRadioButtonFigure(dialogBox.getToSequenceDiagram(), dialogBox.getAbsolutePosition(dialogBox.getToSequenceDiagram().getCoordinate()), "")
                    .draw(graphics, minX, minY, maxX, maxY);
        }
    }

    /**
     * draws the selected radio button
     *
     * @param graphics object used to draw on the program's window
     */
    /*private void drawSelectedRadioButton(Graphics graphics) {
        this.drawSelectedRadioButton(graphics, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }*/
}
