package action;

import diagram.DiagramElement;
import window.diagram.DiagramSubwindow;

import java.util.Set;

/**
 * action that removes a set of diagramelements in the views
 */
public class RemoveInViewsAction extends Action {

    private Set<DiagramElement> deletedElements;

    /**
     * creates a new RemoveInViewsAction
     *
     * @param deletedElements the elements to delete
     */
    public RemoveInViewsAction(Set<DiagramElement> deletedElements) {
        this.deletedElements = deletedElements;
    }

    /**
     * @return the set of elements that this action will delete
     */
    public Set<DiagramElement> getDeletedElements() {
        return deletedElements;
    }

    /**
     * deletes the elements in the views
     *
     * @param subwindow the subwindow to perform an action on
     */
    @Override
    public void performAction(DiagramSubwindow subwindow) {
        subwindow.getFacade().deleteElementsInRepos(deletedElements);
        if (deletedElements.contains(subwindow.getSelected())) {
            subwindow.setSelected(null);
        }
    }
}
