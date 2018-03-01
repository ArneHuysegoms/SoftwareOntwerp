package figures;

import canvas.CanvasWindow;
import figures.basicShapes.Circle;
import figures.basicShapes.Line;
import figures.basicShapes.Rectangle;
import figures.diagramFigures.Arrow;
import figures.diagramFigures.StickMan;

import java.awt.*;

public class CWtestje extends CanvasWindow {
    /**
     * Initializes a CanvasWindow object.
     *
     * @param title Window title
     */
    protected CWtestje(String title) {
        super(title);
    }

    @Override
    protected void paint(Graphics graphics){
        //Circle c = new Circle(300,0,300);
        Rectangle r = new Rectangle(500,500, 50, 70);
        Rectangle xr = new Rectangle(250,250, 5, 5);
        StickMan s = new StickMan(300,300);
        Line l = new Line(0,300, 600, 0);
        Arrow a = new Arrow(100,100, 300);

        a.draw(graphics);
        l.draw(graphics);
        s.draw(graphics);
        r.draw(graphics);
        xr.draw(graphics);
        //c.draw(graphics);

        graphics.drawOval(0, 300, 50, 50);
    }

    @Override
    protected void handleMouseEvent(int id, int x, int y, int clickCount){

    }

    @Override
    protected void handleKeyEvent(int id, int keyCode, char keyChar){

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new CWtestje("My Canvas Window").show();
        });
    }
}
