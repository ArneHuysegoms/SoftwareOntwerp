package figures.helperClasses;

import diagram.message.Message;
import diagram.party.Object;
import diagram.party.Party;
import figures.Drawer.Drawer;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class PartyPair extends Pair {
    private int arrowCount = 1;
    private List<Message> messages;

    public PartyPair(Party first, Party second, Message m) {
        super(first, second);
        messages = new ArrayList<Message>();
        messages.add(m);
    }

    public boolean equalPair(Party sender, Party receiver){
        return sender == this.getA() && receiver == this.getB();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public Party getSender(){
        return (Party) this.getA();
    }

    public Party getReceiver(){
        return (Party) this.getB();
    }

    public Point2D calculateStart(){
        return new Point2D.Double(getSender().getCoordinate().getX()+Object.WIDTH,getSender().getCoordinate().getY());
    }

    public Point2D calculateEnd(int spaceBetweenArrows){
        return new Point2D.Double(getReceiver().getCoordinate().getX(),getReceiver().getCoordinate().getY()+spaceBetweenArrows);
    }

    public void draw(Graphics graphics, Drawer messageDrawer, Drawer labelDrawer) {
        List<Pair<Point2D,Point2D>> coordinates = new ArrayList<Pair<Point2D,Point2D>>();
        int spread = Object.HEIGHT/messages.size();

        for(int i = 0; i<messages.size();i++){
            messageDrawer.draw(graphics, calculateStart(), calculateEnd(i*spread),"");
            //TODO labels tekenen:
            labelDrawer.draw(graphics,calculateLabelStartPosition(),null,message.get);
        }
    }

    private Point2D calculateLabelStartPosition(){
        double x = Math.round((getSender().getCoordinate().getX()+getReceiver().getCoordinate().getX())/2);
        double y = Math.round((getSender().getCoordinate().getY()+getReceiver().getCoordinate().getY())/2);

        return new Point2D.Double(x,y);
    }
}
