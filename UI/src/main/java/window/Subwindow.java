package window;

import action.Action;
import exception.UIException;
import exceptions.DomainException;
import uievents.KeyEvent;
import uievents.MouseEvent;
import window.elements.button.Button;
import window.elements.button.CloseWindowButton;
import window.frame.SubwindowFrame;
import window.frame.SubwindowFrameCorner;
import window.frame.SubwindowFrameRectangle;
import window.frame.TitleBarClick;

import java.awt.geom.Point2D;

/**
 * abstract superclass for all subwindows
 */
public abstract class Subwindow implements Clickable, ILowLevelController, Comparable<Subwindow>, IActionHandler {

    public static final int WINDOWHEIGHT = 600;
    public static final int WINDOWWIDTH = 600;

    private int width;
    private int height;
    private Point2D position;
    private int level;
    private boolean dragging = false;
    protected boolean labelMode = false;
    protected boolean editing = false;

    private SubwindowFrame frame;

    private Button button;

    private Clickable frameElement;

    private Subwindow() {

    }

    private Subwindow(CloseWindowButton closeWindowButton) {

    }

    /**
     * makes a new SubWindow with the given coordinate and level
     *
     * @param coordinate the new coordinate for the subwindow
     * @param level      the level for this subwindow
     */
    public Subwindow(Point2D coordinate, int level) {
        this(coordinate, null, level);
    }

    /**
     * makes a new subwindow
     *
     * @param coordinate        the coordinate for the subwindow
     * @param closeWindowButton the button to close this subwindow width
     * @param level             the level for this subwindow
     */
    public Subwindow(Point2D coordinate, CloseWindowButton closeWindowButton, int level) {
        this.setPosition(coordinate);
        this.setWidth(Subwindow.WINDOWWIDTH);
        this.setHeight(Subwindow.WINDOWHEIGHT);
        this.setLevel(level);
        if (closeWindowButton != null) {
            this.createFrame(closeWindowButton);
        } else {
            this.createFrame();
        }
    }

    /**
     * create the frame for this subwindow
     */
    public void createFrame() {
        frame = new SubwindowFrame(this.getPosition(), height, width);
    }

    /**
     * create the frame with the given button
     *
     * @param closeWindowButton the button to use in the frame
     */
    public void createFrame(Button closeWindowButton) {
        frame = new SubwindowFrame(this.getPosition(), height, width, closeWindowButton);
    }

    /**
     * recreates the frame based on the old frame and potentially new values
     */
    private void recreateFrame() {
        if (frame == null) {
            frame = new SubwindowFrame(this.getPosition(), height, width);
        } else {
            Button button = frame.getButton();
            frame = new SubwindowFrame(this.getPosition(), height, width, button);
        }
    }

    /**
     * @return true if the window.diagram is in label mode
     */
    public boolean isLabelMode() {
        return labelMode;
    }

    /**
     * sets the window.diagram in the given labelmode
     *
     * @param labelMode the new labelmode for this window.diagram
     */
    public void setLabelMode(boolean labelMode) {
        this.labelMode = labelMode;
    }

    /**
     * @return wether or not this window.diagram is editing
     */
    public boolean isEditing() {
        return editing;
    }

    /**
     * @param editing the new mode for the editing flag
     */
    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    /**
     * @return the width of this window.diagram
     */
    public int getWidth() {
        return width;
    }

    /**
     * sets the width of this window.diagram
     *
     * @param width the width for this window.diagram
     * @throws IllegalArgumentException if the width is negative
     */
    public void setWidth(int width) {
        if (width < 0) {
            throw new IllegalArgumentException("Width can't be less than zero");
        }
        this.width = width;
        recreateFrame();
    }

    /**
     * @return the height of this window.diagram
     */
    public int getHeight() {
        return height;
    }

    /**
     * sets the height of this window.diagram
     *
     * @param height the height of this window.diagram
     * @throws IllegalArgumentException if the given height is negative
     */
    public void setHeight(int height) {
        if (height < 0) {
            throw new IllegalArgumentException("Height can't be less than zero");
        }
        this.height = height;
        recreateFrame();
    }

    /**
     * @return the position of the upper left corner of this window.diagram
     */
    public Point2D getPosition() {
        return position;
    }

    /**
     * sets the position of the upper left corner for this window.diagram
     *
     * @param position2D the new position for this window.diagram
     */
    public void setPosition(Point2D position2D) {
        this.position = position2D;
    }

