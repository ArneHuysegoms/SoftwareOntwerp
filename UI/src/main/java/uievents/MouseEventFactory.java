package uievents;

import java.awt.geom.Point2D;

public class MouseEventFactory {

    public MouseEvent createMouseEvent(int id, int clickCount, Point2D point) {
        if (id == java.awt.event.MouseEvent.MOUSE_CLICKED && clickCount == 2) {
            return new MouseEvent(MouseEventType.LEFTDOUBLECLICK, point);
        } else if (id == java.awt.event.MouseEvent.MOUSE_CLICKED && clickCount == 1) {
            return new MouseEvent(MouseEventType.LEFTCLICK, point);
        } else if (id == java.awt.event.MouseEvent.MOUSE_DRAGGED) {
            return new MouseEvent(MouseEventType.DRAG, point);
        } else if (id == java.awt.event.MouseEvent.MOUSE_RELEASED) {
            return new MouseEvent(MouseEventType.RELEASE, point);
        }
        return null;
    }


}
