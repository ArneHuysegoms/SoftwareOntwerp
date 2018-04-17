package figures;

import diagram.Diagram;
import diagram.DiagramElement;
import diagram.label.Label;
import diagram.message.Message;
import diagram.party.Actor;
import diagram.party.Object;
import diagram.party.Party;
import figures.Drawer.BoxDrawer;
import figures.Drawer.Drawer;
import figures.Drawer.LabelDrawer;
import figures.Drawer.SelectionBoxDrawer;
import repo.diagram.DiagramRepo;
import repo.label.LabelRepo;
import repo.message.CommunicationMessageRepo;
import repo.message.MessageRepo;
import repo.message.SequenceMessageRepo;
import repo.party.PartyRepo;
import subwindow.Subwindow;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Map;

public abstract class Converter {
    private Subwindow subwindow;
    protected int x1,y1,x2,y2;
    protected Drawer boxDrawingStrategy,
            labelDrawingStrategy,
            selectionBoxDrawingStrategy;

    public Converter(Subwindow subwindow){
        this.subwindow = subwindow;
        x1 = (int)getSubwindow().getPosition().getX();
        y1 = (int)getSubwindow().getPosition().getY();
        x2 = x1+getSubwindow().getWidth();
        y2 = y1+getSubwindow().getHeight();
        boxDrawingStrategy = new BoxDrawer();
        selectionBoxDrawingStrategy = new SelectionBoxDrawer();
        labelDrawingStrategy = new LabelDrawer();
    }

    public abstract void draw(Graphics graphics, DiagramRepo activeRepo, Diagram diagram, DiagramElement selectedElement);

    protected void drawParties(Graphics graphics, PartyRepo partyRepo, Drawer actorDrawer, Drawer objectDrawer) {
        Map<Party, Point2D> partyMap = partyRepo.getMap();

        for (Map.Entry<Party, Point2D> entry : partyMap.entrySet()) {
            Point2D start = subwindow.getAbsolutePosition(entry.getValue());
            Point2D end;

            if (entry.getKey() instanceof Actor) {
                end = new Point2D.Double(start.getX() + PartyRepo.ACTORWIDTH, start.getY() + PartyRepo.ACTORHEIGHT);
                actorDrawer.draw(graphics, start, end, "", getX1(),getY1(),getX2(),getY2());
            } else {
                end = new Point2D.Double(start.getX() + PartyRepo.OBJECTWIDTH, start.getY() + PartyRepo.OBJECTHEIGHT);
                objectDrawer.draw(graphics, start, end, "", getX1(),getY1(),getX2(),getY2());
            }
        }
    }

    protected void drawLabels(Graphics graphics, LabelRepo messageRepo) {
        Map<diagram.label.Label, Point2D> labelMap = messageRepo.getMap();

        for (Map.Entry<Label, Point2D> entry : labelMap.entrySet()) {
            Point2D start =  subwindow.getAbsolutePosition(entry.getValue());
            labelDrawingStrategy.draw(graphics, start, null, entry.getKey().getLabel(), getX1(),getY1(),getX2(),getY2());
        }

        if (getSubwindow().getSelected() instanceof Label){
            Label selectedLabel = (Label)getSubwindow().getSelected();
            Point2D start =  subwindow.getAbsolutePosition(labelMap.get(selectedLabel));
            labelDrawingStrategy.draw(graphics, start, null, selectedLabel.getLabel(), getX1(),getY1(),getX2(),getY2());
        }
    }

     protected abstract void drawMessages(Graphics graphics, MessageRepo messageRepo, Map<Party, Point2D> map, Message firstMessage);

    /**
     * method that uses the selection box drawer to draw a box around the currently selected selectable parts of the diagram
     *
     * @param graphics object used to draw on the program's window
     * @param selectedElement  the subwindow's selected element     */
    protected void drawSelectionBox(Graphics graphics, DiagramElement selectedElement, DiagramRepo repo) {
        if (selectedElement instanceof Actor) {
            Actor a = (Actor) selectedElement;
            Map<Party, Point2D> partyMap = repo.getPartyRepo().getMap();
            Point2D actorPos = subwindow.getAbsolutePosition(partyMap.get(a));
            Point2D start = new Point2D.Double(actorPos.getX() - (PartyRepo.ACTORWIDTH/ 2), actorPos.getY()),
                    end = new Point2D.Double(actorPos.getX() + (PartyRepo.ACTORWIDTH / 2), actorPos.getY() + PartyRepo.ACTORWIDTH);
            selectionBoxDrawingStrategy.draw(graphics, start, end, "", getX1(),getY1(),getX2(),getY2());
        } else if (selectedElement instanceof  Label) {
            Label l = (Label) selectedElement;
            Point2D start = subwindow.getAbsolutePosition(repo.getLabelRepo().getLocationOfLabel(l));
            selectionBoxDrawingStrategy.draw(graphics, start, new Point2D.Double(start.getX() + LabelRepo.WIDTH, start.getY() + LabelRepo.HEIGHT), "", getX1(),getY1(),getX2(),getY2());
        } else if (selectedElement instanceof Object) {
            Object o = (Object) selectedElement;
            Map<Party, Point2D> partyMap = repo.getPartyRepo().getMap();
            Point2D objectPos = subwindow.getAbsolutePosition(partyMap.get(o));
            int selectionBoxSize = 5;
            Point2D start = new Point2D.Double(objectPos.getX() - selectionBoxSize, objectPos.getY() - selectionBoxSize);
            Point2D end = new Point2D.Double(objectPos.getX() + PartyRepo.OBJECTWIDTH + selectionBoxSize, objectPos.getY() + PartyRepo.OBJECTHEIGHT + selectionBoxSize);
            selectionBoxDrawingStrategy.draw(graphics, start, end, "", getX1(),getY1(),getX2(),getY2());
        } else if (selectedElement instanceof Message) {
            Message m = (Message)selectedElement;
            Point2D start;
            Point2D end;
            Map<Party, Point2D> partyMap = repo.getPartyRepo().getMap();
            if (repo.getMessageRepo() instanceof SequenceMessageRepo){
                Map<Message, Integer> msgMap = ((SequenceMessageRepo) repo.getMessageRepo()).getMap();
                Point2D senderPos = subwindow.getAbsolutePosition(partyMap.get(m.getSender()));
                Point2D receiverPos = subwindow.getAbsolutePosition(partyMap.get(m.getReceiver()));
                start = new Point2D.Double(senderPos.getX(), (msgMap.get(m)+subwindow.getPosition().getY()) - (MessageRepo.HEIGHT / 2));
                end = new Point2D.Double(receiverPos.getX(), (msgMap.get(m)+subwindow.getPosition().getY()) + (MessageRepo.HEIGHT / 2));
                selectionBoxDrawingStrategy.draw(graphics, start, end, "", getX1(),getY1(),getX2(),getY2());
            }
        }
    }

    protected Subwindow getSubwindow() {
        return subwindow;
    }

    protected int getX1() {
        return x1;
    }
    protected int getY1() {
        return y1;
    }
    protected int getX2() {
        return x2;
    }
    protected int getY2() {
        return y2;
    }
}
