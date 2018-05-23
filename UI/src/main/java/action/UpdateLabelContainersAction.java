package action;

import diagram.label.Label;
import window.diagram.DiagramSubwindow;

/**
 * action class for updating labelcontainers
 */
public class UpdateLabelContainersAction extends Action {

    private Label selectedLabel;

    /**
     * creates a new updatelabelcontainers action for the given label
     *
     * @param selectedLabel the label to which to check edits on
     */
    public UpdateLabelContainersAction(Label selectedLabel) {
        this.selectedLabel = selectedLabel;
    }

    /**
     * performs the action of this action class
     *
     * @param subwindow the subwindow to perform an action on
     */
    @Override
    public void performAction(DiagramSubwindow subwindow) {
        if (subwindow.getSelected() instanceof Label && (subwindow.getSelected()).equals(selectedLabel)) {
            subwindow.stopEditingLabel();
            subwindow.setLabelMode(false);
            subwindow.setEditing(false);
        }
    }
}