    /**
     * @return the level of this subwindow
     */
    public int getLevel() {
        return level;
    }

    /**
     * sets the level of this subwindow to the given level
     *
     * @param level the level to to set the level of this subwindow too
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return true if this subwindow is dragging
     */
    public boolean isDragging() {
        return dragging;
    }

    /**
     * sets the dragging flag to the given value
     *
     * @param dragging the value for the dragging flag
     */
    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    /**
     * @return the frame of this window.diagram
     */
    public SubwindowFrame getFrame() {
        return frame;
    }

    /**
     * @return the selected frameElement
     */
    public Clickable getFrameElement() {
        return frameElement;
    }

    /**
     * return true if the frame of this window.diagram is clicked
     *
     * @param clickLocation the location of the click
     * @return true if the frame is clicked, false otherwise
     */
    public boolean frameIsClicked(Point2D clickLocation) {
        frameElement = frame.getFrameElement(clickLocation);
        return this.frame.isClicked(clickLocation);
    }

    /**
     * handles movement of the window.diagram
     *
     * @param movedLocation the new location
     */
    public void handleMovement(Point2D movedLocation) {
        if (frameElement != null) {
            if (frameElement instanceof Button) {
                Button c = (Button) frameElement;
                c.performAction();
            } else if (frameElement instanceof SubwindowFrameCorner) {
                SubwindowFrameCorner corner = (SubwindowFrameCorner) frameElement;
                resizeByCorner(corner, movedLocation);
                recreateFrame();
            } else if (frameElement instanceof SubwindowFrameRectangle) {
                SubwindowFrameRectangle frameRectangle = (SubwindowFrameRectangle) frameElement;
                resizeByFrameRectangle(frameRectangle, movedLocation);
                recreateFrame();
            } else if (frameElement instanceof TitleBarClick) {
                TitleBarClick titleBarClick = (TitleBarClick) frameElement;
                moveSubwindow(titleBarClick, movedLocation);
                recreateFrame();
            }
        }
    }

    /**
     * resize the window.diagram when the user drags by the corner
     *
     * @param corner the corner that was resized
     * @param point  the new point of the window.diagram
     */
    private void resizeByCorner(SubwindowFrameCorner corner, Point2D point) {
        Point2D ogPos = this.getPosition();
        switch (corner.getType()) {
            case TOPLEFT:
                if (!(point.getX() > (ogPos.getX() + width) || point.getY() > (ogPos.getY() + height))) {
                    double deltaTopleftX = -(corner.getCenter().getX() - point.getX());
                    double deltaTopleftY = -(corner.getCenter().getY() - point.getY());
                    this.setPosition(new Point2D.Double(ogPos.getX() + deltaTopleftX, ogPos.getY() + deltaTopleftY));
                    this.setHeight(new Double(getHeight() - deltaTopleftY).intValue());
                    this.setWidth(new Double(getWidth() - deltaTopleftX).intValue());
                }
                break;
            case TOPRIGHT:
                if (!(point.getX() < (ogPos.getX() - width) || point.getY() > (ogPos.getY() + height))) {
                    double deltaToprightX = -(corner.getCenter().getX() - point.getX());
                    double deltaToprightY = -(corner.getCenter().getY() - point.getY());
                    this.setPosition(new Point2D.Double(ogPos.getX(), ogPos.getY() + deltaToprightY));
                    this.setHeight(new Double(getHeight() - deltaToprightY).intValue());
                    this.setWidth(new Double(getWidth() + deltaToprightX).intValue());
                }
                break;
            case BOTTOMLEFT:
                if (!(point.getX() > (ogPos.getX() + width) || point.getY() < (ogPos.getY() - height))) {
                    double deltaBottomleftX = -(corner.getCenter().getX() - point.getX());
                    double deltaBottomleftY = -(corner.getCenter().getY() - point.getY());
                    this.setPosition(new Point2D.Double(ogPos.getX() + deltaBottomleftX, ogPos.getY()));
                    this.setHeight(new Double(getHeight() + deltaBottomleftY).intValue());
                    this.setWidth(new Double(getWidth() - deltaBottomleftX).intValue());
                }
                break;
            case BOTTOMRIGHT:
                if (!(point.getX() < (ogPos.getX() - width) || point.getY() < (ogPos.getY() - height))) {
                    double deltaBottomrightX = -(corner.getCenter().getX() - point.getX());
                    double deltaBottomrightY = -(corner.getCenter().getY() - point.getY());
                    //this.setPosition(new Point2D.Double(ogPos.getX() + deltaBottomrightX, ogPos.getY() + deltaBottomrightY));
                    this.setHeight(new Double(getHeight() + deltaBottomrightY).intValue());
                    this.setWidth(new Double(getWidth() + deltaBottomrightX).intValue());
                }
                break;
            default:
                break;
        }
        recreateFrame();
    }

