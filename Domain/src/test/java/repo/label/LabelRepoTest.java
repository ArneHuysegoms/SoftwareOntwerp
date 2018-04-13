package repo.label;

import diagram.DiagramElement;
import diagram.label.Label;
import diagram.label.PartyLabel;
import exceptions.DomainException;
import org.junit.*;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    @Test (expected = DomainException.class)
    public void Test_updatePartyPosition_changes_party_position() throws DomainException{
        LabelRepo r = new LabelRepo();
        r.addLabelWithLocation(label1, validPoint1);
        r.updateLabelPosition(validPoint2, label1);
        assertEquals(r.getLabelAtPosition(validPoint2), label1);
        r.getLabelAtPosition(validPoint1);
    }

    @Test
    public void Test_removeLabel_works(){
        LabelRepo r = new LabelRepo();
        r.addLabelWithLocation(label1, validPoint1);
        assertTrue(r.getMap().size() == 1);
        r.removeLabel(label1);
        assertTrue(r.getMap().size() == 0);
        assertFalse(r.getMap().containsKey(label1));
    }

    @Test
    public void Test_removeLabelByPosition_works() throws DomainException{
        LabelRepo r = new LabelRepo();
        r.addLabelWithLocation(label1, validPoint1);
        assertTrue(r.getMap().size() == 1);
        r.removeLabelByPosition(validPoint1);
        assertTrue(r.getMap().size() == 0);
        assertFalse(r.getMap().containsKey(label1));
    }

    @Test
    public void Test_label_isClickable(){
        LabelRepo r = new LabelRepo();
        r.addLabelWithLocation(label1, validPoint1);
        Set<DiagramElement> clickeds = r.getClickedLabels(new Point2D.Double(60,60));
        assertTrue(clickeds.contains(label1));
    }

    @Test
    public void Test_getDistances_works(){
        LabelRepo r = new LabelRepo();
        r.addLabelWithLocation(label1, validPoint1);
        Map<Label, Double> distances = r.getDistancesFromPointForLabels(new Point2D.Double(10, 50));
        Map<Label, Double> expected = new HashMap<>();
        expected.put(label1, 40.0);
        assertEquals(distances, expected);
    }
}
