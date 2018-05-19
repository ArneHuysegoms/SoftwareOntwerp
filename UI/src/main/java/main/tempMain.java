package main;

import canvaswindow.CanvasWindow;
import canvaswindow.InteractrCanvas;
import figures.drawable.basicShapes.Circle;
import figures.drawable.basicShapes.FilledCircle;
import figures.drawable.basicShapes.RemoveButtonFigure;

import java.awt.*;
import java.awt.geom.Point2D;

public class tempMain extends CanvasWindow {


    /**
     * Initializes a CanvasWindow object.
     *
     * @param title Window title
     */
    protected tempMain(String title) {
        super(title);
    }

    @Override
    public void paint(Graphics g) {
        new FilledCircle(100,100,50).draw(g,-100,-100,1000,1000);
        new Circle(250,100,50).draw(g,-100,-100,1000,1000);
        new RemoveButtonFigure(new Point2D.Double(80,80),20,20).draw(g,0,0,2000,2000);
    }

    @Override
    public void handleMouseEvent(int id, int x, int y, int clickCount) {

    }


    @Override
    public void handleKeyEvent(int id, int keyCode, char keyChar) {

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new tempMain("Title").show();
        });
    }
}
