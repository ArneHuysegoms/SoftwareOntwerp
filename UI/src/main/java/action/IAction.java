package action;

import window.diagram.DiagramSubwindow;

/**
 * interface for classes that perform an action
 */
public interface IAction {

    /**
     * performs an action on the given diagramsubwindow
     *
     * @param subwindow the subwindow to perform an action on
     */
    void performAction(DiagramSubwindow subwindow);
}
