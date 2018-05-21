package window.diagram;

import command.closeWindow.CloseSubwindowCommand;
import facade.DomainFacade;
import org.junit.Before;
import org.junit.Test;
import window.Subwindow;
import window.elements.button.CloseWindowButton;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DiagramSubwindowTest {

    private DiagramSubwindow diagramSubwindow;
    private DiagramSubwindow diagramSubwindow2;
    private CloseWindowButton button;
    private Point2D point;
    private DomainFacade facade;

    @Before
    public void setUp(){
        point = new Point2D.Double(200,200);
        button = new CloseWindowButton(new CloseSubwindowCommand(diagramSubwindow, null));
        diagramSubwindow = new DiagramSubwindow(point);
        diagramSubwindow.createFrame(button);
    }

    @Test
    public void test_default_constructor(){
        assertEquals(point, diagramSubwindow.getPosition());
        assertEquals(Subwindow.WINDOWHEIGHT, diagramSubwindow.getHeight());
        assertEquals(Subwindow.WINDOWWIDTH, diagramSubwindow.getWidth());
    }

    /*
    @Test
    public void test_subwindow_width(){
        assertEquals(diagramSubwindow.getWidth(), 600);
    }
    @Test
    public void test_subwindow_height(){
        assertEquals(diagramSubwindow.getHeight(), 600);
    }
    @Test
    public void test_subwindow_position(){
        assertTrue(diagramSubwindow.getPosition().equals(point));
    }
    @Test
    public void test_subwindow_mediator(){
        assertTrue(diagramSubwindow.getMediator().equals(interactionMediator));
    }
    @Test
    public void test_mediator_subwindow(){
        assertTrue(diagramSubwindow.getMediator().getDiagramSubwindows().contains(diagramSubwindow));
    }
    @Test
    public void test_subwindow_labelmode(){
        assertFalse(diagramSubwindow.isInLabelMode());
    }
    @Test
    public void test_subwindow_facade_repos_are_empty(){
        assertTrue(diagramSubwindow.getFacade().getActiveRepo().getPartyView().getAllParties().isEmpty());
        assertTrue(diagramSubwindow.getFacade().getActiveRepo().getLabelView().getMap().isEmpty());
    }

    @Test
    public void test_subwindow_frame_has_4_corners(){
        assertTrue(diagramSubwindow.getFrame().getCorners().size() == 4);
    }
    @Test
    public void test_subwindow_frame_has_TL_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(diagramSubwindow.getFrame().getCorners().get(0).getCenter().getX() == 200);
        assertTrue(diagramSubwindow.getFrame().getCorners().get(0).getCenter().getY() == 200);
    }
    @Test
    public void test_subwindow_frame_has_TR_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(diagramSubwindow.getFrame().getCorners().get(1).getCenter().getX() == 800);
        assertTrue(diagramSubwindow.getFrame().getCorners().get(1).getCenter().getY() == 200);
    }
    @Test
    public void test_subwindow_frame_has_BL_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(diagramSubwindow.getFrame().getCorners().get(2).getCenter().getX() == 200);
        assertTrue(diagramSubwindow.getFrame().getCorners().get(2).getCenter().getY() == 800);
    }
    @Test
    public void test_subwindow_frame_has_BR_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(diagramSubwindow.getFrame().getCorners().get(3).getCenter().getX() == 800);
        assertTrue(diagramSubwindow.getFrame().getCorners().get(3).getCenter().getY() == 800);
    }

    @Test
    public void test_subwindow_frame_rectangle_left(){
        assertTrue(diagramSubwindow.getFrame().getRectangles().get(0).getPosition().getX() == 205);
        assertTrue(diagramSubwindow.getFrame().getRectangles().get(0).getPosition().getY() == 195);
    }
    @Test
    public void test_subwindow_frame_rectangle_right(){
        assertTrue(diagramSubwindow.getFrame().getRectangles().get(1).getPosition().getX() == 195);
        assertTrue(diagramSubwindow.getFrame().getRectangles().get(1).getPosition().getY() == 205);
    }
    @Test
    public void test_subwindow_frame_rectangle_top(){
        assertTrue(diagramSubwindow.getFrame().getRectangles().get(2).getPosition().getX() == 205);
        assertTrue(diagramSubwindow.getFrame().getRectangles().get(2).getPosition().getY() == (195 + diagramSubwindow.getHeight()));
    }
    @Test
    public void test_subwindow_frame_rectangle_bottom(){
        assertTrue(diagramSubwindow.getFrame().getRectangles().get(3).getPosition().getX() == (195 + diagramSubwindow.getWidth()));
        assertTrue(diagramSubwindow.getFrame().getRectangles().get(3).getPosition().getY() == 205);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_setMediator_null(){
        DiagramSubwindow s = new DiagramSubwindow(point, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_setHeight_negative(){
        diagramSubwindow.setHeight(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_setWidth_negative(){
        diagramSubwindow.setWidth(-1);
    }


    @Test
    public void test_subwindow2_width(){
        assertEquals(diagramSubwindow2.getWidth(), 600);
    }
    @Test
    public void test_subwindow2_height(){
        assertEquals(diagramSubwindow2.getHeight(), 600);
    }
    @Test
    public void test_subwindow2_position(){
        assertTrue(diagramSubwindow2.getPosition().equals(point));
    }
    @Test
    public void test_subwindow2_mediator(){
        assertTrue(diagramSubwindow2.getMediator().equals(interactionMediator));
    }
    @Test
    public void test_mediator_subwindow2(){
        assertTrue(diagramSubwindow2.getMediator().getDiagramSubwindows().contains(diagramSubwindow2));
    }
    @Test
    public void test_subwindow2_labelmode(){
        assertFalse(diagramSubwindow2.isInLabelMode());
    }
    @Test
    public void test_subwindow2_facade_repos_are_empty(){
        assertTrue(diagramSubwindow2.getFacade().getActiveRepo().getPartyView().getAllParties().isEmpty());
        assertTrue(diagramSubwindow2.getFacade().getActiveRepo().getLabelView().getMap().isEmpty());
    }
    @Test
    public void test_subwindow2_facade_repos_are_not_empty_after_fill() {
        try {
            diagramSubwindow2.getFacade().getActiveRepo().getPartyView().addPartyWithLocation(new Actor(new PartyLabel(":Actor")), new Point2D.Double(30, 30));
        } catch (DomainException d) {
            System.out.println(d.getMessage());
        }
        assertFalse(diagramSubwindow2.getFacade().getActiveRepo().getPartyView().getAllParties().isEmpty());
    }

    @Test
    public void test_subwindow2_frame_has_4_corners(){
        assertTrue(diagramSubwindow2.getFrame().getCorners().size() == 4);
    }
    @Test
    public void test_subwindow2_frame_has_TL_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(diagramSubwindow2.getFrame().getCorners().get(0).getCenter().getX() == 200);
        assertTrue(diagramSubwindow2.getFrame().getCorners().get(0).getCenter().getY() == 200);
    }
    @Test
    public void test_subwindow2_frame_has_TR_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(diagramSubwindow2.getFrame().getCorners().get(1).getCenter().getX() == 800);
        assertTrue(diagramSubwindow2.getFrame().getCorners().get(1).getCenter().getY() == 200);
    }
    @Test
    public void test_subwindow2_frame_has_BL_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(diagramSubwindow2.getFrame().getCorners().get(2).getCenter().getX() == 200);
        assertTrue(diagramSubwindow2.getFrame().getCorners().get(2).getCenter().getY() == 800);
    }
    @Test
    public void test_subwindow2_frame_has_BR_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(diagramSubwindow2.getFrame().getCorners().get(3).getCenter().getX() == 800);
        assertTrue(diagramSubwindow2.getFrame().getCorners().get(3).getCenter().getY() == 800);
    }

    @Test
    public void test_subwindow2_frame_rectangle_left(){
        assertTrue(diagramSubwindow2.getFrame().getRectangles().get(0).getPosition().getX() == 205);
        assertTrue(diagramSubwindow2.getFrame().getRectangles().get(0).getPosition().getY() == 195);
    }
    @Test
    public void test_subwindow2_frame_rectangle_right(){
        assertTrue(diagramSubwindow2.getFrame().getRectangles().get(1).getPosition().getX() == 195);
        assertTrue(diagramSubwindow2.getFrame().getRectangles().get(1).getPosition().getY() == 205);
    }
    @Test
    public void test_subwindow2_frame_rectangle_top(){
        assertTrue(diagramSubwindow2.getFrame().getRectangles().get(2).getPosition().getX() == 205);
        assertTrue(diagramSubwindow2.getFrame().getRectangles().get(2).getPosition().getY() == (195 + diagramSubwindow.getHeight()));
    }
    @Test
    public void test_subwindow2_frame_rectangle_bottom(){
        assertTrue(diagramSubwindow2.getFrame().getRectangles().get(3).getPosition().getX() == (195 + diagramSubwindow.getWidth()));
        assertTrue(diagramSubwindow2.getFrame().getRectangles().get(3).getPosition().getY() == 205);
    }
    @Test
    public void test_absolute_position(){
        assertTrue(diagramSubwindow.getAbsolutePosition(new Point2D.Double(24,25)).getX() == 224);
        assertTrue(diagramSubwindow.getAbsolutePosition(new Point2D.Double(24,25)).getY() == 225);
    }
    @Test
    public void test_subwindow_isClicked(){
        assertTrue(diagramSubwindow.isClicked(new Point2D.Double(200,200)));
    }*/

    @Test
    public void Test(){

    }

}
