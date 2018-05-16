package figures.diagramFigures;

import figures.basicShapes.Circle;
import figures.basicShapes.FilledCircle;
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

        Point2D comm=dialogBox.getToCommunicationDiagram().getCoordinate(), seq=dialogBox.getToSequenceDiagram().getCoordinate();
        //TODO class RadioButtonFigue? want coordinate is MISSCHIEN nie midden van radio button
        //TODO miss Class maken voor radiobuttion, public RadioButton(Point2D, descripton)
        new Circle(comm,RadioButton.HEIGHT).draw(graphics, minX, minY, maxX, maxY);
        graphics.drawString(DiagramDialogBox.TOCOMMUNICATIONDIAGRAM_DESPCRIPTION, (int)comm.getX(), (int)comm.getY());

        new Circle(seq,RadioButton.HEIGHT).draw(graphics, minX, minY, maxX, maxY);
        graphics.drawString(DiagramDialogBox.TOSEQUENCEDIAGRAM_DESCRIPTION, (int)seq.getX(), (int)seq.getY());

        new FilledCircle(dialogBox.getSelected().getCoordinate(), RadioButton.HEIGHT).draw(graphics, minX, minY, maxX, maxY);;
    }
}
