package action;

import diagram.DiagramElement;
import window.diagram.DiagramSubwindow;

public class UpdateLabelAction extends Action {

    private DiagramElement element;

    public UpdateLabelAction(DiagramElement diagramElement){
        this.element = diagramElement;
    }

    public DiagramElement getElement() {
        return element;
    }

    @Override
    public void performAction(DiagramSubwindow subwindow) {
        if(subwindow.getSelected().equals(element)){
            subwindow.setLabelContainer(element.toString());

        }
    }
}
