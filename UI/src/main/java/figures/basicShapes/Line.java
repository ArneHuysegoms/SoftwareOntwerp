package figures.basicShapes;

import figures.PointXY;

import java.awt.*;

public class Line extends Shape{

    private PointXY start;
    private PointXY end;

    public Line (int x1, int y1, int x2, int y2){
        start = new PointXY(x1, y1);
        end = new PointXY(x2, y2);
    }

    public Line (PointXY p1, PointXY p2){
        start = p1;
        end = p2;
    }

    public PointXY getStart() {
        return start;
    }

    public PointXY getEnd() {
        return end;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    /*
    public void drawDashed(Graphics graphics) {
        int finX=start.getX();
        int finY = start.getY();
        int dashSize = 4;
        while(finX != end.getX() && finY != end.getY()){
            graphics.drawLine(finX, finY, finX+4, finY+4);

        }
    }
    */
}
