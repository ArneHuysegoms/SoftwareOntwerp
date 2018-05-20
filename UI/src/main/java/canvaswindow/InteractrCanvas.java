package canvaswindow;

import controller.CanvasController;
import exception.UIException;
import exceptions.DomainException;
import figures.converters.FigureConverter;
import uievents.KeyEvent;
import uievents.KeyEventFactory;
import uievents.MouseEvent;
import uievents.MouseEventFactory;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Collections;

public class InteractrCanvas extends CanvasWindow {
    /**
     * Initializes a CanvasWindow object.
     *
     * @param title Window title
     */

    private CanvasController canvasController;
    private FigureConverter figureConverter;
    private KeyEventFactory keyFactory;
    private MouseEventFactory mouseFactory;

    /**
     *  Creates new factories, controller and figureconverter
     * @param title the title for this canvas
     */
    public InteractrCanvas(String title) {
        super(title);
        keyFactory = new KeyEventFactory();
        mouseFactory = new MouseEventFactory();
        canvasController = new CanvasController();
        figureConverter = new FigureConverter();
    }

    /**
     *
     * @param g This object offers the methods that allow you to paint on the controller.
     */
    /*public void paint(Graphics g){
        Collections.sort(canvasController.getSubwindows());
        Collections.reverse(canvasController.getSubwindows());
        figureConverter.draw(g, canvasController.getSubwindows());
    }*/
    public void paint(Graphics g){
        //Collections.sort(canvasController);
        //Collections.reverse(canvasController.getSubwindows());
        //figureConverter.draw(g, canvasController);
    }

    /**
     * Creates a new MouseEvent using the appropriate factory
     * Passes the MouseEvent to the controller afterwards
     * @param id, the type of the MouseEvent
     * @param x, the x-coordinate of the location of the MouseEvent
     * @param y, the y-coordinate of the location of the MouseEvent
     * @param clickCount, the number of clicks in this MouseEvent
     */
    @Override
    public void handleMouseEvent(int id, int x, int y, int clickCount){
        Point2D point = new Point2D.Double(x,y);
        MouseEvent e = mouseFactory.createMouseEvent(id, clickCount, point);
        canvasController.handleMouseEvent(e);
        this.repaint();
    }

    /**
     * Creates a new KeyEvent using the appropriate factory
     * Passes the KeyEvent to the controller afterwards
     * @param id, the type of KeyEvent
     * @param keyCode, the code of the KeyEvent
     * @param keyChar, the character of the KeyEvent
     */
    @Override
    public void handleKeyEvent(int id, int keyCode, char keyChar){
        KeyEvent e = keyFactory.createKeyEvent(id, keyCode, keyChar);
        try {
            canvasController.handleKeyEvent(e);
        }catch (DomainException ex){
            System.out.println(ex.getMessage());
        }catch (UIException uiex){
            System.out.println(uiex.getMessage());
        }
        this.repaint();
    }
}
