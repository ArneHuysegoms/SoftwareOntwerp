package main;

import canvaswindow.CanvasWindow;
import figures.FigureConverter;
import figures.basicShapes.CloseButton;
import figures.basicShapes.Line;
import figures.diagramFigures.SubwindowFigure;
import uievents.KeyEvent;
import uievents.MouseEvent;

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
        new CloseButton(100, 100).draw(g);
        new SubwindowFigure(new Point2D.Double(50, 50), 300, 300).draw(g);
    }

    @Override
    public void handleMouseEvent(int id, int x, int y, int clickCount) {

    }

    @Override
    public void handleKeyEvent(int id, int keyCode, char keyChar) {

    }
}

