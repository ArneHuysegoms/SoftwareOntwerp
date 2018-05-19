package action;

import diagram.label.Label;
import window.diagram.DiagramSubwindow;

public class UpdateLabelContainersAction extends Action {

    private Label selectedLabel;

    public UpdateLabelContainersAction(Label selectedLabel){
        this.selectedLabel = selectedLabel;
    }

    @Override
    public void performAction(DiagramSubwindow subwindow) {
        if(subwindow.getSelected() instanceof Label && ( subwindow.getSelected()).equals(selectedLabel)){
            subwindow.stopEditingLabel();
            subwindow.setLabelMode(false);
            subwindow.setEditing(false);
        }
    }
}
