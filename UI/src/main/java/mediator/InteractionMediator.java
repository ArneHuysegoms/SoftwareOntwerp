package mediator;

import diagram.DiagramElement;
import diagram.label.Label;
import diagram.message.Message;
import diagram.party.Party;
import subwindow.Subwindow;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InteractionMediator {

    private List<Subwindow> subwindows;

    /**
     * default constructor
     */
    public InteractionMediator(){
        this.subwindows = new ArrayList<>();
    }

    /**
     * add a new party to all the subwindows repos except for the given subwindow
     *
     * @param party  the party to add
     * @param location the location of the new Party
     * @param subwindow the original subwindow
     */
    public void addNewPartyToOtherSubwindowRepos(Party party, Point2D location, Subwindow subwindow){
        for(Subwindow s : subwindows){
            if(! s.equals(subwindow)) {
                s.getFacade().addPartyToRepo(party, location);
            }
        }

    }

    /**
     * remove a set of elements from the the subwindow repos except for the given subwindow
     *
     * @param deletedElements the elements to delete
     * @param subwindow the original subwindow
     */
    public void removeInReposInOtherSubwindows(Set<DiagramElement> deletedElements, Subwindow subwindow){
        for(Subwindow s : subwindows){
            if(! s.equals(subwindow)) {
                s.getFacade().deleteElementsInRepos(deletedElements);
                if(deletedElements.contains(s.getSelected())){
                    s.setSelected(null);
                }
            }
        }
    }

    /**
     * add a subwindow
     *
     * @param subwindow the subwindow to add
     */
    public void addSubwindow(Subwindow subwindow){
        if(! subwindows.contains(subwindow)){
            this.subwindows.add(subwindow);
        }
    }

    /**
     * remove a subwindow
     *
     * @param subwindow the subwindow to remove
     */

    public void removeSubwindow(Subwindow subwindow){
        subwindows.remove(subwindow);
    }

    /**
     * add a set of messages to all the subwindows repos except for the given subwindow
     *
     * @param newMessages the newmesssages to add
     * @param subwindow the original subwindow
     */
    public void addNewMessagesToOtherSubwindowRepos(List<Message> newMessages, Subwindow subwindow) {
        for(Subwindow s : subwindows){
            if( ! s.equals(subwindow)){
                s.getFacade().addMessagesToRepos(newMessages);
            }
        }
    }

    /**
     *
     * @return all subwindows this mediator  mediates
     */
    public List<Subwindow> getSubwindows() {
        return subwindows;
    }

    /**
     * updates the party type in all other subwindows
     * @param oldParty the old type
     * @param newParty the new type
     * @param subwindow the original subwindow
     */
    public void updatePartyTypeInOtherSubwindows(Party oldParty, Party newParty, Subwindow subwindow)  {
        for(Subwindow s : subwindows){
            if (! s.equals(subwindow)){
                s.getFacade().changePartyTypeInRepo(oldParty, newParty);
                s.setSelected(newParty);
            }
        }
    }

    /**
     * updates the labelcontainers of the other subwindows and sets the correct flags for label editing
     * @param selectedLabel the label that has been edited
     * @param subwindow the original subwindow
     */
    public void updateLabelContainers(Label selectedLabel, Subwindow subwindow) {
        for(Subwindow s : subwindows){
            if (! s.equals(subwindow)){
                if(s.getSelected() instanceof Label && ( s.getSelected()).equals(selectedLabel)){
                    s.stopEditingLabel();
                    s.setLabelMode(false);
                    s.setEditing(false);
                }
            }
        }
    }
}
