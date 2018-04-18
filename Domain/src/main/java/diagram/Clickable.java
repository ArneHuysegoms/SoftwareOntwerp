package diagram;

import java.awt.geom.Point2D;

public interface Clickable {

    boolean isClicked(Point2D point2D);

    double getDistance(Point2D point2D);
}