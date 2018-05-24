package figures.converters;

import controller.CanvasController;
import controller.InteractionController;
import figures.converters.diagramSub.CommunicationConverter;
import figures.converters.diagramSub.SequenceConverter;
import figures.converters.dialogbox.DiagramDialogBoxConverter;
import figures.converters.dialogbox.InvocationMessageDialogBoxConverter;
import figures.converters.dialogbox.PartyDialogBoxConverter;
import figures.converters.dialogbox.ResultMessageDialogBoxConverter;
import figures.drawable.subwindowFigures.*;
import figures.converters.dialogbox.*;







import view.diagram.CommunicationView;
import view.diagram.SequenceView;
import window.Subwindow;
import window.diagram.DiagramSubwindow;
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
     * main draw function
     *
     * @param graphics         object used to draw on the program's window
     *                         //* @param subwindowLevels the subwindows to be drawn on the controller
     * @param list the controller that has acces to all the stuff to be drawn
     */
    public void draw(Graphics graphics, List<Subwindow> list) {
        drawBackGroundColor(graphics);
        drawSubwindows(graphics, list);
    }

    /**
     * function that draws all the subwindows from the given list
     *
     * @param graphics      object used to draw on the program's window
     * @param allSubwindows List containing all subwindows
     */
    private void drawSubwindows(Graphics graphics, List<Subwindow> allSubwindows) {
        DiagramSubwindow tempDS;
        for (Subwindow sub : allSubwindows) {
            setConverter(sub);
            converter.draw(graphics);
            converter.drawSubwindow(graphics);
        }
    }

    private void drawDialogBoxes(Graphics graphics, DialogBox sub) {
        drawDialogBoxStructure(graphics, sub);

    }

    public void drawDialogBoxStructure(Graphics graphics, DialogBox sub) {
        if (sub instanceof PartyDialogBox) {
            new PartyDialogBoxFigure((PartyDialogBox) sub).draw(graphics);
        } else if (sub instanceof DiagramDialogBox) {
            new DiagramDialogBoxFigure((DiagramDialogBox) sub).draw(graphics);
        } else if (sub instanceof InvocationMessageDialogBox) {
            new InvocationMessageDialogBoxFigure((InvocationMessageDialogBox) sub).draw(graphics);
        } else if (sub instanceof ResultMessageDialogBox) {
            new ResultMessageDialogBoxFigure((ResultMessageDialogBox) sub).draw(graphics);

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
     * creates and sets the correct converter
     *
     * @param sub
     * @param subwindow subwindow where the converter will be used for
     */
    private void setConverter(Subwindow sub) {
        if (sub instanceof DiagramSubwindow) {
            DiagramSubwindow temp = ((DiagramSubwindow) sub);
    private void setConverter(Subwindow subwindow) {
        if (subwindow instanceof DiagramSubwindow) {
            DiagramSubwindow temp = ((DiagramSubwindow) subwindow);
            if (temp.getFacade().getActiveView() instanceof SequenceView) {
                converter = new SequenceConverter(temp);
            } else if (temp.getFacade().getActiveView() instanceof CommunicationView) {
                converter = new CommunicationConverter(temp);
            }
        } else if (sub instanceof PartyDialogBox) {
            converter = new PartyDialogBoxConverter((PartyDialogBox) sub);
        } else if (sub instanceof InvocationMessageDialogBox) {
            converter = new InvocationMessageDialogBoxConverter((InvocationMessageDialogBox) sub);
        } else if (sub instanceof ResultMessageDialogBox) {
            converter = new ResultMessageDialogBoxConverter((ResultMessageDialogBox) sub);
        } else if (sub instanceof DiagramDialogBox) {
            converter = new DiagramDialogBoxConverter((DiagramDialogBox) sub);
        } else if(subwindow instanceof DialogBox){
            converter = new DialogBoxConverter((DialogBox)subwindow);
        }
    }
}