package repo;

import diagram.label.Label;
import diagram.label.PartyLabel;
import diagram.party.Actor;
import diagram.party.Object;
import diagram.party.Party;
import org.junit.*;
import repo.party.PartyRepo;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class PartyRepoTest {

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

        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void Test_Default_Constructor_works(){
        PartyRepo p = new PartyRepo();
        assertNotNull(p.getPartyPoint2DMap());
        assertEquals(0, p.getPartyPoint2DMap().size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void Test_Constructor_with_null_map_throws_exception(){
        PartyRepo p = new PartyRepo(null);
    }

    @Test
    public void Test_Add_party_works_with_good_arguments(){
        PartyRepo p = new PartyRepo();
    }

}
