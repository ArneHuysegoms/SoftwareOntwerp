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
        System.out.println("hello?");
        new RadioButtonFigure(dialogBox.getToCommunicationDiagram(), dialogBox.getAbsolutePosition(dialogBox.getToCommunicationDiagram().getCoordinate()), DiagramDialogBox.TOCOMMUNICATIONDIAGRAM_DESPCRIPTION)
                .draw(graphics, minX, minY, maxX, maxY);
        new RadioButtonFigure(dialogBox.getToSequenceDiagram(), dialogBox.getAbsolutePosition(dialogBox.getToSequenceDiagram().getCoordinate()), DiagramDialogBox.TOSEQUENCEDIAGRAM_DESCRIPTION)
                .draw(graphics, minX, minY, maxX, maxY);

        drawSelectedRadioButton(graphics);
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
    private void drawSelectedRadioButton(Graphics graphics, int minX, int minY, int maxX, int maxY) {
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
    private void drawSelectedRadioButton(Graphics graphics) {
        if (dialogBox.getDiagramSubwindow().isCommunicationDiagram()) {
            new SelectedRadioButtonFigure(dialogBox.getToCommunicationDiagram(), dialogBox.getAbsolutePosition(dialogBox.getToCommunicationDiagram().getCoordinate()), DiagramDialogBox.TOCOMMUNICATIONDIAGRAM_DESPCRIPTION)
                    .draw(graphics);
        } else if (dialogBox.getDiagramSubwindow().isSequenceDiagram()) {
            new SelectedRadioButtonFigure(dialogBox.getToSequenceDiagram(), dialogBox.getAbsolutePosition(dialogBox.getToSequenceDiagram().getCoordinate()), DiagramDialogBox.TOSEQUENCEDIAGRAM_DESCRIPTION)
                    .draw(graphics);
        }
    }
}
