package figures.drawable.diagramFigures;

import figures.drawable.IDrawable;
import figures.drawable.basicShapes.DashedRectangle;
import figures.drawable.basicShapes.Rectangle;
import window.elements.ListBox;

import java.awt.*;
import java.awt.geom.Point2D;

public class ListBoxFigure implements IDrawable {

    private ListBox listBox;

    public ListBoxFigure(ListBox listBox){
        this.listBox = listBox;
    }

    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        Point2D listBoxPos = listBox.getCoordinate();
        drawBox(graphics, listBoxPos, minX, minY,maxX,maxY);
        drawArguments(graphics, listBoxPos);
        drawArgumentSelection(graphics, listBoxPos, minX, minY,maxX,maxY);
    }

    private void drawArgumentSelection(Graphics graphics, Point2D listBoxPos, int minX, int minY, int maxX, int maxY) {
        //TODO tweak coordinates
        Color temp = graphics.getColor();
        graphics.setColor(Color.LIGHT_GRAY);
        new DashedRectangle((int)listBoxPos.getX()+2,(int)listBoxPos.getY()+(listBox.getSelectedIndex()*ListBox.ARGUMENT_HEIGHT),ListBox.WIDTH-2,ListBox.ARGUMENT_HEIGHT);
        graphics.setColor(temp);
    }

    private void drawArguments(Graphics graphics, Point2D startingPos) {
        //TODO tweak coordinates
        int x= (int) startingPos.getX()+3, y = (int) startingPos.getY();
        int argumentHeight = ListBox.ARGUMENT_HEIGHT;

        for(String str:listBox.getArguments()) {
            graphics.drawString(str,x,y);
            y+=argumentHeight;
        }
    }

    private void drawBox(Graphics graphics, Point2D listBoxPos, int minX, int minY, int maxX, int maxY) {
        Color temp = graphics.getColor();
        graphics.setColor(Color.GRAY);
        new Rectangle(listBoxPos,ListBox.HEIGHT, ListBox.WIDTH)
                .draw(graphics, minX, minY,maxX,maxY);
        graphics.setColor(temp);
    }
}
