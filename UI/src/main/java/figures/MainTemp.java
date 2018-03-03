package figures;

import canvas.CanvasWindow;
import figures.basicShapes.DashedLine;
import figures.basicShapes.DashedRectangle;
import figures.basicShapes.Rectangle;
import figures.diagramFigures.Arrow;
import figures.diagramFigures.StickMan;

import java.awt.*;

//TODO : Test class, delete at will
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
        StickMan s = new StickMan(300,300);
        Arrow a = new Arrow(50, 50, 100, 50);
        Arrow az = new Arrow(100, 100, 50, 100);
        Rectangle r = new Rectangle(300,400,150, 50);
        DashedLine d = new DashedLine(0,300,600,300);
        DashedLine dd = new DashedLine(0,50,100,50);

        DashedRectangle rr = new DashedRectangle(300,460,150, 50);

        dd.draw(graphics);
        rr.draw(graphics);
        d.draw(graphics);
        r.draw(graphics);
        s.draw(graphics);
        a.draw(graphics);
        az.draw(graphics);
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
