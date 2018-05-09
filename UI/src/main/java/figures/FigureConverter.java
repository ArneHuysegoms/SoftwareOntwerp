package figures;

import controller.CanvasController;

import figures.Drawer.*;
import view.diagram.CommunicationView;
import view.diagram.SequenceView;
import subwindow.Subwindow;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * a class that converts the data from the diagram model to drawn figures of the program's window
 */
public class FigureConverter {

    private Converter converter;
    private Drawer subwindowDrawer;

    /**
     * default constructor
     */
    public FigureConverter() {
        subwindowDrawer = new SubwindowDrawer();
    }

    /**
     * main draw function
     *
     * @param graphics        object used to draw on the program's window
     * @param subwindowLevels the subwindows to be drawn on the controller
     */
    public void draw(Graphics graphics, List<CanvasController.SubWindowLevel> subwindowLevels) {
        drawBackGroundColor(graphics);
        Subwindow sub;
        for (CanvasController.SubWindowLevel subLvl : subwindowLevels) {
            sub = subLvl.getSubwindow();
            setConverter(sub);
            drawSubwindow(graphics, sub.getPosition(), sub.getWidth(), sub.getHeight());
            converter.draw(graphics, sub.getFacade().getActiveRepo(), sub.getFacade().getDiagram(), sub.getSelected());
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
     * @param position coordinate of the top-left point of the subwindow
     * @param width    the subwindow's width
     * @param height   the subwindow's height
     */
    private void drawSubwindow(Graphics graphics, Point2D position, int width, int height) {
        subwindowDrawer.draw(graphics, position, new Point2D.Double(position.getX() + width, position.getY() + height), null, 0, 0, 2000, 2000);
    }

    /**
     * creates and sets the correct converter
     *
     * @param sub
     */
    public void setConverter(Subwindow sub) {
        if (sub.getFacade().getActiveRepo() instanceof SequenceView) {
            converter = new SequenceFigureConverter(sub);
        } else if (sub.getFacade().getActiveRepo() instanceof CommunicationView) {
            converter = new CommunicationFigureConverter(sub);
        }
    }
}