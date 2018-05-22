package action;

import diagram.DiagramElement;
import window.diagram.DiagramSubwindow;

import java.util.Set;

public class RemoveInViewsAction extends Action {

    private Set<DiagramElement> deletedElements;

    public RemoveInViewsAction(Set<DiagramElement> deletedElements){
        this.deletedElements = deletedElements;
    }

    public Set<DiagramElement> getDeletedElements() {
        return deletedElements;
    }

    @Override
    public void performAction(DiagramSubwindow subwindow) {
        subwindow.getFacade().deleteElementsInRepos(deletedElements);
        if(deletedElements.contains(subwindow.getSelected())){
            subwindow.setSelected(null);
        }
    }
}
