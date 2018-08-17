package uievents;

import java.awt.geom.Point2D;

public class MouseEvent {
    private MouseEventType mouseEventType;
    private Point2D point;

    /**
     *
     * @param mouseEventType the type that describes the users actions
     * @param point point that got clicked
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
     * @param mouseEventType the type that describes the users actions
     */
    private void setMouseEventType(MouseEventType mouseEventType){
        this.mouseEventType = mouseEventType;
    }

    /**
     *
     * @param point point that got clicked
     */
    public void setPoint(Point2D point){
        this.point = point;
    }

}
