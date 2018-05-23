package action;

import window.diagram.DiagramSubwindow;

/**
 * class for actions that do nothing
 */
public class EmptyAction extends Action {

    /**
     * create a new emptyAction
     */
    public EmptyAction(){

    }

    /**
     * the usefulness of this method is that it does nothing
     *
     * @param subwindow the subwindow to perform an action on
     */
    @Override
    public void performAction(DiagramSubwindow subwindow) {

    }
}
