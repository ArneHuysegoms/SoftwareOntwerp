package subwindow;

import controller.CanvasController;
import mediator.InteractionMediator;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SubwindowTest {
    private Subwindow subwindow;
    private Button button;
    private InteractionMediator interactionMediator;
    private Point2D point;

    @Before
    public void setUp(){
        point = new Point2D.Double(200,200);
        button = new CloseButton();
        interactionMediator = new InteractionMediator();
        subwindow = new Subwindow(point, button, interactionMediator);
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
    public void test_subwindow_button(){
        assertTrue(subwindow.getButton().equals(button));
    }
    @Test
    public void test_subwindow_mediator(){
        assertTrue(subwindow.getMediator().equals(interactionMediator));
    }
    @Test
    public void test_button_subwindow(){
        assertTrue(subwindow.getButton().getSubwindow().equals(subwindow));
    }
    @Test
    public void test_mediator_subwindow(){
        assertTrue(subwindow.getMediator().getSubwindows().contains(interactionMediator));
    }
    @Test
    public void test_subwindow_labelmode(){
        assertFalse(subwindow.isInLabelMode());
    }
    @Test
    public void test_subwindow_facade_repos_are_empty(){
        assertTrue(subwindow.getFacade().getActiveRepo().getPartyRepo().getAllParties().isEmpty());
        assertTrue(subwindow.getFacade().getActiveRepo().getLabelRepo().getMap().isEmpty());
    }

    @Test
    public void test_subwindow_frame_has_4_corners(){
        assertTrue(subwindow.getCorners().size() == 4);
    }
    @Test
    public void test_subwindow_frame_has_TL_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(subwindow.getCorners().get(0).getCenter().getX() == 200);
        assertTrue(subwindow.getCorners().get(0).getCenter().getY() == 200);
    }
    @Test
    public void test_subwindow_frame_has_TR_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(subwindow.getCorners().get(1).getCenter().getX() == 800);
        assertTrue(subwindow.getCorners().get(1).getCenter().getY() == 200);
    }
    @Test
    public void test_subwindow_frame_has_BL_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(subwindow.getCorners().get(2).getCenter().getX() == 200);
        assertTrue(subwindow.getCorners().get(2).getCenter().getY() == 800);
    }
    @Test
    public void test_subwindow_frame_has_BR_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(subwindow.getCorners().get(3).getCenter().getX() == 800);
        assertTrue(subwindow.getCorners().get(3).getCenter().getY() == 800);
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
        Subwindow s = new Subwindow(point, button, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_setButton_null(){
        Subwindow s = new Subwindow(point, null, interactionMediator);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_setHeight_negative(){
        subwindow.setHeight(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_setWidth_negative(){
        subwindow.setWidth(-1);
    }


}
