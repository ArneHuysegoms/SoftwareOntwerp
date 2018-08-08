package figures.converters;

import figures.converters.diagramSub.CommunicationConverter;
import figures.converters.diagramSub.SequenceConverter;
import figures.converters.dialogbox.DialogBoxConverter;
import view.diagram.CommunicationView;
import view.diagram.SequenceView;
import window.Subwindow;
import window.diagram.DiagramSubwindow;
import window.dialogbox.DialogBox;

import java.awt.*;
import java.util.List;

/**
 * This class is the most general of the converter classes. All the drawing starts here.
 * Objects get seperated here to be drawn by their specific converter
 */
public class FigureConverter {

    private SubwindowConverter converter;

    /**
     * main draw function
     *
     * @param graphics         object used to draw on the program's window
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
        for (Subwindow sub : allSubwindows) {
            setConverter(sub);
            converter.drawSubwindow(graphics);
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
     * @param subwindow subwindow where the converter will be used for
     */
    private void setConverter(Subwindow subwindow) {
        if (subwindow instanceof DiagramSubwindow) {
            DiagramSubwindow temp = ((DiagramSubwindow) subwindow);
            if (temp.getFacade().getActiveView() instanceof SequenceView) {
                converter = new SequenceConverter(temp);
            } else if (temp.getFacade().getActiveView() instanceof CommunicationView) {
                converter = new CommunicationConverter(temp);
            }
        } else if(subwindow instanceof DialogBox){
            converter = new DialogBoxConverter((DialogBox)subwindow);
        }
    }
}