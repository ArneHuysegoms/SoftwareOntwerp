package main;

import canvaswindow.CanvasWindow;
import figures.basicShapes.Circle;
import figures.basicShapes.CloseButton;
import figures.basicShapes.DashedLine;
import figures.diagramFigures.SubwindowFigure;

import java.awt.*;
import java.awt.geom.Point2D;

public class TempMain {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new Cvw("My Canvas Window").show();
        });
    }
}

class Cvw extends CanvasWindow {

    /**
     * Initializes a CanvasWindow object.
     *
     * @param title Window title
     */
    protected Cvw(String title) {
        super(title);
    }

    public void paint(Graphics g) {
        SubwindowFigure s;
        s = new SubwindowFigure(new Point2D.Double(50, 50), new Point2D.Double(350, 350));
        //g.setColor(Color.BLACK);
        s.draw(g,0,0,600,600);
        s.draw(g,0,0,600,600);
/*
        new DashedLine(30,0,30,600).draw(g);
        new Circle(new Point2D.Double(100,100),100).draw(g,30,0,600,600);
*/
        double startAngle = Math.toDegrees(Math.asin((100f-170f)/100f));
        System.out.println(startAngle);
        double arcAngle = (360-((90+startAngle)*2));
        new DashedLine(0,170,600,170).draw(g,0,0,600,600);
        g.drawArc((int) 100, 100, (int)200, (int)200, (int)Math.round(startAngle), (int)Math.round(arcAngle));
    }

    @Override
    public void handleMouseEvent(int id, int x, int y, int clickCount) {

    }

    @Override
    public void handleKeyEvent(int id, int keyCode, char keyChar) {

    }
}