    /**
     * resize the window.diagram when the user drags by one of the borders
     *
     * @param rectangle the rectangle that was resized
     * @param point     the new point for the rectangle
     */
    private void resizeByFrameRectangle(SubwindowFrameRectangle rectangle, Point2D point) {
        switch (rectangle.getType()) {
            case TOP:
                double topDelta = this.getPosition().getY() - point.getY();
                this.setPosition(new Point2D.Double(position.getX(), position.getY() - topDelta));
                this.setHeight(new Double(getHeight() + topDelta).intValue());
                break;
            case LEFT:
                double leftDelta = this.getPosition().getX() - point.getX();
                this.setPosition(new Point2D.Double(position.getX() - leftDelta, position.getY()));
                this.setWidth(new Double(getWidth() + leftDelta).intValue());
                break;
            case RIGHT:
                double rightDelta = -(this.getPosition().getX() + width - point.getX());
                this.setWidth(new Double(getWidth() + rightDelta).intValue());
                break;
            case BOTTOM:
                double bottomDelta = -(this.getPosition().getY() + height - point.getY());
                this.setHeight(new Double(getHeight() + bottomDelta).intValue());
                break;
            default:
                break;
        }
        recreateFrame();
    }

    /**
     * move the window.diagram when the user drags by the titlebar
     *
     * @param titleBarClick the click on the titlebar
     * @param point         the new point of the titlebar
     */
    private void moveSubwindow(TitleBarClick titleBarClick, Point2D point) {
        double x = point.getX() - titleBarClick.getInitialClickPosition().getX();
        double y = point.getY() - titleBarClick.getInitialClickPosition().getY();
        setPosition(new Point2D.Double(position.getX() + x, position.getY() + y));
        recreateFrame();
    }

    /**
     * checks if this window.diagram is clicked
     *
     * @param position the position of the click
     * @return true if this element is clicked, false otherwise
     */
    @Override
    public boolean isClicked(Point2D position) {
        double startX = this.getPosition().getX();
        double endX = this.getPosition().getX() + this.getWidth();
        double startY = this.getPosition().getY();
        double endY = this.getPosition().getY() + this.getHeight();
        return (startX <= position.getX() && endX >= position.getX()) && (startY <= position.getY() && endY >= position.getY());
    }

    /**
     * @return the absolute postion for a relative point within this window.diagram
     */
    public Point2D getAbsolutePosition(Point2D relativePoint) {
        return new Point2D.Double(relativePoint.getX() + this.getPosition().getX(), relativePoint.getY() + this.getPosition().getY());
    }

    /**
     * returns a relative point based on the given location and the location of the window.diagram
     *
     * @param location the location that needs to be translated
     * @return a relative point to this window.diagram based on the given location
     */
    public Point2D getRelativePoint(Point2D location) {
        return new Point2D.Double(location.getX() - this.getPosition().getX(), location.getY() - this.getPosition().getY());
    }

    /**
     * compares the subwindow to the other subwindow based on level, the implementation is the same as for Integer.compareTo
     *
     * @param other the subwindow to compare too
     * @return Integer.compare(this.getLevel (), other.getLevel());
     */
    @Override
    public int compareTo(Subwindow other) {
        return Integer.compare(this.getLevel(), other.getLevel());
    }

    /**
     * handle the given mouseEvent
     *
     * @param mouseEvent the mouseEvent to handle
     * @return an action detailing what happened in the handling of the event
     */
    public abstract Action handleMouseEvent(MouseEvent mouseEvent);

    /**
     * handles the given keyEvent
     *
     * @param keyEvent the keyEvent to handle
     * @return an action detailing what happened in the handling of the event
     * @throws DomainException if illegal modifications in domain
     * @throws UIException     if illegal modifications in UI
     */
    public abstract Action handleKeyEvent(KeyEvent keyEvent) throws DomainException, UIException;
}
