package mediator;

import diagram.label.Label;
import diagram.message.Message;
import diagram.party.Party;
import subwindow.Subwindow;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class InteractionMediator {
    private ArrayList<Subwindow> subwindows;

    public void updateLabel(Label label){
        // update de labels die overeenkomen met dit label
        // hoe de overeenkomstige labels vinden?
        for(Subwindow s : this.subwindows){
            s.setLabel(label);
        }
    }

    public void updatePartyType(Party party){
        for(Subwindow s : getSubwindows()){
            //
        }
    }

    public void addParty(Party party){
        /*
        Alle subwindows afgaan en domainfacade laten updaten?
        of 1 keer domain aanpassen en alle subwindows laten refreshen..?
         */
        for(Subwindow s : getSubwindows()){
            s.getFacade().getActiveRepo().getPartyRepo().;
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
