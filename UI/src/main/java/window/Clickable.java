package window;

import java.awt.geom.Point2D;

/**
 * defines interface for elements that are clickable
 */
public interface Clickable {

    /**
     * returns whether or not the object is clicked on the given location
     *
     * @param location the location of the click
     * @return true if the element is clicked, false otherwise
     */
    boolean isClicked(Point2D location);
}
