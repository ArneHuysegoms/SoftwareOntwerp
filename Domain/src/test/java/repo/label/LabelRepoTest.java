package repo.label;

import diagram.label.Label;
import diagram.label.PartyLabel;
import exceptions.DomainException;
import org.junit.*;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class LabelRepoTest {

    private static final Point2D INVALID_EMPTY_POINT = new Point2D.Double(0,0);

    Point2D validPoint1;
    Label label1;

    Point2D validPoint2;
    Label label2;

    Point2D validPoint3;
    Label label3;

    @Before
    public void setUp(){
        try {
            validPoint1 = new Point2D.Double(50,50);
            label1 = new PartyLabel("a:A");

            validPoint2 = new Point2D.Double(100,100);
            label2 = new PartyLabel("b:B");

            validPoint3 = new Point2D.Double(150,150);
            label3 = new PartyLabel("c:C");
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void Test_Default_constructor_works() {
        LabelRepo r = new LabelRepo();
        assertNotNull(r.getMap());
    }

    @Test (expected = IllegalArgumentException.class)
    public void Test_Constructor_with_null_map_throws_exception(){
        LabelRepo r = new LabelRepo(null);
    }

    @Test
    public void Test_addLabel_works(){
        LabelRepo r = new LabelRepo();
        assertTrue(r.getMap().size() == 0);
        r.addLabelWithLocation(label1, validPoint1);
        assertTrue(r.getMap().size() == 1);
    }

    @Test
    public void Test_getLabelAtPosition_works() throws DomainException {
        LabelRepo r = new LabelRepo();
        assertTrue(r.getMap().size() == 0);
        r.addLabelWithLocation(label1, validPoint1);
        assertTrue(r.getMap().size() == 1);
        assertEquals(label1, r.getLabelAtPosition(validPoint1));
    }

    @Test (expected = DomainException.class)
    public void Test_getLabelAtPosition_throws_exception_if_no_label_at_position() throws DomainException {
        LabelRepo r = new LabelRepo();
        assertTrue(r.getMap().size() == 0);
        r.addLabelWithLocation(label1, validPoint1);
        assertTrue(r.getMap().size() == 1);
        r.getLabelAtPosition(INVALID_EMPTY_POINT);
    }
}
