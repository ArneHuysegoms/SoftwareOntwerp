package repo.party;

import diagram.DiagramElement;
import diagram.label.Label;
import diagram.label.PartyLabel;
import diagram.party.Actor;
import diagram.party.Object;
import diagram.party.Party;
import exceptions.DomainException;
import org.junit.*;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class PartyRepoTest {

    private static final Point2D INVALID_EMPTY_POINT = new Point2D.Double(0,0);

    Party actor1;
    Point2D validPoint1;
    Label label1;

    Party object1;
    Point2D validPoint2;
    Label label2;

    Party actor2;
    Point2D validPoint3;
    Label label3;

    @Before
    public void setUp(){
        try {
            validPoint1 = new Point2D.Double(50,50);
            label1 = new PartyLabel("a:A");
            actor1 = new Actor(label1);

            validPoint2 = new Point2D.Double(100,100);
            label2 = new PartyLabel("b:B");
            object1 = new Object(label2);

            validPoint3 = new Point2D.Double(150,150);
            label3 = new PartyLabel("c:C");
            actor2 = new Actor(label3);
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void Test_Default_Constructor_works(){
        PartyRepo p = new PartyRepo();
        assertNotNull(p.getMap());
        assertEquals(0, p.getMap().size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void Test_Constructor_with_null_map_throws_exception(){
        PartyRepo p = new PartyRepo(null);
    }

    @Test
    public void Test_Add_party_works_with_good_arguments(){
        PartyRepo p = new PartyRepo();
        assertEquals(0, p.getMap().size());
        p.addPartyWithLocation(actor1, validPoint1);
        assertEquals(1, p.getMap().size());
        assertTrue(p.getAllParties().contains(actor1));
    }

    @Test
    public void Test_Find_party_at_location(){
        PartyRepo p = new PartyRepo();
        p.addPartyWithLocation(actor1, validPoint1);
        p.addPartyWithLocation(object1, validPoint2);
        try {
            assertEquals(p.getPartyAtPosition(validPoint1), actor1);
            assertEquals(p.getPartyAtPosition(validPoint2), object1);
        }
        catch (Exception e){
            fail();
        }
    }

    @Test (expected = DomainException.class)
    public void Test_no_party_at_invalid_location() throws DomainException{
        PartyRepo p = new PartyRepo();
        p.addPartyWithLocation(actor1, validPoint1);
        p.getPartyAtPosition(INVALID_EMPTY_POINT);
    }

    @Test (expected = DomainException.class)
    public void Test_updatePartyPosition_changes_party_position() throws DomainException{
        PartyRepo p = new PartyRepo();
        p.addPartyWithLocation(actor1, validPoint1);
        p.updatePartyPosition(validPoint2, actor1);
        assertEquals(p.getPartyAtPosition(validPoint2), actor1);
        p.getPartyAtPosition(validPoint1);
    }

    @Test
    public void Test_getLocationOffParty(){
        PartyRepo p = new PartyRepo();
        p.addPartyWithLocation(actor1, validPoint1);
        assertEquals(validPoint1, p.getLocationOfParty(actor1));
    }

    @Test
    public void Test_removeParty_works(){
        PartyRepo p = new PartyRepo();
        p.addPartyWithLocation(actor1, validPoint1);
        assertEquals(1, p.getAllParties().size());
        p.removeParty(actor1);
        assertEquals(0, p.getAllParties().size());
    }

    @Test
    public void Test_removePartyByPosition_works() throws DomainException{
        PartyRepo p = new PartyRepo();
        p.addPartyWithLocation(actor1, validPoint1);
        assertEquals(1, p.getAllParties().size());
        p.removePartyByPosition(validPoint1);
        assertEquals(0, p.getAllParties().size());
    }

    @Test
    public void Test_getDistancesFromPointForParties_works() {
        PartyRepo p = new PartyRepo();
        p.addPartyWithLocation(actor1, validPoint1);
        Map<Party, Double> distances = p.getDistancesFromPointForParties(new Point2D.Double(50,10));
        Map<Party, Double> expected = new HashMap<>();
        expected.put(actor1, 40.0);
        assertEquals(distances,expected);
    }

    @Test
    public void Test_getCorrectLabelPosition_for_party_works(){
        PartyRepo p = new PartyRepo();
        p.addPartyWithLocation(actor1, validPoint1);
        assertEquals(new Point2D.Double(40,100), p.getCorrectLabelPosition(actor1));
    }

    @Test
    public void Test_getCorrectLabelPosition_for_object_works(){
        PartyRepo p = new PartyRepo();
        p.addPartyWithLocation(object1, validPoint2);
        assertEquals(new Point2D.Double(110,125), p.getCorrectLabelPosition(object1));
    }

    @Test
    public void Test_getClickedParties(){
        PartyRepo p = new PartyRepo();
        p.addPartyWithLocation(actor1, validPoint1);
        p.addPartyWithLocation(object1, validPoint2);
        Set<DiagramElement> clickeds = p.getClickedParties(new Point2D.Double(50,60));
        assertEquals(clickeds.size(),1);
        assertTrue(clickeds.contains(actor1));
    }

    @Test
    public void Test_getXLocationOfLifeline(){
        PartyRepo p = new PartyRepo();
        p.addPartyWithLocation(actor1, validPoint1);
        p.addPartyWithLocation(object1, validPoint2);
        assertTrue(validPoint1.getX() == p.getXLocationOfLifeline(actor1));
        assertTrue(validPoint2.getX() + PartyRepo.OBJECTWIDTH/2 == p.getXLocationOfLifeline(object1));
    }
}
