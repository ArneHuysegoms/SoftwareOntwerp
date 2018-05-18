package figures;

import controller.CanvasController;

import figures.diagramFigures.*;
import window.Subwindow;
import window.diagram.DiagramSubwindow;
import view.diagram.CommunicationView;
import view.diagram.SequenceView;
import window.dialogbox.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * a class that converts the data from the diagram model to drawn figures of the program's window
 */
public class FigureConverter {

    private Converter converter;
    //private Drawer subwindowDrawer;

    /**
     * default constructor
     */
    public FigureConverter() {

    }

    /**
     * main draw function
     *
     * @param graphics        object used to draw on the program's window
     //* @param subwindowLevels the subwindows to be drawn on the controller
     */
    public void draw(Graphics graphics, CanvasController canvasController) {

        drawBackGroundColor(graphics);
        drawSubwindows(graphics, sortDiagramSubwindows(canvasController));

    }

    private List<Subwindow> sortDiagramSubwindows(CanvasController canvasController) {
        List<DiagramSubwindow> temp = new ArrayList<DiagramSubwindow>();
        List<Subwindow> result = new ArrayList<Subwindow>();

        for (InteractionController interaction : canvasController.getInteractionControllers()) {
            temp.addAll(interaction.getDiagramSubwindows());
        }
        for (DiagramSubwindow sub : temp) {
            result.addAll(sub.getDialogBoxes());
        }
        result.addAll(temp);
        Collections.sort(result);
        return result;
    }

    private void drawSubwindows(Graphics graphics, List<Subwindow> canvasController) {
        DiagramSubwindow tempDS;
        for (Subwindow sub : canvasController) {
            if (sub instanceof DiagramSubwindow) {
                tempDS = (DiagramSubwindow)sub;
                setConverter(tempDS);
                drawSubwindow(graphics, tempDS);
                converter.draw(graphics, tempDS.getFacade().getActiveRepo(), tempDS.getFacade().getDiagram(), tempDS.getSelected());
            }
            else {
                drawDialogBoxes(graphics,(DialogBox)sub);
            }
        }
    }

    private void drawDialogBoxes(Graphics graphics, DialogBox sub) {
        if(sub instanceof PartyDialogBox){
            new PartyDialogBoxFigure((PartyDialogBox)sub).draw(graphics,0, 0, 2000, 2000);
        }
        else if(sub instanceof DiagramDialogBox){
            new DiagramDialogBoxFigure((DiagramDialogBox)sub).draw(graphics,0, 0, 2000, 2000);
        }
        else if(sub instanceof InvocationMessageDialogBox){
            new InvocationMessageDialogBoxFigure((InvocationMessageDialogBox)sub).draw(graphics,0, 0, 2000, 2000);

        }
        else if(sub instanceof ResultMessageDialogBox){
            new ResultMessageDialogBoxFigure((ResultMessageDialogBox)sub).draw(graphics,0, 0, 2000, 2000);

        }
    }

    /**
     * function that draws the backround gray
     *
     * @param graphics object used to draw on the program's window
     */
    private void drawBackGroundColor(Graphics graphics) {
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, 2000, 1000);
        graphics.setColor(Color.BLACK);
    }

    /**
     * method that draws a subwindow
     *
     * @param graphics object used to draw on the program's window
     //* @param position coordinate of the top-left point of the subwindow
     //* @param width    the subwindow's width
     //* @param height   the subwindow's height
     */
    private void drawSubwindow(Graphics graphics, DiagramSubwindow sub) {
        new DiagramSubwindowFigure(sub).draw(graphics, 0, 0, 2000, 2000);
    }

    /**
     * creates and sets the correct converter
     *
     * @param sub
     */
    public void setConverter(DiagramSubwindow sub) {
        if (sub.getFacade().getActiveRepo() instanceof SequenceView) {
            converter = new SequenceFigureConverter(sub);
        } else if (sub.getFacade().getActiveRepo() instanceof CommunicationView) {
            converter = new CommunicationFigureConverter(sub);
        }
    }

    private class InteractionController {
        private List<DiagramSubwindow> diagramSubwindows;

        public List<DiagramSubwindow> getDiagramSubwindows() {
            return diagramSubwindows;
        }
    }
}