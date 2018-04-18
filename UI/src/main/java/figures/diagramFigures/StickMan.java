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

    /**
     * @param point top of the stickman's head
     */
    public StickMan(Point2D point) {
        this((int) point.getX(), (int) point.getY());
    }

    /**
     * @param x the x-coordinate of the top of the stickman's head
     * @param y the y-coordinate of the top of the stickman's head
     */
    public StickMan(int x, int y) {
        double bodyLen = 20;
        int headRadius = 5;
        int legSpread = 8;
        int legLen = 15;
        setTopOfHead(new Point2D.Double(x, y));
        //stickManCenter = new Point2D.Double(x, y);
        setConnectionHead(new Point2D.Double(x, y + (2 * headRadius)));
        setConnectionLegs(new Point2D.Double(x, y + (2 * headRadius) + bodyLen));
        setBody(new Line(this.getConnectionHead(), this.getConnectionLegs()));
        setConnectionArms(new Point2D.Double(x, y + (2 * headRadius) + 5));
        assambleFigure(headRadius, legSpread, legLen);
    }

    /**
     * @param headRadius the radius of the stikmans head
     * @param legSpread  the width of the spread between the stickman's legs
     * @param legLen     the length of the stickman's legs
     */
    private void assambleFigure(int headRadius, int legSpread, int legLen) {
        setHead(new Circle((int) this.getConnectionHead().getX(), (int) this.getConnectionHead().getY() - headRadius, headRadius));
        setArms(new Line((int) this.getConnectionArms().getX() - 6, (int) this.getConnectionArms().getY(), (int) this.getConnectionArms().getX() + 6, (int) this.getConnectionArms().getY()));
        setLegL(new Line((int) this.getConnectionLegs().getX(), (int) this.getConnectionLegs().getY(), (int) this.getConnectionLegs().getX() - (legSpread / 2), (int) this.getConnectionLegs().getY() + legLen));
        setLegR(new Line((int) this.getConnectionLegs().getX(), (int) this.getConnectionLegs().getY(), (int) this.getConnectionLegs().getX() + (legSpread / 2), (int) this.getConnectionLegs().getY() + legLen));
    }

    /**
     * returns the point of the top of the stickman's head
     *
     * @return the point of the top of the stickman's head
     */
    public Point2D getTopOfHead() {
        return topOfHead;
    }

    /**
     * sets the point of the top of the stickman's head
     *
     * @param topOfHead the point of the top of the stickman's head
     */
    public void setTopOfHead(Point2D topOfHead) {
        this.topOfHead = topOfHead;
    }

    /**
     * returns the point where the stickman's body and head meet
     *
     * @return the point where the stickman's body and head meet
     */
    public Point2D getConnectionHead() {
        return connectionHead;
    }

    /**
     * sets the point where the stickman's body and head meet
     *
     * @param connectionHead the point where the stickman's body and head meet
     */
    public void setConnectionHead(Point2D connectionHead) {
        this.connectionHead = connectionHead;
    }

    /**
     * returns the point where the stickman's body and legs meet
     *
     * @return the point where the stickman's body and legs meet
     */
    public Point2D getConnectionLegs() {
        return connectionLegs;
    }

    /**
     * sets the point where the stickman's body and legs meet
     *
     * @param connectionLegs the point where the stickman's body and legs meet
     */
    public void setConnectionLegs(Point2D connectionLegs) {
        this.connectionLegs = connectionLegs;
    }

    /**
     * returns the point where the stickman's body and arms meet
     *
     * @return the point where the stickman's body and arms meet
     */
    public Point2D getConnectionArms() {
        return connectionArms;
    }

    /**
     * sets the point where the stickman's body and arms meet
     *
     * @param connectionArms the point where the stickman's body and arms meet
     */
    public void setConnectionArms(Point2D connectionArms) {
        this.connectionArms = connectionArms;
    }

    /**
     * returns a Circle that is the stickman's head
     *
     * @return a Circle that is the stickman's head
     */
    public Circle getHead() {
        return head;
    }

    /**
     * sets a Circle that is the stickman's head
     *
     * @param head a Circle that is the stickman's head
     */
    public void setHead(Circle head) {
        this.head = head;
    }

    /**
     * returns a Line that is the stickman's body
     *
     * @return a Line that is the stickman's body
     */
    public Line getBody() {
        return body;
    }

    /**
     * sets a Line that is the stickman's body
     *
     * @param body a Line that is the stickman's body
     */
    public void setBody(Line body) {
        this.body = body;
    }

    /**
     * returns one line that are the two arms of a stickman
     *
     * @return one line that are the two arms of a stickman
     */
    public Line getArms() {
        return arms;
    }

    /**
     * sets one line that are the two arms of a stickman
     *
     * @param arms one line that are the two arms of a stickman
     */
    public void setArms(Line arms) {
        this.arms = arms;
    }

    /**
     * returns the stickman's left leg
     *
     * @return the stickman's left leg
     */
    public Line getLegL() {
        return legL;
    }

    /**
     * sets the stickman's left leg
     *
     * @param legL the stickman's left leg
     */
    public void setLegL(Line legL) {
        this.legL = legL;
    }

    /**
     * returns the stickman's right leg
     *
     * @return the stickman's right leg
     */
    public Line getLegR() {
        return legR;
    }

    /**
     * sets the stickman's right leg
     *
     * @param legR the stickman's right leg
     */
    public void setLegR(Line legR) {
        this.legR = legR;
    }

    /**
     * a draw fucntion that draws on the Graphics parameter object
     *
     * @param graphics object used to draw on the program's window
     * @param minX     minimum possible x coördinate value
     * @param minY     minimum possible y coördinate value
     * @param maxX     maximum possible x coördinate value
     * @param maxY     maximum possible y coördinate value
     */
    @Override
    public void draw(Graphics graphics, int minX, int minY, int maxX, int maxY) {
        this.getHead().draw(graphics, minX, minY, maxX, maxY);
        this.getBody().draw(graphics, minX, minY, maxX, maxY);
        this.getArms().draw(graphics, minX, minY, maxX, maxY);
        this.getLegL().draw(graphics, minX, minY, maxX, maxY);
        this.getLegR().draw(graphics, minX, minY, maxX, maxY);
    }
}
