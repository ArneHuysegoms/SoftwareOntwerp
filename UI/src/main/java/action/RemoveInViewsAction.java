package action;

import diagram.DiagramElement;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Party;
import window.Subwindow;
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
        if (deletedElements.contains(subwindow.getSelected())) {
            subwindow.setSelected(null);
        }
        for(DiagramElement element : deletedElements) {
            if(lableIsSelectedInSubwindow(element, subwindow)){
                subwindow.setSelected(null);
            }
        }
        subwindow.getFacade().deleteElementsInRepos(deletedElements);
    }

    /**
     * checks if the given element is selected in the given subwindow
     * @param element the element to check
     * @param subwindow the subwindow to check
     * @return true if the element is selected in the subwindow
     */
    private boolean lableIsSelectedInSubwindow(DiagramElement element, DiagramSubwindow subwindow) {
        if(element instanceof Party){
            Party p = (Party) element;
            if(subwindow.getSelected() == p.getLabel()){
                return true;
            }
        }
        else if(element instanceof Message){
            Message m = (Message) element;
            if(subwindow.getSelected() == m.getLabel()){
               return true;
            }
        }
        return false;
    }
}
