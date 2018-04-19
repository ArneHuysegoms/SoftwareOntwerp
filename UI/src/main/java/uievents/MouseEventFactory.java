package uievents;

import java.awt.geom.Point2D;

public class MouseEventFactory {

    /**
     *
     * @param id of MouseEvent
     * @param clickCount of MouseEvent
     * @param point of MouseEvent
     * @return new MouseEvent with MouseEventType
     *            | LEFTDOUBLECLICK if MOUSE_CLICKED and clickCount == 2
     *            | LEFTCLICK if MOUSE_CLICKED and clickCount == 1
     *            | DRAG if MOUSE_DRAGGED
     *            | PRESSED if MOUSE_PRESSED
     *            | RELEASE if MOUSE_RELEASED
     *            | else IRRELEVANT
     */
    public MouseEvent createMouseEvent(int id, int clickCount, Point2D point) {
        if (id == java.awt.event.MouseEvent.MOUSE_CLICKED && clickCount == 2) {
            return new MouseEvent(MouseEventType.LEFTDOUBLECLICK, point);
        } else if (id == java.awt.event.MouseEvent.MOUSE_CLICKED && clickCount == 1) {
            return new MouseEvent(MouseEventType.LEFTCLICK, point);
        } else if (id == java.awt.event.MouseEvent.MOUSE_DRAGGED) {
            return new MouseEvent(MouseEventType.DRAG, point);
        } else if (id == java.awt.event.MouseEvent.MOUSE_RELEASED) {
            return new MouseEvent(MouseEventType.RELEASE, point);
        } else if (id == java.awt.event.MouseEvent.MOUSE_PRESSED) {
            return new MouseEvent(MouseEventType.PRESSED, point);
        }
        return new MouseEvent(MouseEventType.IRRELEVANT, point);
    }


}
