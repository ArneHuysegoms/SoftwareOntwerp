package figures.diagramFigures;

import figures.basicShapes.Circle;
import figures.basicShapes.Line;

import java.awt.*;
import java.awt.geom.Point2D;

public class StickMan extends Figure {
    private Point2D topOfHead;

    private Point2D connectionHead;
    private Point2D connectionLegs;
    private Point2D connectionArms;

    private Circle head;
    private Line body;
    private Line arms;
    private Line legL;
    private Line legR;

    public StickMan(Point2D point){
        this((int)point.getX(), (int)point.getY());
    }

    public StickMan(int x, int y){
        double bodyLen = 20;
        int headRadius = 5;
        int legSpread = 8;
        int legLen = 15;
        topOfHead = new Point2D.Double(x, y);

        //stickManCenter = new Point2D.Double(x, y);

        connectionHead = new Point2D.Double(x, y+(2*headRadius));
        connectionLegs = new Point2D.Double(x, y+(2*headRadius)+bodyLen);

        body = new Line(connectionHead, connectionLegs);
        connectionArms = new Point2D.Double(x,y+(2*headRadius)+5);

        assambleFigure(headRadius, legSpread, legLen);
    }

    private void assambleFigure(int headRadius, int legSpread, int legLen){
        head = new Circle((int)connectionHead.getX(), (int)connectionHead.getY()-headRadius, headRadius);
        arms = new Line((int)connectionArms.getX()-6,(int)connectionArms.getY(), (int)connectionArms.getX()+6, (int)connectionArms.getY());
        legL = new Line ((int)connectionLegs.getX(), (int)connectionLegs.getY(), (int)connectionLegs.getX()-(legSpread/2), (int)connectionLegs.getY()+legLen);
        legR = new Line ((int)connectionLegs.getX(), (int)connectionLegs.getY(), (int)connectionLegs.getX()+(legSpread/2), (int)connectionLegs.getY()+legLen);
    }

    public Point2D getTopOfHead() {
        return topOfHead;
    }

    public void setTopOfHead(Point2D topOfHead) {
        this.topOfHead = topOfHead;
    }

    public Point2D getConnectionHead() {
        return connectionHead;
    }

    public void setConnectionHead(Point2D connectionHead) {
        this.connectionHead = connectionHead;
    }

    public Point2D getConnectionLegs() {
        return connectionLegs;
    }

    public void setConnectionLegs(Point2D connectionLegs) {
        this.connectionLegs = connectionLegs;
    }

    public Point2D getConnectionArms() {
        return connectionArms;
    }

    public void setConnectionArms(Point2D connectionArms) {
        this.connectionArms = connectionArms;
    }

    public Circle getHead() {
        return head;
    }

    public void setHead(Circle head) {
        this.head = head;
    }

    public Line getBody() {
        return body;
    }

    public void setBody(Line body) {
        this.body = body;
    }

    public Line getArms() {
        return arms;
    }

    public void setArms(Line arms) {
        this.arms = arms;
    }

    public Line getLegL() {
        return legL;
    }

    public void setLegL(Line legL) {
        this.legL = legL;
    }

    public Line getLegR() {
        return legR;
    }

    public void setLegR(Line legR) {
        this.legR = legR;
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
