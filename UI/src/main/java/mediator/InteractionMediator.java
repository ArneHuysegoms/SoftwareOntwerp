package mediator;

import diagram.label.Label;
import diagram.message.Message;
import diagram.party.Party;
import subwindow.Subwindow;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class InteractionMediator {
    private ArrayList<Subwindow> subwindows;

    public void updateLabel(char c){
        /*
        Check if label correct
        juist -> alles aangepast
        fout -> enkel
         */
        for(Subwindow s : getSubwindows()){
            s.getFacade().addCharToLabel(c);
        }
    }

    public void updatePartyType(Party party){
        for(Subwindow s : getSubwindows()){
            s.getFacade().changePartyType(party.getCoordinate());
        }
    }

    public void addParty(Party party){
        /*
        Alle subwindows afgaan en domainfacade laten updaten?
        of 1 keer domain aanpassen en alle subwindows laten refreshen..?
         */
        for(Subwindow s : getSubwindows()){
            s.getFacade().addNewParty(party.getCoordinate());
        }

    }

    public void addMessage(Message message){
        /*for(Subwindow s : getSubwindows()){
            s.getFacade().addNewMessage(new Point2D( ,message.getyLocation()));
        }*/
        // Hoe geraak ik aan x-coordinaat? Key-Event?
    }

    public void addSubwindow(Subwindow subwindow){
        if(!this.getSubwindows().contains(subwindow)){
            this.subwindows.add(subwindow);
        }
    }

    public void updateSubwindow(Subwindow subwindow){
        /*
        ?
         */
    }

    public ArrayList<Subwindow> getSubwindows() {
        return subwindows;
    }
}
