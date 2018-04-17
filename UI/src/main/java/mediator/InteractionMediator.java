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
     * @param party
     * @param location
     * @param subwindow
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
     * @param deletedElements
     * @param subwindow
     */
    public void removeInReposInOtherSubwindows(Set<DiagramElement> deletedElements, Subwindow subwindow){
        for(Subwindow s : subwindows){
            if(! s.equals(subwindow)) {
                s.getFacade().deleteElementsInRepos(deletedElements);
            }
        }
    }

    /**
     * add a subwindow
     *
     * @param subwindow
     */
    public void addSubwindow(Subwindow subwindow){
        if(! subwindows.contains(subwindow)){
            this.subwindows.add(subwindow);
        }
    }

    /**
     * remove a subwindow
     *
     * @param subwindow
     */

    public void removeSubwindow(Subwindow subwindow){
        subwindows.remove(subwindow);
    }

    /**
     * add a set of messages to all the subwindows repos except for the given subwindow
     *
     * @param newMessages
     * @param subwindow
     */
    public void addNewMessagesToOtherSubwindowRepos(List<Message> newMessages, Subwindow subwindow) {
        for(Subwindow s : subwindows){
            if( ! s.equals(subwindow)){
                s.getFacade().addMessagesToRepos(newMessages);
            }
        }
    }

    public List<Subwindow> getSubwindows() {
        return subwindows;
    }
}
