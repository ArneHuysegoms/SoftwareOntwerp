package figures.drawable.subwindowFigures;

import figures.drawable.diagramFigures.RadioButtonFigure;
import figures.drawable.diagramFigures.SelectedRadioButtonFigure;
import window.dialogbox.DiagramDialogBox;

import java.awt.*;

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

        new RadioButtonFigure(dialogBox.getToCommunicationDiagram(), DiagramDialogBox.TOCOMMUNICATIONDIAGRAM_DESPCRIPTION)
                .draw(graphics, minX, minY, maxX, maxY);
        new RadioButtonFigure(dialogBox.getToSequenceDiagram(), DiagramDialogBox.TOSEQUENCEDIAGRAM_DESCRIPTION)
                .draw(graphics, minX, minY, maxX, maxY);

        drawSelectedRadioButton(graphics, minX, minY, maxX, maxY);
    }

    private void drawSelectedRadioButton(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        if (dialogBox.getDiagramSubwindow().isCommunicationDiagram()) {
            new SelectedRadioButtonFigure(dialogBox.getToCommunicationDiagram(), "")
                    .draw(graphics, minX, minY, maxX, maxY);
        } else if (dialogBox.getDiagramSubwindow().isSequenceDiagram()) {
            new SelectedRadioButtonFigure(dialogBox.getToSequenceDiagram(),"")
                    .draw(graphics, minX, minY, maxX, maxY);
        }
    }
}
