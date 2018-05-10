package mediator;

import diagram.DiagramElement;
import diagram.label.Label;
import diagram.message.Message;
import diagram.party.Party;
import window.diagram.DiagramSubwindow;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InteractionMediator {

    private List<DiagramSubwindow> diagramSubwindows;

    /**
     * default constructor
     */
    public InteractionMediator(){
        this.diagramSubwindows = new ArrayList<>();
    }

    /**
     * add a new party to all the diagramSubwindows repos except for the given diagramSubwindow
     *
     * @param party  the party to add
     * @param location the location of the new Party
     * @param diagramSubwindow the original diagramSubwindow
     */
    public void addNewPartyToOtherSubwindowRepos(Party party, Point2D location, DiagramSubwindow diagramSubwindow){
        for(DiagramSubwindow s : diagramSubwindows){
            if(! s.equals(diagramSubwindow)) {
                s.getFacade().addPartyToRepo(party, location);
            }
        }

    }

    /**
     * remove a set of elements from the the diagramSubwindow repos except for the given diagramSubwindow
     *
     * @param deletedElements the elements to delete
     * @param diagramSubwindow the original diagramSubwindow
     */
    public void removeInReposInOtherSubwindows(Set<DiagramElement> deletedElements, DiagramSubwindow diagramSubwindow){
        for(DiagramSubwindow s : diagramSubwindows){
            if(! s.equals(diagramSubwindow)) {
                s.getFacade().deleteElementsInRepos(deletedElements);
                if(deletedElements.contains(s.getSelected())){
                    s.setSelected(null);
                }
            }
        }
    }

    /**
     * add a diagramSubwindow
     *
     * @param diagramSubwindow the diagramSubwindow to add
     */
    public void addSubwindow(DiagramSubwindow diagramSubwindow){
        if(! diagramSubwindows.contains(diagramSubwindow)){
            this.diagramSubwindows.add(diagramSubwindow);
        }
    }

    /**
     * remove a diagramSubwindow
     *
     * @param diagramSubwindow the diagramSubwindow to remove
     */

    public void removeSubwindow(DiagramSubwindow diagramSubwindow){
        diagramSubwindows.remove(diagramSubwindow);
    }

    /**
     * add a set of messages to all the diagramSubwindows repos except for the given diagramSubwindow
     *
     * @param newMessages the newmesssages to add
     * @param diagramSubwindow the original diagramSubwindow
     */
    public void addNewMessagesToOtherSubwindowRepos(List<Message> newMessages, DiagramSubwindow diagramSubwindow) {
        for(DiagramSubwindow s : diagramSubwindows){
            if( ! s.equals(diagramSubwindow)){
                s.getFacade().addMessagesToRepos(newMessages);
            }
        }
    }

    /**
     *
     * @return all diagramSubwindows this mediator  mediates
     */
    public List<DiagramSubwindow> getDiagramSubwindows() {
        return diagramSubwindows;
    }

    /**
     * updates the party type in all other diagramSubwindows
     * @param oldParty the old type
     * @param newParty the new type
     * @param diagramSubwindow the original diagramSubwindow
     */
    public void updatePartyTypeInOtherSubwindows(Party oldParty, Party newParty, DiagramSubwindow diagramSubwindow)  {
        for(DiagramSubwindow s : diagramSubwindows){
            if (! s.equals(diagramSubwindow)){
                s.getFacade().changePartyTypeInRepo(oldParty, newParty);
                s.setSelected(newParty);
            }
        }
    }

    /**
     * updates the labelcontainers of the other diagramSubwindows and sets the correct flags for label editing
     * @param selectedLabel the label that has been edited
     * @param diagramSubwindow the original diagramSubwindow
     */
    public void updateLabelContainers(Label selectedLabel, DiagramSubwindow diagramSubwindow) {
        for(DiagramSubwindow s : diagramSubwindows){
            if (! s.equals(diagramSubwindow)){
                if(s.getSelected() instanceof Label && ( s.getSelected()).equals(selectedLabel)){
                    s.stopEditingLabel();
                    s.setLabelMode(false);
                    s.setEditing(false);
                }
            }
        }
    }
}
