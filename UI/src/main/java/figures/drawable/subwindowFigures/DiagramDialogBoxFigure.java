package figures.drawable.subwindowFigures;

import figures.drawable.basicShapes.Circle;
import figures.drawable.basicShapes.FilledCircle;
import figures.drawable.basicShapes.RadioButtonFigure;
import window.dialogbox.DiagramDialogBox;
import window.elements.RadioButton;

import java.awt.*;
import java.awt.geom.Point2D;

//TODO A dialog box for a diagram shows two radio buttons for choosing between a sequence diagram and a communication diagram.

public class DiagramDialogBoxFigure extends SubwindowFigure {

    private DiagramDialogBox dialogBox;
    /**
     * @param dialogBox
     */
    public DiagramDialogBoxFigure(DiagramDialogBox dialogBox) {
        super(dialogBox.getFrame());
        this.dialogBox = dialogBox;
    }


    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        super.draw(graphics, minX, minY, maxX, maxY);

        new RadioButtonFigure(dialogBox.getToCommunicationDiagram(),DiagramDialogBox.TOCOMMUNICATIONDIAGRAM_DESPCRIPTION)
        .draw(graphics, minX, minY, maxX, maxY);
        new RadioButtonFigure(dialogBox.getToSequenceDiagram(),DiagramDialogBox.TOSEQUENCEDIAGRAM_DESCRIPTION)
        .draw(graphics, minX, minY, maxX, maxY);

       drawSelectedRadioButton(graphics, minX, minY, maxX, maxY)
        new FilledCircle(dialogBox.getSelected().getCoordinate(), RadioButton.HEIGHT).draw(graphics, minX, minY, maxX, maxY);;
    }

    private void drawSelectedRadioButton(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        //TODO NEXT
        //if(dialogBox.getDiagramSubwindow() instanceof Communication)
    }


}
