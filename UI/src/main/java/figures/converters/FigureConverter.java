package figures.converters;

import controller.CanvasController;

import controller.InteractionController;
import figures.drawable.subwindowFigures.*;
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
 * This class is the most general of the converter classes. All the drawing starts here.
 * Objects get seperated here to be drawn by their specific converter
 */
public class FigureConverter {

    private SubwindowConverter converter;
    //private Drawer subwindowDrawer;

    /**
     * default constructor
     */
    public FigureConverter() {

    }

    /**
     * main draw function
     *
     * @param graphics object used to draw on the program's window
     *                 //* @param subwindowLevels the subwindows to be drawn on the controller
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
            result.addAll(sub.getDialogBoxlist());
        }
        result.addAll(temp);
        Collections.sort(result);
        return result;
    }

    private void drawSubwindows(Graphics graphics, List<Subwindow> allSubwindows) {
        DiagramSubwindow tempDS;
        for (Subwindow sub : allSubwindows) {
            setConverter(sub);
            converter.draw(graphics);

            /*
            if (sub instanceof DiagramSubwindow) {
                tempDS = (DiagramSubwindow) sub;
                setConverter(tempDS);
                drawSubwindow(graphics, tempDS);
                converter.draw(graphics);
            } else {
                drawDialogBoxes(graphics, (DialogBox) sub);
            }
            */
        }
    }

    private void drawDialogBoxes(Graphics graphics, DialogBox sub) {
        drawDialogBoxStructure(graphics, sub);

    }

    public void drawDialogBoxStructure(Graphics graphics, DialogBox sub) {
        if (sub instanceof PartyDialogBox) {
            new PartyDialogBoxFigure((PartyDialogBox) sub).draw(graphics, 0, 0, 2000, 2000);
        } else if (sub instanceof DiagramDialogBox) {
            new DiagramDialogBoxFigure((DiagramDialogBox) sub).draw(graphics, 0, 0, 2000, 2000);
        } else if (sub instanceof InvocationMessageDialogBox) {
            new InvocationMessageDialogBoxFigure((InvocationMessageDialogBox) sub).draw(graphics, 0, 0, 2000, 2000);

        } else if (sub instanceof ResultMessageDialogBox) {
            new ResultMessageDialogBoxFigure((ResultMessageDialogBox) sub).draw(graphics, 0, 0, 2000, 2000);

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
     *                 //* @param position coordinate of the top-left point of the subwindow
     *                 //* @param width    the subwindow's width
     *                 //* @param height   the subwindow's height
     */
    private void drawSubwindow(Graphics graphics, DiagramSubwindow sub) {
        //TODO replace to specific converters
        new DiagramSubwindowFigure(sub).draw(graphics, 0, 0, 2000, 2000);
    }

    /**
     * creates and sets the correct converter
     *
     * @param sub
     */
    public void setConverter(Subwindow sub) {
        if (sub instanceof DiagramSubwindow) {
            DiagramSubwindow temp = ((DiagramSubwindow) sub);
            if (temp.getFacade().getActiveRepo() instanceof SequenceView) {
                converter = new SequenceConverter(temp);
            } else if (temp.getFacade().getActiveRepo() instanceof CommunicationView) {
                converter = new CommunicationConverter(temp);
            }
        } else if (sub instanceof DialogBox) {
            converter = new DialogBoxConverter((DialogBox) sub);
        }
    }
}