package figures.helperClasses;

import canvaswindow.CanvasWindow;
import figures.basicShapes.DashedLine;
import figures.basicShapes.DashedRectangle;
import figures.basicShapes.Line;
import figures.basicShapes.Rectangle;
import figures.diagramFigures.Arrow;
import figures.diagramFigures.Box;
import figures.diagramFigures.StickMan;

import java.awt.*;
import java.awt.geom.Point2D;

//TODO : this is a test-class, delete at will
public class MainTemp extends CanvasWindow {
    /**
     * Initializes a CanvasWindow object.
     *
     * @param title Window title
     */
    protected MainTemp(String title) {
        super(title);
    }


    @Override
    protected void paint(Graphics graphics){

        StickMan s = new StickMan(10,10);
        Arrow a = new Arrow(50, 25, 100, 25);
        Arrow az = new Arrow(100, 100, 50, 100);
        Rectangle r = new Rectangle(300,400,150, 50);
        DashedLine d = new DashedLine(0,300,600,300);
        DashedLine dd = new DashedLine(0,450,600,450);

        DashedRectangle rr = new DashedRectangle(300,460,150, 50);

        dd.draw(graphics);
        rr.draw(graphics);
        d.draw(graphics);
        r.draw(graphics);
        s.draw(graphics);
        a.draw(graphics);
        az.draw(graphics);
        graphics.drawString("Mis je", 20,20);

        new Box(new Point2D.Double(10,10), new Point2D.Double(600,24)).draw(graphics);
        new Line(0,0,10,10).draw(graphics);
        new Line(0,500,600,500).draw(graphics);
        new Arrow(100, 100, 150, 150).draw(graphics);
        new Arrow(100, 100, 150, 50).draw(graphics);
        new Arrow(100, 100, 50, 50).draw(graphics);
        new Arrow(100, 100, 50, 150).draw(graphics);
        new Arrow(100, 100, 100, 150).draw(graphics);
        new Arrow(100, 100, 100, 50).draw(graphics);

    }

    @Override
    protected void handleMouseEvent(int id, int x, int y, int clickCount){

    }

    @Override
    protected void handleKeyEvent(int id, int keyCode, char keyChar){

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new MainTemp("My Canvas Window").show();
        });
    }
}
