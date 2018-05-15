package main;

import canvaswindow.CanvasWindow;
import canvaswindow.InteractrCanvas;
import figures.basicShapes.Circle;
import figures.basicShapes.FilledCircle;

import java.awt.*;

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
