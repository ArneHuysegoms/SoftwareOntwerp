package uievents;

import java.awt.geom.Point2D;

public class MouseEvent {
    private MouseEventType mouseEventType;
    private Point2D point;

    /**
     *
     * @param mouseEventType
     * @param point
     */
    public MouseEvent(MouseEventType mouseEventType, Point2D point){
        this.setMouseEventType(mouseEventType);
        this.setPoint(point);
    }

    /**
     *
     * @return mouseEventType of MouseEvent
     */
    public MouseEventType getMouseEventType() {
        return mouseEventType;
    }

    /**
     *
     * @return point of MouseEvent
     */
    public Point2D getPoint(){
        return point;
    }

    /**
     *
     * @param mouseEventType
     */
    private void setMouseEventType(MouseEventType mouseEventType){
        this.mouseEventType = mouseEventType;
    }

    /**
     *
     * @param point
     */
    public void setPoint(Point2D point){
        this.point = point;
    }

}
