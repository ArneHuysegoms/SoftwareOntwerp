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
            lableIsSelectedInSubwindow(element, subwindow);
        }
        subwindow.getFacade().deleteElementsInViews(deletedElements);
    }

    /**
     * checks if the given element is selected in the given subwindow
     * @param element the element to check
     * @param subwindow the subwindow to check
     */
    private void lableIsSelectedInSubwindow(DiagramElement element, DiagramSubwindow subwindow) {
        if(element instanceof Party){
            Party p = (Party) element;
            if(subwindow.getSelected() == p.getLabel()){
                subwindow.setSelected(null);
                subwindow.setEditing(false);
                subwindow.setLabelMode(false);
                subwindow.setLabelContainer("");
            }
        }
        else if(element instanceof Message){
            Message m = (Message) element;
            if(subwindow.getSelected() == m.getLabel()){
                subwindow.setSelected(null);
                subwindow.setEditing(false);
                subwindow.setLabelMode(false);
                subwindow.setLabelContainer("");
            }
        }
    }
}
