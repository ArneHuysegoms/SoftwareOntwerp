package uievents;

import java.awt.geom.Point2D;

public class MouseEvent {
    MouseEventType mouseEventType;
    private Point2D point;

    public MouseEvent(MouseEventType mouseEventType, Point2D point){
        this.setMouseEventType(mouseEventType);
        this.setPoint(point);
    }

    public MouseEventType getMouseEventType() {
        return mouseEventType;
    }
    public Point2D getPoint(){
        return point;
    }
    private void setMouseEventType(MouseEventType mouseEventType){
        this.mouseEventType = mouseEventType;
    }
    private void setPoint(Point2D point){
        this.point = point;
    }
}
