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


    public void addNewPartyToOtherSubwindowRepos(Party party, Point2D location, Subwindow subwindow){
        for(Subwindow s : subwindows){
            if(! s.equals(subwindow)) {
                s.getFacade().addPartyToRepo(party, location);
            }
        }

    }

    public void removeInReposInOtherSubwindows(Set<DiagramElement> deletedElements, Subwindow subwindow){
        for(Subwindow s : subwindows){
            if(! s.equals(subwindow)) {
                s.getFacade().deleteElementsInRepos(deletedElements);
            }
        }
    }

    public void addSubwindow(Subwindow subwindow){
        if(! subwindows.contains(subwindow)){
            this.subwindows.add(subwindow);
        }
    }

    public void removeSubwindow(Subwindow subwindow){
        subwindows.remove(subwindow);
    }

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
