package windowelements;

import org.junit.Test;
import windowElements.TitleBar;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class TitleBarTest {

    @Test
    public void Test_constructor(){
        TitleBar bar = new TitleBar(new Point2D.Double(50, 50), 200);
    }

    @Test
    public void Test_isClicked(){
        TitleBar bar = new TitleBar(new Point2D.Double(50, 50), 200);
        assertTrue(bar.isClicked(new Point2D.Double(80,70)));
    }
}
