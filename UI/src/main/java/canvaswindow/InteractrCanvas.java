package canvaswindow;

import canvas.CanvasMakeUp;
import canvaswindow.CanvasWindow;
import figures.FigureConverter;
import figures.basicShapes.DashedLine;
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

    private CanvasMakeUp canvasMakeUp;
    //private FigureConverter figureConverter;
    private KeyEventFactory keyFactory;
    private MouseEventFactory mouseFactory;

    public InteractrCanvas(String title) {
        super(title);
        keyFactory = new KeyEventFactory();
        mouseFactory = new MouseEventFactory();
        canvasMakeUp = new CanvasMakeUp();
        //figureConverter = new FigureConverter();
    }

    public void paint(Graphics g){
        g.drawLine(0,0, (int)((Math.random()*250)+1),45);
        new DashedLine(0,45,600,45).draw(g);
        new DashedLine(0,105,600,105).draw(g);
        FigureConverter.getInstance().draw(g,canvasMakeUp.getActiveDiagram());
    }

    @Override
    public void handleMouseEvent(int id, int x, int y, int clickCount){
        Point2D point = new Point2D.Double(x,y);
        MouseEvent e = mouseFactory.createMouseEvent(id, clickCount, point);
        canvasMakeUp.handleMouseEvent(e);
        this.repaint();
    }

    @Override
    public void handleKeyEvent(int id, int keyCode, char keyChar){
        KeyEvent e = keyFactory.createKeyEvent(id, keyCode, keyChar);
        canvasMakeUp.handleKeyEvent(e);
        this.repaint();
    }
}
