package figures.diagramFigures;

import figures.basicShapes.Circle;
import figures.basicShapes.Line;

import java.awt.*;
import java.awt.geom.Point2D;

public class StickMan extends Figure {
    private Point2D stickManCenter;

    private Point2D connectionHead;
    private Point2D connectionLegs;
    private Point2D connectionArms;

    private Circle head;
    private Line body;
    private Line arms;
    private Line legL;
    private Line legR;

    public StickMan(int x, int y){
        double bodyLen = 20;
        stickManCenter = new Point2D.Double(x, y);

        connectionHead = new Point2D.Double(x, y-(bodyLen/2));
        connectionLegs = new Point2D.Double(x, y+(bodyLen/2));

        body = new Line(connectionHead, connectionLegs);
        connectionArms = new Point2D.Double(x,y-3);

        assambleFigure();
    }

    private void assambleFigure(){
        int headRadius = 5;
        int legSpread = 8;
        int legLen = 15;
        head = new Circle((int)connectionHead.getX(), (int)connectionHead.getY()-headRadius, headRadius);
        arms = new Line((int)connectionArms.getX()-6,(int)connectionArms.getY(), (int)connectionArms.getX()+6, (int)connectionArms.getY());
        legL = new Line ((int)connectionLegs.getX(), (int)connectionLegs.getY(), (int)connectionLegs.getX()-(legSpread/2), (int)connectionLegs.getY()+legLen);
        legR = new Line ((int)connectionLegs.getX(), (int)connectionLegs.getY(), (int)connectionLegs.getX()+(legSpread/2), (int)connectionLegs.getY()+legLen);
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
