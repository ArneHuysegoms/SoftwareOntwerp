package action;

import diagram.DiagramElement;
import diagram.label.Label;
import window.diagram.DiagramSubwindow;

public class UpdateLabelAction extends Action {

    private DiagramElement element;
    private Label selectedLabel;

    public UpdateLabelAction(DiagramElement diagramElement, Label selectedLabel){
        this.element = diagramElement;
        this.selectedLabel = selectedLabel;
    }

    public DiagramElement getElement() {
        return element;
    }

    public Label getSelectedLabel() {
        return selectedLabel;
    }

    @Override
    public void performAction(DiagramSubwindow subwindow) {
        if(subwindow.getSelected().equals(element)){
            subwindow.setLabelContainer(element.toString());
        }
        if(subwindow.getSelected() instanceof Label && ( subwindow.getSelected()).equals(selectedLabel)){
            subwindow.stopEditingLabel();
            subwindow.setLabelMode(false);
            subwindow.setEditing(false);
        }
    }
}
