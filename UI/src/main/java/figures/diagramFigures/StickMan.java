package figures.diagramFigures;

import figures.PointXY;
import figures.basicShapes.Circle;
import figures.basicShapes.Line;

import java.awt.*;

public class StickMan extends Figure {
    private PointXY stickManCenter;

    private PointXY connectionHead;
    private PointXY connectionLegs;
    private PointXY connectionArms;

    private Circle head;
    private Line body;
    private Line arms;
    private Line legL;
    private Line legR;

    public StickMan(int x, int y){
        stickManCenter = new PointXY(x, y);

        connectionHead = new PointXY(x, y-10);
        connectionLegs = new PointXY(x, y+10);

        body = new Line(connectionHead, connectionLegs);
        connectionArms = new PointXY(x,y-3);

        assambleFigure();
    }

    private void assambleFigure(){
        head = new Circle(connectionHead.getX(), connectionHead.getY()-5, 5);
        arms = new Line(connectionArms.getX()-6,connectionArms.getY(), connectionArms.getX()+6, connectionArms.getY());
        legL = new Line (connectionLegs.getX(), connectionLegs.getY(), connectionLegs.getX()-4, connectionLegs.getY()+15);
        legR = new Line (connectionLegs.getX(), connectionLegs.getY(), connectionLegs.getX()+4, connectionLegs.getY()+15);


    }

    @Override
    public void draw(Graphics graphics) {
        head.draw(graphics);
        body.draw(graphics);
        arms.draw(graphics);
        legL.draw(graphics);
        legR.draw(graphics);
    }
}
