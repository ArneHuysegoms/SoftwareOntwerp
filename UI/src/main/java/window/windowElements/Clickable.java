package window.windowElements;

import java.awt.geom.Point2D;

public interface Clickable {

    /**
     * returns whether or not the object is clicked on the given location
     * @param location the location of the click
     * @return true if the element is clicked, false otherwise
     */
    boolean isClicked(Point2D location);
}
