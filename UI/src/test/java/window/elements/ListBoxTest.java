package window.elements;

import exception.UIException;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class ListBoxTest {

    ListBox listBox;

    @Before
    public void setup(){
        try {
            listBox = new ListBox(new Point2D.Double(50, 50), "ListBox");
        }
        catch (UIException e){
            fail();
        }
    }

    @Test
    public void test_constructor() throws UIException {
        ListBox listBox = new ListBox(new Point2D.Double(50, 50), "ListBox");
        assertTrue(listBox.getArguments().size() == 0);
        assertTrue(listBox.getSelectedIndex() == -1);
        assertEquals(listBox.getDescription(), "ListBox");
        assertEquals(listBox.getCoordinate(), new Point2D.Double(50, 50));
    }

    @Test
    public void test_isClicked(){
        listBox.isClicked(new Point2D.Double(60, 60));
        listBox.isClicked(new Point2D.Double(49,49));
        listBox.isClicked(new Point2D.Double(100, 100));
    }

    @Test
    public void test_relativePoint(){
        assertEquals(new Point2D.Double(50,50), listBox.getRelativePoint(new Point2D.Double(100, 100)));
    }

    @Test
    public void test_selectArgument(){
        listBox.addArgument("Bla0");
        listBox.addArgument("Bla1");
        listBox.addArgument("Bla2");
        listBox.addArgument("Bla3");
        listBox.selectArgument(new Point2D.Double(50, 50 + 3 * 14 - 2));
        assertTrue(listBox.getSelectedIndex() == 2);
        listBox.selectArgument(new Point2D.Double(50, 50 + 2 * 14 - 2));
        assertTrue(listBox.getSelectedIndex() == 1);
        listBox.selectArgument(new Point2D.Double(50, 50 + 10 * 14 - 2));
        assertTrue(listBox.getSelectedIndex() == 3);
    }

    @Test
    public void test_add_and_remove_argument(){
        listBox.addArgument("Bla");
        assertTrue(listBox.getArguments().size() == 1);
        listBox.selectArgument(new Point2D.Double(50, 50+12));
        listBox.removeArgument();
        assertTrue(listBox.getArguments().size() == 0);
    }

    @Test
    public void test_moveDown(){
        listBox.addArgument("Bla0");
        listBox.addArgument("Bla1");
        listBox.addArgument("Bla2");
        listBox.selectArgument(new Point2D.Double(50, 50 + 2 * 14 - 2));
        assertTrue(listBox.getSelectedIndex() == 1);
        listBox.moveDown();
        assertTrue(listBox.getSelectedIndex() == 2);
        assertEquals("Bla1", listBox.getArguments().get(2));
        assertEquals("Bla2", listBox.getArguments().get(1));
    }

    @Test
    public void test_moveUp(){
        listBox.addArgument("Bla0");
        listBox.addArgument("Bla1");
        listBox.addArgument("Bla2");
        listBox.selectArgument(new Point2D.Double(50, 50 + 2 * 14 - 2));
        assertTrue(listBox.getSelectedIndex() == 1);
        listBox.moveUp();
        assertTrue(listBox.getSelectedIndex() == 0);
        assertEquals("Bla1", listBox.getArguments().get(0));
        assertEquals("Bla0", listBox.getArguments().get(1));
    }
}
