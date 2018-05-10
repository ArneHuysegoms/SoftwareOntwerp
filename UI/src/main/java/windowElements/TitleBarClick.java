package windowElements;

import window.windowElements.Clickable;

import java.awt.geom.Point2D;

/**
 * container helper class for determining the click on a titlebar
 */
public class TitleBarClick implements Clickable {

    private TitleBar titleBar;
    private Point2D initialClickPosition;

    /**
     * create a new titlebarclick which contains the parameters
     * @param titleBar the titlebar that was clicked on
     * @param initialClickPosition the position the titlebar was clicked on
     */
    public TitleBarClick(TitleBar titleBar, Point2D initialClickPosition){
        this.titleBar = titleBar;
        this.initialClickPosition = initialClickPosition;
    }

    /**
     *
     * @return the titlebar
     */
    public TitleBar getTitleBar() {
        return titleBar;
    }

    /**
     *
     * @return the initial click location
     */
    public Point2D getInitialClickPosition() {
        return initialClickPosition;
    }

    /**
     * default false, titlebarclick can't be clicked on
     * @param location the location of the click
     * @return false
     */
    @Override
    public boolean isClicked(Point2D location) {
        return false;
    }
}
