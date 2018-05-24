package action;

import diagram.DiagramElement;
import diagram.label.Label;
import window.diagram.DiagramSubwindow;

/**
 * Action that updates labels
 */
public class UpdateLabelAction extends Action {

    private DiagramElement element;
    private Label selectedLabel;

    /**
     * creates an updatelabelaction for the given diagramelement and the given label
     * @param diagramElement the diagramelement
     * @param selectedLabel the label
     */
    public UpdateLabelAction(DiagramElement diagramElement, Label selectedLabel){
        this.element = diagramElement;
        this.selectedLabel = selectedLabel;
    }

    /**
     *
     * @return the element for this action
     */
    public DiagramElement getElement() {
        return element;
    }

    /**
     *
     * @return the label for this action
     */
    public Label getSelectedLabel() {
        return selectedLabel;
    }

    /**
     * changes the labelcontainers and selected labels for the given subwindow
     * @param subwindow the subwindow to perform an action on
     */
    @Override
    public void performAction(DiagramSubwindow subwindow) {
        if(subwindow.getSelected() != null){
            if(subwindow.getSelected().equals(element)){
                subwindow.setLabelContainer(element.toString() + "I");
            }
            if(subwindow.getSelected() instanceof Label && ( subwindow.getSelected()).equals(selectedLabel)){
                subwindow.stopEditingLabel();
                subwindow.setLabelMode(false);
                subwindow.setEditing(false);
            }
        }
    }
}
