package figures.diagramFigures;

import figures.basicShapes.Circle;
import figures.basicShapes.Line;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * a class used to draw a stickman
 */
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
        setTopOfHead(new Point2D.Double(x, y));
        //stickManCenter = new Point2D.Double(x, y);
        setConnectionHead(new Point2D.Double(x, y+(2*headRadius)));
        setConnectionLegs(new Point2D.Double(x, y+(2*headRadius)+bodyLen));
        setBody(new Line(this.getConnectionHead(), this.getConnectionLegs()));
        setConnectionArms(new Point2D.Double(x,y+(2*headRadius)+5));
        assambleFigure(headRadius, legSpread, legLen);
    }

    private void assambleFigure(int headRadius, int legSpread, int legLen){
        setHead(new Circle((int)this.getConnectionHead().getX(), (int)this.getConnectionHead().getY()-headRadius, headRadius));
        setArms(new Line((int)this.getConnectionArms().getX()-6,(int)this.getConnectionArms().getY(), (int)this.getConnectionArms().getX()+6, (int)this.getConnectionArms().getY()));
        setLegL(new Line ((int)this.getConnectionLegs().getX(), (int)this.getConnectionLegs().getY(), (int)this.getConnectionLegs().getX()-(legSpread/2), (int)this.getConnectionLegs().getY()+legLen));
        setLegR(new Line ((int)this.getConnectionLegs().getX(), (int)this.getConnectionLegs().getY(), (int)this.getConnectionLegs().getX()+(legSpread/2), (int)this.getConnectionLegs().getY()+legLen));
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
        this.getHead().draw(graphics);
        this.getBody().draw(graphics);
        this.getArms().draw(graphics);
        this.getLegL().draw(graphics);
        this.getLegR().draw(graphics);
    }
}
