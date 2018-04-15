package canvaswindow;

import canvas.CanvasController;
import figures.FigureConverter;
import uievents.KeyEvent;
import uievents.KeyEventFactory;
import uievents.MouseEvent;
import uievents.MouseEventFactory;

import java.awt.*;
import java.awt.geom.Point2D;

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

    public InteractrCanvas(String title) {
        super(title);
        keyFactory = new KeyEventFactory();
        mouseFactory = new MouseEventFactory();
        canvasController = new CanvasController();
        figureConverter = new FigureConverter();
    }

    public void paint(Graphics g){
        figureConverter.draw(g, canvasController.getSubwindows());
    }

    @Override
    public void handleMouseEvent(int id, int x, int y, int clickCount){
        Point2D point = new Point2D.Double(x,y);
        MouseEvent e = mouseFactory.createMouseEvent(id, clickCount, point);
        canvasController.handleMouseEvent(e);
        this.repaint();
    }

    @Override
    public void handleKeyEvent(int id, int keyCode, char keyChar){
        KeyEvent e = keyFactory.createKeyEvent(id, keyCode, keyChar);
        canvasController.handleKeyEvent(e);
        this.repaint();
    }
}
