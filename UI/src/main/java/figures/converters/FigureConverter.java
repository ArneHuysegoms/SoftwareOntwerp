package figures.converters;

import controller.CanvasController;

import controller.InteractionController;
import exception.UIException;
import figures.converters.diagramSub.CommunicationConverter;
import figures.converters.diagramSub.SequenceConverter;
import figures.converters.dialogbox.*;
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
     * creates and sets the correct converter
     *
     * @param sub
     */
    public void setConverter(Subwindow sub){
        if (sub instanceof DiagramSubwindow) {
            DiagramSubwindow temp = ((DiagramSubwindow) sub);
            if (temp.getFacade().getActiveRepo() instanceof SequenceView) {
                converter = new SequenceConverter(temp);
            } else if (temp.getFacade().getActiveRepo() instanceof CommunicationView) {
                converter = new CommunicationConverter(temp);
            }
        } else if (sub instanceof PartyDialogBox) {
            converter = new PartyDialogboxConverter((PartyDialogBox) sub);
        }else if (sub instanceof InvocationMessageDialogBox) {
            converter = new InvocationMessageDialogboxConverter((InvocationMessageDialogBox) sub);
        }else if (sub instanceof ResultMessageDialogBox) {
            converter = new ResultMessageDialogboxConverter((ResultMessageDialogBox) sub);
        }else if (sub instanceof DiagramDialogBox) {
            converter = new DiagramDialogboxConverter((DiagramDialogBox) sub);
        }
    }
}