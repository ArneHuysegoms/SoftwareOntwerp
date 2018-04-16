package windowElements;

import subwindow.Clickable;

import java.awt.geom.Point2D;

public class TitleBarClick implements Clickable {

    private TitleBar titleBar;
    private Point2D initialClickPosition;

    public TitleBarClick(TitleBar titleBar, Point2D initialClickPosition){
        this.titleBar = titleBar;
        this.initialClickPosition = initialClickPosition;
    }

    public TitleBar getTitleBar() {
        return titleBar;
    }

    public Point2D getInitialClickPosition() {
        return initialClickPosition;
    }

    @Override
    public boolean isClicked(Point2D location) {
        return false;
    }
}
