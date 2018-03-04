package canvascomponents.diagram;

import exceptions.DomainException;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class ActorTest {

    @Test
    public void isClicked() throws DomainException {
        Point2D point2D = new Point2D.Double(50, 400);
        Point2D lblPoint = new Point2D.Double(25, 350);
        PartyLabel actorLabel = new PartyLabel("test:Actor", lblPoint);
        Actor a = new Actor(10, point2D, actorLabel);
        Point2D clickPoint = new Point2D.Double(50, 390);
        assertTrue(a.isClicked(clickPoint));
    }

    @Test
    public void getDistance() throws DomainException {
        Point2D point2D = new Point2D.Double(50, 400);
        Point2D lblPoint = new Point2D.Double(25, 350);
        PartyLabel actorLabel = new PartyLabel("test:Actor", lblPoint);
        Actor a = new Actor(10, point2D, actorLabel);
        Point2D point = new Point2D.Double(55, 400);
        assertEquals(5,a.getDistance(point), 0.001);
    }

    @Test
    public void actorConstructor() throws DomainException{
        Point2D point2D = new Point2D.Double(50, 400);
        Point2D lblPoint = new Point2D.Double(25, 350);
        PartyLabel actorLabel = new PartyLabel("test:Actor", lblPoint);
        Actor a = new Actor("test", "Actor",10, point2D, actorLabel);
        assertEquals("test", a.getInstanceName());
        assertEquals("Actor", a.getClassName());
        assertEquals(point2D, a.getCoordinate());
        assertEquals(actorLabel, a.getLabel());
    }
}