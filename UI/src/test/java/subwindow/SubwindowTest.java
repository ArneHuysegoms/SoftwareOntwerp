package subwindow;

import diagram.label.PartyLabel;
import diagram.party.Actor;
import exceptions.DomainException;
import facade.DomainFacade;
import mediator.InteractionMediator;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SubwindowTest {
    private Subwindow subwindow;
    private Subwindow subwindow2;
    private Button button;
    private InteractionMediator interactionMediator;
    private Point2D point;
    private DomainFacade facade;

    @Before
    public void setUp(){
        point = new Point2D.Double(200,200);
        button = new Button();
        interactionMediator = new InteractionMediator();
        subwindow = new Subwindow(point, interactionMediator);
        facade = new DomainFacade();
        subwindow2 = new Subwindow(point, facade, interactionMediator);

    }

    @Test
    public void test_subwindow_width(){
        assertEquals(subwindow.getWidth(), 600);
    }
    @Test
    public void test_subwindow_height(){
        assertEquals(subwindow.getHeight(), 600);
    }
    @Test
    public void test_subwindow_position(){
        assertTrue(subwindow.getPosition().equals(point));
    }
    @Test
    public void test_subwindow_mediator(){
        assertTrue(subwindow.getMediator().equals(interactionMediator));
    }
    @Test
    public void test_mediator_subwindow(){
        assertTrue(subwindow.getMediator().getSubwindows().contains(subwindow));
    }
    @Test
    public void test_subwindow_labelmode(){
        assertFalse(subwindow.isInLabelMode());
    }
    @Test
    public void test_subwindow_facade_repos_are_empty(){
        assertTrue(subwindow.getFacade().getActiveRepo().getPartyView().getAllParties().isEmpty());
        assertTrue(subwindow.getFacade().getActiveRepo().getLabelView().getMap().isEmpty());
    }

    @Test
    public void test_subwindow_frame_has_4_corners(){
        assertTrue(subwindow.getFrame().getCorners().size() == 4);
    }
    @Test
    public void test_subwindow_frame_has_TL_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(subwindow.getFrame().getCorners().get(0).getCenter().getX() == 200);
        assertTrue(subwindow.getFrame().getCorners().get(0).getCenter().getY() == 200);
    }
    @Test
    public void test_subwindow_frame_has_TR_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(subwindow.getFrame().getCorners().get(1).getCenter().getX() == 800);
        assertTrue(subwindow.getFrame().getCorners().get(1).getCenter().getY() == 200);
    }
    @Test
    public void test_subwindow_frame_has_BL_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(subwindow.getFrame().getCorners().get(2).getCenter().getX() == 200);
        assertTrue(subwindow.getFrame().getCorners().get(2).getCenter().getY() == 800);
    }
    @Test
    public void test_subwindow_frame_has_BR_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(subwindow.getFrame().getCorners().get(3).getCenter().getX() == 800);
        assertTrue(subwindow.getFrame().getCorners().get(3).getCenter().getY() == 800);
    }

    @Test
    public void test_subwindow_frame_rectangle_left(){
        assertTrue(subwindow.getFrame().getRectangles().get(0).getPosition().getX() == 205);
        assertTrue(subwindow.getFrame().getRectangles().get(0).getPosition().getY() == 195);
    }
    @Test
    public void test_subwindow_frame_rectangle_right(){
        assertTrue(subwindow.getFrame().getRectangles().get(1).getPosition().getX() == 195);
        assertTrue(subwindow.getFrame().getRectangles().get(1).getPosition().getY() == 205);
    }
    @Test
    public void test_subwindow_frame_rectangle_top(){
        assertTrue(subwindow.getFrame().getRectangles().get(2).getPosition().getX() == 205);
        assertTrue(subwindow.getFrame().getRectangles().get(2).getPosition().getY() == (195 + subwindow.getHeight()));
    }
    @Test
    public void test_subwindow_frame_rectangle_bottom(){
        assertTrue(subwindow.getFrame().getRectangles().get(3).getPosition().getX() == (195 + subwindow.getWidth()));
        assertTrue(subwindow.getFrame().getRectangles().get(3).getPosition().getY() == 205);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_setMediator_null(){
        Subwindow s = new Subwindow(point, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_setHeight_negative(){
        subwindow.setHeight(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_setWidth_negative(){
        subwindow.setWidth(-1);
    }


    @Test
    public void test_subwindow2_width(){
        assertEquals(subwindow2.getWidth(), 600);
    }
    @Test
    public void test_subwindow2_height(){
        assertEquals(subwindow2.getHeight(), 600);
    }
    @Test
    public void test_subwindow2_position(){
        assertTrue(subwindow2.getPosition().equals(point));
    }
    @Test
    public void test_subwindow2_mediator(){
        assertTrue(subwindow2.getMediator().equals(interactionMediator));
    }
    @Test
    public void test_mediator_subwindow2(){
        assertTrue(subwindow2.getMediator().getSubwindows().contains(subwindow2));
    }
    @Test
    public void test_subwindow2_labelmode(){
        assertFalse(subwindow2.isInLabelMode());
    }
    @Test
    public void test_subwindow2_facade_repos_are_empty(){
        assertTrue(subwindow2.getFacade().getActiveRepo().getPartyView().getAllParties().isEmpty());
        assertTrue(subwindow2.getFacade().getActiveRepo().getLabelView().getMap().isEmpty());
    }
    @Test
    public void test_subwindow2_facade_repos_are_not_empty_after_fill() {
        try {
            subwindow2.getFacade().getActiveRepo().getPartyView().addPartyWithLocation(new Actor(new PartyLabel(":Actor")), new Point2D.Double(30, 30));
        } catch (DomainException d) {
            System.out.println(d.getMessage());
        }
        assertFalse(subwindow2.getFacade().getActiveRepo().getPartyView().getAllParties().isEmpty());
    }

    @Test
    public void test_subwindow2_frame_has_4_corners(){
        assertTrue(subwindow2.getFrame().getCorners().size() == 4);
    }
    @Test
    public void test_subwindow2_frame_has_TL_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(subwindow2.getFrame().getCorners().get(0).getCenter().getX() == 200);
        assertTrue(subwindow2.getFrame().getCorners().get(0).getCenter().getY() == 200);
    }
    @Test
    public void test_subwindow2_frame_has_TR_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(subwindow2.getFrame().getCorners().get(1).getCenter().getX() == 800);
        assertTrue(subwindow2.getFrame().getCorners().get(1).getCenter().getY() == 200);
    }
    @Test
    public void test_subwindow2_frame_has_BL_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(subwindow2.getFrame().getCorners().get(2).getCenter().getX() == 200);
        assertTrue(subwindow2.getFrame().getCorners().get(2).getCenter().getY() == 800);
    }
    @Test
    public void test_subwindow2_frame_has_BR_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(subwindow2.getFrame().getCorners().get(3).getCenter().getX() == 800);
        assertTrue(subwindow2.getFrame().getCorners().get(3).getCenter().getY() == 800);
    }

    @Test
    public void test_subwindow2_frame_rectangle_left(){
        assertTrue(subwindow2.getFrame().getRectangles().get(0).getPosition().getX() == 205);
        assertTrue(subwindow2.getFrame().getRectangles().get(0).getPosition().getY() == 195);
    }
    @Test
    public void test_subwindow2_frame_rectangle_right(){
        assertTrue(subwindow2.getFrame().getRectangles().get(1).getPosition().getX() == 195);
        assertTrue(subwindow2.getFrame().getRectangles().get(1).getPosition().getY() == 205);
    }
    @Test
    public void test_subwindow2_frame_rectangle_top(){
        assertTrue(subwindow2.getFrame().getRectangles().get(2).getPosition().getX() == 205);
        assertTrue(subwindow2.getFrame().getRectangles().get(2).getPosition().getY() == (195 + subwindow.getHeight()));
    }
    @Test
    public void test_subwindow2_frame_rectangle_bottom(){
        assertTrue(subwindow2.getFrame().getRectangles().get(3).getPosition().getX() == (195 + subwindow.getWidth()));
        assertTrue(subwindow2.getFrame().getRectangles().get(3).getPosition().getY() == 205);
    }
    @Test
    public void test_absolute_position(){
        assertTrue(subwindow.getAbsolutePosition(new Point2D.Double(24,25)).getX() == 224);
        assertTrue(subwindow.getAbsolutePosition(new Point2D.Double(24,25)).getY() == 225);
    }
    @Test
    public void test_subwindow_isClicked(){
        assertTrue(subwindow.isClicked(new Point2D.Double(200,200)));
    }

    @Test
    public void Test(){

    }

}
